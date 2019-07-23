package com.wm.job;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.Instant;


@Service
public class MyCronJob {
    @Resource
    private RedisTemplate redisTemplate;
    private Logger logger = LoggerFactory.getLogger(MyCronJob.class);

    @Scheduled(fixedRate = 1000)
    public void test() {
        redisTemplate.opsForValue().set("k" , Instant.now().toString());
        logger.info("1---{}",Instant.now().toString());
    }
    @Scheduled(cron = "*/5 * * * * ? ")
    public void test2() {
        redisTemplate.delete("k");
        logger.info("2---{}",Instant.now().toString());
    }
}

