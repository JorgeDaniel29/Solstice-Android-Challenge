package jorge.gonzalez.solstice.contactslist.presentation.model

data class MapContactsItem (
        val favoriteContactsList: List<AvailableContactItem>,
        val otherContactsList: List<AvailableContactItem>
)