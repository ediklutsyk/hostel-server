package com.course.work.hostelserver.entity;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class Room {
    private int roomId;
    private int roomTypeId;
    private int bedTypeId;
    private int price;
    private int beds;
    // Goods
    private boolean teaMaker;
    private boolean coffeeMachine;
    private boolean wifi;
    private boolean tv;
    private boolean kitchen;
}
