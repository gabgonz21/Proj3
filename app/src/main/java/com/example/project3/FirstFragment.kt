package com.example.project3

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.ui.platform.ComposeView
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.Alignment
import androidx.compose.material.icons.Icons
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.*
import androidx.compose.foundation.clickable


import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.material.DropdownMenu

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
                Spinner()
            }
        }

    }

    @Composable
    fun Spinner() {
        val list = listOf("Cat One, Cat Two")
        val expanded = remember{ mutableStateOf(false) }
        val currentValue = remember {mutableStateOf(list[0]) }

        Box(modifier = Modifier.fillMaxWidth()) {
            Row(modifier = Modifier
                .clickable {
                    expanded.value = !expanded.value
                }
                .align(Alignment.Center)){
                Text(
                    text = currentValue.value,
                    style = TextStyle(color = Color.Black, fontSize = 20.sp)
                )
                Icon(imageVector = Icons.Filled.ArrowDropDown, contentDescription = null)
                DropdownMenu(expanded = expanded.value, onDismissRequest = {
                    expanded.value = !expanded.value
                }) {
                    list.forEach{
                        DropdownMenu(onClick =
                            currentValue.value = it
                            expanded.value = false )
                            Text(text = it)

                        }
                    }
                }
            }


        }
    }

}