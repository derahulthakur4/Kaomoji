package de.rahulthakur4.kaomoji.ui.fragments

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import de.rahulthakur4.kaomoji.R
import de.rahulthakur4.kaomoji.domain.model.Kaomoji
import de.rahulthakur4.kaomoji.extensions.ctx
import de.rahulthakur4.kaomoji.ui.components.SwitchImage
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.list_item_kaomoji.*

class KaomojisAdapter(private val kamojisList: ArrayList<Kaomoji>,
                      private val itemClick: (Kaomoji) -> Unit,
                      private val itemFavoriteClick: (SwitchImage, Kaomoji) -> Unit,
                      private val isFavoriteEnabled: Boolean) :
        RecyclerView.Adapter<KaomojisAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.ctx).inflate(R.layout.list_item_kaomoji, parent, false)
        return ViewHolder(view, itemClick, itemFavoriteClick, isFavoriteEnabled)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindKaomoji(kamojisList[position])
    }

    override fun getItemCount() = kamojisList.size

    fun addItems(listToAdd: ArrayList<Kaomoji>) {
        for (kaomoji in listToAdd) {
            kamojisList.add(kaomoji)
        }
        notifyDataSetChanged()
    }

    class ViewHolder(override val containerView: View,
                     private val itemClick: (Kaomoji) -> Unit,
                     private val itemFavoriteClick: (SwitchImage, Kaomoji) -> Unit,
                     private val isFavoriteEnabled: Boolean)
        : RecyclerView.ViewHolder(containerView), LayoutContainer {

        fun bindKaomoji(kaomoji: Kaomoji) {
            with(kaomoji) {
                value_tv.text = text
                if (!isFavoriteEnabled) {
                    favorite_iv.visibility = View.GONE
                } else {
                    favorite_iv.initState(kaomoji.isFavorite)
                    favorite_iv.checkState()
                    favorite_iv.setOnClickListener { itemFavoriteClick(favorite_iv, this) }
                }
                itemView.setOnClickListener { itemClick(this) }
            }
        }
    }
}