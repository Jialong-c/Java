package com.yupi.apiinterface;

import com.yupi.apiclientsdk.ApiClientConfig;
import com.yupi.apiclientsdk.client.ApiClient;
import com.yupi.apiclientsdk.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class ApiInterfaceApplicationTests {

    @Resource
    private ApiClient apiClient;
    @Test
    void contextLoads() {
        String result1 = apiClient.getNameByGet("jialong");
        System.out.println(result1);

        String result2 = apiClient.getNameByPost("jialong");
        System.out.println(result2);

        User user = new User();
        user.setUsername("jialong");
        String result3 = apiClient.getUserNameByPost(user);
        System.out.println(result3);
    }

}
