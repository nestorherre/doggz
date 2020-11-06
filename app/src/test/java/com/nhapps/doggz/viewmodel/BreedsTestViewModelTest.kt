package com.nhapps.doggz.viewmodel

import android.view.View
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.nhapps.doggz.model.repository.RepositoryFailedTest
import com.nhapps.doggz.model.repository.RepositorySuccessTest
import com.nhapps.doggz.utils.MainCoroutineRule
import com.nhapps.doggz.utils.getOrAwaitValue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule

@ExperimentalCoroutinesApi
class BreedsTestViewModelTest {

    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()
    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    private lateinit var viewModelSuccess: BreedsViewModel
    private lateinit var viewModelError: BreedsViewModel

    @Before
    fun setup(){
        val repositoryFailed = RepositoryFailedTest()
        val repositorySuccess = RepositorySuccessTest()
        viewModelError = BreedsViewModel(repositoryFailed)
        viewModelSuccess = BreedsViewModel(repositorySuccess)
    }

    @Test
    fun `if the API call failed, errorImage is visible`(){
        viewModelError.getBreedList()
        val observable = viewModelError.errorImage.getOrAwaitValue()
        assertEquals(View.VISIBLE, observable)
    }

    @Test
    fun `if the API call failed, reintentarButton is visible`(){
        viewModelError.getBreedList()
        val observable = viewModelError.reintentarButton.getOrAwaitValue()
        assertEquals(View.VISIBLE, observable)
    }

    @Test
    fun `if the API call failed, loading is invisible`(){
        viewModelError.getBreedList()
        val observable = viewModelError.loading.getOrAwaitValue()
        assertEquals(View.INVISIBLE, observable)
    }

    @Test
    fun `if the API call failed, breedList is empty`(){
        viewModelError.getBreedList()
        val observable = viewModelError.breedList.getOrAwaitValue()
        assertEquals(true, observable.isEmpty())
    }

    @Test
    fun `if the API call failed, breedSearch is invisible`(){
        viewModelError.getBreedList()
        val observable = viewModelError.breedSearch.getOrAwaitValue()
        assertEquals(View.INVISIBLE, observable)
    }

    @Test
    fun `if the API call succeeded, errorImage is invisible`(){
        viewModelSuccess.getBreedList()
        val observable = viewModelSuccess.errorImage.getOrAwaitValue()
        assertEquals(View.INVISIBLE, observable)
    }

    @Test
    fun `if the API call succeeded, reintentarButton is invisible`(){
        viewModelSuccess.getBreedList()
        val observable = viewModelSuccess.reintentarButton.getOrAwaitValue()
        assertEquals(View.INVISIBLE, observable)
    }

    @Test
    fun `if the API call succeeded, loading is invisible`(){
        viewModelSuccess.getBreedList()
        val observable = viewModelSuccess.loading.getOrAwaitValue()
        assertEquals(View.INVISIBLE, observable)
    }

    @Test
    fun `if the API call succeeded, breedList value is not empty`(){
        viewModelSuccess.getBreedList()
        val observable = viewModelSuccess.breedList.getOrAwaitValue()
        assertEquals(false, observable.isEmpty())
    }

    @Test
    fun `if the API call succeeded, breedSearch is visible`(){
        viewModelSuccess.getBreedList()
        val observable = viewModelSuccess.breedSearch.getOrAwaitValue()
        assertEquals(View.VISIBLE, observable)
    }

}