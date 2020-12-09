package de.rahulthakur4.kaomoji.domain.commands

import de.rahulthakur4.kaomoji.domain.datasource.KaomojiProvider
import de.rahulthakur4.kaomoji.domain.model.Kaomoji

class RequestKaomojiCommand(
        private val id: Long,
        private val kaomojiProvider: KaomojiProvider = KaomojiProvider()) :
        Command<Kaomoji> {

    override fun execute() = kaomojiProvider.requestItemKaomoji(id)
}