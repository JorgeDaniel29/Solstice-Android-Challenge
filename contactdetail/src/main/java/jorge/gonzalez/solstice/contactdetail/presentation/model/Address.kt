package jorge.gonzalez.solstice.contactdetail.presentation.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Address(
        val street: String?,
        val city: String?,
        val state: String?,
        val country: String?,
        val zipCode: String?
) : Parcelable