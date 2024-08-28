package com.example.majkomulti.data.models.Task

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SearchTask(
    @SerialName("search_str") var searchString: String = ""
)