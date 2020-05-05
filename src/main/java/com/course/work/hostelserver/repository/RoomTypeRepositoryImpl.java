package com.course.work.hostelserver.repository;

import com.course.work.hostelserver.entity.Room;
import com.course.work.hostelserver.mapper.ObjectRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class RoomTypeRepositoryImpl implements RoomTypeRepository {

    public RoomTypeRepositoryImpl(NamedParameterJdbcTemplate template) {
        this.template = template;
    }

    private NamedParameterJdbcTemplate template;

    @Override
    public List<Map<String, Object>> getAmountOfRoomsByEachType() {
        return template.query("SELECT rt.room_type_name, COUNT(r.room_id) " +
                "FROM room_type rt " +
                "RIGHT JOIN room r on rt.room_type_id = r.room_type_id " +
                "GROUP BY rt.room_type_id", new ObjectRowMapper());
    }
}

