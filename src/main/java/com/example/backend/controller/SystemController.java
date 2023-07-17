package com.example.backend.controller;

import com.aliyun.dysmsapi20170525.Client;
import com.aliyun.dysmsapi20170525.models.SendSmsRequest;
import com.aliyun.teaopenapi.models.Config;
import com.example.backend.VO.PhoneLoginVO;
import com.example.backend.entity.Admin;
import com.example.backend.entity.LoginForm;
import com.example.backend.entity.User;
import com.example.backend.service.AdminService;
import com.example.backend.service.IUserService;
import com.example.backend.util.CreateVerifiCodeImage;
import com.example.backend.util.JwtHelper;
import com.example.backend.util.Result;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.backend.util.ResultCodeEnum;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.io.File;
import java.util.UUID;


@RestController
@RequestMapping("/sms/system")
public class SystemController {

    @Autowired
    private AdminService adminService;
    @Autowired
    private IUserService userService;

    // 阿里云短信服务的配置信息
    private static final String ACCESS_KEY_ID = "LTAI5tAXJbFawkk88QUF2ioL";
    private static final String ACCESS_KEY_SECRET = "uYyjHPq8QoNAtKOGVgpoChOuS6HNyI";
    private static final String ENDPOINT = "dysmsapi.aliyuncs.com";
    private static final String REGION_ID = "cn-hangzhou";
    private static final String SIGN_NAME = "阿里云短信测试";
    private static final String TEMPLATE_CODE = "SMS_154950909";

    // 生成指定长度的随机数字验证码
    private static String generateVerificationCode(int length) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int digit = (int) (Math.random() * 10);
            sb.append(digit);
        }
        return sb.toString();
    }

    // 发送短信验证码
    private String sendVerificationCode(String phoneNumber) throws Exception {
        // 生成验证码
        String verificationCode = generateVerificationCode(6);

        // 设置阿里云短信服务的配置
        Config config = new Config()
                .setAccessKeyId(ACCESS_KEY_ID)
                .setAccessKeySecret(ACCESS_KEY_SECRET)
                .setEndpoint(ENDPOINT)
                .setRegionId(REGION_ID);

        // 创建短信服务客户端
        Client client = new Client(config);

        // 创建短信发送请求
        SendSmsRequest request = new SendSmsRequest()
                .setPhoneNumbers(phoneNumber)
                .setSignName(SIGN_NAME)
                .setTemplateCode(TEMPLATE_CODE)
                .setTemplateParam("{\"code\":\"" + verificationCode + "\"}");

        try {
            // 发送短信
            client.sendSms(request);
            System.out.println("验证码已发送到 " + phoneNumber);
        } catch (Exception e) {
            System.out.println("发送短信验证码失败：" + e.getMessage());
        }

        return verificationCode;
    }

    // 验证短信验证码
    private boolean verifyCode(String inputCode, String expectedCode) {
        return inputCode.equals(expectedCode);
    }

    @PostMapping("/phone-login")
    public ResponseEntity<?> login(@RequestBody PhoneLoginVO phoneLoginVO) {
        String phoneNumber = phoneLoginVO.getPhone(); // 获取用户手机号码

        String expectedCode = null;
        String receivedCode = phoneLoginVO.getVCode(); // 获取用户输入的验证码

        try {
            expectedCode = sendVerificationCode(phoneNumber);
        } catch (Exception e) {
            // 处理发送短信验证码失败的情况
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to send verification code: " + e.getMessage());
        }

        boolean isCodeValid = verifyCode(receivedCode, expectedCode);

        if (isCodeValid) {
            // 处理验证码验证通过的情况
            return ResponseEntity.ok(phoneLoginVO);
        } else {
            // 处理验证码验证失败的情况
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Invalid verification code");
        }
    }


    @ApiOperation("文件上传统一入口")
    @PostMapping("/headerImgUpload")
    public Result headerImgUpload(
            @ApiParam("图像文件") @RequestPart("multipartFile") MultipartFile multipartFile,
            HttpServletRequest request
    ){
        String uuid = UUID.randomUUID().toString().replace("_","").toLowerCase();
        String originalFilename = multipartFile.getOriginalFilename();
        int i = originalFilename.lastIndexOf(".");
        String newFilename = uuid + originalFilename.substring(i);
        //保存文件:将文件发送到第三方或独立的图片服务器上
        String portraitPath = "D:/workSpace/project/23-internship---backstage/src/main/resources/public/upload/".concat(newFilename);
        try {
            multipartFile.transferTo(new File(portraitPath));
        } catch (IOException e) {
            e.printStackTrace();
        }
        //响应图片的路径
        String path = "upload/".concat(newFilename);
        return Result.ok(path);
    }

    @ApiOperation("通过token口令获取当前登录的用户信息的方法")
    @GetMapping("/getInfo")
    public Result getInfoByToken(
            @ApiParam("token口令") @RequestHeader("token") String token
    ){
        //校验token是否过期
        boolean expiration = JwtHelper.isExpiration(token);
        if(expiration){
            return Result.build(null, ResultCodeEnum.TOKEN_ERROR);
        }
        //从token中解析出 用户id 和用户类型
        Long useId = JwtHelper.getUserId(token);
        Integer userType = JwtHelper.getUserType(token);

        Map<String,Object> map = new LinkedHashMap<>();
        switch (userType){
            case 1:
                Admin admin = adminService.getAdminById(useId);
                map.put("userType",1);
                map.put("user",admin);
                break;
            case 2:
                User user = userService.getUserById(useId);
                map.put("userType",2);
                map.put("user",user);
                break;
        }
        return Result.ok(map);
    }


    @ApiOperation("登录的方法")
    @PostMapping("/login")
    public Result login(
            @ApiParam("登录提交信息的form表单") @RequestBody LoginForm loginForm,
            HttpServletRequest request
    ){
        //验证码校验
        HttpSession session = request.getSession();
        String sessionVerifiCode = (String)session.getAttribute("verifiCode");
        String loginVerifiCode = loginForm.getVerifiCode();
        if("".equals(sessionVerifiCode) || null ==sessionVerifiCode){
            return Result.fail().message("验证码失效，请刷新后重试");
        }
        if(!sessionVerifiCode.equalsIgnoreCase(loginVerifiCode)){
            return Result.fail().message("验证码有误，请小心输入后重试");
        }
        //从session域中移除现有验证码
        session.removeAttribute("verifiCode");
        //分用户类型进行校验

        // 准备一个map用户存放响应的数据
        Map<String,Object> map=new LinkedHashMap<>();
        switch(loginForm.getUserType()){
            case 1:  //在管理员中寻找用户
                try {
                    Admin admin = adminService.login(loginForm);
                    if(null != admin){
                        //用户类型和用户id转换成一个密文，以token的名称向客户端反馈
                        map.put("token", JwtHelper.createToken(admin.getId().longValue(), 1));
                    }else{
                        throw new RuntimeException("用户名或者密码有误");
                    }
                    return Result.ok(map);
                } catch (RuntimeException e) {
                    e.printStackTrace();
                    return Result.fail().message(e.getMessage());
                }
            case 2:  //在普通用户中寻找用户
                try {
                    User user = userService.login(loginForm);
                    if(null != user){
                        //用户类型和用户id转换成一个密文，以token的名称向客户端反馈
                        map.put("token",JwtHelper.createToken(user.getUserId().longValue(), 2));
                    }else{
                        throw new RuntimeException("用户名或者密码有误");
                    }
                    return Result.ok(map);
                } catch (RuntimeException e) {
                    e.printStackTrace();
                    return Result.fail().message(e.getMessage());
                }
        }
        return Result.fail().message("查无此用户");
    }

    @ApiOperation("获取验证码图片")
    @RequestMapping("/getVerifiCodeImage")
    public void getVerifiCodeImage(HttpServletRequest request, HttpServletResponse response){
        //获取图片
        BufferedImage verifiCodeImage = CreateVerifiCodeImage.getVerifiCodeImage();
        //获取图片上的验证码
        String verifiCode =new String( CreateVerifiCodeImage.getVerifiCode());
        //将验证码文本放入session域，为下一次验证做准备
        HttpSession session = request.getSession();
        session.setAttribute("verifiCode",verifiCode);
        //将验证码图片相应给浏览器

        try {
            ImageIO.write(verifiCodeImage,"JPEG",response.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
