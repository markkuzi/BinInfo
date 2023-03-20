package com.example.bininfo.presentation.fragments.binlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.bininfo.R
import com.example.bininfo.databinding.FragmentBinListBinding
import com.example.bininfo.presentation.fragments.bininfo.BinInfoFragment
import com.example.bininfo.presentation.fragments.binlist.adapter.BinListAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlin.random.Random

class BinListFragment : Fragment() {

    private lateinit var binListAdapter: BinListAdapter
    private val binListViewModel: BinListViewModel by viewModel()

    private var _binding: FragmentBinListBinding? = null
    private val binding: FragmentBinListBinding
        get() = _binding ?: throw RuntimeException(BINDING_ERROR)

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
            val binId = Random.nextInt(4000, 40000000).toString()
            binListViewModel.loadNewBin(binId)
            launchBinInfoFragment(binId)
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
            launchBinInfoFragment(it.binId)
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

    private fun launchBinInfoFragment(binId: String) {
        val args = Bundle().apply {
            putString(BinInfoFragment.KEY_NEW_BIN, binId)
        }
        findNavController().navigate(R.id.action_binListFragment_to_binInfoFragment, args)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        private const val BINDING_ERROR = "FragmentBinListBinding is null"
    }

}