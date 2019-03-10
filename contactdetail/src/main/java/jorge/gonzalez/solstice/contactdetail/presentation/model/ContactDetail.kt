package jorge.gonzalez.solstice.contactdetail.presentation.model

data class ContactDetail(
        val emailAddress: String?,
        val birthdate: String?,
        val phone: Phone?,
        val address: String?
)