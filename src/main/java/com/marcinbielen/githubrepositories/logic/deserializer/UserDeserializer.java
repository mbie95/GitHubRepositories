package com.marcinbielen.githubrepositories.logic.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.marcinbielen.githubrepositories.model.entity.Repository;
import com.marcinbielen.githubrepositories.model.entity.User;

import java.io.IOException;

public class UserDeserializer extends StdDeserializer<User> {

    public UserDeserializer() {
        this(null);
    }

    public UserDeserializer(Class<Repository> c) {
        super(c);
    }

    @Override
    public User deserialize(JsonParser jp, DeserializationContext ctxt)
            throws IOException, JsonProcessingException {
        JsonNode node = jp.getCodec().readTree(jp);
        String login = node.get("login").asText();

        return User.builder()
                .login(login)
                .build();
    }
}
