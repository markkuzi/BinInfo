package com.example.bininfo.presentation.fragments.binlist.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.bininfo.databinding.ItemBinBinding
import com.example.bininfo.domain.entities.BinList

class BinListAdapter : ListAdapter<BinList, BinItemListViewHolder>(BinItemDiffCallback()) {

    var onBinItemListClickListener: ((binList: BinList) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BinItemListViewHolder {
        val binding = ItemBinBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return BinItemListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BinItemListViewHolder, position: Int) {
        val bin = getItem(position)

        holder.binding.tvBinId.text = bin.binId
        holder.binding.tvDate.text = bin.date
        holder.binding.tvStatus.text = bin.status.toString()

        holder.binding.root.setOnClickListener {
            onBinItemListClickListener?.invoke(bin)
        }

    }
}