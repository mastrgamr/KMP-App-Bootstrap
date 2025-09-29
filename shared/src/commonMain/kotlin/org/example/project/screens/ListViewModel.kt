package org.example.project.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jetbrains.kmpapp.di.ViewModelKey
import com.jetbrains.kmpapp.di.ViewModelScope
import dev.zacsweers.metro.ContributesIntoMap
import dev.zacsweers.metro.Inject
import org.example.project.data.MuseumObject
import org.example.project.data.MuseumRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn

@ContributesIntoMap(ViewModelScope::class)
@ViewModelKey(ListViewModel::class)
@Inject
class ListViewModel(museumRepository: MuseumRepository) : ViewModel() {

    init {
        museumRepository.initialize()
    }

    val objects: StateFlow<List<MuseumObject>> =
        museumRepository.getObjects()
            .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())
}
