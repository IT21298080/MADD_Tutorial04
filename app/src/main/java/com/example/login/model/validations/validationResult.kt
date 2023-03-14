package com.example.login.model.validations

sealed class validationResult{

    data class empty(val errorMessage:String) : validationResult()
    data class invalid(val errorMessage: String) : validationResult()
    object valid : validationResult()
}
