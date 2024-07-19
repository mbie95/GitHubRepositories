package com.marcinbielen.githubrepositories.model.entity;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Commit {
    private String sha;
}
