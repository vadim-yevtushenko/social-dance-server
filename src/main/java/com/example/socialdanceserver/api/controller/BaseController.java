package com.example.socialdanceserver.api.controller;

import com.example.socialdanceserver.api.exceptions.notfound.NotFoundException;
import ma.glasnost.orika.MapperFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.UUID;

@Transactional
@CrossOrigin(origins = "http://localhost:3000")
public abstract class BaseController {

    @Autowired
    protected transient MapperFacade mapper;

    protected void validateFound(Object object, Class<?> clazz, UUID id) {
        validateFound(object, clazz.getSimpleName() + " with ID " + id + " not found.");
    }

    protected void validateFound(Object object, String errorMessage) {
        if (object == null) {
            throw new NotFoundException(errorMessage);
        }
    }

}
