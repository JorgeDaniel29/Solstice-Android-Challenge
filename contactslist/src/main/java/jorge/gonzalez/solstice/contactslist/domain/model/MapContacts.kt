package jorge.gonzalez.solstice.contactslist.domain.model

data class MapContacts(
        val favoriteContactsList: List<AvailableContact>,
        val otherContactsList: List<AvailableContact>
)