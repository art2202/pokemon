package com.example.pokemon.api.model

class MovesDataResponse(val move : MoveDataResponse,
                        val version_group_details: List<VersionGroupDetailsDataResponse>) {
}