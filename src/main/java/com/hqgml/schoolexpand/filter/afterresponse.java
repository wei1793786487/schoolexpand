package com.hqgml.schoolexpand.filter;


import cn.hutool.core.codec.Base64;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.CharsetUtil;
import cn.hutool.crypto.Mode;
import cn.hutool.crypto.Padding;
import cn.hutool.crypto.symmetric.AES;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.hqgml.schoolexpand.pojo.JwtProperties;
import com.hqgml.schoolexpand.tool.RsaUtils;
import org.bouncycastle.pqc.math.linearalgebra.PolynomialRingGF2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.lang.reflect.Method;
import java.nio.charset.StandardCharsets;
import java.util.List;

@ControllerAdvice
public class afterresponse implements ResponseBodyAdvice<Object> {
    @Autowired
    private JwtProperties jwtProperties;

    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
       if (request.getURI().toString().contains("publicKey")){
           return body;
       }else {
           String token = request.getHeaders().get("Token").get(0);
           File file = new File(jwtProperties.getPriKeyPath());
           String decrypt = null;
           try {
               decrypt = RsaUtils.decrypt(token, FileUtil.readString(file, StandardCharsets.UTF_8));
           } catch (Exception e) {
               e.printStackTrace();
           }
           AES aes = new AES(Mode.CBC, Padding.ZeroPadding,decrypt.getBytes(StandardCharsets.UTF_8),jwtProperties.getIv().getBytes(StandardCharsets.UTF_8));
           String encode = Base64.encode(aes.encrypt(JSONObject.toJSON(body).toString()));
           return encode;
       }
    }
}
