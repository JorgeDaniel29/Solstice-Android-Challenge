package jorge.gonzalez.solstice.contactslist.data.repository

import io.reactivex.Completable
import io.reactivex.Single
import jorge.gonzalez.solstice.contactslist.data.model.ContactListCacheData
import jorge.gonzalez.solstice.contactslist.domain.repository.UpdateRepository
import jorge.gonzalez.solstice.contactslist.data.functions.replace
import jorge.gonzalez.solstice.networkdatasource.data.cache.CacheProvider

class UpdateContactRepository(
        private val cacheProvider: CacheProvider
) : UpdateRepository {
    override fun updateContact(id: String): Completable = Single.just(id).flatMapCompletable {
        val listCacheData = cacheProvider.provide(
                ContactListCacheData::class.java,
                ContactListCacheData::class.java.simpleName
        )?.contactsList

        listCacheData?.find { contact -> contact.id == it }?.let { contact ->
            contact.isFavorite = !contact.isFavorite

            listCacheData.replace(contact) { it.id == contact.id }
            cacheProvider.store(ContactListCacheData(listCacheData), ContactListCacheData::class.java.simpleName)
            Completable.complete()
        } ?: Completable.error(Exception())
    }
}