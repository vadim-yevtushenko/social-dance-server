package com.example.socialdanceserver.api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class PageDto<T extends BaseDto> {

    private long total;

    private List<T> results;

}
