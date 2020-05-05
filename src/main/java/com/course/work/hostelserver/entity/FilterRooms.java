package com.course.work.hostelserver.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
public class FilterRooms {
    private List<Integer> roomsId;
    private Integer min;
    private Integer max;
    private String roomType;
    private List<Integer> beds;
    private Boolean wifi;
    private Boolean tv;
    private Boolean kitchen;
    private Boolean teaMaker;
    private Boolean coffeeMachine;
}
