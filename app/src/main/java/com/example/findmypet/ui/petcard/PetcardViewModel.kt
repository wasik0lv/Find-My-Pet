package com.example.findmypet.ui.petcard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class PetcardViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is petcard Fragment"
    }
    val text: LiveData<String> = _text
}