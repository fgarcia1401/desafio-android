package com.picpay.desafio.android.domain.mapper

import com.picpay.desafio.android.feature.contacts.domain.mappers.ContactsResponseMapper
import com.picpay.desafio.android.util.ContactsHelperTest
import com.picpay.desafio.android.util.ContactsHelperTest.getFakeContactsResponse
import org.junit.Assert
import org.junit.Test

class ContactsResponseMapperTest {

    @Test
    fun `GIVEN a fake data WHEN transforming it THEN success`() {
        // Given
        val expected = ContactsHelperTest.getFakeContactsEntity()

        // When
        val mapped = ContactsResponseMapper.transform(getFakeContactsResponse())

        // Then
        Assert.assertEquals(expected, mapped)
    }
}
