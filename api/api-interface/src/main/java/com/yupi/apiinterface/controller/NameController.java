package com.yupi.apiinterface.controller;


import com.yupi.apiclientsdk.model.User;
import com.yupi.apiclientsdk.utils.SignUtils;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/name")
public class NameController {

    @GetMapping("/get")
    public String getNameByGet(@RequestParam String name) {
        return "GET 你的名字是" + name;
    }

    @PostMapping("/post")
    public String getNameByPost(@RequestParam String name) {
        return "POST 你的名字是" + name;
    }

    @PostMapping("/user")
    public String getUserNameByPost(@RequestBody User user, HttpServletRequest request) {
        // 1.拿到这五个我们可以一步一步去做校验,比如 accessKey 我们先去数据库中查一下
        // 从请求头中获取参数
//        String accessKey = request.getHeader("accessKey");
//        String nonce = request.getHeader("nonce");
//        String timestamp = request.getHeader("timestamp");
//        String sign = request.getHeader("sign");
//        String body = request.getHeader("body");
//        // 不能直接获取秘钥
//        // String secretKey = request.getHeader("secretKey");
//
//        // 2.校验权限,这里模拟一下,直接判断 accessKey 是否为"root",实际应该查询数据库验证权限
//        if (!accessKey.equals("root")){
//            return "无权限";
//        }
//
//        // 3.校验一下随机数,因为时间有限,就不带大家再到后端去存储了,后端存储用hashmap或redis都可以
//        // 校验随机数,模拟一下,直接判断nonce是否大于10000
//        if (Long.parseLong(nonce) > 10000) {
//            return "无权限";
//        }
//
//        // 4.校验时间戳与当前时间的差距,判断是否大于60s
//        if (System.currentTimeMillis() / 1000-Long.parseLong(timestamp)>60) {
//            return "无权限";
//        }
//
//        // 5.校验签名是否正确
//        String serverSign= SignUtils.genSign(body,"abcdefgh");
//        if(!sign.equals(serverSign)){
//            return "无权限";
//        }

        // 如果权限校验通过，返回 "POST 用户名字是" + 用户名
        String result = "POST 用户名字是" + user.getUsername();
        return result;
    }
}
