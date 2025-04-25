package com.example.samacharapp.Adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.samacharapp.R
import com.example.samacharapp.databinding.EachnewscardBinding
import com.example.samacharapp.mydata.Article

class NewsAdapter : RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {

    private var newsList: List<Article> = emptyList()
//    // Updates the list of articles and refreshes the view

      fun submitList(list: List<Article>) {
        newsList = list
        notifyDataSetChanged() // Notifies the RecyclerView to refresh its display



    }

    // Creates a new view holder and inflates the layout
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val binding=EachnewscardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NewsViewHolder(binding)
    }

    // Binds data to the view holder
    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.bind(newsList[position])  // Binds the article data to the ViewHolder
    }

    // Returns the total number of items in the list
    override fun getItemCount(): Int = newsList.size

    // ViewHolder to hold and bind each news item
    class NewsViewHolder(private val binding: EachnewscardBinding) :
        RecyclerView.ViewHolder(binding.root) {

        // Binds article data (title, description, and image)
        fun bind(article: Article) {
            binding.title.text = article.title
            binding.description.text = article.description

            // Loads the article image using Glide
            Glide.with(binding.root.context)
                .load(article.urlToImage)
                .placeholder(R.drawable.loading_circles_blue_gradient)  // Placeholder image until the actual image is loaded
                .error(R.drawable.mage_not_supported)  // Error image if the image fails to load
                .into(binding.myimageView)  // Binds the image to the ImageView
        }
    }
}
