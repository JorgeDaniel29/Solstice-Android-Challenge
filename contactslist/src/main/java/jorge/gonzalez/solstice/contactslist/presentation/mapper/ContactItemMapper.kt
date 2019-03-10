package jorge.gonzalez.solstice.contactslist.presentation.mapper

import jorge.gonzalez.solstice.base.data.DataMapper
import jorge.gonzalez.solstice.contactslist.domain.model.AvailableContact
import jorge.gonzalez.solstice.contactslist.presentation.model.AvailableContactItem

class ContactItemMapper : DataMapper<AvailableContactItem, AvailableContact> {
    override fun mapFromEntity(entity: AvailableContactItem): AvailableContact {
        throw NotImplementedError()
    }

    override fun mapToEntity(domainModel: AvailableContact): AvailableContactItem =
            AvailableContactItem(
                    domainModel.name,
                    domainModel.id,
                    domainModel.companyName,
                    domainModel.isFavorite,
                    domainModel.smallImageURL
            )
}