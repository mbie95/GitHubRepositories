package com.marcinbielen.githubrepositories.model.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExceptionDto {

    private int status;
    private String message;

}
