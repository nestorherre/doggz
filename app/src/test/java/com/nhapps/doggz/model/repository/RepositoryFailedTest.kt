package com.nhapps.doggz.model.repository

import com.nhapps.doggz.model.data.BreedImages
import com.nhapps.doggz.model.data.Breeds
import okhttp3.MediaType
import okhttp3.ResponseBody
import retrofit2.Response

class RepositoryFailedTest(): RepositoryI {
    override suspend fun getBreeds(): Response<Breeds> {
        return Response.error(500, ResponseBody.create(MediaType.parse("text/plain"), "error"))
    }

    override suspend fun getBreedImages(breedType: String): Response<BreedImages> {
        return Response.error(500, ResponseBody.create(MediaType.parse("text/plain"), "error"))
    }
}