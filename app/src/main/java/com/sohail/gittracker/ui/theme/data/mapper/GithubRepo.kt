package com.sohail.gittracker.ui.theme.data.mapper

import com.sohail.gittracker.ui.theme.data.local.GithubRepoEntity
import com.sohail.gittracker.ui.theme.data.remote.dto.GitRepoDto
import com.sohail.gittracker.ui.theme.domain.model.GithubRepo

fun GithubRepoEntity.toGithubRepo(): GithubRepo {
    return GithubRepo(
        name = name,
        description = description,
        avatarUrl = avatarUrl
    )
}

fun GitRepoDto.toGithubRepo() : GithubRepo{
    return GithubRepo(
        name = name,
        description = description,
        avatarUrl = owner.avatar_url
    )
}

fun GithubRepo.toGithubRepoEntity() : GithubRepoEntity{
    return GithubRepoEntity(
        name = name,
        description = description,
        avatarUrl = avatarUrl
    )
}

//fun GitRepoDto.toGithubRepoEntity(): GithubRepoEntity {
//    return GithubRepoEntity(
//        name = name,
//        description = description,
//        avatarUrl = owner.avatar_url
//    )
//}