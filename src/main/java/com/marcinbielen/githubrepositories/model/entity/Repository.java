package com.marcinbielen.githubrepositories.model.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.marcinbielen.githubrepositories.logic.deserializer.RepositoryDeserializer;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
@JsonDeserialize(using = RepositoryDeserializer.class)
public class Repository {
    private String name;
    private Owner owner;
    private Boolean fork;
    private List<Branch> branches;
}
