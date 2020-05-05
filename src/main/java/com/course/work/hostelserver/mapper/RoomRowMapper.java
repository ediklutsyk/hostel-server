package com.course.work.hostelserver.mapper;

import com.course.work.hostelserver.entity.Room;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RoomRowMapper implements RowMapper<Room> {

    @Override
    public Room mapRow(ResultSet rs, int arg1) throws SQLException {
        Room room = new Room();
        room.setRoomId(rs.getInt("room_id"));
        room.setRoomTypeId(rs.getInt("room_type_id"));
        room.setPrice(rs.getInt("price"));
        room.setBedTypeId(rs.getInt("bed_type_id"));
        room.setBeds(rs.getInt("beds"));
        room.setWifi(rs.getBoolean("wifi"));
        room.setTv(rs.getBoolean("tv"));
        room.setKitchen(rs.getBoolean("kitchen"));
        room.setTeaMaker(rs.getBoolean("tea_maker"));
        room.setCoffeeMachine(rs.getBoolean("coffee_machine"));
        return room;
    }

}
