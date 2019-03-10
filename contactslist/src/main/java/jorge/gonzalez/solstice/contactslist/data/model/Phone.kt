package jorge.gonzalez.solstice.contactslist.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Phone(
        @SerializedName("work") val work: String?,
        @SerializedName("home") val home: String?,
        @SerializedName("mobile") val mobile: String?
) : Parcelable