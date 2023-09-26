package com.sohail.gittracker.util

//Helper resource class
sealed class Resource<T>(val data: T? = null, val message : String? = null){

    //when in success it will contain data
    class Success<T>(data: T?): Resource<T>(data = data)

    //when error it will contain error message
    class Error<T>(message: String?, data: T? = null) : Resource<T>(data = data, message = message)

    //when at loading stage
    class Loading<T>(val isLoading: Boolean = true): Resource<T>(null)

    //when at initial stage
    class Initial<T> : Resource<T>(null)
}