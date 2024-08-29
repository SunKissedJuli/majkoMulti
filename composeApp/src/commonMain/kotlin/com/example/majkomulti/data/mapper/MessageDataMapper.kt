package com.example.majkomulti.data.mapper

import com.example.majkomulti.data.models.MessageData
import com.example.majkomulti.domain.modelsUI.MessageDataUi

fun MessageData.toUI() : MessageDataUi {
    return MessageDataUi(
        message = this.message.orEmpty()
    )
}