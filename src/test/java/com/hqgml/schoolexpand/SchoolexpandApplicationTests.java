package com.hqgml.schoolexpand;

import cn.hutool.core.io.FileUtil;
import com.hqgml.schoolexpand.pojo.JwtProperties;
import com.hqgml.schoolexpand.tool.RsaUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.nio.charset.StandardCharsets;

@SpringBootTest
class SchoolexpandApplicationTests {

    @Autowired
    JwtProperties jwtProperties;



    @Test
    void contextLoads() throws Exception {
//        File file = new File(jwtProperties.getPubKeyPath());
//        String hellow = RsaUtils.encrypt("你好啊", FileUtil.readString(file, StandardCharsets.UTF_8));
//        System.out.println(hellow);
    }

}
