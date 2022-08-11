package test.ui

import alldocumentreader.officereader.viewer.filereader.pdf.excel.testproject.R
import alldocumentreader.officereader.viewer.filereader.pdf.excel.testproject.databinding.ActivityDetailBinding
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide

class DetailActivity : AppCompatActivity() {

    private lateinit var binding:ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        init()
    }

    private fun init(){
        Glide.with(this).load(intent.getIntExtra("image",0)).into(binding.productImage)
        binding.tvStars.text = intent.getFloatExtra("star",0F).toString()
        binding.tvStars.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_baseline_star_24, 0)
        binding.tvRationAndReview.text = getString(R.string.mixText,intent.getIntExtra("noOfRating",0).toString(),intent.getIntExtra("noOfReview",0).toString())
        binding.tvName.text = intent.getStringExtra("name")
        binding.tvCost.text = intent.getStringExtra("cost")
        binding.tvRefundCost.text = intent.getStringExtra("cost")
        binding.tvSaleType.text = intent.getStringExtra("type")
    }
}