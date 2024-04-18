package com.example.project3

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.fragment.app.Fragment
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.project3.databinding.FragmentFirstBinding
import org.json.JSONArray
import org.json.JSONObject


//Spinner Fragment

class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null
    private val binding get() = _binding!!
    //var catData = ArrayList<String>()
    val catData = mutableListOf<String>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?


    ): View {
        return ComposeView(requireContext()).apply {
            printCatData()
            setContent{
                Column(modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                    verticalArrangement = Arrangement.Top,
                    horizontalAlignment = Alignment.CenterHorizontally) {

                    Spinner()


                }
            }
        }

    }




    // method to interact with API
    fun printCatData() {
        var catUrl = "https://api.thecatapi.com/v1/breeds" + "?api_key=live_qbPAKVMdrw7jfRwaJdnm4Rh0sdvpQx36clCkLuFsrLCHOMMbmtGQ1TtZ6p7LTn7I"

        val queue = Volley.newRequestQueue(requireContext())

        // Request a string response from the provided URL.
        val stringRequest = StringRequest(
            Request.Method.GET, catUrl,
            { response ->
                var catsArray : JSONArray = JSONArray(response)

                //indices from 0 through catsArray.length()-1
                for(i in 0 until catsArray.length()) {
                    //${} is to interpolate the string /
                    // uses a string template
                    var theCat : JSONObject = catsArray.getJSONObject(i)

                    //now get the properties we want:  name and description
                    //Log.i("MainActivity", "Cat name: ${theCat.getString("name")}")
                    //binding.catText.text = "Cat name: ${theCat.getString("name")}"
                    //Log.i("MainActivity", "Cat description: ${theCat.getString("description")}")
                    //Log.i("MainActivity", "Cat Temperment: ${theCat.getString("temperament")}")
                    //Log.i("MainActivity", "Cat Origin: ${theCat.getString("origin")}")
                    catData.add("${theCat.getString("name")}")
                    //catData.add(i.toString())
                }//end for
            },
            {
                Log.i("MainActivity", "That didn't work!")
            })

// Add the request to the RequestQueue.
        queue.add(stringRequest)
    }//end printCatData


    @OptIn(ExperimentalMaterialApi::class)
    @Composable
    fun Spinner() {
        val list = listOf("Choose Cat")
        var expanded by remember {mutableStateOf(false)}
        var selectedItem by remember {mutableStateOf(list[0]) }
        var mContext = LocalContext.current
        

        ExposedDropdownMenuBox(expanded = expanded, onExpandedChange ={expanded = !expanded}){
            TextField(
                value = selectedItem,
                onValueChange = {},
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)},
                readOnly = true,
                textStyle = TextStyle.Default.copy(fontSize = 28.sp)
            )
        ExposedDropdownMenu(expanded = expanded,
            onDismissRequest = { expanded = false }) {

            catData.forEach{
                    selectedCat -> DropdownMenuItem(onClick = {
                        selectedItem = selectedCat
                    expanded = false

                val secondFragment = SecondFragment().apply {
                    arguments = Bundle().apply{
                        putString("selectedCat", selectedCat)
                    }
                }
                parentFragmentManager.beginTransaction()
                    .replace(R.id.bottomFragment, secondFragment)
                    .addToBackStack(null)
                    .commit()
                    Toast.makeText(mContext, "" + selectedItem, Toast.LENGTH_LONG).show()
                }) {
                Text(text = selectedCat, fontSize = 28.sp)
                }
            }
        }
    }

    }


}

