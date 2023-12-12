package com.example.data.mappers

import com.example.data.model.api.response.Photo
import com.example.data.model.api.response.RandomPhotosResponse
import com.example.data.model.api.response.Src
import com.example.domain.model.api.reponse.PhotoDTO
import com.example.domain.model.api.reponse.RandomPhotosResponseDTO
import com.example.domain.model.api.reponse.SrcDTO

fun Src.toSrcDTO(): SrcDTO {
    return SrcDTO(
        medium = medium,
        portrait = portrait,
        tiny = tiny
    )
}

fun Photo.toPhotoDTO(): PhotoDTO {
    return PhotoDTO(
        alt = alt,
        id = id,
        photographer = photographer,
        photographerUrl = photographerUrl,
        src = src?.toSrcDTO(),
        url = url
    )
}

fun RandomPhotosResponse.toRandomPhotoResponseDTO(): RandomPhotosResponseDTO {
    val photoDTOList = photos?.map { it.toPhotoDTO() }
    return RandomPhotosResponseDTO(photoDTOList)
}