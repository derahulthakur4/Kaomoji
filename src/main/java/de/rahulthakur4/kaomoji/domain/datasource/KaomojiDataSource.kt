package de.rahulthakur4.kaomoji.domain.datasource

import de.rahulthakur4.kaomoji.domain.model.Kaomoji
import de.rahulthakur4.kaomoji.domain.model.KaomojiList

interface KaomojiDataSource {

    fun requestAllItemKaomoji(offset: Int): KaomojiList?

    fun requestFavoriteItemKaomoji(): KaomojiList?

    fun requestKaomojiByType(type: String): KaomojiList?

    fun requestItemKaomoji(id: Long): Kaomoji?
}