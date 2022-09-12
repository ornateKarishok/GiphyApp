package com.mycompany.giphyapp.ui

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.view.*
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager.widget.ViewPager
import com.mycompany.giphyapp.R
import com.mycompany.giphyapp.batabase.ImageViewModel
import com.mycompany.giphyapp.batabase.entities.Image
import com.mycompany.giphyapp.util.FragmentUtil


class FullScreenFragment : Fragment() {
    private lateinit var viewPager: ViewPager
    private lateinit var viewPagerAdapter: ViewPagerAdapter
    private lateinit var giphsList: ArrayList<Image>
    private lateinit var databaseViewModel: ImageViewModel
    private var position: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            val giphsListarg = arguments?.getParcelableArrayList<Image>("list")
            if (giphsListarg != null) {
                giphsList = giphsListarg
            }
            position = arguments?.getInt("position")!!
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_full_screen, container, false)
        databaseViewModel = ViewModelProvider(this).get(ImageViewModel::class.java)
        viewPager = view.findViewById(R.id.viewPager)
        viewPagerAdapter = ViewPagerAdapter(requireContext(), giphsList)

        viewPager.offscreenPageLimit = giphsList.size
        viewPager.adapter = viewPagerAdapter
        viewPager.currentItem = position
        setHasOptionsMenu(true)
        return view
    }

    companion object {
        @JvmStatic
        fun newInstance(images: List<Image>, position: Int): FullScreenFragment {
            val fragment = FullScreenFragment()
            val args = Bundle()
            args.putParcelableArrayList("list", images as ArrayList<Image>)
            args.putInt("position", position)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.delete) {
            deleteImage()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun deleteImage() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Yes") { _, _ ->
            databaseViewModel.deleteGif(giphsList[viewPager.currentItem])
            giphsList.removeAt(viewPager.currentItem)
            viewPagerAdapter.notifyDataSetChanged()
            FragmentUtil.replaceFragment(GiphyListFragment.newInstance(), parentFragmentManager)
        }
        builder.setNegativeButton("No") { _, _ -> }
        builder.setTitle("Are you sure you want to delete gif?")
        builder.create().show()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        val callback: OnBackPressedCallback =
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    FragmentUtil.replaceFragment(
                        GiphyListFragment.newInstance(),
                        parentFragmentManager
                    )
                }
            }
        requireActivity().onBackPressedDispatcher.addCallback(
            this,
            callback
        )
    }

}