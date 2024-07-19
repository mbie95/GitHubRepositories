package com.marcinbielen.githubrepositories.logic.mapper;

import com.marcinbielen.githubrepositories.model.dto.BranchDto;
import com.marcinbielen.githubrepositories.model.entity.Branch;
import com.marcinbielen.githubrepositories.model.entity.Commit;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class GitHubBranchMapper {

    public List<BranchDto> map (List<Branch> branches) {
        return branches.stream()
                .map(branch -> map(branch))
                .toList();
    }

    private BranchDto map(Branch branch) {
        String lastCommitSha = Optional.of(branch)
                .map(Branch::getCommit)
                .map(Commit::getSha)
                .orElse(null);
        return BranchDto.builder()
            .name(branch.getName())
            .lastCommitSha(lastCommitSha)
            .build();
    }
}
