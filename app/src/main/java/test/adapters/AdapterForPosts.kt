package test.adapters

import test.data.Posts
import alldocumentreader.officereader.viewer.filereader.pdf.excel.testproject.databinding.PostItemBinding
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.text.HtmlCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import java.io.File

class AdapterForPosts(val click:(Posts,ImageView)->Unit) : RecyclerView.Adapter<AdapterForPosts.ViewHolder>() {

    private val arrayList = ArrayList<Posts>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(PostItemBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setData(arrayList[position])
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }

    inner class ViewHolder(private val binding: PostItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun setData(post: Posts){
            Glide.with(binding.postImage).load(post.image).into(binding.postImage)
            binding.tvName.text = post.name
            boldPrice(binding.tvCost,post.cost)

            binding.root.setOnClickListener {
                click.invoke(post,binding.postImage)
            }
        }
    }

    private fun boldPrice(tv:TextView,string: String){
        val a=string.indexOf("/")
        val beforeSubString=string.substring(0, a)
        val afterSubString=string.substring(a, string.length)
        val html = "<b>$beforeSubString</b> $afterSubString"
        tv.text = HtmlCompat.fromHtml(html, HtmlCompat.FROM_HTML_MODE_LEGACY)
    }

    fun submitList(newList : ArrayList<Posts>){
        arrayList.clear()
        arrayList.addAll(newList)
        notifyDataSetChanged()
    }

    fun addRecent(post: Posts){
        arrayList.add(0,post)
        notifyItemInserted(0)
    }

    fun getList():ArrayList<Posts>{
        return arrayList
    }
}