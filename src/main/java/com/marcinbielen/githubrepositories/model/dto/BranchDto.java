package com.marcinbielen.githubrepositories.model.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BranchDto {
    private String name;
    private String lastCommitSha;
}
