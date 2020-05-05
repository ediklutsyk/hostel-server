package com.course.work.hostelserver.repository;

import com.course.work.hostelserver.entity.FilterRooms;
import com.course.work.hostelserver.mapper.ObjectRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@Repository
public class RoomRepositoryImpl implements RoomRepository {

    public RoomRepositoryImpl(NamedParameterJdbcTemplate template) {
        this.template = template;
    }

    private NamedParameterJdbcTemplate template;

    @Override
    public List<Map<String, Object>> findAll() {
        return template.query("SELECT r.room_id, r.price, r.price," +
                " rt.room_type_name, bt.bed_type_name, r.wifi," +
                " r.tv, r.kitchen, r.tea_maker, r.coffee_machine, r.beds " +
                " FROM room r" +
                " INNER JOIN  room_type rt ON r.room_type_id = rt.room_type_id" +
                " INNER JOIN  bed_type bt on r.bed_type_id = bt.bed_type_id", new ObjectRowMapper());
    }

    @Override
    public List<Map<String, Object>> findPrivate() {
        return template.query("SELECT r.room_id, r.price, r.price," +
                " rt.room_type_name, bt.bed_type_name, r.wifi," +
                " r.tv, r.kitchen, r.tea_maker, r.coffee_machine, r.beds " +
                " FROM room r" +
                " INNER JOIN  room_type rt ON r.room_type_id = rt.room_type_id" +
                " INNER JOIN  bed_type bt on r.bed_type_id = bt.bed_type_id " +
                " WHERE rt.room_type_name LIKE '%private'", new ObjectRowMapper());
    }

    @Override
    public List<Map<String, Object>> findPrivateForGuestNum(Integer amount) {
        return template.query("SELECT r.room_id, r.price, r.price," +
                        " rt.room_type_name, bt.bed_type_name, r.wifi," +
                        " r.tv, r.kitchen, r.tea_maker, r.coffee_machine, r.beds " +
                        " FROM room r" +
                        " INNER JOIN  room_type rt ON r.room_type_id = rt.room_type_id" +
                        " INNER JOIN  bed_type bt on r.bed_type_id = bt.bed_type_id " +
                        " WHERE rt.room_type_name LIKE '%private' " +
                        " AND r.beds >= :amount",
                Collections.singletonMap("amount", amount),
                new ObjectRowMapper());
    }

    @Override
    public List<Map<String, Object>> findDorms() {
        return template.query("SELECT r.room_id, r.price, r.price," +
                " rt.room_type_name, bt.bed_type_name, r.wifi," +
                " r.tv, r.kitchen, r.tea_maker, r.coffee_machine, r.beds" +
                " FROM room r" +
                " INNER JOIN  room_type rt ON r.room_type_id = rt.room_type_id" +
                " INNER JOIN  bed_type bt on r.bed_type_id = bt.bed_type_id " +
                " WHERE rt.room_type_name LIKE '%dorms'", new ObjectRowMapper());
    }

    @Override
    public List<Map<String, Object>> findDormsForGuestNum(Integer amount) {
        return template.query("SELECT r.room_id, " +
                        "       r.price, " +
                        "       r.price, " +
                        "       rt.room_type_name, " +
                        "       bt.bed_type_name, " +
                        "       r.wifi, " +
                        "       r.tv, " +
                        "       r.kitchen, " +
                        "       r.tea_maker, " +
                        "       r.coffee_machine, " +
                        "       r.beds, " +
                        "       COUNT(res.residence_id)          AS guest_count, " +
                        "       r.beds - COUNT(res.residence_id) AS free_beds " +
                        "FROM room r " +
                        "       INNER JOIN bed_type bt on r.bed_type_id = bt.bed_type_id " +
                        "       INNER JOIN room_type rt on r.room_type_id = rt.room_type_id " +
                        "       LEFT JOIN residence res on r.room_id = res.room_id " +
                        "WHERE rt.room_type_name LIKE '%dorms' " +
                        "  AND NOW() BETWEEN res.check_in AND res.check_out " +
                        "GROUP BY r.room_id, rt.room_type_name, bt.bed_type_name " +
                        "HAVING r.beds - COUNT(res.residence_id) > :amount;",
                Collections.singletonMap("amount", amount),
                new ObjectRowMapper());
    }


    @Override
    public List<Map<String, Object>> filterRooms(FilterRooms filter) {

        MapSqlParameterSource param = new MapSqlParameterSource()
                .addValue("roomsId", filter.getRoomsId());
        String sql = "SELECT r.room_id, " +
                "       r.price, " +
                "       r.price, " +
                "       rt.room_type_name, " +
                "       bt.bed_type_name, " +
                "       r.wifi, " +
                "       r.tv, " +
                "       r.kitchen, " +
                "       r.tea_maker, " +
                "       r.coffee_machine, " +
                "       r.beds " +
                "FROM room r " +
                "       INNER JOIN bed_type bt on r.bed_type_id = bt.bed_type_id " +
                "       INNER JOIN room_type rt on r.room_type_id = rt.room_type_id " +
                "WHERE r.room_id IN (:roomsId) ";
        if (filter.getMax() != null && filter.getMin() != null) {
            sql += "AND r.price BETWEEN :min AND :max ";
            param.addValue("min", filter.getMin());
            param.addValue("max", filter.getMax());
        }
        if (filter.getBeds() != null) {
            sql += "AND beds IN (:beds)";
            param.addValue("beds", filter.getBeds());
        }
        if (filter.getRoomType() != null) {
            sql += "AND rt.room_type_name = :roomType";
            param.addValue("roomType", filter.getRoomType());
        }
        if (filter.getWifi() != null) {
            sql += "AND r.wifi = :wifi";
            param.addValue("wifi", filter.getWifi());
        }
        if (filter.getTv() != null) {
            sql += "AND r.tv = :tv";
            param.addValue("tv", filter.getTv());
        }
        if (filter.getKitchen() != null) {
            sql += "AND r.kitchen = :kitchen";
            param.addValue("kitchen", filter.getKitchen());
        }
        if (filter.getTeaMaker() != null) {
            sql += "AND r.tea_maker = :tea_maker";
            param.addValue("tea_maker", filter.getTeaMaker());
        }
        if (filter.getCoffeeMachine() != null) {
            sql += "AND r.coffee_machine = :coffee_machine";
            param.addValue("coffee_machine", filter.getCoffeeMachine());
        }
        return template.query(sql, param, new ObjectRowMapper());
    }


    @Override
    public List<Map<String, Object>> getAllBedsSizes() {
        return template.query("SELECT DISTINCT beds as value" +
                "                  FROM public.room" +
                "                  ORDER BY beds", new ObjectRowMapper());
    }

    @Override
    public List<Map<String, Object>> getMinAndMaxPrice() {
        return template.query("SELECT MIN(price), MAX(price) FROM room ", new ObjectRowMapper());
    }

    @Override
    public List<Map<String, Object>> findById(Integer id) {
        return template.query("SELECT r.room_id, r.price, r.price," +
                        " rt.room_type_name, bt.bed_type_name, r.wifi," +
                        " r.tv, r.kitchen, r.tea_maker, r.coffee_machine, r.beds " +
                        " FROM room r" +
                        " INNER JOIN  room_type rt ON r.room_type_id = rt.room_type_id" +
                        " INNER JOIN  bed_type bt on r.bed_type_id = bt.bed_type_id" +
                        " WHERE r.room_id = :id",
                Collections.singletonMap("id", id), new ObjectRowMapper());
    }

    @Override
    public List<Map<String, Object>> findAllCleaningById(Integer id) {
        return template.query("SELECT c.date, cr.\"name\", cr.surname " +
                        "FROM cleaning c " +
                        "LEFT JOIN room r on c.room_id = r.room_id " +
                        "LEFT JOIN cleaner cr ON c.cleaner_id = cr.cleaner_id " +
                        "WHERE r.room_id = :id",
                Collections.singletonMap("id", id), new ObjectRowMapper());
    }


    @Override
    public List<Map<String, Object>> findAllGuestCurrentlyLivingInTheRoom(Integer id) {
        return template.query("SELECT g.name, g.surname, g.email, g.phone, res.check_out " +
                        "FROM room r " +
                        "INNER JOIN residence res ON r.room_id = res.room_id " +
                        "INNER JOIN guest g ON g.guest_id = res.guest_id " +
                        "WHERE NOW() BETWEEN res.check_in AND res.check_out and r.room_id =:id",
                Collections.singletonMap("id", id), new ObjectRowMapper());
    }


}
