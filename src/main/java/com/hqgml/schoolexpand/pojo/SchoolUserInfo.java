package com.hqgml.schoolexpand.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class SchoolUserInfo {

    private String userid;

    private Integer msg;

    private String shixiTypes;

    private String userType;

    @JsonIgnore
    private String pwd;

}
