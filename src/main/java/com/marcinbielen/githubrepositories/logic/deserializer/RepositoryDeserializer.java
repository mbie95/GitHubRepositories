package com.marcinbielen.githubrepositories.logic.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.marcinbielen.githubrepositories.model.entity.Owner;
import com.marcinbielen.githubrepositories.model.entity.Repository;

import java.io.IOException;

public class RepositoryDeserializer extends StdDeserializer<Repository> {

    public RepositoryDeserializer() {
        this(null);
    }

    public RepositoryDeserializer(Class<Repository> c) {
        super(c);
    }

    @Override
    public Repository deserialize(JsonParser jp, DeserializationContext ctxt)
            throws IOException, JsonProcessingException {
        JsonNode node = jp.getCodec().readTree(jp);
        String name = node.get("name").asText();
        String ownerLogin = node.get("owner").get("login").asText();
        Boolean isFork = node.get("fork").asBoolean();

        Owner owner = Owner.builder()
                .login(ownerLogin)
                .build();
        return Repository.builder()
                .name(name)
                .owner(owner)
                .fork(isFork)
                .build();
    }
}
