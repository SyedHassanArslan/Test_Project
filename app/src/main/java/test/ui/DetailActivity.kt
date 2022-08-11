package test.ui

import alldocumentreader.officereader.viewer.filereader.pdf.excel.testproject.R
import alldocumentreader.officereader.viewer.filereader.pdf.excel.testproject.databinding.ActivityDetailBinding
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.text.HtmlCompat
import com.bumptech.glide.Glide

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        init()
        listeners()
    }

    private fun init() {
        Glide.with(this).load(intent.getIntExtra("image", 0)).into(binding.productImage)
        binding.tvStars.text = intent.getFloatExtra("star", 0F).toString()
        binding.tvStars.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_baseline_star_24, 0)
        binding.tvRationAndReview.text = getString(R.string.mixText, intent.getIntExtra("noOfRating", 0).toString(), intent.getIntExtra("noOfReview", 0).toString())
        binding.tvName.text = intent.getStringExtra("name")
        binding.tvSaleType.text = intent.getStringExtra("type")
        binding.tvType2.text = intent.getStringExtra("type")
        binding.tvCost2.text = HtmlCompat.fromHtml("<b> $5039 </b> for 36Month ", HtmlCompat.FROM_HTML_MODE_LEGACY)
        boldPrice(intent.getStringExtra("cost")!!)
    }

    private fun listeners() {
        binding.tvSize1.setOnClickListener {
            selectSize(it)
        }
        binding.tvSize2.setOnClickListener {
            selectSize(it)
        }
        binding.tvSize3.setOnClickListener {
            selectSize(it)
        }
        binding.tvSize4.setOnClickListener {
            selectSize(it)
        }
        binding.btnCart.setOnClickListener {
            Toast.makeText(this, "Add to Cart", Toast.LENGTH_SHORT).show()
        }
        binding.tvBack.setOnClickListener {
            super.onBackPressed()
        }
    }

    private fun boldPrice(string: String) {
        val a = string.indexOf("/")
        val beforeSubString = string.substring(0, a)
        binding.tvRefundCost.text = beforeSubString
        val afterSubString = string.substring(a, string.length)
        val html = "<b>$beforeSubString</b> $afterSubString"
        binding.tvCost.text = HtmlCompat.fromHtml(html, HtmlCompat.FROM_HTML_MODE_LEGACY)
    }

    private fun selectSize(selectedText: View) {
        binding.tvSize1.setBackgroundColor(ContextCompat.getColor(this, android.R.color.transparent))
        binding.tvSize2.setBackgroundColor(ContextCompat.getColor(this, android.R.color.transparent))
        binding.tvSize3.setBackgroundColor(ContextCompat.getColor(this, android.R.color.transparent))
        binding.tvSize4.setBackgroundColor(ContextCompat.getColor(this, android.R.color.transparent))
        selectedText.setBackgroundResource(R.drawable.circle_outline)
    }
}