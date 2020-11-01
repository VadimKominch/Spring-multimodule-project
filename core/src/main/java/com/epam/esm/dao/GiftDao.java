package com.epam.esm.dao;

import com.epam.esm.converter.DateConverter;
import com.epam.esm.entity.GiftSertificate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.List;

@Component
public class GiftDao {
    private JdbcTemplate template;

    @Autowired
    public GiftDao(DataSource source) {
        this.template = new JdbcTemplate(source);
    }

    private RowMapper<GiftSertificate> ROW_MAPPER = (ResultSet resultSet, int rowNum) -> {
        return new GiftSertificate(resultSet.getString("title"),
                resultSet.getString("description"),
                resultSet.getDouble("price"),
                new DateConverter().formatDate(resultSet.getTimestamp("creation_date")));
    };

    public List<GiftSertificate> getAll() {
        return template.query("select * from Certificates", ROW_MAPPER);
    }


    public GiftSertificate getById(Integer id) {
        return template.queryForObject("select * from Certificates where id = ?", new Object[]{id}, (rs, rowNum) ->
                new GiftSertificate(
                        rs.getString("title"),
                        rs.getString("description"),
                        rs.getDouble("price"),
                        new DateConverter().formatDate(rs.getTimestamp("creation_date")))
                );
    }


    public boolean save(GiftSertificate entity) {
        int result = template.update("insert into Certificates(title,description,price,creation_date) values (?,?,?,?)", entity.getName(), entity.getDescription(),entity.getPrice(),new Timestamp(new DateConverter().parse(entity.getCreationDate()).getTime()));
        return result > 0;
    }


    public boolean delete(Integer id) {
        return template.update("delete from certificates where id = ?", id) != 0;
    }


    public GiftSertificate update(Integer id, GiftSertificate newObj) {
        template.update("update certificates set title = ?,description = ?,price=?,creation_date = ? where id= ?", newObj.getName(), newObj.getDescription(),newObj.getPrice(),new Timestamp(new DateConverter().parse(newObj.getCreationDate()).getTime()), id);
        return newObj;
    }

}
