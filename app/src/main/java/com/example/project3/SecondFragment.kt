package com.example.project3

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.compose.material.Text
import androidx.compose.ui.platform.ComposeView
import androidx.navigation.fragment.findNavController
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.project3.databinding.FragmentSecondBinding
import org.json.JSONArray
import org.json.JSONObject

class SecondFragment : Fragment() {
    private var _binding: FragmentSecondBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val selectedCat = arguments?.getString("selectedCat")
        view.findViewById<TextView>(R.id.nameTextView).text = selectedCat

        var catUrl =
            "https://api.thecatapi.com/v1/breeds/search?q=$selectedCat" + "?api_key=live_qbPAKVMdrw7jfRwaJdnm4Rh0sdvpQx36clCkLuFsrLCHOMMbmtGQ1TtZ6p7LTn7I"

        val queue = Volley.newRequestQueue(requireContext())
        val stringRequest = StringRequest(
            Request.Method.GET, catUrl,
            { response ->
                val catArray = JSONArray(response)
                if (catArray.length() > 0) {
                    val catObject = catArray.getJSONObject(0)
                    val origin = catObject.getString("origin")
                    val temperament = catObject.getString("temperament")
                    binding.originTextView.text = origin
                    binding.temperamentTextView.text = temperament
                } else {
                    binding.originTextView.text = "Not found"
                }
            },
            {
                Log.e("SecondFragment", "API request failed")
                binding.nameTextView.text = "Cant find info"
            })
        queue.add(stringRequest)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}














