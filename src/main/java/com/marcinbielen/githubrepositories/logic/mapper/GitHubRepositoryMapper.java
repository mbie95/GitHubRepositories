package com.marcinbielen.githubrepositories.logic.mapper;

import com.marcinbielen.githubrepositories.model.dto.RepositoryDto;
import com.marcinbielen.githubrepositories.model.entity.Branch;
import com.marcinbielen.githubrepositories.model.entity.Repository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GitHubRepositoryMapper {

    private final GitHubBranchMapper gitHubBranchMapper;

    public GitHubRepositoryMapper(GitHubBranchMapper gitHubBranchMapper) {
        this.gitHubBranchMapper = gitHubBranchMapper;
    }

    public List<RepositoryDto> map(List<Repository> repositories) {
        return repositories.stream()
                .map(repository -> map(repository.getName(), repository.getOwner().getLogin(), repository.getBranches()))
                .toList();
    }

    private RepositoryDto map(String repositoryName, String ownerLogin, List<Branch> branches) {
        return RepositoryDto.builder()
                .repositoryName(repositoryName)
                .ownerLogin(ownerLogin)
                .branches(gitHubBranchMapper.map(branches))
                .build();
    }
}
