package jorge.gonzalez.solstice.contactslist.domain.usecase

import io.reactivex.Single
import jorge.gonzalez.solstice.base.domain.SingleUseCase
import jorge.gonzalez.solstice.contactslist.domain.model.AvailableContact
import jorge.gonzalez.solstice.contactslist.domain.model.MapContacts
import jorge.gonzalez.solstice.contactslist.domain.repository.ContactsRepository

class ContactListUseCase(
        private val contactsRepository: ContactsRepository
) : SingleUseCase<MapContacts, Unit>() {
    override fun buildUseCaseObservable(params: Unit?): Single<MapContacts> =
            contactsRepository.fetchContacts()
                    .setUpUseCase()
                    .map { buildMapContacts(it) }

    private fun buildMapContacts(contactList: List<AvailableContact>): MapContacts =
            MapContacts(contactList.filter { it.isFavorite },
                    contactList.filter { !it.isFavorite })
}