package com.pierretest.cardstoreapp.di

import com.google.firebase.auth.FirebaseAuth
import com.pierretest.cardstoreapp.common.ApiDetails
import com.pierretest.cardstoreapp.data.remote.ApiCall
import com.pierretest.cardstoreapp.data.repository.Repository
import com.pierretest.cardstoreapp.data.repository.RepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module

class AppModule {

    @Provides
    @Singleton
    fun provideOkHttpInstance() : OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofitInstance(
        client: OkHttpClient
    ) : Retrofit {
        return Retrofit.Builder()
            .baseUrl(ApiDetails.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }

    @Provides
    @Singleton
    fun provideAPI(
        retrofit: Retrofit
    ) : ApiCall {
        return retrofit.create(ApiCall::class.java)
    }

    @Provides
    fun provideFirebaseAuthInstance() : FirebaseAuth {
        return FirebaseAuth.getInstance()
    }

    @Provides
    @Singleton
    fun provideRepository(
        apiCall: ApiCall,
        firebaseAuth: FirebaseAuth
    ) : Repository {
        return RepositoryImpl(apiCall, firebaseAuth)
    }
}