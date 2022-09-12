package com.mycompany.giphyapp.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mycompany.giphyapp.R
import com.mycompany.giphyapp.api.GiphyRepository
import com.mycompany.giphyapp.api.GiphyViewModel
import com.mycompany.giphyapp.api.GiphyViewModelFactory
import com.mycompany.giphyapp.api.RetrofitService
import com.mycompany.giphyapp.batabase.ImageViewModel
import com.mycompany.giphyapp.batabase.entities.Image
import com.mycompany.giphyapp.models.DataObject
import com.mycompany.giphyapp.util.FragmentUtil


class GiphyListFragment : Fragment() {
    companion object {
        fun newInstance() = GiphyListFragment()
    }

    private lateinit var retrofitViewModel: GiphyViewModel
    private lateinit var databaseViewModel: ImageViewModel
    private val retrofitService = RetrofitService.getInstance()
    var gifs: List<Image> = mutableListOf()
    private lateinit var mContext: Context
    private lateinit var searchView: SearchView
    private lateinit var recyclerView: RecyclerView
    private val adapter by lazy { GiphyListAdapter(mContext) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view =
            inflater.inflate(R.layout.fragment_giph_list, container, false)

        recyclerView = view.findViewById(R.id.recyclerView)
        databaseViewModel = ViewModelProvider(this)[ImageViewModel::class.java]
        databaseViewModel.readAllData.observe(viewLifecycleOwner) { image ->
            gifs = image
            adapter.setData(gifs)
        }
        if (gifs.isEmpty()) {
            retrofitViewModel =
                ViewModelProvider(
                    this,
                    GiphyViewModelFactory(GiphyRepository(retrofitService))
                )[GiphyViewModel::class.java]
            retrofitViewModel.giphyList.observe(viewLifecycleOwner) {
                insertDataToDatabase(it.res)
            }

            retrofitViewModel.errorMessage.observe(viewLifecycleOwner) {
            }
            retrofitViewModel.getGiphyList()
        }

        recyclerView.adapter = adapter
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = GridLayoutManager(mContext, 2)
        adapter.setOnClickListener(object : GiphyListAdapter.OnItemClickListener {
            override fun onItemClick(position: Int) {
                FragmentUtil.replaceFragment(
                    FullScreenFragment.newInstance(gifs, position),
                    parentFragmentManager
                )
            }
        })

        searchView = view.findViewById(R.id.search)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                return true
            }

            override fun onQueryTextChange(newText: String): Boolean {
                searchDatabase(newText)
                return true
            }
        })
        return view
    }

    private fun insertDataToDatabase(res: List<DataObject>) {
        for (i in res.indices) {
            val image = Image(res[i].images.ogImages.url, res[i].name)
            databaseViewModel.addImage(image)
        }
    }

    private fun searchDatabase(query: String) {
        val searchQuery = "%$query%"

        databaseViewModel.searchDatabase(searchQuery).observe(this) { list ->
            list.let {
                adapter.setData(it)
            }
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }
}