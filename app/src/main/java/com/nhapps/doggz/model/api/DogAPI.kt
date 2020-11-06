package com.nhapps.doggz.model.api

import com.nhapps.doggz.model.data.BreedImages
import com.nhapps.doggz.model.data.Breeds
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface DogAPI {
    @GET("breeds/list")
    suspend fun getBreeds(): Response<Breeds>

    @GET("breed/{breed_type}/images")
    suspend fun getBreedImages(@Path(value = "breed_type", encoded = true) breedType: String): Response<BreedImages>

}