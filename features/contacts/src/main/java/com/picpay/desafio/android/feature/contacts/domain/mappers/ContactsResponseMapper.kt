package com.picpay.desafio.android.feature.contacts.domain.mappers

import com.picpay.android.common.BaseMapper
import com.picpay.desafio.android.feature.contacts.data.entities.ContactEntity
import com.picpay.desafio.android.feature.contacts.data.entities.UserResponse

object ContactsResponseMapper : BaseMapper<UserResponse, ContactEntity>() {

    override fun transform(entity: UserResponse) = ContactEntity(
        id = entity.id,
        img = entity.img.orEmpty(),
        name = entity.name.orEmpty(),
        username = entity.username.orEmpty()
    )
}
