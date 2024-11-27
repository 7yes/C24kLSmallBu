package com.jesse.c24lksmall.di

import com.jesse.c24lksmall.data.SampleApiServ
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    @Singleton
    fun providesRetrofit():Retrofit{
        return Retrofit.Builder()
            .baseUrl("https://bobsburgers-api.herokuapp.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    fun providesSampleApi(retrofit: Retrofit): SampleApiServ {
        return retrofit.create(SampleApiServ::class.java)
    }

}