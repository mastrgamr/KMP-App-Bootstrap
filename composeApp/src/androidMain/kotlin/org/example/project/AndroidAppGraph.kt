package org.example.project

import android.app.Activity
import android.app.Application
import android.content.Context
import dev.zacsweers.metro.AppScope
import dev.zacsweers.metro.DependencyGraph
import dev.zacsweers.metro.Multibinds
import dev.zacsweers.metro.Provider
import dev.zacsweers.metro.Provides
import org.example.project.data.DataProviders
import org.example.project.viewmodel.ViewModelGraph
import kotlin.reflect.KClass

@DependencyGraph(
    scope = AppScope::class,
    bindingContainers = [DataProviders::class],
)
interface AndroidAppGraph : ViewModelGraph.Factory {

    /**
     * A multibinding map of activity classes to their providers accessible for
     * [MetroAppComponentFactory].
     */
    @Multibinds
    val activityProviders: Map<KClass<out Activity>, Provider<Activity>>

    @Provides
    fun provideApplicationContext(application: Application): Context = application

    @DependencyGraph.Factory
    interface Factory {
        fun create(
            @Provides application: Application,
        ): AndroidAppGraph
    }
}
