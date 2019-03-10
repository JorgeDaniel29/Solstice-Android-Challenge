package jorge.gonzalez.solstice.contactslist.domain.model

import android.os.Parcelable
import jorge.gonzalez.solstice.contactslist.data.model.Address
import jorge.gonzalez.solstice.contactslist.data.model.Phone
import kotlinx.android.parcel.Parcelize

@Parcelize
data class AvailableContact(
        val name: String,
        val id: String,
        val companyName: String?,
        var isFavorite: Boolean,
        val smallImageURL: String?,
        val largeImageURL: String?,
        val emailAddress: String?,
        val birthdate: String?,
        val phone: Phone?,
        val address: Address?
) : Parcelable