package com.hqgml.schoolexpand.filter;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.CharsetUtil;
import cn.hutool.crypto.Mode;
import cn.hutool.crypto.Padding;
import cn.hutool.crypto.symmetric.AES;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.hqgml.schoolexpand.pojo.JwtProperties;
import com.hqgml.schoolexpand.tool.RsaUtils;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

/**
 * @Description TODO
 * @Author yyf
 * @Date 2020/10/29 12:48
 * @Version 1.0
 **/
@Slf4j
public class decrypRequestWrapper extends HttpServletRequestWrapper {

   private JwtProperties jwtProperties;

    /**
     * 存储body数据的容器
     */
    private byte[] body;

    public decrypRequestWrapper( HttpServletRequest request, JwtProperties jwtProperties) throws Exception {
        super(request);
        //接下来的request使用这个
        this.jwtProperties=jwtProperties;
        String bodyStr = getBodyString(request);
        body = bodyStr.getBytes(Charset.defaultCharset());
    }

    /**
     * 获取请求Body
     *
     * @param request request
     * @return String
     */
    public String getBodyString(final HttpServletRequest request) throws Exception{
        try {
            return inputStream2String(request.getInputStream(),request.getHeader("Token"));
        } catch (IOException e) {
            log.error("", e);
            throw new RuntimeException(e);
        }
    }

    /**
     * 获取请求Body
     *
     * @return String
     */
    public String getBodyString() throws Exception {
        final InputStream inputStream = new ByteArrayInputStream(body);

        return inputStream2String(inputStream,"");
    }

    /**
     * 将inputStream里的数据读取出来并转换成字符串
     *
     * @param inputStream inputStream
     * @return String
     */
    private String inputStream2String(InputStream inputStream,String token) throws Exception {
        StringBuilder sb = new StringBuilder();
        BufferedReader reader = null;

        try {
            reader = new BufferedReader(new InputStreamReader(inputStream, Charset.defaultCharset()));
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
        } catch (IOException e) {
            log.error("", e);
            throw new RuntimeException(e);
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    log.error("", e);
                }
            }
        }
        if (token!=null){
            File file = new File(jwtProperties.getPriKeyPath());
            String decrypt = RsaUtils.decrypt(token, FileUtil.readString(file, StandardCharsets.UTF_8));

            AES aes = new AES(Mode.CBC, Padding.ZeroPadding,decrypt.getBytes(StandardCharsets.UTF_8),jwtProperties.getIv().getBytes(StandardCharsets.UTF_8));

            return aes.decryptStr(sb.toString(), CharsetUtil.CHARSET_UTF_8);
        }
        else{
            return sb.toString();
        }

    }

    @Override
    public BufferedReader getReader() throws IOException {
        return new BufferedReader(new InputStreamReader(getInputStream()));
    }

    @Override
    public ServletInputStream getInputStream() throws IOException {

        final ByteArrayInputStream inputStream = new ByteArrayInputStream(body);

        return new ServletInputStream() {
            @Override
            public int read() throws IOException {
                return inputStream.read();
            }

            @Override
            public boolean isFinished() {
                return false;
            }

            @Override
            public boolean isReady() {
                return false;
            }

            @Override
            public void setReadListener(ReadListener readListener) {
            }
        };
    }
}
