package org.example.project.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jetbrains.kmpapp.di.ViewModelKey
import com.jetbrains.kmpapp.di.ViewModelScope
import dev.zacsweers.metro.ContributesIntoMap
import dev.zacsweers.metro.Inject
import org.example.project.data.MuseumObject
import org.example.project.data.MuseumRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.stateIn

@ContributesIntoMap(ViewModelScope::class)
@ViewModelKey(DetailViewModel::class)
@Inject
class DetailViewModel(private val museumRepository: MuseumRepository) : ViewModel() {

    init {
        museumRepository.initialize()
    }

    private val objectId = MutableStateFlow<Int?>(null)

    @OptIn(ExperimentalCoroutinesApi::class)
    val museumObject: StateFlow<MuseumObject?> = objectId
        .flatMapLatest {
            val id = it ?: return@flatMapLatest flowOf(null)
            museumRepository.getObjectById(id)
        }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), null)

    fun setId(objectId: Int) {
        this.objectId.value = objectId
    }
}
