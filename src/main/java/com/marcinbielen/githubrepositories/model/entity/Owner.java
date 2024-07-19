package com.marcinbielen.githubrepositories.model.entity;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Owner {
    private String login;
}
