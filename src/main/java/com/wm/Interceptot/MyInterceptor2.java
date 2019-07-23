package com.wm.Interceptot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.Instant;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@Component
public class MyInterceptor2 implements HandlerInterceptor {

    private Logger logger  = LoggerFactory.getLogger(MyInterceptor2.class);

    @Resource
    private RedisTemplate<String, String> redisTemplate;

    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        String requestURI = httpServletRequest.getRequestURI();

        logger.info("2 preHandle被调用，请求的uri为：{}", requestURI);

        if ("/user/find".equals(requestURI)) {
            final Set<String> keys = redisTemplate.keys("find:Interceptor*");
            if (keys != null && keys.size() > 10) {
                logger.info("请求超出限制 {}",  keys.size());
                return false;
            }
            final Instant now = Instant.now();
            redisTemplate.opsForValue().set("find:Interceptor" + now.getNano(), now.toString(), 10 , TimeUnit.SECONDS);
        }
        return true;  //如果false，停止流程，api被拦截
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        logger.info("2 postHandle");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        logger.info("2 afterCompletion");
    }
}
