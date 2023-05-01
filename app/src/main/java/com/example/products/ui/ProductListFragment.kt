package com.example.products.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.products.R
import com.example.products.data.network.dto.ProductResponse
import com.example.products.databinding.ProductListBinding
import com.example.products.ui.adapter.*
import com.example.products.util.CustomProgressDialog
import dagger.hilt.android.AndroidEntryPoint
import java.util.*
import kotlin.collections.ArrayList


@AndroidEntryPoint
class ProductListFragment : Fragment(), ProductDetailClick {

    private var _binding: ProductListBinding? = null
    private val viewModel: ProductFetchViewModel by viewModels()
    private val progressDialog by lazy { CustomProgressDialog(requireActivity()) }
    private val items: MutableList<ListItem> = ArrayList()
    private val productAdapter: ProductAdapter by lazy { ProductAdapter(this) }

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = ProductListBinding.inflate(inflater, container, false)
        viewModel.requestProduct()
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.productRecyclerview.apply {
            adapter = productAdapter
        }

        viewModel.requestProducts.observe(viewLifecycleOwner) { result ->
            result?.getContentIfNotHandled()?.let { value ->
                when {
                    value.isSuccess() -> {
                        value.data?.let {
                            //categorizing the products to there brand name
                            val products: Map<String, List<ProductResponse>?> = toMap(it)

                            for (brand in products.keys) {
                                val header = HeaderItem(brand)
                                items.add(header)
                                for (product in products[brand]!!) {
                                    val item = ProductItem(product)
                                    items.add(item)
                                }
                            }

                            productAdapter.submitList(items as ArrayList<ListItem>)
                        }
                        progressDialog.stop()
                    }

                    value.isLoading() -> {
                        progressDialog.start(title = "products ...")
                    }

                    value.isError() -> {

                        progressDialog.stop()
                        Toast.makeText(requireContext(), value.message, Toast.LENGTH_SHORT).show()
                    }

                    else -> {}
                }
            }
        }
    }

    private fun toMap(products: List<ProductResponse>): Map<String, MutableList<ProductResponse>> {
        val map: MutableMap<String, MutableList<ProductResponse>> = TreeMap()
        for (product in products) {
            var value: MutableList<ProductResponse>? = map.get(product.brand ?: "unknown")
            if (value == null) {
                value = ArrayList()
                map.put(product.brand ?: "unknown", value)
            }
            value.add(product)
        }
        return map
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun clickOnItem(data: ProductItem) {
        val bundle = Bundle()
        bundle.putParcelable("product", data.getProduct())
        findNavController().navigate(R.id.action_ProductList_to_ProductDetail, bundle)
    }
}
