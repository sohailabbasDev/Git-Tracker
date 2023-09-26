package com.sohail.gittracker.util

//similar to resource helper class
sealed class Status(val message : String? = null){
    class Success() : Status()
    class Loading() : Status()
    class Error(message: String?) : Status(message = message)
    class Initial() : Status()
}
