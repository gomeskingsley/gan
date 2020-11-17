package com.sample.gan.models.response

data class CharacterItemResponse(
    val char_id: Int,
    val name: String,
    val birthday: String,
    val occupation: List<String>,
    val img: String,
    val status: String,
    val nickname: String,
    val appearance: List<Int>? = emptyList(),
    val portrayed: String,
    val category: String,
    val better_call_saul_appearance: List<Int>
)