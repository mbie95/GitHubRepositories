package com.marcinbielen.githubrepositories.logic.mapper;

import com.marcinbielen.githubrepositories.model.dto.ExceptionDto;
import org.springframework.stereotype.Component;

@Component
public class ExceptionMapper {

    public ExceptionDto map(int statusCode, String errorMessage) {
        return ExceptionDto.builder()
            .status(statusCode)
            .message(errorMessage)
            .build();
    }
}
