package br.com.senac.api.schedulers;

import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

@Configuration
@Service
public class TesteTriggerBuild {

    @Bean
    public JobDetail jobDetail() {
        JobDetail job = JobBuilder.newJob(TesteJob.class)
                .withIdentity("testeJOB", "grupo01")
                .storeDurably()
                .build();

        return job;
    }

    @Bean
    public Trigger trigger() {

        Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity("testeTRIGGER","grupo01")
                .withSchedule(CronScheduleBuilder.cronSchedule("*/1 * * ? * * *"))
                .forJob(jobDetail())
                .build();

        return trigger;
    }
}
