package com.example.backend.common;

import com.aliyun.auth.credentials.Credential;
import com.aliyun.auth.credentials.provider.StaticCredentialProvider;
import com.aliyun.core.http.HttpClient;
import com.aliyun.core.http.HttpMethod;
import com.aliyun.core.http.ProxyOptions;
import com.aliyun.httpcomponent.httpclient.ApacheAsyncHttpClientBuilder;
import com.aliyun.sdk.service.dysmsapi20170525.models.*;
import com.aliyun.sdk.service.dysmsapi20170525.*;
import com.google.gson.Gson;
import darabonba.core.RequestConfiguration;
import darabonba.core.client.ClientOverrideConfiguration;
import darabonba.core.utils.CommonUtil;
import darabonba.core.TeaPair;

//import javax.net.ssl.KeyManager;
//import javax.net.ssl.X509TrustManager;
import java.net.InetSocketAddress;
import java.time.Duration;
import java.util.*;
import java.util.concurrent.CompletableFuture;

/**
 * 还没实现完
 */
public class SendSms {
    public static void main(String[] args) throws Exception {
        // Configure Credentials authentication information, including ak, secret, token
        StaticCredentialProvider provider = StaticCredentialProvider.create(Credential.builder()
                // Please ensure that the environment variables ALIBABA_CLOUD_ACCESS_KEY_ID and ALIBABA_CLOUD_ACCESS_KEY_SECRET are set.
                .accessKeyId(System.getenv("LTAI5tLRw8SewHZDARF9zzc5"))
                .accessKeySecret(System.getenv("h8qmtyffFnfbXd476S1nKImwVaHYco"))
                //.securityToken(System.getenv("ALIBABA_CLOUD_SECURITY_TOKEN")) // use STS token
                .build());

        // Configure the Client
        AsyncClient client = AsyncClient.builder()
                .region("cn-hangzhou") // Region ID
                .credentialsProvider(provider)
                .overrideConfiguration(
                        ClientOverrideConfiguration.create()
                                .setEndpointOverride("dysmsapi.aliyuncs.com")
                         )
                .build();

        // Parameter settings for API request
        SendSmsRequest sendSmsRequest = SendSmsRequest.builder()
                .phoneNumbers("19186243797").signName("hello")
                .templateCode("123")
                .build();

        // Asynchronously get the return value of the API request
        CompletableFuture<SendSmsResponse> response = client.sendSms(sendSmsRequest);
        // Synchronously get the return value of the API request
        SendSmsResponse resp = response.get();
        System.out.println(new Gson().toJson(resp));
        // Asynchronous processing of return values
        // Finally, close the client
        client.close();
    }

}