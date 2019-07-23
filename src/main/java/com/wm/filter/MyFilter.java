package com.wm.filter;

import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Component
public class MyFilter implements Filter {

    private Logger logger = LoggerFactory.getLogger(MyFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        logger.info("Filter init");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        logger.info("doFilter");
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        logger.info(JSON.toJSONString(servletRequest.getParameterMap()));
        filterChain.doFilter(servletRequest, servletResponse);
        logger.info("doFilter done");
    }

    @Override
    public void destroy() {
        logger.info("destroy");
    }
}
