package com.picpay.desafio.android.domain.mapper

import com.picpay.desafio.android.feature.contacts.domain.mappers.ContactsEntityMapper
import com.picpay.desafio.android.util.ContactsHelperTest
import com.picpay.desafio.android.util.ContactsHelperTest.getFakeContactsEntity
import org.junit.Assert
import org.junit.Test

class ContactsEntityMapperTest {

    @Test
    fun `GIVEN a fake data WHEN transforming it THEN success`() {
        // Given
        val expected = ContactsHelperTest.getFakeContacts()

        // When
        val mapped = ContactsEntityMapper.transform(getFakeContactsEntity())

        // Then
        Assert.assertEquals(expected, mapped)
    }
}