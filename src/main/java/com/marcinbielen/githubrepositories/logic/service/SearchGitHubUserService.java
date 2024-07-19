package com.marcinbielen.githubrepositories.logic.service;

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
public class SearchGitHubUserService {

    private static final String GITHUB_API_URL = "https://api.github.com";

    private final UrlConnector urlConnector;
    private final SearchGitHubRepositoryService searchGitHubRepositoryService;

    public SearchGitHubUserService(UrlConnector urlConnector,
                                   SearchGitHubRepositoryService searchGitHubRepositoryService) {
        this.urlConnector = urlConnector;
        this.searchGitHubRepositoryService = searchGitHubRepositoryService;
    }

    public List<Repository> findAllUserRepositories(String ownerName) {
        try {
            URL url = new URL (GITHUB_API_URL + "/users/" + ownerName);
            String content = urlConnector.getResponse(url);
            User user = new ObjectMapper().readValue(content, User.class);
            return searchGitHubRepositoryService.findAllRepositoriesByUser(user);
        } catch (IOException e) {
            String errorMessage = "User not found!";
            log.error(errorMessage, e);
            throw new NotFoundException(errorMessage);
        }
    }
}
