package com.example.recognizer.api

data class PlantDTO(
    val image_url: String,
    val common_name: String,
    val scientific_name: String,
    val search_name: String
){
    companion object {
        fun toPlantDto(plant: Plant): PlantDTO {
            return PlantDTO(
                plant.data.image_url,
                plant.data.common_name,
                plant.data.scientific_name,
                plant.data.slug
            )
        }

        fun toPlantDto(plants: List<Plant>?): List<PlantDTO> {
            return plants?.map { toPlantDto(it) } ?: emptyList()
        }
    }
}