package com.course.work.hostelserver.mapper;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class ObjectRowMapper implements RowMapper<Map<String, Object>> {

    private String snakeToCamel(String snake) {
        while (snake.contains("_")) {
            snake = snake.replaceFirst("_[a-z]", String.valueOf(snake.charAt(snake.indexOf("_") + 1)).toUpperCase());
        }
        return snake;
    }


    @Override
    public Map<String, Object> mapRow(ResultSet rs, int arg1) throws SQLException {
        Map<String, Object> map = new HashMap<>();
        ResultSetMetaData rsmd = rs.getMetaData();
        int columnCount = rsmd.getColumnCount();
        for (int i = 1; i <= columnCount; i++) {
            String name = rsmd.getColumnName(i);
            Object value = rs.getObject(name);
            map.put(snakeToCamel(name), value);
        }
        return map;
    }


}
