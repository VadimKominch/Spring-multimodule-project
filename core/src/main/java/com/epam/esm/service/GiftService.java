package com.epam.esm.service;

import com.epam.esm.entity.GiftSertificate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


@Service
@ComponentScan
public class GiftService{
    private JdbcTemplate template;

    @Autowired
    public GiftService(JdbcTemplate template) {
        this.template = template;
    }

    private RowMapper<GiftSertificate> ROW_MAPPER = (ResultSet resultSet, int rowNum) -> {
        return null;/*new GiftSertificate(resultSet.getString("id"), resultSet.getString("name"), resultSet.getString("start_date"));*/
    };

    public List<GiftSertificate> getAll() {
        return template.query("select * from Certificates",ROW_MAPPER);
    }


    public GiftSertificate getById(String id) {
        return null;
    }


    public boolean save(GiftSertificate entity) {
        int result = template.update("insert into Certificates values (?,?)",entity.getName(),entity.getDescription());
        return result >0;
    }


    public boolean delete(String id) {
        return false;
    }


    public GiftSertificate update(String id, GiftSertificate newObj) {
        return null;
    }
}
