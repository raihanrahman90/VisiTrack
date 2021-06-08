package com.visitrack.core.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.visitrack.core.R
import com.visitrack.core.databinding.RvItemNotificationBinding
import com.visitrack.core.domain.model.Violation

class NotificationAdapter: RecyclerView.Adapter<NotificationAdapter.ListViewHolder>() {

    private var listData = ArrayList<Violation>()
    var onItemClick: ((Violation) -> Unit)? = null

    fun setData(newListData: List<Violation>) {
        listData.clear()
        listData.addAll(newListData)
        notifyDataSetChanged()
    }

    inner class ListViewHolder(view: View): RecyclerView.ViewHolder(view) {
        private val binding = RvItemNotificationBinding.bind(view)
        fun bind(data: Violation) {
            with(binding) {
                tvViolation.text = itemView.context.getString(R.string.violation_text, data.typeViolation)
                tvTimestamp.text = data.timeViolation
            }
        }

        init {
            binding.root.setOnClickListener {
                onItemClick?.invoke(listData[bindingAdapterPosition])
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        return ListViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.rv_item_notification, parent, false))
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.bind(listData[position])
    }

    override fun getItemCount(): Int {
        return if (listData.size > 100) {
            100
        } else {
            listData.size
        }
    }
}