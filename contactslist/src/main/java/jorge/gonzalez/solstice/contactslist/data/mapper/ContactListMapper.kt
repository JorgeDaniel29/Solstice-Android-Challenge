package jorge.gonzalez.solstice.contactslist.data.mapper

import jorge.gonzalez.solstice.base.data.DataMapper
import jorge.gonzalez.solstice.contactslist.data.model.Contact
import jorge.gonzalez.solstice.contactslist.domain.model.AvailableContact

class ContactListMapper : DataMapper<Contact, AvailableContact> {
    override fun mapFromEntity(entity: Contact): AvailableContact =
            AvailableContact(entity.name,
                    entity.id,
                    entity.companyName,
                    entity.isFavorite,
                    entity.smallImageURL,
                    entity.largeImageURL,
                    entity.emailAddress,
                    entity.birthDate,
                    entity.phone,
                    entity.address)

    override fun mapToEntity(domainModel: AvailableContact): Contact {
        throw NotImplementedError()
    }
}