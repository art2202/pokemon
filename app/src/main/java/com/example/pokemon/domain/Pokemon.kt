package com.example.pokemon.domain

import com.example.pokemon.api.model.*

class Pokemon(
    val habilidades: List<AbilitiesDataResponse>?,
    val experienciaBase : Int?,
    val formas: List<FormsDataResponse>?,
    val gameIndicesDataResponse: List<GameIndiceDataResponse>?,
    val altura : Int?,
    val heldItemsDataResponse: List<HeldItemDataResponse>?,
    val id : Int?,
    val isDefault : Boolean?,
    val movesDataResponse: List<MovesDataResponse>?,
    val nome : String?,
    val order : Int?,
    val speciesDataResponse: SpeciesDataResponse?,
    val statsDataResponse: List<StatsDataResponse>?,
    val typesDataResponse: List<TypesDataResponse>?,
    val peso : Int?
) {
}