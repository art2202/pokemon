package com.example.pokemon.api.model

class PokemonDataResponse(
    val abilitiesDataResponse: List<AbilitiesDataResponse>?,
    val baseExperience : Int?,
    val formsDataResponse: List<FormsDataResponse>?,
    val gameIndicesDataResponse: List<GameIndiceDataResponse>?,
    val height : Int?,
    val heldItemsDataResponse: List<HeldItemDataResponse>?,
    val id : Int?,
    val isDefault : Boolean?,
    val locationAreaEncounters : String?,
    val movesDataResponse: List<MovesDataResponse>?,
    val name : String?,
    val order : Int?,
    val speciesDataResponse: SpeciesDataResponse?,
    val spriteDataResponse: SpriteDataResponse?,
    val statsDataResponse: List<StatsDataResponse>?,
    val typesDataResponse: List<TypesDataResponse>?,
    val weight : Int?
)