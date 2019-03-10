package jorge.gonzalez.solstice.contactslist.presentation.model

sealed class ContactListUiState {
    object Error : ContactListUiState()
    object Loading : ContactListUiState()
    class Data(val contactItems: MapContactsItem) : ContactListUiState()
}