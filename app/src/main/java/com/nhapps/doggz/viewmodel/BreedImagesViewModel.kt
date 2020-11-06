package com.nhapps.doggz.viewmodel

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nhapps.doggz.model.repository.RepositoryI
import kotlinx.coroutines.launch

class BreedImagesViewModel(private val repository: RepositoryI): ViewModel() {
    private val _imagesList: MutableLiveData<List<String>> = MutableLiveData()
    val imagesList: LiveData<List<String>> = _imagesList

    private val _loading: MutableLiveData<Int> = MutableLiveData()
    val loading: LiveData<Int> = _loading

    private val _reintentarButton: MutableLiveData<Int> = MutableLiveData()
    val reintentarButton: LiveData<Int> = _reintentarButton

    private val _errorImage: MutableLiveData<Int> = MutableLiveData()
    val errorImage: LiveData<Int> = _errorImage

    fun getBreedImages(breed: String){
        _imagesList.value = listOf()
        _loading.value = View.VISIBLE
        viewModelScope.launch {
            try {
                val response = repository.getBreedImages(breed)
                if (response.isSuccessful){
                    handleSuccess()
                    _imagesList.value = response.body()!!.message
                } else {
                    handleError()
                }
            }catch(error: Throwable){
                handleError()
            }

        }
    }

    private fun handleSuccess() {
        _loading.value = View.INVISIBLE
        _reintentarButton.value = View.INVISIBLE
        _errorImage.value = View.INVISIBLE
    }

    private fun handleError() {
        _loading.value = View.INVISIBLE
        _reintentarButton.value = View.VISIBLE
        _errorImage.value = View.VISIBLE
    }

}