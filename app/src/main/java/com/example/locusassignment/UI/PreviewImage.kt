package com.example.locusassignment.UI

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.DialogFragment
import com.example.locusassignment.databinding.ImagePreviewBinding

class PreviewImage : DialogFragment() {
    lateinit var binding: ImagePreviewBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=  ImagePreviewBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.MATCH_PARENT
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView()
        setupClickListeners(view)
    }

    private fun setupView() {
         binding.iv.setImageURI(Uri.parse(arguments?.get(IMAGE_URI).toString()))
    }

    private fun setupClickListeners(view: View) {
        binding.ivRemoved.setOnClickListener {
            dismiss()
        }
    }

    companion object{

        fun newInstance(uri: String): PreviewImage {
            val args = Bundle()
            args.putString(IMAGE_URI,uri)
            val fragment = PreviewImage()
            fragment.arguments = args
            return fragment
        }

        val TAG: String="PreviewImage"
        private const val IMAGE_URI = "image_uri"
    }

}