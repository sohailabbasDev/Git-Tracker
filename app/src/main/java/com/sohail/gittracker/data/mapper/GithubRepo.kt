package com.sohail.gittracker.data.mapper

import com.sohail.gittracker.data.local.GithubRepoEntity
import com.sohail.gittracker.data.remote.dto.GitRepoDto
import com.sohail.gittracker.domain.model.GithubRepo

//Converts GithubRepoEntity to GithubRepo
fun GithubRepoEntity.toGithubRepo(): GithubRepo {
    return GithubRepo(
        name = name,
        description = description,
        avatarUrl = avatarUrl
    )
}

//converts GithubRepo to GithubRepo
fun GitRepoDto.toGithubRepo() : GithubRepo {
    return GithubRepo(
        name = name,
        description = description,
        avatarUrl = owner.avatar_url
    )
}

//Converts GithubRepo to GithubRepoEntity
fun GithubRepo.toGithubRepoEntity() : GithubRepoEntity {
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