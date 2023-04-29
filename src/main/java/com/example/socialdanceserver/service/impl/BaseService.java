package com.example.socialdanceserver.service.impl;

import ma.glasnost.orika.MapperFacade;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class BaseService {

    @Autowired
    protected transient MapperFacade mapper;

}
