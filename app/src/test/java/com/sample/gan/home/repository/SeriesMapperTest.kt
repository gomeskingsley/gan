package com.sample.gan.home.repository

import com.sample.gan.models.response.CharacterItemResponse
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class SeriesMapperTest {

    private val subject = SeriesMapper()

    private val charId = 1
    private val name = "name"
    private val birthday = "birthday"
    private val occupation = emptyList<String>()
    private val img = "img"
    private val status = "status"
    private val nickname = "nickname"
    private val portrayed = "portrayed"
    private val category = "category"
    private val betterCallSaulAppearance = emptyList<Int>()

    @Test
    fun `GIVEN  null appearances  from maps response into CharacterItem`() {
        val appearance = null

        val response = CharacterItemResponse(
            char_id = charId,
            name = name,
            birthday = birthday,
            occupation = occupation,
            img = img,
            status = status,
            nickname = nickname,
            appearance = appearance,
            portrayed = portrayed,
            category = category,
            better_call_saul_appearance = betterCallSaulAppearance,
        )
        val items = subject.from(listOf(response))

        assertThat(items.size).isEqualTo(1)
        with(items.first()) {
            assertThat(this.charId).isEqualTo(charId)
            assertThat(this.name).isEqualTo(name)
            assertThat(this.birthday).isEqualTo(birthday)
            assertThat(this.occupation).isEqualTo(occupation)
            assertThat(this.img).isEqualTo(img)
            assertThat(this.status).isEqualTo(status)
            assertThat(this.nickname).isEqualTo(nickname)
            assertThat(this.appearance).isEqualTo(emptyList<Int>())
            assertThat(this.portrayed).isEqualTo(portrayed)
        }
    }

    @Test
    fun `GIVEN not null appearances from maps response into CharacterItem`() {
        val appearance = listOf(1)

        val response = CharacterItemResponse(
            char_id = charId,
            name = name,
            birthday = birthday,
            occupation = occupation,
            img = img,
            status = status,
            nickname = nickname,
            appearance = appearance,
            portrayed = portrayed,
            category = category,
            better_call_saul_appearance = betterCallSaulAppearance,
        )
        val items = subject.from(listOf(response))

        assertThat(items.size).isEqualTo(1)
        with(items.first()) {
            assertThat(this.charId).isEqualTo(charId)
            assertThat(this.name).isEqualTo(name)
            assertThat(this.birthday).isEqualTo(birthday)
            assertThat(this.occupation).isEqualTo(occupation)
            assertThat(this.img).isEqualTo(img)
            assertThat(this.status).isEqualTo(status)
            assertThat(this.nickname).isEqualTo(nickname)
            assertThat(this.appearance).isEqualTo(appearance)
            assertThat(this.portrayed).isEqualTo(portrayed)
        }
    }
}