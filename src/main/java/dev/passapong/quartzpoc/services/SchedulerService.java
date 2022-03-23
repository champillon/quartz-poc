package dev.passapong.quartzpoc.services;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.passapong.quartzpoc.entities.TimerInfo;
import dev.passapong.quartzpoc.utils.TimerUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class SchedulerService {
    private static final Logger LOG = LoggerFactory.getLogger(SchedulerService.class);
    private Scheduler scheduler;

    @Autowired
    public SchedulerService(Scheduler scheduler) {
        this.scheduler = scheduler;
    }

    public void schedule(final Class jobClass, final TimerInfo info) {
        final JobDetail jobDetail = TimerUtil.buiJobDetail(jobClass, info);
        final Trigger trigger = TimerUtil.buildTrigger(jobClass, info);

        try{
            scheduler.scheduleJob(jobDetail, trigger);
        } catch (SchedulerException e) {
            LOG.error(e.getMessage(), e);
        }
    }

    @PostConstruct
    public void init() {
        try{
            scheduler.start();
        } catch (SchedulerException e) {
            LOG.error(e.getMessage(),e);
        }
    }

    @PreDestroy
    public void ending() {
        try{
            scheduler.shutdown();
        } catch (SchedulerException e) {
            LOG.error(e.getMessage(),e);
        }
    }

}