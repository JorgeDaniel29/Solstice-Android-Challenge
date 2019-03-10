package jorge.gonzalez.solstice.contactdetail.presentation.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Contact(
        val name: String,
        val id: String,
        val companyName: String?,
        val isFavorite: Boolean,
        val largeImageURL: String?,
        val emailAddress: String?,
        val birthdate: String?,
        val phone: Phone?,
        val address: Address?
) : Parcelable