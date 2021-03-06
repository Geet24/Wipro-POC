package com.kotlin.mvvm.common.di.module

import android.content.res.Resources
import com.kotlin.mvvm.BaseApplication
import com.kotlin.mvvm.BuildConfig
import com.kotlin.mvvm.data.repository.FeedsRepository
import com.kotlin.mvvm.data.repository.FeedsRepositoryImpl
import com.kotlin.mvvm.data.sources.api.ApiService
import com.kotlin.mvvm.data.sources.api.FeedsApi
import com.kotlin.mvvm.data.sources.api.FeedsApiImpl
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module(includes = [ActivityModule::class, ViewModelModule::class])
class AppModule {

    @Singleton
    @Provides
    fun provideApiService(): ApiService {
         val gson: Gson = GsonBuilder().create()
         val gsonConverterFactory: GsonConverterFactory = GsonConverterFactory.create(gson)
         val loggingInterceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
         val okHttpClient = OkHttpClient.Builder()
                .connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .addInterceptor(loggingInterceptor)
                .build()
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(gsonConverterFactory)
            .build()
            .create(ApiService::class.java)
    }


    @Provides
    @Singleton
    fun providesResources(application: BaseApplication): Resources = application.resources


    @Provides
    @Singleton
    fun providesFeedApi(apiService: ApiService): FeedsApi {
        return FeedsApiImpl(apiService)
    }

    @Provides
    @Singleton
    fun providesFeedRepository(feedsApi: FeedsApi): FeedsRepository {
        return FeedsRepositoryImpl(feedsApi)
    }

}