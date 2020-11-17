package com.sample.gan.home.injection

import androidx.lifecycle.ViewModel
import com.sample.gan.home.data.network.APIService
import com.sample.gan.home.view.HomeActivity
import com.sample.gan.home.viewmodel.HomeViewModelImpl
import com.sample.gan.injection.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap
import retrofit2.Retrofit

@Module(includes = [HomeBindingModule::class])
object HomeModule {

    @Provides
    fun provideNewsService(retrofit: Retrofit): APIService {
        return retrofit.create(APIService::class.java)
    }
}

@Module
interface HomeBindingModule {

    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModelImpl::class)
    fun bindMainViewModel(homeViewModel: HomeViewModelImpl): ViewModel

    @ContributesAndroidInjector
    fun bindHomeActivity(): HomeActivity
}