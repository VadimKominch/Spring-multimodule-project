package com.epam.esm.service;

import com.epam.esm.entity.GiftSertificate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


@Component
public class GiftService{
    private JdbcTemplate template;

    @Autowired
    public GiftService(DataSource source) {
        this.template = new JdbcTemplate(source);
    }

    private RowMapper<GiftSertificate> ROW_MAPPER = (ResultSet resultSet, int rowNum) -> {
        return new GiftSertificate(resultSet.getString("title"), resultSet.getString("description"));
    };

    public List<GiftSertificate> getAll() {
        return template.query("select * from Certificates",ROW_MAPPER);
    }


    public GiftSertificate getById(Integer id) {
        return template.queryForObject("select * from Certificates where id = ?",new Object[]{id},(rs, rowNum) ->
                new GiftSertificate(
                        rs.getString("title"),
                        rs.getString("description")
                ));
    }


    public boolean save(GiftSertificate entity) {
        int result = template.update("insert into Certificates(title,description) values (?,?)",entity.getName(),entity.getDescription());
        return result >0;
    }


    public boolean delete(Integer id) {
        return template.update("delete from certificates where id = ?", id)!=0;
    }


    public GiftSertificate update(Integer id, GiftSertificate newObj) {
        template.update("update certificates set title = ?,description = ? where id= ?",newObj.getName(),newObj.getDescription(),id);
        return newObj;
    }


}
