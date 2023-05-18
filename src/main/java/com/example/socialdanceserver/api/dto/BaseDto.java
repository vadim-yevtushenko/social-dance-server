package com.example.socialdanceserver.api.dto;

import lombok.Getter;
import lombok.Setter;
import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
public abstract class BaseDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private UUID id;

}
