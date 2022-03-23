package dev.passapong.quartzpoc.utils;

import java.util.Date;

import org.quartz.JobBuilder;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;

import dev.passapong.quartzpoc.entities.TimerInfo;

public final class TimerUtil {

    private TimerUtil() {}

    public static JobDetail buiJobDetail(final Class jobClass, final TimerInfo info) {
        final JobDataMap jobDataMap = new JobDataMap();

        jobDataMap.put(jobClass.getSimpleName(), info);

        return JobBuilder
        .newJob(jobClass)
        .withIdentity(jobClass.getSimpleName())
        .setJobData(jobDataMap)
        .build();
    }

    public static Trigger buildTrigger(final Class jobClass, final TimerInfo info){
        SimpleScheduleBuilder builder = SimpleScheduleBuilder.simpleSchedule().withIntervalInMilliseconds(info.repeatIntervalMs);

        if(info.runForever) {
            builder = builder.repeatForever();
        } else {
            builder = builder.withRepeatCount(info.totalFireCount);
        }

        return TriggerBuilder
        .newTrigger()
        .withIdentity(jobClass.getSimpleName())
        .withSchedule(builder)
        .startAt(new Date(System.currentTimeMillis() + info.initialOffsetMs))
        .build();
    }
    
}
