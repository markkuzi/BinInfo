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
import com.example.bininfo.domain.entities.BinInfo
import com.example.bininfo.utils.NetworkResult
import com.example.bininfo.utils.Status
import org.koin.androidx.viewmodel.ext.android.viewModel

class BinInfoFragment : Fragment() {

    private lateinit var status: Status
    private val binInfoViewModel: BinInfoViewModel by viewModel()
    private var binId: String = DEFAULT_BIN_ID

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

        setupStatusSet()
        setupButtons()

    }

    private fun setupButtons() {
        with(binding) {
            btnBack.setOnClickListener {
                findNavController().popBackStack()
            }

            btnDelete.setOnClickListener {
                binInfoViewModel.deleteBinById(binId)
                findNavController().popBackStack()
            }

            btnRetry.setOnClickListener {
                binInfoViewModel.loadNewBin(binId)
                loadBinInfo()
            }
        }
    }

    private fun setupStatusSet() {
        when (status) {
            Status.SUCCESS -> {
                setupSuccessVisibility()
                binInfoViewModel.getBinInfo(binId)
                binInfoViewModel.binInfo.observe(viewLifecycleOwner) {
                    setupBinInfo(it)
                }
            }
            Status.NO_RESULT -> {
                setupErrorVisibility()
                setupMessageTextIfNoResult()
            }
            Status.ERROR -> {
                setupErrorVisibility()
                setupMessageTextIfError()
            }
            Status.NONE -> {
                binInfoViewModel.loadNewBin(binId)
                loadBinInfo()
            }
        }
    }

    private fun loadBinInfo() {
        binInfoViewModel.binNewInfo.observe(viewLifecycleOwner) { result ->
            when (result) {
                is NetworkResult.Success -> {
                    setupSuccessVisibility()
                    setupBinInfo(result.data)
                }
                is NetworkResult.Loading -> {
                    setupLoadingVisibility()
                }
                is NetworkResult.Error -> {
                    when (result.status) {
                        Status.NO_RESULT -> {
                            setupErrorVisibility()
                            setupMessageTextIfNoResult()
                        }
                        Status.ERROR -> {
                            setupErrorVisibility()
                            setupMessageTextIfError()
                        }
                        else -> {}
                    }
                }
            }
        }
    }

    private fun setupMessageTextIfError() {
        binding.tvBinError.text = getString(R.string.bin_request_error)
    }

    private fun setupMessageTextIfNoResult() {
        binding.tvBinError.text = String.format(
            getString(R.string.bin_request_empty),
            binId
        )
    }

    private fun setupErrorVisibility() {
        with(binding) {
            groupBinInfo.visibility = View.GONE
            groupButton.visibility = View.VISIBLE
            progressBar.visibility = View.GONE
            groupError.visibility = View.VISIBLE
        }
    }

    private fun setupLoadingVisibility() {
        with(binding) {
            groupButton.visibility = View.GONE
            groupBinInfo.visibility = View.GONE
            groupError.visibility = View.GONE
            progressBar.visibility = View.VISIBLE
        }
    }

    private fun setupSuccessVisibility() {
        with(binding) {
            groupBinInfo.visibility = View.VISIBLE
            groupButton.visibility = View.VISIBLE
            progressBar.visibility = View.GONE
            groupError.visibility = View.GONE
        }
    }

    private fun setupBinInfo(it: BinInfo?) {
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
                getString(R.string.bin_info_country_coordinate),
                it?.countryLatitude, it?.countryLongitude
            )
            setupBinNumberLuhn(it?.numberLuhn)
            setupBinType(it?.type)
            setupBinPrepaid(it?.prepaid)
        }
    }

    private fun setupBinNumberLuhn(numberLuhn: Boolean?) {
        with(binding) {
            when (numberLuhn) {
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
        }
    }

    private fun setupBinType(type: String?) {
        with(binding) {
            when (type) {
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
        }
    }

    private fun setupBinPrepaid(prepaid: Boolean?) {
        with(binding) {
            when (prepaid) {
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

    private fun parseArgs() {
        requireArguments().getString(KEY_NEW_BIN)?.let {
            binId = it
        }
        requireArguments().getParcelable<Status>(KEY_LOAD_BIN)?.let {
            status = it
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