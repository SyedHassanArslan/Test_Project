package test.fragments

import alldocumentreader.officereader.viewer.filereader.pdf.excel.testproject.R
import alldocumentreader.officereader.viewer.filereader.pdf.excel.testproject.databinding.FragmentHomeBinding
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.annotation.MenuRes
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import test.adapters.AdapterForPosts
import test.data.Posts

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var adapterTrending: AdapterForPosts
    private lateinit var recentPostViewAdapter: AdapterForPosts

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentHomeBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setAdapter()
        populateTrendingList()
        listeners()
    }

    private fun setAdapter() {
        adapterTrending = AdapterForPosts {
            visibleRecyclerView()
            recentPostViewAdapter.getList().remove(it)
            recentPostViewAdapter.notifyDataSetChanged()
            recentPostViewAdapter.addRecent(it)
            binding.recyclerRecentView.smoothScrollToPosition(0)
        }
        binding.recyclerViewTrending.adapter = adapterTrending

        recentPostViewAdapter = AdapterForPosts {
        }
        binding.recyclerRecentView.adapter = recentPostViewAdapter
    }

    private fun visibleRecyclerView() {
        if (!binding.tvRecentView.isVisible) {
            binding.tvRecentView.visibility = View.VISIBLE
            binding.recyclerRecentView.visibility = View.VISIBLE
        }
    }

    private fun listeners() {
        binding.tvDropDown.setOnClickListener {
            showMenu(it, R.menu.drop_down)
        }
    }

    private fun showMenu(v: View, @MenuRes menuRes: Int) {
        val popup = PopupMenu(requireContext(), v)
        popup.menuInflater.inflate(menuRes, popup.menu)

        popup.setOnMenuItemClickListener { menuItem: MenuItem ->
            true
        }
        popup.setOnDismissListener {

        }
        popup.show()
    }

    private fun populateTrendingList() {
        adapterTrending.submitList(
            arrayListOf(
                Posts(R.drawable.dummy_image, "Dummy", "$849/month", 3.5F, 200, 2345, "Sale"),
                Posts(R.drawable.dummy_image1, "Dummy 1", "$849/month", 3.5F, 23, 43, "Rent"),
                Posts(R.drawable.dummy_image2, "Dummy 2", "$461/month", 4.5F, 452, 2345, "Sale"),
                Posts(R.drawable.dummy_image3, "Dummy 3", "$134/month", 5F, 231, 234, "Rent"),
                Posts(R.drawable.dummy_image4, "Dummy 4", "$954/month", 2.5F, 325, 565, "Sale"),
                Posts(R.drawable.dummy_image5, "Dummy 5", "$435/month", 4.4F, 654, 685, "Rent")
            )
        )
    }
}