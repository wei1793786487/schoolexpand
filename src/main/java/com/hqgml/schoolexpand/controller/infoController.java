package com.hqgml.schoolexpand.controller;


import cn.hutool.core.io.FileUtil;

import com.hqgml.schoolexpand.pojo.ApiResponse;
import com.hqgml.schoolexpand.pojo.JwtProperties;
import com.hqgml.schoolexpand.pojo.SchoolUserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.nio.charset.StandardCharsets;


@RestController
public class infoController {

    @Autowired
    JwtProperties jwtProperties;




    @PostMapping("login")
    public SchoolUserInfo getMaueInfo(@RequestBody() SchoolUserInfo schoolUserInfo)  {
//        return userApi.login(SchoolUserInfo);
        return null;
    }

    @GetMapping("publicKey")
    public ApiResponse getPublicKey() {
        File file = new File(jwtProperties.getPubKeyPath());
        return ApiResponse.ofSuccess(FileUtil.readString(file, StandardCharsets.UTF_8));
    }
}
