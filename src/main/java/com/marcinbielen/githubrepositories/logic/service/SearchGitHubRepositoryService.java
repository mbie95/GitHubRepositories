package com.marcinbielen.githubrepositories.logic.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.marcinbielen.githubrepositories.logic.UrlConnector;
import com.marcinbielen.githubrepositories.model.entity.Repository;
import com.marcinbielen.githubrepositories.model.entity.User;
import com.marcinbielen.githubrepositories.model.exception.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URL;
import java.util.List;

@Slf4j
@Service
public class SearchGitHubRepositoryService {

    private static final String GITHUB_API_URL = "https://api.github.com";

    private final UrlConnector urlConnector;
    private final SearchGitHubBranchService searchGitHubBranchService;

    public SearchGitHubRepositoryService(UrlConnector urlConnector,
                                         SearchGitHubBranchService searchGitHubBranchService) {
        this.urlConnector = urlConnector;
        this.searchGitHubBranchService = searchGitHubBranchService;
    }


    public List<Repository> findAllRepositoriesByUser(User user) {
        try {
            URL url = new URL (GITHUB_API_URL + "/users/" + user.getLogin() + "/repos");
            String content = urlConnector.getResponse(url);
            List<Repository> repositories = new ObjectMapper().readValue(content, new TypeReference<List<Repository>>(){});
            repositories = repositories.stream()
                .filter(repository -> !repository.getFork())
                .toList();
            repositories.forEach(repository ->
                repository.setBranches(searchGitHubBranchService.findAllBranchesByRepository(user, repository))
            );
            return repositories;
        } catch (IOException e) {
            String errorMessage = "Repositories not found!";
            log.error(errorMessage, e);
            throw new NotFoundException(errorMessage);
        }
    }

}
