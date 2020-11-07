package com.kotlin.mvvm.common.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.kotlin.mvvm.common.di.ViewModelFactory
import com.kotlin.mvvm.presentation.feed.FeedListViewModel
import dagger.Binds
import dagger.MapKey
import dagger.Module
import dagger.multibindings.IntoMap
import kotlin.reflect.KClass



@Module
abstract class ViewModelModule {
    /**
     * Binding FeedViewModel using this key "FeedViewModel::class"
     * So you can get FeedViewModel using "FeedViewModel::class" key from factory
     */
    @Binds
    @IntoMap
    @ViewModelKey(FeedListViewModel::class)
    abstract fun bindFeedViewModel(feedsViewModel: FeedListViewModel): ViewModel

    /**
     * Binds ViewModels factory to provide ViewModels.
     */
    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}

@MustBeDocumented
@Target(
    AnnotationTarget.FUNCTION,
    AnnotationTarget.PROPERTY_GETTER,
    AnnotationTarget.PROPERTY_SETTER
)
@Retention(AnnotationRetention.RUNTIME)
@MapKey
annotation class ViewModelKey(val value: KClass<out ViewModel>)