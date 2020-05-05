package com.course.work.hostelserver.entity;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class Guest {
    private String name;
    private String surname;
    private String phone;
    private String email;
    private Integer days;
    private Integer room;
}
