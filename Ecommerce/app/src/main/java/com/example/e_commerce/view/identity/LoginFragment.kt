package com.example.e_commerce.view.identity

import android.app.ProgressDialog
import android.content.Context
import android.content.SharedPreferences
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
import com.example.e_commerce.repostries.SHARED_PREF_FILE
import com.example.e_commerce.repostries.TOKEN_KEY


class LoginFragment : Fragment() {
    private lateinit var binding:FragmentLoginBinding
    private val loginViewModel:LoginViewMode by activityViewModels()
    private lateinit var pogressDialog:ProgressDialog
    private lateinit var sharedPref:SharedPreferences
    private lateinit var sharedPreferencesEditor: SharedPreferences.Editor
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        pogressDialog = ProgressDialog(requireActivity())
        pogressDialog.setTitle("Loading..")
        pogressDialog.setCancelable(false)

        sharedPref = requireActivity().getSharedPreferences(SHARED_PREF_FILE,Context.MODE_PRIVATE)
        sharedPreferencesEditor = sharedPref.edit()

        binding = FragmentLoginBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observer()
      val email =  binding.loginEmailEditText.toString()
      val password = binding.loginPassworEditTextAfter
        binding.registerLoginEditText.setOnClickListener{
            findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
        }

        binding.theLoginbutton.setOnClickListener(){
            val email =  binding.loginEmailEditText.text.toString()
            val password = binding.loginPassworEditTextAfter.text.toString()

            if(email.isNotBlank()&&password.isNotBlank()){
                pogressDialog.show()
                loginViewModel.login(email,password)
            }else{
                Toast.makeText(requireActivity(), "Email and password must not be empty", Toast.LENGTH_SHORT).show()

            }

        }
    }
    fun observer(){

        loginViewModel.loginLiveData.observe(viewLifecycleOwner,{

            it?.let {
                sharedPreferencesEditor.putString(TOKEN_KEY,it.token)
                sharedPreferencesEditor.commit()
                pogressDialog.dismiss()
                loginViewModel.loginLiveData.postValue(null)
                findNavController().popBackStack()
            }

        })
        loginViewModel.loginErrorLiveData.observe(viewLifecycleOwner,{
            pogressDialog.dismiss()
            Toast.makeText(requireActivity(), it, Toast.LENGTH_SHORT).show()

        })
    }

}