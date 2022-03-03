package com.example.heroapitask

import android.view.View
import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.heroapitask.network.Data

import com.example.heroapitask.ui.HeroApiStatus
import com.example.heroapitask.ui.HeroListAdapter

@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView,data:List<Data>?){
    val adapter=recyclerView.adapter as HeroListAdapter
    adapter.submitList(data)
}


@BindingAdapter("imageUrl")
fun bindImage(imgView: ImageView, imgUrl: String?) {
    imgUrl?.let {
        val imgUri = imgUrl.toUri().buildUpon().scheme("https").build()
        imgView.load(imgUri)
    }
}

@BindingAdapter("apiStatus")
fun bindStatus(statusImageView: ImageView,status:HeroApiStatus?){
    when(status){
        HeroApiStatus.LOADING->{
            statusImageView.visibility= View.VISIBLE
            statusImageView.setImageResource(R.drawable.loading_animation)
        }
        HeroApiStatus.DONE->{
            statusImageView.visibility=View.GONE
        }

        else -> {
            statusImageView.visibility=View.VISIBLE
            statusImageView.setImageResource(R.drawable.ic_connection_error)
        }
    }
}