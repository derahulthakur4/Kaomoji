package de.rahulthakur4.kaomoji.domain.commands

import de.rahulthakur4.kaomoji.domain.datasource.KaomojiProvider
import de.rahulthakur4.kaomoji.domain.model.KaomojiList

class RequestAllKaomojiCommand(
        private val offset: Int,
        private val kaomojiProvider: KaomojiProvider = KaomojiProvider()) :
        Command<KaomojiList> {

    companion object {
        const val LIST_DESCRIPTION = "Contains all available kaomojis"
        const val LIST_TYPE = "All kaomojies"
        const val LIMIT_COUNT = 100
    }

    override fun execute() = kaomojiProvider.requestAllItemKaomoji(offset).apply {
        type = LIST_TYPE
        description = LIST_DESCRIPTION
    }
}