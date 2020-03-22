package com.example.pokemon.api.model

class PokemonDataResponse(
    val abilities: List<AbilitiesDataResponse>?,
    val base_experience : Int?,
    val forms: List<FormsDataResponse>?,
    val game_indices: List<GameIndiceDataResponse>?,
    val height : Int?,
    val held_items: List<HeldItemDataResponse>?,
    val id : Int?,
    val is_default : Boolean?,
    val locationAreaEncounters : String?,
    val moves: List<MovesDataResponse>?,
    val name : String?,
    val order : Int?,
    val species: SpeciesDataResponse?,
    val sprites: SpriteDataResponse?,
    val stats: List<StatsDataResponse>?,
    val types: List<TypesDataResponse>?,
    val weight : Int?
)