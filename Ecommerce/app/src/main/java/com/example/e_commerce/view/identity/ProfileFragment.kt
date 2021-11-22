package com.example.e_commerce.view.identity

import android.app.Activity.RESULT_OK
import android.app.ProgressDialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import com.example.e_commerce.R
import com.example.e_commerce.databinding.FragmentProductsBinding
import com.example.e_commerce.databinding.FragmentProfileBinding
import com.squareup.picasso.Picasso
import com.zhihu.matisse.Matisse
import com.zhihu.matisse.MimeType
import com.zhihu.matisse.internal.entity.CaptureStrategy
import java.io.File


class ProfileFragment : Fragment() {
    private val IMAGE_PICKER = 0
    private lateinit var binding:FragmentProfileBinding
    private lateinit var progressDialog: ProgressDialog
    private val profileViewModel:ProfileViewMode by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        progressDialog = ProgressDialog(requireActivity())
        progressDialog.setTitle("Loading")
        progressDialog.setCancelable(false)
        binding = FragmentProfileBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observer()
        profileViewModel.callUserProfile()
        binding.profileImageView.setOnClickListener{
            showImagePicture()
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode==IMAGE_PICKER &&resultCode ==RESULT_OK){
            progressDialog.show()
            val imagePath = Matisse.obtainPathResult(data)[0]
            val imgeFile = File(imagePath)
            profileViewModel.uploadUserImage(imgeFile)
        }
    }
    ///scope storage is for writing permission
    fun showImagePicture(){
        Matisse.from(this).choose(MimeType.ofImage(),false).
        capture(true).
        captureStrategy(CaptureStrategy(true,"com.example.e_commerce"))
            .forResult(IMAGE_PICKER)
    }


    fun observer(){
        profileViewModel.profileLiveData.observe(viewLifecycleOwner,{
            binding.ProfileProgressBar.animate().alpha(0f)
            progressDialog.dismiss()
            binding.emailTextView.text = it.email
            binding.fullNameTextView.text = it.fullName
            Picasso.get().load("http://18.196.156.64/Images/${it.image}").
            into(binding.profileImageView)

        })

        profileViewModel.uploadImageLiveData.observe(viewLifecycleOwner,{
            profileViewModel.callUserProfile()

        })
        profileViewModel.profileErrorLiveData.observe(viewLifecycleOwner,{
            progressDialog.dismiss()
            binding.ProfileProgressBar.animate().alpha(0f)
            Toast.makeText(requireActivity(), it, Toast.LENGTH_SHORT).show()

        })
    }



}