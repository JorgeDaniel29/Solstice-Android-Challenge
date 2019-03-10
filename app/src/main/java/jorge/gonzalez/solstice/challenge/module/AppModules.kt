package jorge.gonzalez.solstice.challenge.module

import jorge.gonzalez.solstice.contactdetail.module.detail
import jorge.gonzalez.solstice.contactslist.module.list
import jorge.gonzalez.solstice.networkdatasource.module.network

val appModules = listOf(
        network,
        navigation,
        list,
        detail
)