package org.example.project

import android.app.Activity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.HasDefaultViewModelProviderFactory
import androidx.lifecycle.ViewModelProvider
import com.jetbrains.kmpapp.di.ActivityKey
import dev.zacsweers.metro.AppScope
import dev.zacsweers.metro.ContributesIntoMap
import dev.zacsweers.metro.Inject
import dev.zacsweers.metro.binding

@ContributesIntoMap(AppScope::class, binding<Activity>())
@ActivityKey(MainActivity::class)
@Inject
class MainActivity(
    private val viewModelFactory: ViewModelProvider.Factory
) : ComponentActivity(), HasDefaultViewModelProviderFactory {

    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)

        setContent {
//            App()
            MuseumApp()
        }
    }
    // ViewModel Factory
    override val defaultViewModelProviderFactory: ViewModelProvider.Factory
        get() = viewModelFactory
}

@Preview
@Composable
fun AppAndroidPreview() {
    App()
}