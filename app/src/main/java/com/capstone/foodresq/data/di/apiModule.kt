package com.capstone.foodresq.data.di

import com.capstone.foodresq.BuildConfig
import com.capstone.foodresq.data.datastore.UserPreference
import com.capstone.foodresq.data.remote.retrofit.ApiService
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val apiModule = module {
    single {
        HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    factory {
        val loggingInterceptor: HttpLoggingInterceptor = get()
        val userPreference: UserPreference = get()

        val client = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .addInterceptor { chain ->
                val original = chain.request()
                val requestBuilder = original.newBuilder()
                val token = runBlocking {
                    userPreference.getSession().first().token
                }
                requestBuilder.header("Authorization", "Bearer $token")
                val newRequest = requestBuilder.build()
                chain.proceed(newRequest)
            }
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()

        retrofit.create(ApiService::class.java)
    }
}
