package jorge.gonzalez.solstice.contactslist.presentation.model

data class AvailableContactItem(
        val name: String,
        val id: String,
        val companyName: String?,
        val isFavorite: Boolean,
        val smallImageURL: String?
)