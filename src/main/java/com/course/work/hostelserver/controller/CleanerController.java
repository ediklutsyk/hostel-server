package com.course.work.hostelserver.controller;

import com.course.work.hostelserver.service.CleanerService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/staff")
public class CleanerController {

    @Resource
    CleanerService service;

    @GetMapping
    public List<Map<String, Object>> getStaff() {
        return service.findAll();
    }

}
