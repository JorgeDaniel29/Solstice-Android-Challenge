package jorge.gonzalez.solstice.contactslist.domain.repository

import io.reactivex.Completable

interface UpdateRepository {
    fun updateContact(id: String): Completable
}