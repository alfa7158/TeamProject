package com.example.e_commerce.view.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.e_commerce.R
import com.example.e_commerce.databinding.FragmentFavoriteBinding
import com.example.e_commerce.view.adapter.FavoriteRecyclerViewAdapter
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class FavoriteFragment : Fragment() {
private lateinit var binding:FragmentFavoriteBinding
private val favoriteViewModel:FavoriteViewModel by activityViewModels()
private lateinit var favoriteRecyclerViewAdapter: FavoriteRecyclerViewAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFavoriteBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observer()
        favoriteRecyclerViewAdapter = FavoriteRecyclerViewAdapter()
        binding.favoriteRecyclerView.adapter = favoriteRecyclerViewAdapter

        favoriteViewModel.callFavorite()

        binding.favoriteLoginButton.setOnClickListener{
            findNavController().navigate(R.id.action_favoriteFragment_to_loginFragment)
        }
    }

    fun observer(){
       favoriteViewModel.favoriteLiveData.observe(viewLifecycleOwner,{
           binding.favoriteProgresBar.animate().alpha(0f)
          favoriteRecyclerViewAdapter.submitList(it)
       })
        favoriteViewModel.favoriteLiveDataError.observe(viewLifecycleOwner,{
            binding.favoriteProgresBar.animate().alpha(0f)

            it?.let {
                Toast.makeText(requireActivity(), it, Toast.LENGTH_SHORT).show()
                if(it=="Unauthorized"){
                    binding.favoriteLoginButton.visibility = View.VISIBLE
                    favoriteRecyclerViewAdapter.submitList(listOf())
                }
                favoriteViewModel.favoriteLiveDataError.postValue(null)
            }
        })

    }
}

