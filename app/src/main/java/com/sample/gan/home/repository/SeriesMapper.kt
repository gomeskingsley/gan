package com.sample.gan.home.repository

import com.sample.gan.models.response.CharacterItem
import com.sample.gan.models.response.CharacterItemResponse
import javax.inject.Inject

class SeriesMapper @Inject constructor() {

    fun from(response: List<CharacterItemResponse>): List<CharacterItem> =
        response.map {
            CharacterItem(
                charId = it.char_id,
                name = it.name,
                birthday = it.birthday,
                occupation = it.occupation,
                img = it.img,
                status = it.status,
                nickname = it.nickname,
                appearance = it.appearance ?: emptyList(),
                portrayed = it.portrayed,
                category = it.category,
                betterCallSaulAppearance = it.better_call_saul_appearance,
            )
        }
}