package jorge.gonzalez.solstice.contactslist.domain.repository

import io.reactivex.Single
import jorge.gonzalez.solstice.contactslist.domain.model.AvailableContact

interface ContactsRepository {
    fun fetchContacts(): Single<List<AvailableContact>>
}