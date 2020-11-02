package com.epam.esm.dao;

import com.epam.esm.converter.DateConverter;
import com.epam.esm.entity.GiftSertificate;
import com.epam.esm.entity.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.List;

@Component
public class TagDao {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public TagDao(DataSource source) {
        jdbcTemplate = new JdbcTemplate(source);
    }

    private RowMapper<Tag> ROW_MAPPER = (ResultSet resultSet, int rowNum) -> {

        Tag tag = new Tag(resultSet.getString("name"));
        return tag;
    };

    public List<Tag> getAll() {
        return jdbcTemplate.query("select * from Tags",ROW_MAPPER);
    }


    public Tag getById(Integer id) {
        return jdbcTemplate.queryForObject("select * from Tags where id = ?",new Object[]{id},(rs, rowNum) -> {
            Tag tag = new Tag(rs.getString("name"));
            return tag;
        });
    }


    public boolean save(Tag entity) {
        int result = jdbcTemplate.update("insert into Tags(name) values (?)",entity.getName());
        return result >0;
    }


    public boolean delete(Integer id) {
        return jdbcTemplate.update("delete from Tags where id = ?", id)!=0;
    }
}
