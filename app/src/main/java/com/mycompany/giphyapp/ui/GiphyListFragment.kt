package com.mycompany.giphyapp.ui

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.view.*
import android.view.ContextMenu.ContextMenuInfo
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
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
    var giphs: List<Image> = mutableListOf()
    lateinit var mContext: Context

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view =
            inflater.inflate(R.layout.fragment_giph_list, container, false)


        val adapter = GiphyListAdapter(mContext)
        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)
        databaseViewModel = ViewModelProvider(this)[ImageViewModel::class.java]
        databaseViewModel.readAllData.observe(viewLifecycleOwner, Observer { image ->
            giphs = image
            adapter.setData(image)
        })
        if (giphs.isEmpty()) {
            retrofitViewModel =
                ViewModelProvider(
                    this,
                    GiphyViewModelFactory(GiphyRepository(retrofitService))
                ).get(
                    GiphyViewModel::class.java
                )
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
                    FullScreenFragment.newInstance(giphs, position),
                    parentFragmentManager
                )
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

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }


}