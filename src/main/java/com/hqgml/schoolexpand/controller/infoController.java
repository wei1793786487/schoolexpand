package com.hqgml.schoolexpand.controller;


import cn.hutool.core.io.FileUtil;

import com.hqgml.schoolexpand.pojo.Common;
import com.hqgml.schoolexpand.pojo.JwtProperties;
import com.hqgml.schoolexpand.pojo.userInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.nio.charset.StandardCharsets;


@RestController
public class infoController {

    @Autowired
    JwtProperties jwtProperties;

    @PostMapping("info")
    public userInfo getMaueInfo(@RequestBody() userInfo userInfo) throws Exception {
        return userInfo;
    }

    @GetMapping("publicKey")
    public ResponseEntity<Common<String>> getPublicKey() {
        File file = new File(jwtProperties.getPubKeyPath());
        return ResponseEntity.ok(new Common(FileUtil.readString(file, StandardCharsets.UTF_8)));
    }
}
