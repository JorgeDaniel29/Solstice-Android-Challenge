package jorge.gonzalez.solstice.contactdetail.module

import jorge.gonzalez.solstice.contactdetail.presentation.viewmodel.ContactDetailViewModel
import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

val detail = module {
    viewModel { ContactDetailViewModel() }
}