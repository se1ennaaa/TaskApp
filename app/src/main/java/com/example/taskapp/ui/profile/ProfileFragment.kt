package com.example.appleadd.ui.profile

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import com.example.appleadd.ARG_PARAM1
import com.example.appleadd.ARG_PARAM2
import com.example.appleadd.R
import com.example.appleadd.data.Pref
import com.example.appleadd.databinding.FragmentProfileBinding
import com.example.appleadd.utilse.loadImage

class ProfileFragment : Fragment() {

    private lateinit var binding: FragmentProfileBinding
    private lateinit var pref: Pref

    private val launcher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result: ActivityResult ->
        if (result.resultCode == Activity.RESULT_OK
            && result.data != null
        ) {
            val photoUri = result.data?.data
            pref.saveImage(photoUri.toString())
            binding.imgAvatar.loadImage(photoUri.toString())
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        pref = Pref(requireContext())
        binding.imgAvatar.loadImage(pref.getImage())

        nameSave()

        binding.imgAvatar.setOnClickListener {
            val intent = Intent()
            intent.type = "image/*"
            intent.action = Intent.ACTION_GET_CONTENT
            launcher.launch(intent)
        }
    }

    private fun nameSave() {
        binding.etName.setText(pref.getName())
        binding.etName.addTextChangedListener {
            pref.saveName(binding.etName.text.toString())
        }
    }
}