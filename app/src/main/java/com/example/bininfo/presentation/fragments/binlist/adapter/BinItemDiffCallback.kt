package com.example.bininfo.presentation.fragments.binlist.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.bininfo.domain.entities.BinList

class BinItemDiffCallback: DiffUtil.ItemCallback<BinList>() {
    override fun areItemsTheSame(oldItem: BinList, newItem: BinList): Boolean {
        return oldItem.binId == newItem.binId
    }

    override fun areContentsTheSame(oldItem: BinList, newItem: BinList): Boolean {
        return oldItem == newItem
    }
}