package jorge.gonzalez.solstice.challenge.module

import jorge.gonzalez.solstice.challenge.presentation.viewmodel.NavigationViewModel
import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

val navigation = module {
    viewModel { NavigationViewModel() }
}