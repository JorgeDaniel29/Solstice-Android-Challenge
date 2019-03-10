package jorge.gonzalez.solstice.contactslist.data.repository

import io.reactivex.Single
import jorge.gonzalez.solstice.contactslist.data.api.ContactListApi
import jorge.gonzalez.solstice.contactslist.data.mapper.ContactListMapper
import jorge.gonzalez.solstice.contactslist.data.model.ContactListCacheData
import jorge.gonzalez.solstice.contactslist.domain.model.AvailableContact
import jorge.gonzalez.solstice.contactslist.domain.repository.ContactsRepository
import jorge.gonzalez.solstice.networkdatasource.data.api.NetworkApi

class ContactsListRepository(private val networkApi: NetworkApi,
                             private val mapper: ContactListMapper) : ContactsRepository {
    override fun fetchContacts(): Single<List<AvailableContact>> = networkApi.makeCacheableApiCallForResponse(
            ContactListApi::class.java,
            ContactListCacheData::class.java,
            ContactListCacheData::class.java.simpleName,
            { ContactListCacheData(it) },
            { it?.contactsList },
            { it.getContacts() })
            .map { contactsList -> contactsList.map { mapper.mapFromEntity(it) }.sortedBy { it.name } }
}