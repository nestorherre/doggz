package com.nhapps.doggz.model.repository

import com.nhapps.doggz.model.api.RetrofitInstance
import com.nhapps.doggz.model.data.BreedImages
import com.nhapps.doggz.model.data.Breeds
import retrofit2.Response

class Repository: RepositoryI {

    override suspend fun getBreeds(): Response<Breeds> {
        return RetrofitInstance.api.getBreeds()
    }

    override suspend fun getBreedImages(breedType: String): Response<BreedImages> {
        return RetrofitInstance.api.getBreedImages(breedType)
    }

}