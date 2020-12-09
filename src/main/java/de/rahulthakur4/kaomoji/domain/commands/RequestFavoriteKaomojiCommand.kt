package de.rahulthakur4.kaomoji.domain.commands

import de.rahulthakur4.kaomoji.domain.datasource.KaomojiProvider
import de.rahulthakur4.kaomoji.domain.model.KaomojiList

class RequestFavoriteKaomojiCommand(
        private val kaomojiProvider: KaomojiProvider = KaomojiProvider()) :
        Command<KaomojiList> {

    companion object {
        const val LIST_DESCRIPTION = "Contains your favorite kaomojis"
        const val LIST_TYPE = "Favorites"
    }

    override fun execute() = kaomojiProvider.requestFavoriteItemKaomoji().apply {
        type = LIST_TYPE
        description = LIST_DESCRIPTION
    }
}