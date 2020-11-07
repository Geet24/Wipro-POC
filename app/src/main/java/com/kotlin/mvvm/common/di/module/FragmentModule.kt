package com.kotlin.mvvm.common.di.module

import com.kotlin.mvvm.presentation.feed.FeedListFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Suppress("unused")
@Module
abstract class FragmentModule {

    /**
     * Injecting Fragments
     */
    @ContributesAndroidInjector
    internal abstract fun contributeCountryListFragment(): FeedListFragment
}
