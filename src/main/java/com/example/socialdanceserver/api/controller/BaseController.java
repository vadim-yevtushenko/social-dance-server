package com.example.socialdanceserver.api.controller;

import com.example.socialdanceserver.api.exceptions.notfound.NotFoundException;
import ma.glasnost.orika.MapperFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import java.util.UUID;

@Transactional
public abstract class BaseController {

    @Autowired
    protected transient MapperFacade mapper;

    public void validateFound(Object object, Class<?> clazz, UUID id) {
        validateFound(object, clazz.getSimpleName() + " with ID " + id + " not found.");
    }

    public void validateFound(Object object, String errorMessage) {
        if (object == null) {
            throw new NotFoundException(errorMessage);
        }
    }

}
