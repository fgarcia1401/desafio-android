package com.picpay.desafio.android.feature.contacts.domain.mappers

import com.picpay.android.common.BaseMapper
import com.picpay.desafio.android.feature.contacts.data.entities.ContactEntity
import com.picpay.desafio.android.feature.contacts.domain.model.User

object ContactsEntityMapper : BaseMapper<ContactEntity, User>() {

    override fun transform(entity: ContactEntity) = User(
        id = entity.id,
        img = entity.img.orEmpty(),
        name = entity.name.orEmpty(),
        username = entity.username.orEmpty()
    )
}
