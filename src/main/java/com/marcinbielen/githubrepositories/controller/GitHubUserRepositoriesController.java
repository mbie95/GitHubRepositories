package com.marcinbielen.githubrepositories.controller;

import com.marcinbielen.githubrepositories.logic.mapper.GitHubRepositoryMapper;
import com.marcinbielen.githubrepositories.logic.service.SearchGitHubUserService;
import com.marcinbielen.githubrepositories.model.dto.UserNameDto;
import com.marcinbielen.githubrepositories.model.entity.Repository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/repos")
public class GitHubUserRepositoriesController {

    private static final String CONTENT_TYPE_JSON = "application/json";

    private final SearchGitHubUserService searchGitHubUserService;
    private final GitHubRepositoryMapper gitHubRepositoryMapper;

    public GitHubUserRepositoriesController(SearchGitHubUserService searchGitHubUserService,
                                            GitHubRepositoryMapper gitHubRepositoryMapper) {
        this.searchGitHubUserService = searchGitHubUserService;
        this.gitHubRepositoryMapper = gitHubRepositoryMapper;
    }

    @GetMapping(consumes = CONTENT_TYPE_JSON, produces = CONTENT_TYPE_JSON)
    ResponseEntity<?> getAllRepositoriesByOwnerLogin(@RequestHeader(name = "Accept") String acceptHeader,
                                                     @RequestBody UserNameDto userNameDto) {
        List<Repository> repositoryList = searchGitHubUserService.findAllUserRepositories(userNameDto.getUserName());
        return new ResponseEntity<>(gitHubRepositoryMapper.map(repositoryList), HttpStatus.OK);
    }
}
