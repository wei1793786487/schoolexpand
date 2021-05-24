package com.hqgml.schoolexpand.api;


import com.dtflys.forest.annotation.BaseRequest;
import com.dtflys.forest.annotation.Body;
import com.dtflys.forest.annotation.Post;
import com.hqgml.schoolexpand.pojo.SchoolUserInfo;
import com.hqgml.schoolexpand.pojo.UserInfo;

@BaseRequest(baseURL = "${url}")
public interface userApi {

    @Post("/MobUserLoginAction.do")
    SchoolUserInfo userLogin(@Body SchoolUserInfo userInfo);

}