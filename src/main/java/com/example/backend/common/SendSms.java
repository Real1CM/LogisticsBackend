package com.example.backend.common;

import com.aliyun.auth.credentials.Credential;
import com.aliyun.auth.credentials.provider.StaticCredentialProvider;
import com.aliyun.sdk.service.dysmsapi20170525.models.*;
import com.aliyun.sdk.service.dysmsapi20170525.*;
import com.google.gson.Gson;
import darabonba.core.client.ClientOverrideConfiguration;
import java.util.concurrent.CompletableFuture;

/**
 * 还没实现完
 */
public class SendSms {
    public static void main(String[] args) throws Exception {
        StaticCredentialProvider provider = StaticCredentialProvider.create(Credential.builder()
                .accessKeyId(System.getenv("LTAI5tLRw8SewHZDARF9zzc5"))
                .accessKeySecret(System.getenv("h8qmtyffFnfbXd476S1nKImwVaHYco"))
                .build());
        AsyncClient client = AsyncClient.builder()
                .region("cn-hangzhou")
                .credentialsProvider(provider)
                .overrideConfiguration(
                        ClientOverrideConfiguration.create()
                                .setEndpointOverride("dysmsapi.aliyuncs.com")
                )
                .build();
        SendSmsRequest sendSmsRequest = SendSmsRequest.builder()
                .signName("梁宸铭的博客")
                .templateCode("SMS_461866211")
                .phoneNumbers("19186243797")
                .templateParam("{\"code\":\"1234\"}")
                .build();
        CompletableFuture<SendSmsResponse> response = client.sendSms(sendSmsRequest);
        SendSmsResponse resp = response.get();
        System.out.println(new Gson().toJson(resp));
        client.close();
    }
}