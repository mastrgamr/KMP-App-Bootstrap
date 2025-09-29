package com.jetbrains.kmpapp.di

import dev.zacsweers.metro.Scope


@Scope
@Target(AnnotationTarget.CLASS, AnnotationTarget.FUNCTION, AnnotationTarget.PROPERTY_GETTER)
annotation class MainAppScope