package com.picpay.desafio.android.feature.contacts.domain.mappers

import com.picpay.android.common.BaseMapper
import com.picpay.desafio.android.feature.contacts.data.entities.UserResponse
import com.picpay.desafio.android.feature.contacts.domain.model.User


object ContactsMapper : BaseMapper<UserResponse, User>() {
    override fun transform(entity: UserResponse) = User(
        id = entity.id,
        img = entity.img.orEmpty(),
        name = entity.name.orEmpty(),
        username = entity.username.orEmpty()
    )

}