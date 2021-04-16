package com.hqgml.schoolexpand.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class userInfo {
    @JsonProperty("user_id")
    String userId;
    String shixiTypes;
}
