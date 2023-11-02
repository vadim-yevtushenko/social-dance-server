package com.example.socialdanceserver.service.impl;

import com.example.socialdanceserver.api.exceptions.notfound.NotFoundException;
import com.example.socialdanceserver.service.model.PaginationRequest;
import ma.glasnost.orika.MapperFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Transactional
public abstract class BaseService {

    @Autowired
    protected transient MapperFacade mapper;

    protected PaginationRequest buildPaginationRequest(List<String > orders, Map<String, String> mapPredicates, int pageNumber, int size, int total) {

        return PaginationRequest.builder()
                .pageNumber(pageNumber)
                .pageSize(size)
                .total(total)
                .orders(orders)
                .predicates(mapPredicates)
                .build();
    }

    public void validateFound(Object object, Class<?> clazz, UUID id) {
        validateFound(object, clazz.getSimpleName() + " with ID " + id + " not found.");
    }

    public void validateFound(Object object, String errorMessage) {
        if (object == null) {
            throw new NotFoundException(errorMessage);
        }
    }

}
