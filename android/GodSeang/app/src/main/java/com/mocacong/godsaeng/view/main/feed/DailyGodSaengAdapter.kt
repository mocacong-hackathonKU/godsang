package com.mocacong.godsaeng.view.main.feed

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mocacong.godsaeng.data.DailyInfo
import com.mocacong.godsaeng.databinding.ItemDailyGodsaengBinding

class DailyGodSaengAdapter : RecyclerView.Adapter<DailyGodSaengAdapter.MyViewHolder>() {

    private var onItemClickListener: OnItemClickListener? = null
    private var godSaengList = mutableListOf<DailyInfo>()

    interface OnItemClickListener {
        fun onItemClick(item: DailyInfo)
    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.onItemClickListener = listener
    }

    inner class MyViewHolder(private val binding: ItemDailyGodsaengBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(godSaeng: DailyInfo) {
            binding.dailyGodSaeng = godSaeng
            binding.proofBtn.setOnClickListener {
                onItemClickListener?.onItemClick(godSaeng)
            }
        }
    }


    fun setData(godSaengs: List<DailyInfo>) {
        godSaengList.clear()
        godSaengList.addAll(godSaengs)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding =
            ItemDailyGodsaengBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int = godSaengList.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(godSaengList[position])
    }
}
