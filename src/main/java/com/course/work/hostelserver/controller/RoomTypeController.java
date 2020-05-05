package com.course.work.hostelserver.controller;

import com.course.work.hostelserver.service.RoomTypeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/room-type")
public class RoomTypeController {

    @Resource
    RoomTypeService service;

    @GetMapping(value = "/rooms/amount")
    public List<Map<String, Object>> getRoomsTemp() {
        return service.getAmountOfRoomsByEachType();
    }

}
