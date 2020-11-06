package com.example.myapplication.common.di.module

import com.example.myapplication.presentation.feed.FeedListActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector



/**
 * All your Activities participating in DI would be listed here.
 */
@Module(includes = [FragmentModule::class]) // Including Fragment Module Available For Activities
abstract class ActivityModule {

    /**
     * Marking Activities to be available to contributes for Android Injector
     */
    @ContributesAndroidInjector
    abstract fun contributeFeedListActivity(): FeedListActivity

}