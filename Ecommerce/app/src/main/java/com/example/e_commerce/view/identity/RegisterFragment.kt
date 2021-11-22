package com.example.e_commerce.view.identity

import android.app.ProgressDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.e_commerce.R
import com.example.e_commerce.databinding.FragmentLoginBinding
import com.example.e_commerce.databinding.FragmentRegisterBinding
import com.example.e_commerce.util.RegisterValidation


class RegisterFragment : Fragment() {
private lateinit var binding: FragmentRegisterBinding
private val registerViewModel:RegisterViewModel by activityViewModels()
    private val validator = RegisterValidation()

    private lateinit var progressDialog:ProgressDialog


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        progressDialog = ProgressDialog(requireActivity())
        progressDialog.setTitle("Loading..")
        progressDialog.setCancelable(false)
      binding = FragmentRegisterBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observer()
        binding.registerButton.setOnClickListener{
            val firstName = binding.firstNameEditText.text.toString()
            val lastName = binding.lastNameEditText.text.toString()
            val email = binding.emailEditText.text.toString()
            val password = binding.passwordEditText.text.toString()
            val confirmPassword = binding.confirmPasswordEditText.text.toString()

            if(firstName.isNotBlank() &&lastName.isNotBlank()&& email.isNotBlank() &&password.isNotBlank() &&confirmPassword.isNotBlank()){
                    if(password == confirmPassword){
                        if(validator.emailIsValid(email)){
                            if(validator.passwordValid(password)){
                                progressDialog.show()
                                registerViewModel.register(firstName,lastName,email,password)

                            }else{
                                Toast.makeText(requireActivity(), "Make sure your password is correct", Toast.LENGTH_SHORT).show()
                            }

                        }else{
                            Toast.makeText(requireActivity(), "Make sure you type your email correctly ", Toast.LENGTH_SHORT).show()

                        }


                    }else{
                        Toast.makeText(requireActivity(), "Password and confirm password do not match", Toast.LENGTH_SHORT).show()
                    }

            }else{
                Toast.makeText(requireActivity(), "Registration filed must not be empty", Toast.LENGTH_SHORT).show()
            }


        }



    }
    fun observer(){
        registerViewModel.registerLiveData.observe(viewLifecycleOwner,{
            it?.let {
                progressDialog.dismiss()
                findNavController().popBackStack()
                registerViewModel.registerLiveData.postValue(null)
            }

        })

        registerViewModel.registerErrorLiveData.observe(viewLifecycleOwner,{
            progressDialog.dismiss()
            Toast.makeText(requireActivity(), it, Toast.LENGTH_SHORT).show()
        })
    }


}