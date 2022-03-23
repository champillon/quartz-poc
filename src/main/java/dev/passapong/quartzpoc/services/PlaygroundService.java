package dev.passapong.quartzpoc.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.passapong.quartzpoc.entities.TimerInfo;
import dev.passapong.quartzpoc.jobs.HelloWorldJob;

@Service
public class PlaygroundService {
    private final SchedulerService scheduler;

    @Autowired
    public PlaygroundService(final SchedulerService scheduler) {
        this.scheduler = scheduler;
    }

    public void runHelloWorld(){
        TimerInfo info = new TimerInfo();
        info.totalFireCount = 5;
        info.repeatIntervalMs = 2000;
        info.initialOffsetMs = 1000;
        info.callbackData = ("My callback data");

        scheduler.schedule(HelloWorldJob.class, info);
    }

    public List<TimerInfo> getAllRunningTimers() {
        return scheduler.getAllRunningTimers();
    }
    
}
