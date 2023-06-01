package com.example.mapapp.coordinates

import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.viewModels
import com.example.mapapp.common.listeners.DialogDismissListener
import com.example.mapapp.common.flow.observe
import com.example.mapapp.common.fragments.showToast
import com.example.mapapp.databinding.FragmentCoordinatesBinding
import dagger.hilt.android.AndroidEntryPoint

private const val TAG = "CoordinatesDialog"

@AndroidEntryPoint
class CoordinatesDialogFragment : DialogFragment() {

    private val viewModel: CoordinatesViewModel by viewModels()

    private var _binding: FragmentCoordinatesBinding? = null
    private val binding get() = _binding!!

    private val latitudeArgs get() = arguments?.getString(ARG_LATITUDE)
    private val longitudeArgs get() = arguments?.getString(ARG_LONGITUDE)

    private var dialogDismissListener: DialogDismissListener? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        val view = FragmentCoordinatesBinding.inflate(layoutInflater, container, false)
        _binding = view
        return view.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupCoordinatesFields()
        observeLatitudeText()
        observeLongitudeText()
        setupSaveButton()

        viewModel.toastMessage.observe(viewLifecycleOwner, observer = ::showToast)
        viewModel.dismissDialog.observe(viewLifecycleOwner) { if (it) dismiss() }

        viewModel.loadOnLaunch(latitudeArgs.toString(), longitudeArgs.toString())

        setupDialogDismissListener()
    }

    private fun setupCoordinatesFields() = with(binding) {
        latitude.setText(latitudeArgs)
        longitude.setText(longitudeArgs)
    }

    private fun observeLatitudeText() = with(binding) {
        latitude.doAfterTextChanged {
            viewModel.latitudeDoAfterTextChanged(it.toString())
        }
    }

    private fun observeLongitudeText() = with(binding) {
        longitude.doAfterTextChanged {
            viewModel.longitudeDoAfterTextChanged(it.toString())
        }
    }

    private fun setupSaveButton() {
        binding.save.setOnClickListener {
            viewModel.onSaveButtonClicked()
        }
    }

    private fun setupDialogDismissListener() {
        val listener =
            parentFragmentManager.primaryNavigationFragment as? DialogDismissListener
        this.dialogDismissListener = listener
    }

    override fun onResume() {
        super.onResume()
        dialog?.window?.apply {
            val params = attributes
            params.width = ViewGroup.LayoutParams.MATCH_PARENT
            params.height = ViewGroup.LayoutParams.WRAP_CONTENT
            attributes = params
        }
    }

    override fun onDismiss(dialog: DialogInterface) {
        super.onDismiss(dialog)
        dialogDismissListener?.onDialogDismissed()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    fun show(fragmentManager: FragmentManager) = show(fragmentManager, TAG)

    companion object {
        const val ARG_LATITUDE = "latitude"
        const val ARG_LONGITUDE = "longitude"
    }
}