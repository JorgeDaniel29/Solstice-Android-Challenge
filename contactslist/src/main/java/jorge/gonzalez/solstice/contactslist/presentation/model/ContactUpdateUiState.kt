package jorge.gonzalez.solstice.contactslist.presentation.model

sealed class ContactUpdateUiState {
    object Error : ContactUpdateUiState()
    object Complete : ContactUpdateUiState()
}