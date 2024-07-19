package com.marcinbielen.githubrepositories.logic.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.marcinbielen.githubrepositories.model.entity.Branch;
import com.marcinbielen.githubrepositories.model.entity.Commit;

import java.io.IOException;

public class BranchDeserializer extends StdDeserializer<Branch> {

    public BranchDeserializer() {
        this(null);
    }

    public BranchDeserializer(Class<Branch> c) {
        super(c);
    }

    @Override
    public Branch deserialize(JsonParser jp, DeserializationContext ctxt)
            throws IOException, JsonProcessingException {
        JsonNode node = jp.getCodec().readTree(jp);
        String name = node.get("name").asText();
        String sha = node.get("commit").get("sha").asText();

        Commit commit = Commit.builder()
                .sha(sha)
                .build();
        return Branch.builder()
                .name(name)
                .commit(commit)
                .build();
    }
}
