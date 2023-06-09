package com.example.bininfo.presentation.fragments.binlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.bininfo.R
import com.example.bininfo.databinding.FragmentAddNewBinBinding
import com.example.bininfo.presentation.fragments.bininfo.BinInfoFragment
import com.example.bininfo.utils.Status
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class AddNewBinFragment : BottomSheetDialogFragment() {

    private var _binding: FragmentAddNewBinBinding? = null
    private val binding: FragmentAddNewBinBinding
        get() = _binding ?: throw RuntimeException(BINDING_ERROR)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddNewBinBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupButtons()
    }

    private fun setupButtons() {
        binding.submitCheckout.setOnClickListener {
            val binId = binding.etBinInput.text.toString()
            launchBinInfoFragment(binId, Status.NONE)
            dismiss()
        }
    }

    private fun launchBinInfoFragment(binId: String, status: Status) {
        val args = Bundle().apply {
            putString(BinInfoFragment.KEY_NEW_BIN, binId)
            putParcelable(BinInfoFragment.KEY_LOAD_BIN, status)
        }
        findNavController().navigate(R.id.action_binListFragment_to_binInfoFragment, args)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        private const val BINDING_ERROR = "FragmentAddNewBinBinding is null"
    }
}