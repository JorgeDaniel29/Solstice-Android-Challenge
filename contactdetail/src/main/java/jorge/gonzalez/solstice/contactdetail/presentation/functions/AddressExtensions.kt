package jorge.gonzalez.solstice.contactdetail.presentation.functions

import jorge.gonzalez.solstice.contactdetail.presentation.model.Address
import java.lang.StringBuilder

fun Address?.toAddressFormat(): String? {
    if (this == null) return null

    return StringBuilder()
            .append(street)
            .append("\n")
            .append("$city,")
            .append(" $state $zipCode, ")
            .append(country)
            .toString()
}