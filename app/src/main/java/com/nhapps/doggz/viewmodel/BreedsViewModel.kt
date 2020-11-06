package com.nhapps.doggz.viewmodel

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nhapps.doggz.model.repository.RepositoryI
import kotlinx.coroutines.launch

class BreedsViewModel(private val repository: RepositoryI): ViewModel()  {
    private val _breedList: MutableLiveData<List<String>> = MutableLiveData()
    val breedList: LiveData<List<String>> = _breedList

    private val _loading: MutableLiveData<Int> = MutableLiveData()
    val loading: LiveData<Int> = _loading

    private val _reintentarButton: MutableLiveData<Int> = MutableLiveData()
    val reintentarButton: LiveData<Int> = _reintentarButton

    private val _errorImage: MutableLiveData<Int> = MutableLiveData()
    val errorImage: LiveData<Int> = _errorImage

    private val _breedSearch: MutableLiveData<Int> = MutableLiveData()
    val breedSearch: LiveData<Int> = _breedSearch

    fun getBreedList(){
        _loading.value = View.VISIBLE
        _breedList.value = listOf()
        viewModelScope.launch {
            try {
                val response = repository.getBreeds()
                if (response.isSuccessful){
                    handleSuccess()
                    _breedList.value = response.body()!!.message
                } else {
                    handleError()
                }
            } catch (error: Throwable){
                handleError()
            }

        }
    }

    private fun handleSuccess() {
        _loading.value = View.INVISIBLE
        _reintentarButton.value = View.INVISIBLE
        _errorImage.value = View.INVISIBLE
        _breedSearch.value = View.VISIBLE
    }

    private fun handleError() {
        _loading.value = View.INVISIBLE
        _reintentarButton.value = View.VISIBLE
        _errorImage.value = View.VISIBLE
        _breedSearch.value = View.INVISIBLE
    }

}