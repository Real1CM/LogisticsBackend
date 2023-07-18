package com.example.backend.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.backend.VO.PageVO;
import com.example.backend.VO.QuaryPageVO;
import com.example.backend.entity.Dish;
import com.example.backend.mapper.DishMapper;
import com.example.backend.service.IDishService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author liang-chenming
 * @since 2023-07-07
 */
@RestController
@RequestMapping("/dish")
public class DishController {
    @Autowired
    private IDishService iDishService;

    @ApiOperation("添加菜品信息")
    @PostMapping("/saveDish")
    public boolean save(@RequestBody Dish dish) {
        return iDishService.save(dish);
    }

    @ApiOperation("添加或修改菜品信息")
    @PostMapping("/updateOrSaveDish")
    public boolean updateOrSave(@RequestBody Dish dish) {
        return iDishService.saveOrUpdate(dish);
    }

    @ApiOperation("修改菜品信息")
    @PostMapping("/updateDish")
    public boolean update(@RequestBody Dish dish) {
        return iDishService.updateById(dish);
    }

    @ApiOperation("删除菜品信息")
    @DeleteMapping("/removeDish")
    public boolean remove(@RequestBody Dish dish) {
        return iDishService.removeById(dish);
    }

    @ApiOperation("查询菜品信息")
    @GetMapping("/selectDish")
    public List<Dish> select(@RequestBody Dish dish) {
        LambdaQueryWrapper<Dish> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.like(Dish::getDishName, dish.getDishName());
        return iDishService.list(lambdaQueryWrapper);
    }

    @ApiOperation("菜品的分页")
    @PostMapping("/listPageDish")
    private List<Dish> listPage(@RequestBody QuaryPageVO quaryPageVO) {
        Page<Dish> page = new Page<>();
        page.setCurrent(quaryPageVO.getPageNum());
        page.setSize(quaryPageVO.getPageSize());

        HashMap map = quaryPageVO.getMap();
        String a = (String) map.get("dishId");
        String b = (String) map.get("dishName");
        String c = (String) map.get("money");

        LambdaQueryWrapper<Dish> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.like(Dish::getDishId, a)
                .like(Dish::getDishName, b)
                .like(Dish::getMoney, c);

        IPage res = iDishService.page(page, lambdaQueryWrapper);
        System.out.println(res.getTotal());

        return res.getRecords();
    }

    @ApiOperation("分页2")
    @PostMapping("/page")
    public List<Dish> page(@RequestBody PageVO pageVO) {
        Page<Dish> page = new Page<>();
        page.setCurrent(pageVO.getPageNum());
        page.setSize(pageVO.getPageSize());
        IPage<Dish> result = iDishService.page(page);
        return result.getRecords();
    }

    @Autowired
    private DishMapper dishMapper;

    @ApiOperation("返回所有数据!")
    @GetMapping("/getAll")
    public List<Dish> getAll() {
        List<Dish> dishes = dishMapper.selectList(null);
        return dishes;
    }

    @ApiOperation("分类查询")
    @PostMapping("/selectByClass")
    public List<Dish> selectByClass(@RequestBody Dish dish) {
        LambdaQueryWrapper<Dish> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Dish::getDishClass,dish.getDishClass());
        List<Dish>  ok= dishMapper.selectList(lambdaQueryWrapper);
        return ok;
    }
}
