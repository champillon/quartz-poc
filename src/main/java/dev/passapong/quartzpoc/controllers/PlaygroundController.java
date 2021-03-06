package dev.passapong.quartzpoc.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.passapong.quartzpoc.entities.TimerInfo;
import dev.passapong.quartzpoc.services.PlaygroundService;

@RestController
@RequestMapping("/api/timer")
public class PlaygroundController {

    private final PlaygroundService service;

    @Autowired
    public PlaygroundController(PlaygroundService service){
        this.service = service;
    }

    @GetMapping("/run-hello-world")
    public void runHelloWorld() {
        service.runHelloWorld();
    } 

    @GetMapping
    public List<TimerInfo> getAllRunningTimers() {
        return service.getAllRunningTimers();
    }

}