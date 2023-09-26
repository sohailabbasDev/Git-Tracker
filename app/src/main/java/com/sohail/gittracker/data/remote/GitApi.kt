package com.sohail.gittracker.data.remote

import com.sohail.gittracker.data.remote.dto.GitRepoDto
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

//Git API instance
interface GitApi {
//    /repos/{owner}/{repo}
    @GET("repos/{owner}/{repo}")
    suspend fun getRepo(
    @Header("Authorization") token :String = API_KEY,
    @Path("owner") owner : String,
    @Path("repo") repo : String
    ) : GitRepoDto

    companion object{

        //Change this with your own token, This token may expire
        const val API_KEY = "Bearer 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"

        //The API base url
        const val BASE_URL = "https://api.github.com/"
    }
}