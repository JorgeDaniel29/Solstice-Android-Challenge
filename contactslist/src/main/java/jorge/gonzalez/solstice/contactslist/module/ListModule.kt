package jorge.gonzalez.solstice.contactslist.module

import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder
import jorge.gonzalez.solstice.contactslist.data.mapper.ContactListMapper
import jorge.gonzalez.solstice.contactslist.data.repository.ContactsListRepository
import jorge.gonzalez.solstice.contactslist.data.repository.UpdateContactRepository
import jorge.gonzalez.solstice.contactslist.domain.repository.ContactsRepository
import jorge.gonzalez.solstice.contactslist.domain.repository.UpdateRepository
import jorge.gonzalez.solstice.contactslist.domain.usecase.ContactListUseCase
import jorge.gonzalez.solstice.contactslist.domain.usecase.UpdateContactUseCase
import jorge.gonzalez.solstice.contactslist.presentation.mapper.ContactItemMapper
import jorge.gonzalez.solstice.contactslist.presentation.viewmodel.ContactListViewModel
import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

val list = module {
    //ui
    factory { GroupAdapter<ViewHolder>() }

    //view model
    viewModel { ContactListViewModel(get(), get(), get()) }

    //use case
    factory { ContactListUseCase(get()) }
    factory { UpdateContactUseCase(get()) }

    //mapper
    factory { ContactListMapper() }
    factory { ContactItemMapper() }

    //repository
    factory<ContactsRepository> { ContactsListRepository(get(), get()) }
    factory<UpdateRepository> { UpdateContactRepository(get()) }
}