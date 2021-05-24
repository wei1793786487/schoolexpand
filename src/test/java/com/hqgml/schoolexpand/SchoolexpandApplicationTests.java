package com.hqgml.schoolexpand;

import com.hqgml.schoolexpand.api.userApi;
import com.hqgml.schoolexpand.pojo.JwtProperties;
import com.hqgml.schoolexpand.pojo.SchoolUserInfo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.PrimitiveIterator;

@SpringBootTest
class SchoolexpandApplicationTests {

    @Autowired
    JwtProperties jwtProperties;

    @Autowired
    userApi userApi;

    @Test
    void contextLoads() throws Exception {
            SchoolUserInfo schoolUserInfo=new SchoolUserInfo();
            schoolUserInfo.setUserid("201825240226");
            schoolUserInfo.setPwd("lx1793786487");
            SchoolUserInfo schoolUserInfo1 = userApi.userLogin(schoolUserInfo);
        System.out.println(schoolUserInfo1);
//        File file = new File(jwtProperties.getPubKeyPath());
//        String hellow = RsaUtils.encrypt("你好啊", FileUtil.readString(file, StandardCharsets.UTF_8));
//        System.out.println(hellow);
    }
    @Test
    void  demologin(){
        SchoolUserInfo schoolUserInfo = new SchoolUserInfo();
//        SchoolUserInfo.setPassword("lx1793786487");
//        SchoolUserInfo login = userApi.login(SchoolUserInfo);
//        System.out.println(login);
    }

}
