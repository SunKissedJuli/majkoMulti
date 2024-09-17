package com.example.majkomulti.data.models.Task

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class SearchTask(
    @SerialName("search_str") var searchString: String = ""
)