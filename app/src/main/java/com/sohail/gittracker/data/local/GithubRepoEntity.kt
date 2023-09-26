package com.sohail.gittracker.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

// ROOM DB Entity
@Entity
data class GithubRepoEntity(
    val name : String,
    val description : String,
    val avatarUrl : String,
    @PrimaryKey val id : Int? = null
)