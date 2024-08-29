package com.example.majkomulti.data.models.Task

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TaskById(
    @SerialName("taskId") val taskId: String,
)

@Serializable
data class TaskByIdUnderscore(
    @SerialName("task_id") val taskId: String,
)