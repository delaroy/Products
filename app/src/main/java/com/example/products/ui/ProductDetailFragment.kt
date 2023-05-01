package com.example.products.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.products.R
import com.example.products.data.network.dto.ProductResponse
import com.example.products.databinding.ProductDetailBinding
import com.google.android.material.transition.MaterialFadeThrough

class ProductDetailFragment : Fragment() {

    private var _binding: ProductDetailBinding? = null

    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enterTransition = MaterialFadeThrough().apply {
            duration = resources.getInteger(R.integer.reply_motion_duration_large).toLong()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = ProductDetailBinding.inflate(inflater, container, false)
        val mResult = arguments?.getParcelable<ProductResponse>("product")

        binding.title.text = mResult?.name
        binding.desc.text = mResult?.description
        Glide.with(requireActivity())
            .load(mResult?.imageLink)
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(binding.productImg)

        binding.toolbar.setNavigationOnClickListener {
            findNavController().navigateUp()
        }

        binding.price.text = "$" + mResult?.price
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}