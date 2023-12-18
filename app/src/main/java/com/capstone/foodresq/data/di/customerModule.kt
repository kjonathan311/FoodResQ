package com.capstone.foodresq.data.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.capstone.foodresq.data.datastore.UserPreference
import com.capstone.foodresq.data.repository.DetailRepository
import com.capstone.foodresq.data.repository.ExploreRepository
import com.capstone.foodresq.data.repository.HistoryOrderRepository
import com.capstone.foodresq.data.repository.ListRepository
import com.capstone.foodresq.data.repository.LoginRepository
import com.capstone.foodresq.data.repository.ProfileRepository
import com.capstone.foodresq.data.repository.RegisterRepository
import com.capstone.foodresq.data.repository.SubscriptionRepository
import com.capstone.foodresq.data.repository.UserRepository
import com.capstone.foodresq.ui.detail.DetailViewModel
import com.capstone.foodresq.ui.list.ListViewModel
import com.capstone.foodresq.ui.login.LoginViewModel
import com.capstone.foodresq.ui.main.MainViewModel
import com.capstone.foodresq.ui.main.explore.ExploreViewModel
import com.capstone.foodresq.ui.main.profile.ProfileViewModel
import com.capstone.foodresq.ui.register.RegisterViewModel
import com.capstone.foodresq.ui.subscription.SubscriptionViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val customerModule= module {

    //Datastore
    single { provideDataStore(androidContext()) }
    single { UserPreference(get()) }

    //repositories
    single { ExploreRepository(get()) }
    single { HistoryOrderRepository(get()) }
    single { LoginRepository(get()) }
    single { ProfileRepository(get()) }
    single { RegisterRepository(get()) }
    single { UserRepository(get()) }
    single { DetailRepository(get()) }
    single { ListRepository(get()) }
    single { SubscriptionRepository(get()) }

    //viewModels
    viewModel { LoginViewModel(get(),get()) }
    viewModel { RegisterViewModel(get()) }
    viewModel { MainViewModel(get()) }
    viewModel { ProfileViewModel(get(),get()) }
    viewModel { ExploreViewModel(get()) }
    viewModel { DetailViewModel(get()) }
    viewModel { ListViewModel(get()) }
    viewModel { SubscriptionViewModel(get()) }

}
fun provideDataStore(context: Context): DataStore<Preferences> {
    return context.dataStore
}

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "session")
