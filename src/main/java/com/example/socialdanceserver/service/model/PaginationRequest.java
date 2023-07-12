package com.example.socialdanceserver.service.model;

import lombok.*;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class PaginationRequest{

    private int firstIndex;

    private int pageSize;

    private int pageNumber;

    private long total;

    private List<String> orders;

    private Map<String, String> predicates;

    public int calculateFirstIndex() {
        firstIndex = pageSize * (pageNumber - 1);
        if (firstIndex >= total){
            firstIndex = (int) ((total / pageSize) * pageSize);
        }
        return firstIndex;
    }
}
