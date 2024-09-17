package com.example.majkomulti.data.models.Task

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class TaskById(
    @SerialName("taskId") val taskId: String,
)

@Serializable
class TaskByIdUnderscore(
    @SerialName("task_id") val taskId: String,
)