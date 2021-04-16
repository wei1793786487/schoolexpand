package com.hqgml.schoolexpand.filter;

import com.hqgml.schoolexpand.pojo.JwtProperties;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.PrintStream;

/**
 * @Description TODO
 * @Author yyf
 * @Date 2020/10/29 13:20
 * @Version 1.0
 **/
@Slf4j
public class ReplaceStreamFilter implements Filter {

    private JwtProperties jwtProperties;

    public ReplaceStreamFilter(JwtProperties jwtProperties) {
        this.jwtProperties = jwtProperties;
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("StreamFilter初始化...");
    }

    @SneakyThrows
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        ServletRequest requestWrapper = null;
        //获取请求中的流，将取出来的字符串，再次转换成流，然后把它放入到新request对象中,
        if (request instanceof HttpServletRequest) {
            HttpServletRequest new_request = (HttpServletRequest) request;
            String requestURI = new_request.getRequestURI();
            if (requestURI.contains("publicKey")){
                chain.doFilter(request, response);
                return;
            }
            requestWrapper = new decrypRequestWrapper((HttpServletRequest) request,jwtProperties);
        }

        // 在chain.doFiler方法中传递新的request对象
        if (requestWrapper == null) {
            chain.doFilter(request, response);
        } else {
            chain.doFilter(requestWrapper, response);
        }
    }

    @Override
    public void destroy() {
        log.info("StreamFilter销毁...");
    }
}
