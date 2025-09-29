package org.example.project.viewmodel

import android.app.Application
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import com.jetbrains.kmpapp.di.ViewModelScope
import dev.zacsweers.metro.GraphExtension
import dev.zacsweers.metro.Multibinds
import dev.zacsweers.metro.Provider
import dev.zacsweers.metro.Provides
import kotlin.reflect.KClass

@GraphExtension(ViewModelScope::class)
interface ViewModelGraph {

    @Multibinds
    val viewModelProviders: Map<KClass<out ViewModel>, Provider<ViewModel>>

    @Provides
    fun provideApplication(creationExtras: CreationExtras): Application =
        creationExtras[APPLICATION_KEY]!!

    @Provides
    fun provideSavedStateHandle(creationExtras: CreationExtras): SavedStateHandle =
        creationExtras.createSavedStateHandle()

    @GraphExtension.Factory
    fun interface Factory {
        fun create(
            @Provides creationExtras: CreationExtras,
        ): ViewModelGraph
    }
}
