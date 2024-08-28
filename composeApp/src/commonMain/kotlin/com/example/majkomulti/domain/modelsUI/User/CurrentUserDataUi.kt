package com.example.majkomulti.domain.modelsUI.User

data class CurrentUserDataUi(
    var id : String,
    var createdAt : String,
    var updatedAt : String,
    var name : String,
    var image : String,
    var email : String,
){
    companion object{
        fun empty() = CurrentUserDataUi(
            id = "",
            createdAt = "",
            updatedAt = "",
            name = "",
            image = "",
            email = "")
    }
}