package com.example.socialdanceserver.service.impl;

import com.example.socialdanceserver.service.model.PaginationRequest;
import ma.glasnost.orika.MapperFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Map;

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

}
