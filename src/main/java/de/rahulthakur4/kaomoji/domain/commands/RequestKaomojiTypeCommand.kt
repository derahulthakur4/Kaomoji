package de.rahulthakur4.kaomoji.domain.commands

import de.rahulthakur4.kaomoji.domain.datasource.KaomojiProvider
import de.rahulthakur4.kaomoji.domain.model.KaomojiList

class RequestKaomojiTypeCommand(
        private val type: String,
        private val kaomojiProvider: KaomojiProvider = KaomojiProvider()) :
        Command<KaomojiList> {

    override fun execute() = kaomojiProvider.requestKaomojiByType(type)
}