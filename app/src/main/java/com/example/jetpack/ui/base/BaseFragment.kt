package com.example.jetpack.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.viewbinding.ViewBinding
import com.example.jetpack.data.repository.BaseRepository

abstract class BaseFragment<VM : ViewModel, B : ViewBinding, R : BaseRepository> : Fragment() {

    protected lateinit var binding: B

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = getBinding(inflater, container)
        return binding.root
    }

    abstract fun getViewModel(): Class<VM>

    abstract fun getBinding(inflater: LayoutInflater, container: ViewGroup?): B

    abstract fun getRepository(): R
}