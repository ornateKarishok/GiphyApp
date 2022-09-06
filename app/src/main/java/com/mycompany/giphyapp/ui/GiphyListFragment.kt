package com.mycompany.giphyapp.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.mycompany.giphyapp.api.*
import com.mycompany.giphyapp.databinding.FragmentGiphListBinding


class GiphyListFragment : Fragment() {
    companion object {
        fun newInstance() = GiphyListFragment()
    }

    private lateinit var viewModel: GiphyViewModel
    private lateinit var binding: FragmentGiphListBinding
    private val retrofitService = RetrofitService.getInstance()
    lateinit var mContext: Context

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view =
            inflater.inflate(com.mycompany.giphyapp.R.layout.fragment_giph_list, container, false)
        binding =
            FragmentGiphListBinding.inflate(inflater, container, false)

        viewModel =
            ViewModelProvider(this, GiphyViewModelFactory(GiphyRepository(retrofitService))).get(
                GiphyViewModel::class.java
            )
        val adapter = GiphyListAdapter(mContext)
        binding.recyclerView.adapter = adapter
        viewModel.giphyList.observe(viewLifecycleOwner) {
            adapter.setGiphList(it.res)
        }
        viewModel.errorMessage.observe(viewLifecycleOwner) {
        }
        viewModel.getGiphyList()
//        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)
//        val adapter = GiphyListAdapter(this, gifs)
        return view
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }


}