package com.example.cristiandev.pelicula.di.module

import android.content.Context
import com.example.cristiandev.pelicula.net.PeliculaClient
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * Created by CristianDev on 22/11/2017.
 */
@Module
class NetModule {


    @Provides
    @Singleton
    fun provideRetrofit(context: Context): Retrofit = Retrofit.
            Builder()
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder()
                    .setDateFormat("dd/MM/yyyy")
                    .create()
            ))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
            .baseUrl("https://gankotest.herokuapp.com/")
            .build()

    @Provides
    @Singleton
    fun providePeliculaClient(retrofit: Retrofit): PeliculaClient =
            retrofit.create(PeliculaClient::class.java)

}