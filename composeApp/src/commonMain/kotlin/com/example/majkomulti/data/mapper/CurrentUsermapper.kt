package com.example.majkomulti.data.mapper

import com.example.majkomulti.commons.Constantas
import com.example.majkomulti.data.models.User.CurrentUserDataResponse
import com.example.majkomulti.domain.modelsUI.User.CurrentUserDataUi

fun CurrentUserDataResponse.toUI() : CurrentUserDataUi{
    return CurrentUserDataUi(
        id = this.id.orEmpty(),
        createdAt = this.createdAt.orEmpty(),
        updatedAt = this.updatedAt.orEmpty(),
        name = this.name.orEmpty(),
        image = this.image?.let { Constantas.BASE_URL+this.image } ?: "",
        email = this.email.orEmpty(),
    )
}
