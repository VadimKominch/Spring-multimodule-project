package com.epam.esm.service;

import com.epam.esm.converter.DateConverter;
import com.epam.esm.dao.TagDao;
import com.epam.esm.entity.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.util.List;

@Component
public class TagService {
    private TagDao tagDao;

    @Autowired
    public TagService(TagDao tagDao) {
        this.tagDao = tagDao;
    }

    public List<Tag> getAll() {
        try {
            return tagDao.getAll();
        }catch (DataAccessException e) {
            return null;
        }
    }


    public Tag getById(Integer id) {
        try {
            return tagDao.getById(id);
        }catch (DataAccessException e) {
            return null;
        }
    }


    public Tag save(Tag entity) {
        return tagDao.save(entity);
    }


    public boolean delete(Integer id) {
        return tagDao.delete(id);
    }
}
