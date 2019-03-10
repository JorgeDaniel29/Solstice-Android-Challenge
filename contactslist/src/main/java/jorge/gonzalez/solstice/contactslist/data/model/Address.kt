package jorge.gonzalez.solstice.contactslist.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Address(
        @SerializedName("street") val street: String?,
        @SerializedName("city") val city: String?,
        @SerializedName("state") val state: String?,
        @SerializedName("country") val country: String?,
        @SerializedName("zipCode") val zipCode: String?
) : Parcelable