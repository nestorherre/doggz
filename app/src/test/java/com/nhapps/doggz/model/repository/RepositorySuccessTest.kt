package com.nhapps.doggz.model.repository

import com.nhapps.doggz.model.data.BreedImages
import com.nhapps.doggz.model.data.Breeds
import retrofit2.Response

class RepositorySuccessTest(): RepositoryI {

    private val messagesBreeds = listOf<String>("affenpinscher","african","airedale","akita","appenzeller","australian","basenji","beagle","bluetick","borzoi","bouvier","boxer","brabancon","briard","buhund","bulldog","bullterrier","cairn","cattledog","chihuahua","chow","clumber","cockapoo","collie","coonhound","corgi","cotondetulear","dachshund","dalmatian","dane","deerhound","dhole","dingo","doberman","elkhound","entlebucher","eskimo","finnish","frise","germanshepherd","greyhound","groenendael","havanese","hound","husky","keeshond","kelpie","komondor","kuvasz","labrador","leonberg","lhasa","malamute","malinois","maltese","mastiff","mexicanhairless","mix","mountain","newfoundland","otterhound","ovcharka","papillon","pekinese","pembroke","pinscher","pitbull","pointer","pomeranian","poodle","pug","puggle","pyrenees","redbone","retriever","ridgeback","rottweiler","saluki","samoyed","schipperke","schnauzer","setter","sheepdog","shiba","shihtzu","spaniel","springer","stbernard","terrier","vizsla","waterdog","weimaraner","whippet","wolfhound")
    private val messagesBreedImages = listOf<String>("https://images.dog.ceo/breeds/hound-afghan/n02088094_1003.jpg",
        "https://images.dog.ceo/breeds/hound-afghan/n02088094_1007.jpg",
        "https://images.dog.ceo/breeds/hound-afghan/n02088094_1023.jpg",
        "https://images.dog.ceo/breeds/hound-afghan/n02088094_10263.jpg",
        "https://images.dog.ceo/breeds/hound-afghan/n02088094_10715.jpg",
        "https://images.dog.ceo/breeds/hound-afghan/n02088094_10822.jpg",
        "https://images.dog.ceo/breeds/hound-afghan/n02088094_10832.jpg",
        "https://images.dog.ceo/breeds/hound-afghan/n02088094_10982.jpg",
        "https://images.dog.ceo/breeds/hound-afghan/n02088094_11006.jpg")

    private val status = "success"

    private val responseBreeds = Breeds(
        messagesBreeds, status
    )

    private val responseBreedImages = BreedImages(
        messagesBreedImages, status
    )

    override suspend fun getBreeds(): Response<Breeds> {
       return Response.success(responseBreeds)
    }

    override suspend fun getBreedImages(breedType: String): Response<BreedImages> {
        return Response.success(responseBreedImages)
    }

}