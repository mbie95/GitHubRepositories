package com.marcinbielen.githubrepositories.controller;

import com.marcinbielen.githubrepositories.logic.mapper.ExceptionMapper;
import com.marcinbielen.githubrepositories.model.dto.ExceptionDto;
import com.marcinbielen.githubrepositories.model.exception.GitHubConnectionException;
import com.marcinbielen.githubrepositories.model.exception.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandler {

    private final ExceptionMapper exceptionMapper;

    public ExceptionHandler(ExceptionMapper exceptionMapper) {
        this.exceptionMapper = exceptionMapper;
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(GitHubConnectionException.class)
    public ResponseEntity<ExceptionDto> handleGitHubConnectionException(GitHubConnectionException exception) {
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        ExceptionDto exceptionDto = exceptionMapper.map(status.value(), exception.getMessage());
        return new ResponseEntity<>(exceptionDto, status);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ExceptionDto> handleNotFoundException(NotFoundException exception) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        ExceptionDto exceptionDto = exceptionMapper.map(status.value(), exception.getMessage());
        return new ResponseEntity<>(exceptionDto, status);
    }
}
