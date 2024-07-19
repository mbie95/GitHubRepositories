package com.marcinbielen.githubrepositories.model.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.marcinbielen.githubrepositories.logic.deserializer.BranchDeserializer;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@JsonDeserialize(using = BranchDeserializer.class)
public class Branch {
    private String name;
    private Commit commit;
}
