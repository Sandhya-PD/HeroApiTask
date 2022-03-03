package com.example.heroapitask.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.heroapitask.databinding.ListViewItemBinding
import com.example.heroapitask.network.Data

class HeroListAdapter(val clickListener:HeroListener) :
    ListAdapter<Data, HeroListAdapter.HeroViewHolder>(DiffCallback){

class HeroViewHolder(
    var binding:ListViewItemBinding
):RecyclerView.ViewHolder(binding.root){
    fun bind(clickListener: HeroListener, hero: Data){
        binding.hero=hero
        binding.clickListener=clickListener
        binding.executePendingBindings()
    }
}
    companion object DiffCallback:DiffUtil.ItemCallback<Data>() {
        override fun areItemsTheSame(oldItem: Data, newItem: Data): Boolean {
            return oldItem.name== newItem.name

        }

        override fun areContentsTheSame(oldItem: Data, newItem: Data): Boolean {
            return oldItem._id== newItem._id && oldItem.name== newItem.name
        }
    }

        override fun onCreateViewHolder(parent:ViewGroup,viewType:Int):HeroViewHolder{
            val layoutInflater=LayoutInflater.from(parent.context)
            return HeroViewHolder(
                ListViewItemBinding.inflate(layoutInflater,parent,false)
            )

    }

        override fun onBindViewHolder(holder:HeroViewHolder,position:Int){
            val hero=getItem(position)
            holder.bind(clickListener,hero)
        }


}




class HeroListener(val clickListener: (hero:Data)->Unit){
    fun onClick(hero: Data) = clickListener(hero)
}