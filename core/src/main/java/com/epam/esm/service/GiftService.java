package com.epam.esm.service;

import com.epam.esm.constant.CustomErrorCodes;
import com.epam.esm.dao.GiftDao;
import com.epam.esm.entity.ErrorResponse;
import com.epam.esm.entity.GiftSertificate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;


import java.sql.SQLException;
import java.util.List;


@Component
public class GiftService {
    private GiftDao giftDao;

    @Autowired
    public GiftService(GiftDao giftDao) {
        this.giftDao = giftDao;
    }

    public List<GiftSertificate> getAll() {
        return giftDao.getAll();
    }

    public ResponseEntity<?> getById(Integer id) {
        try {
            return new ResponseEntity<GiftSertificate>(giftDao.getById(id),HttpStatus.OK);
        } catch(EmptyResultDataAccessException e) {
            return new ResponseEntity<ErrorResponse>(new ErrorResponse("Certificate to read","404"+ CustomErrorCodes.READ_ERROR),HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<?> save(GiftSertificate entity) {
        try{
            giftDao.save(entity);
            return new ResponseEntity<String>("OK", HttpStatus.GONE);
        }   catch(EmptyResultDataAccessException e) {
            return new ResponseEntity<ErrorResponse>(new ErrorResponse("Certificate to save","400"+ CustomErrorCodes.CREATE_ERROR),HttpStatus.NOT_FOUND);
        }
    }


    public ResponseEntity<?> delete(Integer id) {
        try{
            giftDao.delete(id);
        return new ResponseEntity<String>("OK", HttpStatus.GONE);
    }   catch(EmptyResultDataAccessException e) {
        return new ResponseEntity<ErrorResponse>(new ErrorResponse("Certificate to delete not exist","404"+ CustomErrorCodes.DELETE_ERROR),HttpStatus.NOT_FOUND);
    }
    }

    public ResponseEntity<?> update(Integer id, GiftSertificate newObj) {
        try{
            giftDao.update(id,newObj);
            return new ResponseEntity<String>("OK", HttpStatus.GONE);
        }   catch(EmptyResultDataAccessException e) {
            return new ResponseEntity<ErrorResponse>(new ErrorResponse("Certificate to modify not exist","404"+ CustomErrorCodes.UPDATE_ERROR),HttpStatus.NOT_FOUND);
        }
    }

}
