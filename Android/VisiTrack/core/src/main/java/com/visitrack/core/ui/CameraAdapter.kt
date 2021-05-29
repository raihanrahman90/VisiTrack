package com.visitrack.core.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.visitrack.core.R
import com.visitrack.core.databinding.RvItemCameraBinding
import com.visitrack.core.domain.model.Camera

class CameraAdapter: RecyclerView.Adapter<CameraAdapter.ListViewHolder>() {
    private var listData = ArrayList<Camera>()

    fun setData(newListData: List<Camera>) {
        listData.clear()
        listData.addAll(newListData)
        notifyDataSetChanged()
    }

    inner class ListViewHolder(view: View): RecyclerView.ViewHolder(view) {
        private val binding = RvItemCameraBinding.bind(view)
        fun bind(data: Camera) {
            with(binding) {
                Glide.with(itemView.context)
                    .load(data.imageUrl)
                    .into(ivCamera)
                tvCamera.text = data.nameCamera
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        return ListViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.rv_item_camera, parent, false))
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.bind(listData[position])
    }

    override fun getItemCount(): Int {
        return listData.size
    }
}