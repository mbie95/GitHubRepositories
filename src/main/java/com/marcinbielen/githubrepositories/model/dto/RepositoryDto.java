package com.marcinbielen.githubrepositories.model.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RepositoryDto {
    private String repositoryName;
    private String ownerLogin;
    private List<BranchDto> branches;
}
