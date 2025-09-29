package org.example.project

import android.app.Application
import dev.zacsweers.metro.createGraphFactory

class KotlinProjectApp : Application() {
    /** Holder reference for the app graph for [MetroAppComponentFactory]. */
    val appGraph by lazy { createGraphFactory<AndroidAppGraph.Factory>().create(application = this) }
}