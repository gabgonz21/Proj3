package com.example.project3

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.ui.platform.ComposeView
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp
import androidx.compose.material.*
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding

import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp

import com.example.project3.databinding.FragmentFirstBinding

//Spinner Fragment

class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent{
                Column(modifier = Modifier.fillMaxSize().padding(16.dp),
                    verticalArrangement = Arrangement.Top,
                    horizontalAlignment = Alignment.CenterHorizontally) {
                    Spinner()
                }
            }
        }

    }

    @OptIn(ExperimentalMaterialApi::class)
    @Composable
    fun Spinner() {
        val list = listOf("Cat One", "Cat Two")
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

            list.forEach{
                selectedCat -> DropdownMenuItem(onClick = {
                    selectedItem = selectedCat
                expanded = false
                Toast.makeText(mContext, "" + selectedItem, Toast.LENGTH_LONG).show()
            }) {
                Text(text = selectedCat, fontSize = 28.sp)
            }
            }
        }
    }

        }
    }

