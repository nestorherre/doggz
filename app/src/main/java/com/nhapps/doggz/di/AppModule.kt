package com.nhapps.doggz.di

import com.nhapps.doggz.model.repository.Repository
import com.nhapps.doggz.model.repository.RepositoryI
import com.nhapps.doggz.viewmodel.factories.BreedImagesVMFactory
import com.nhapps.doggz.viewmodel.BreedImagesViewModel
import com.nhapps.doggz.viewmodel.factories.BreedsVMFactory
import com.nhapps.doggz.viewmodel.BreedsViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single<RepositoryI> { Repository() }
    single { BreedsVMFactory(get()) }
    single { BreedImagesVMFactory(get()) }
    viewModel { BreedsViewModel(get()) }
    viewModel { BreedImagesViewModel(get()) }
}