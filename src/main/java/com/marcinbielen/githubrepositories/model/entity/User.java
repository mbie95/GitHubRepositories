package com.marcinbielen.githubrepositories.model.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.marcinbielen.githubrepositories.logic.deserializer.UserDeserializer;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@JsonDeserialize(using = UserDeserializer.class)
public class User {
    private String login;
}
