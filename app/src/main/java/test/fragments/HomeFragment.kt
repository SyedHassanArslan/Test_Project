package test.fragments

import alldocumentreader.officereader.viewer.filereader.pdf.excel.testproject.R
import alldocumentreader.officereader.viewer.filereader.pdf.excel.testproject.databinding.FragmentHomeBinding
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.PopupMenu
import androidx.annotation.MenuRes
import androidx.core.app.ActivityOptionsCompat
import androidx.core.view.ViewCompat
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import test.adapters.AdapterForPosts
import test.data.Posts
import test.ui.DetailActivity

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
        adapterTrending = AdapterForPosts { item, image ->
            visibleRecyclerView()
            openDetailScreen(item, image)
        }
        binding.recyclerViewTrending.adapter = adapterTrending

        recentPostViewAdapter = AdapterForPosts { item, image ->
            openDetailScreen(item, image)
        }
        binding.recyclerRecentView.adapter = recentPostViewAdapter
    }

    private fun openDetailScreen(it: Posts, imageView: ImageView) {
        recentPostViewAdapter.getList().remove(it)
        recentPostViewAdapter.notifyDataSetChanged()
        recentPostViewAdapter.addRecent(it)
        binding.recyclerRecentView.smoothScrollToPosition(0)
        val intent = Intent(requireContext(), DetailActivity::class.java)
        intent.putExtra("name", it.name)
        intent.putExtra("image", it.image)
        intent.putExtra("cost", it.cost)
        intent.putExtra("star", it.star)
        intent.putExtra("type", it.saleType)
        intent.putExtra("noOfRating", it.noOfRatings)
        intent.putExtra("noOfReview", it.noOfReviews)
        val options = ActivityOptionsCompat.makeSceneTransitionAnimation(
            requireActivity(), imageView, ViewCompat.getTransitionName(imageView)!!
        )
        startActivity(intent, options.toBundle())
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