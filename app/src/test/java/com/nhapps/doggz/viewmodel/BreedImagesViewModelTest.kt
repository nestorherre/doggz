package com.nhapps.doggz.viewmodel

import android.view.View
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.nhapps.doggz.model.repository.RepositoryFailedTest
import com.nhapps.doggz.model.repository.RepositorySuccessTest
import com.nhapps.doggz.utils.MainCoroutineRule
import com.nhapps.doggz.utils.getOrAwaitValue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule

@ExperimentalCoroutinesApi
class BreedImagesViewModelTest {

    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()
    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    private lateinit var viewModelSuccess: BreedImagesViewModel
    private lateinit var viewModelError: BreedImagesViewModel
    private val breedType = "hound"

    @Before
    fun setup(){
        val repositoryFailed = RepositoryFailedTest()
        val repositorySuccess = RepositorySuccessTest()
        viewModelError = BreedImagesViewModel(repositoryFailed)
        viewModelSuccess = BreedImagesViewModel(repositorySuccess)
    }

    @Test
    fun `if the API call failed, errorImage is visible`(){
        viewModelError.getBreedImages(breedType)
        val observable = viewModelError.errorImage.getOrAwaitValue()
        Assert.assertEquals(View.VISIBLE, observable)
    }

    @Test
    fun `if the API call failed, reintentarButton is visible`(){
        viewModelError.getBreedImages(breedType)
        val observable = viewModelError.reintentarButton.getOrAwaitValue()
        Assert.assertEquals(View.VISIBLE, observable)
    }

    @Test
    fun `if the API call failed, loading is invisible`(){
        viewModelError.getBreedImages(breedType)
        val observable = viewModelError.loading.getOrAwaitValue()
        Assert.assertEquals(View.INVISIBLE, observable)
    }

    @Test
    fun `if the API call failed, imagesList is empty`(){
        viewModelError.getBreedImages(breedType)
        val observable = viewModelError.imagesList.getOrAwaitValue()
        Assert.assertEquals(true, observable.isEmpty())
    }

    @Test
    fun `if the API call succeeded, errorImage is invisible`(){

        viewModelSuccess.getBreedImages(breedType)
        val observable = viewModelSuccess.errorImage.getOrAwaitValue()
        Assert.assertEquals(View.INVISIBLE, observable)
    }

    @Test
    fun `if the API call succeeded, reintentarButton is invisible`(){
        viewModelSuccess.getBreedImages(breedType)
        val observable = viewModelSuccess.reintentarButton.getOrAwaitValue()
        Assert.assertEquals(View.INVISIBLE, observable)
    }

    @Test
    fun `if the API call succeeded, loading is invisible`(){
        viewModelSuccess.getBreedImages(breedType)
        val observable = viewModelSuccess.loading.getOrAwaitValue()
        Assert.assertEquals(View.INVISIBLE, observable)
    }

    @Test
    fun `if the API call succeeded, imagesList value is not empty`(){
        viewModelSuccess.getBreedImages(breedType)
        val observable = viewModelSuccess.imagesList.getOrAwaitValue()
        Assert.assertEquals(false, observable.isEmpty())
    }

}