package com.example.e_commerce.view.main

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewTreeLifecycleOwner
import androidx.navigation.fragment.findNavController
import com.example.e_commerce.R
import com.example.e_commerce.databinding.FragmentProductsBinding
import com.example.e_commerce.model.products.Product
import com.example.e_commerce.repostries.SHARED_PREF_FILE
import com.example.e_commerce.repostries.TOKEN_KEY
import com.example.e_commerce.view.adapter.ProductRecyclerViewAdapter


class ProductsFragment : Fragment() {
    private val productViewMode:ProductsViewModel by activityViewModels()
    private lateinit var sharedPref:SharedPreferences
    private lateinit var sharedPrefEditor: SharedPreferences.Editor

    private lateinit var logoutItem:MenuItem
    private lateinit var profileItem:MenuItem
    private var allProducts = listOf<Product>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ////////this line below is to make the option view visible ////
        setHasOptionsMenu(true)
        ///////////////////////////////////////////////////////////
        sharedPref = requireActivity().getSharedPreferences(SHARED_PREF_FILE, Context.MODE_PRIVATE)
        sharedPrefEditor = sharedPref.edit()
    }
    private lateinit var productAdapter :ProductRecyclerViewAdapter

    private lateinit var binding:FragmentProductsBinding



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProductsBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        productAdapter = ProductRecyclerViewAdapter(productViewMode)
        binding.productsRecyclerrView.adapter = productAdapter
        obsevers()
        productViewMode.callProducts()

    }

        fun obsevers () {
            productViewMode.productsLiveData.observe(viewLifecycleOwner, {
                binding.productsProgressBar.animate().alpha(0f).setDuration(1000)
                productAdapter.submitList(it)
                allProducts = it
                binding.productsRecyclerrView.animate().alpha(1f)

            })
            ///----------------------the observer below for errors only---------
            productViewMode.productsErrorLiveData.observe(viewLifecycleOwner,{ error ->
                error?.let {
                    Toast.makeText(requireActivity(), error ,Toast.LENGTH_SHORT).show()
                    if(error=="Unauthorized"){
                        findNavController().navigate(R.id.action_productsFragment_to_loginFragment)
                        productViewMode.productsErrorLiveData.postValue(null)
                    }
                }

            })

            //-----------------------------------------------------------------

            /// the line below is to set on click listener for the option menu

        }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.logout_item -> {
                /// THE BELOW LINES
                sharedPrefEditor.putString(TOKEN_KEY,"")
                sharedPrefEditor.commit()
                logoutItem.isVisible = false
                profileItem.isVisible = false


                binding.productsProgressBar.animate().alpha(1f)
                binding.productsRecyclerrView.animate().alpha(0f)

                productViewMode.callProducts()
            }
            R.id.profile_Item ->{
                findNavController().navigate(R.id.action_productsFragment_to_profileFragment)

            }

        }
        return super.onOptionsItemSelected(item)

    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        /////////////////////to link the menu we create to the action bar to work///////////////
        requireActivity().menuInflater.inflate(R.menu.main_menu, menu)
        //////////////////////////////////////////////////////////////////////////////
        val searchItem = menu.findItem(R.id.app_bar_search)
        logoutItem = menu.findItem(R.id.logout_item)
        profileItem = menu.findItem(R.id.profile_Item)

        val searchView = searchItem.actionView as SearchView
        val token = sharedPref.getString(TOKEN_KEY,"")
        if(token!!.isEmpty()){

            logoutItem.isVisible = false
            profileItem.isVisible = false
        }
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                productAdapter.submitList(allProducts.filter {
                    it.description.lowercase().contains(query!!.lowercase())||
                            it.title.lowercase().contains(query!!.lowercase())
                })
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                /////////// the commented code below is similar to auto complete search
//                productAdapter.submitList(allProducts.filter {
//                    it.description.lowercase().contains(newText!!.lowercase())||
//                            it.title.lowercase().contains(newText!!.lowercase())
//                })
                return true
            }


        })
        searchItem.setOnActionExpandListener(object :MenuItem.OnActionExpandListener{
            override fun onMenuItemActionExpand(item: MenuItem?): Boolean {
                return true
            }

            override fun onMenuItemActionCollapse(item: MenuItem?): Boolean {
                productAdapter.submitList(allProducts)
                return true
            }


        })


    }

}