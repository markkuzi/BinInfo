package com.example.bininfo.presentation.fragments.binlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.bininfo.databinding.FragmentBinListBinding
import com.example.bininfo.presentation.fragments.binlist.adapter.BinListAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlin.random.Random

class BinListFragment : Fragment() {

    private lateinit var binListAdapter: BinListAdapter
    private val binListViewModel: BinListViewModel by viewModel()

    private var _binding: FragmentBinListBinding? = null
    private val binding: FragmentBinListBinding
        get() = _binding ?: throw RuntimeException("FragmentBinListBinding is null")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBinListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()


        binding.buttonAddShopItem.setOnClickListener {
            binListViewModel.loadNewBin(Random.nextInt(4000, 40000000).toString())
        }


    }

    private fun setupRecyclerView() {
        binListAdapter = BinListAdapter()
        binding.rvBinList.adapter = binListAdapter
        binListViewModel.binHistoryList.observe(viewLifecycleOwner) {
            binListAdapter.submitList(it)
        }

        setupClickListener()
        setupSwipeListener()
    }

    private fun setupClickListener() {
        binListAdapter.onBinItemListClickListener = {
            Toast.makeText(requireContext(), it.status.toString(), Toast.LENGTH_LONG).show()
        }
    }

    private fun setupSwipeListener() {
        val callback = object : ItemTouchHelper.SimpleCallback(
            0,
            ItemTouchHelper.LEFT
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val item = binListAdapter.currentList[viewHolder.adapterPosition]
                binListViewModel.deleteBinById(item.binId)
            }

        }
        val itemTouchHelper = ItemTouchHelper(callback)
        itemTouchHelper.attachToRecyclerView(binding.rvBinList)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}