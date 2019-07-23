package com.wm;

import com.wm.job.MyCronJob;
import com.wm.job.MyJob;
import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;


@Configuration
@EnableScheduling
public class QuartzConfig {

    // 使用jobDetail包装job
    // 通过JobBuilder构建JobDetail实例，JobDetail规定只能是实现Job接口的实例
    // JobDetail 是具体Job实例
    @Bean
    public JobDetail myJobDetail() {
        return JobBuilder.newJob(MyJob.class).withIdentity("myJob").storeDurably().build();
    }

    // 把jobDetail注册到trigger上去
    // TriggerBuilder 用于构建触发器实例
    @Bean
    public Trigger myJobTrigger() {
        SimpleScheduleBuilder scheduleBuilder = SimpleScheduleBuilder.simpleSchedule()
                .withIntervalInSeconds(15).repeatForever();

        return TriggerBuilder.newTrigger()
                .forJob(myJobDetail())
                .withIdentity("myJobTrigger")
                .withSchedule(scheduleBuilder)
                .build();
    }

    // 使用jobDetail包装job
//    @Bean
//    public JobDetail myCronJobDetail() {
//        return JobBuilder.newJob(MyCronJob.class).withIdentity("myCronJob").storeDurably().build();
//    }

    // 把jobDetail注册到Cron表达式的trigger上去
//    @Bean
//    public Trigger CronJobTrigger() {
//        CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule("0/10 * * * * ?");
//
//        return TriggerBuilder.newTrigger()
//                .forJob(myCronJobDetail())
//                .withIdentity("myCronJobTrigger")
//                .withSchedule(cronScheduleBuilder)
//                .build();
//    }

}
