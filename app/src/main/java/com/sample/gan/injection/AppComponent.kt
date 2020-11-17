package com.sample.gan.injection

import com.sample.gan.GANApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule

@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        NetworkModule::class
    ]
)
interface AppComponent : AndroidInjector<GANApplication> {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance application: GANApplication): AppComponent
    }
}