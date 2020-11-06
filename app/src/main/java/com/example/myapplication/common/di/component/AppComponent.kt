package com.example.myapplication.common.di.component

import com.example.myapplication.BaseApplication
import com.example.myapplication.common.di.module.AppModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton


@Singleton
@Component(modules = [AndroidInjectionModule::class, AppModule::class])
interface AppComponent : AndroidInjector<BaseApplication> {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(app: BaseApplication): Builder

        fun build(): AppComponent
    }

    override fun inject(app: BaseApplication)

}
