package com.marcinbielen.githubrepositories.logic.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.marcinbielen.githubrepositories.logic.UrlConnector;
import com.marcinbielen.githubrepositories.model.entity.Branch;
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
public class SearchGitHubBranchService {

    private static final String GITHUB_API_URL = "https://api.github.com";

    private final UrlConnector urlConnector;

    public SearchGitHubBranchService(UrlConnector urlConnector) {
        this.urlConnector = urlConnector;
    }


    public List<Branch> findAllBranchesByRepository(User user, Repository repository) {
        try {
            URL url = new URL (GITHUB_API_URL + "/repos/" + user.getLogin() + "/" + repository.getName() + "/branches");
            String content = urlConnector.getResponse(url);
            return new ObjectMapper().readValue(content, new TypeReference<List<Branch>>(){});
        } catch (IOException e) {
            String errorMessage = "Branches for repository " + repository.getName() + " not found!";
            log.error(errorMessage, e);
            throw new NotFoundException(errorMessage);
        }
    }

}
