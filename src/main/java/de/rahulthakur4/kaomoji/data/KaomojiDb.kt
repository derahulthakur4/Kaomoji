package de.rahulthakur4.kaomoji.data

import de.rahulthakur4.kaomoji.domain.commands.RequestAllKaomojiCommand
import de.rahulthakur4.kaomoji.domain.datasource.KaomojiDataSource
import de.rahulthakur4.kaomoji.domain.model.Kaomoji
import de.rahulthakur4.kaomoji.domain.model.KaomojiList
import de.rahulthakur4.kaomoji.extensions.byId
import de.rahulthakur4.kaomoji.extensions.parseList
import de.rahulthakur4.kaomoji.extensions.parseOpt
import de.rahulthakur4.kaomoji.extensions.toLong
import org.jetbrains.anko.db.select
import org.jetbrains.anko.db.update

class KaomojiDb(private val kamomojiDbHelper: KaomojiDbHelper = KaomojiDbHelper.instance,
                private val dataMapper: DbDataMapper = DbDataMapper()) : KaomojiDataSource {

    override fun requestAllItemKaomoji(offset: Int): KaomojiList? = kamomojiDbHelper.use {
        val itemKaomoji = select(ItemKaomojiTable.NAME)
                .orderBy(ItemKaomojiTable.TYPE_ID)
                .limit(offset, RequestAllKaomojiCommand.LIMIT_COUNT)
                .parseList { ItemKaomoji(HashMap(it)) }
        val blankKaomojis = TypeKaomoji(0, "", "", itemKaomoji)
        blankKaomojis.let { dataMapper.convertToDomain(it) }
    }

    override fun requestFavoriteItemKaomoji(): KaomojiList? = kamomojiDbHelper.use {
        val isFavorite = true.toLong().toString()
        val itemKaomoji = select(ItemKaomojiTable.NAME)
                .whereSimple("${ItemKaomojiTable.IS_FAVORITE} = ?", isFavorite)
                .parseList { ItemKaomoji(HashMap(it)) }

        val blankKaomojis = TypeKaomoji(0, "", "", itemKaomoji)
        blankKaomojis.let { dataMapper.convertToDomain(it) }
    }

    override fun requestKaomojiByType(type: String): KaomojiList? = kamomojiDbHelper.use {
        val typeId = dataMapper.convertTypeToId(type)
        val itemKaomoji = select(ItemKaomojiTable.NAME)
                .whereSimple("${ItemKaomojiTable.TYPE_ID} = ?", typeId)
                .parseList { ItemKaomoji(HashMap(it)) }

        val typeKaomojis = select(TypeKaomojiTable.NAME)
                .whereSimple("${TypeKaomojiTable.ID} = ?", typeId)
                .parseOpt { TypeKaomoji(HashMap(it), itemKaomoji) }
        typeKaomojis?.let { dataMapper.convertToDomain(it) }
    }

    override fun requestItemKaomoji(id: Long): Kaomoji? = kamomojiDbHelper.use {
        val kaomoji = select(ItemKaomojiTable.NAME).byId(id).parseOpt { ItemKaomoji(HashMap(it)) }
        kaomoji?.let { dataMapper.convertItemToDomain(it) }
    }

    fun updateKaomoji(kaomoji: Kaomoji) = kamomojiDbHelper.use {
        update(ItemKaomojiTable.NAME, ItemKaomojiTable.IS_FAVORITE to kaomoji.isFavorite.toLong())
                .whereSimple("${ItemKaomojiTable.ID} = ?", kaomoji.id.toString())
                .exec()
    }
}