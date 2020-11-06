package com.nhapps.doggz.model.repository

import com.nhapps.doggz.model.data.BreedImages
import com.nhapps.doggz.model.data.Breeds
import retrofit2.Response

interface RepositoryI {

    suspend fun getBreeds(): Response<Breeds>

    suspend fun getBreedImages(breedType: String): Response<BreedImages>
}