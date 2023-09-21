package com.sohail.gittracker.ui.theme.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class GithubRepoEntity(
    val name : String,
    val description : String,
    val avatarUrl : String,
    @PrimaryKey val id : Int? = null
)