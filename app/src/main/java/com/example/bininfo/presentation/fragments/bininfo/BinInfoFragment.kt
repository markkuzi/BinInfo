package com.example.bininfo.presentation.fragments.bininfo

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.bininfo.R
import com.example.bininfo.databinding.FragmentBinInfoBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class BinInfoFragment : Fragment() {

    private val binInfoViewModel: BinInfoViewModel by viewModel()
    private var binId: String = DEFAULT_BIN_ID
    private var loadStatus = false

    private var _binding: FragmentBinInfoBinding? = null
    private val binding: FragmentBinInfoBinding
        get() = _binding ?: throw RuntimeException(BINDING_ERROR)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        parseArgs()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBinInfoBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (loadStatus) {
            binInfoViewModel.loadNewBin(binId)
        }

        setupBinInfo()
        setupPending()

        binding.btnBack.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.btnDelete.setOnClickListener {
            binInfoViewModel.deleteBinById(binId)
            findNavController().popBackStack()
        }
    }

    private fun setupPending() {
        binInfoViewModel.pendingStatus.observe(viewLifecycleOwner) {
            if (!it) {
                binding.container.visibility = View.GONE
                binding.progressBar.visibility = View.VISIBLE
            } else {
                binding.container.visibility = View.VISIBLE
                binding.progressBar.visibility = View.GONE
            }
        }
    }

    private fun setupBinInfo() {
        binInfoViewModel.getBinInfo(binId).observe(viewLifecycleOwner) {
            with(binding) {
                tvBinId.text = it?.binId
                tvBinScheme.text = it?.scheme
                tvBinBrand.text = it?.brand
                tvBinCardLength.text = it?.numberLength
                tvBinCardCountryImage.text = it?.countryEmoji
                tvBinCardCountryName.text = it?.countryName
                tvBinCardBankName.text = it?.bankName
                tvBinCardBankAddress.text = it?.bankCity
                tvBinCardBankSite.text = it?.bankUrl
                tvBinCardBankPhone.text = it?.bankPhone
                binding.tvBinCardCountryCoordinate.text = String.format(
                    requireContext().resources.getString(R.string.bin_info_country_coordinate),
                    it?.countryLatitude, it?.countryLongitude
                )


                when (it?.nuberLuhn) {
                    true -> {
                        tvBinCardLuhnYes.setTextColor(Color.WHITE)
                        tvBinCardLuhnNo.setTextColor(Color.GRAY)
                    }
                    false -> {
                        tvBinCardLuhnYes.setTextColor(Color.GRAY)
                        tvBinCardLuhnNo.setTextColor(Color.WHITE)
                    }
                    null -> {
                        tvBinCardLuhnYes.setTextColor(Color.GRAY)
                        tvBinCardLuhnNo.setTextColor(Color.GRAY)
                    }
                }

                when (it?.type) {
                    DEBIT_CARD -> {
                        tvBinCardTypeDebit.setTextColor(Color.WHITE)
                        tvBinCardTypeCredit.setTextColor(Color.GRAY)
                    }
                    CREDIT_CARD -> {
                        tvBinCardTypeDebit.setTextColor(Color.GRAY)
                        tvBinCardTypeCredit.setTextColor(Color.WHITE)
                    }
                    null -> {
                        tvBinCardTypeDebit.setTextColor(Color.GRAY)
                        tvBinCardTypeCredit.setTextColor(Color.GRAY)
                    }
                }

                when (it?.prepaid) {
                    true -> {
                        tvBinCardPrepaidYes.setTextColor(Color.WHITE)
                        tvBinCardPrepaidNo.setTextColor(Color.GRAY)
                    }
                    false -> {
                        tvBinCardPrepaidYes.setTextColor(Color.GRAY)
                        tvBinCardPrepaidNo.setTextColor(Color.WHITE)
                    }
                    null -> {
                        tvBinCardPrepaidYes.setTextColor(Color.GRAY)
                        tvBinCardPrepaidNo.setTextColor(Color.GRAY)
                    }
                }

            }

        }
    }

    private fun parseArgs() {
        requireArguments().getString(KEY_NEW_BIN)?.let {
            binId = it
        }
        requireArguments().getBoolean(KEY_LOAD_BIN).let {
            loadStatus = it
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        private const val CREDIT_CARD = "credit"
        private const val DEBIT_CARD = "debit"
        private const val BINDING_ERROR = "FragmentBinInfoBinding is null"
        private const val DEFAULT_BIN_ID = ""
        const val KEY_NEW_BIN = "new_bin"
        const val KEY_LOAD_BIN = "load_bin"
    }


}