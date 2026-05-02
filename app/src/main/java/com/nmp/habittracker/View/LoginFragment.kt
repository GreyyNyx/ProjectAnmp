package com.nmp.habittracker.View

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.nmp.habittracker.R
import com.nmp.habittracker.ViewModel.LoginViewModel
import com.nmp.habittracker.databinding.FragmentLoginBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [LoginFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class LoginFragment : Fragment() {
    private lateinit var viewModel: LoginViewModel
    private lateinit var binding: FragmentLoginBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)

        observeViewModel()

        binding.btnLogin.setOnClickListener {
             val username = binding.txtUsername.text.toString()
    val password = binding.txtPassword.text.toString()

    if (username == "student" && password == "123") {
        Toast.makeText(requireContext(), "Login berhasil", Toast.LENGTH_SHORT).show()

        // pindah ke dashboard
        val action = LoginFragmentDirections.actionLoginToDashboard()
        Navigation.findNavController(it).navigate(action)

    } else {
        Toast.makeText(requireContext(), "Username / Password salah", Toast.LENGTH_SHORT).show()
    }
    }

    fun observeViewModel() {
        viewModel.loginSuccessLD.observe(viewLifecycleOwner, Observer {
            if (it == true) {
                val action = LoginFragmentDirections.actionBlankFragment()
                Navigation.findNavController(requireView()).navigate(action)
            }
        })

        viewModel.errorLD.observe(viewLifecycleOwner, Observer {
            if (it != null && viewModel.loginSuccessLD.value == false) {
                Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
            }
        })
    }
}