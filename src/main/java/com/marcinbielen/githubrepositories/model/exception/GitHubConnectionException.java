package com.marcinbielen.githubrepositories.model.exception;

public class GitHubConnectionException extends RuntimeException {
    public GitHubConnectionException(String message) {
        super(message);
    }
}
