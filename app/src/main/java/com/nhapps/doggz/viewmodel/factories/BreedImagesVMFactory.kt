package com.nhapps.doggz.viewmodel.factories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.nhapps.doggz.model.repository.RepositoryI
import com.nhapps.doggz.viewmodel.BreedImagesViewModel

class BreedImagesVMFactory(private val repository: RepositoryI): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return BreedImagesViewModel(repository) as T
    }
}