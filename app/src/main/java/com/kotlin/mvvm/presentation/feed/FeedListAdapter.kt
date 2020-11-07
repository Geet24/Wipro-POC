package com.kotlin.mvvm.presentation.feed

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.kotlin.mvvm.R
import com.kotlin.mvvm.databinding.ListItemFeedBinding
import com.kotlin.mvvm.domain.entities.FeedEntity

class FeedListAdapter(private var feeds: List<FeedEntity>? = null) :
    RecyclerView.Adapter<FeedListAdapter.FeedItemViewHolder>() {

    private lateinit var context: Context

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        context = recyclerView.context
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FeedListAdapter.FeedItemViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)

        return FeedItemViewHolder(ListItemFeedBinding.inflate(layoutInflater, parent, false))
    }

    override fun onBindViewHolder(holder: FeedListAdapter.FeedItemViewHolder, position: Int) {
        val item = feeds?.get(position)
        Glide.with(context).load(item?.image).centerCrop()
            .apply(getOptions()).into(holder.binding.ivArticleImage)
        holder.binding.tvArticleTitle.text = item?.title
        holder.binding.tvArticleDescription.text =
            item?.description ?: context.resources.getString(R.string.article_description)
    }

    private fun getOptions(): RequestOptions {
        return RequestOptions()
            .placeholder(R.drawable.ic_image_grey_24dp)
            .error(R.drawable.ic_broken_image_grey_24dp)
    }

    override fun getItemCount(): Int {
        return feeds?.size ?: 0
    }

    fun refreshFeeds(feeds: List<FeedEntity>) {
        this.feeds = feeds
        notifyDataSetChanged()
    }

    inner class FeedItemViewHolder(val binding: ListItemFeedBinding) :
        RecyclerView.ViewHolder(binding.root)
}