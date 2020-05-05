package com.course.work.hostelserver.controller;

import com.course.work.hostelserver.service.ServicesService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/services")
public class ServicesController {

    @Resource
    private ServicesService service;

    @GetMapping
    public List<Map<String, Object>> servicesById(@RequestParam Integer id) {
        return service.servicesById(id);
    }

    @GetMapping(value = "/sum")
    public List<Map<String, Object>> servicesSumById(@RequestParam Integer id) {
        return service.servicesSumById(id);
    }

    @GetMapping(value = "/filter")
    public List<Map<String, Object>> filter(@RequestParam(required = false) Double price,
                                            @RequestParam(required = false) Integer amount) {
        return service.filter(price, amount);
    }

}
