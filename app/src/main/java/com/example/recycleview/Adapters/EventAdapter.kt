package com.example.recycleview.Adapters

import android.content.Context
import android.content.SharedPreferences
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.recycleview.app.MyApp
import com.example.recycleview.databinding.EventitemBinding
import com.example.recycleview.pojo.Event

class EventAdapter:RecyclerView.Adapter<EventAdapter.EventViewHolder>() {
    inner class EventViewHolder(val binding: EventitemBinding) :
        RecyclerView.ViewHolder(binding.root)

    private var eventList = ArrayList<Event>()
    var onItemclick: ((Event) -> Unit)? = null
    fun seteventList(eventList: List<Event>) {
        this.eventList = eventList as ArrayList<Event>
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder {
        return EventViewHolder(
            EventitemBinding.inflate(LayoutInflater.from(parent.context))
        )
    }


    override fun onBindViewHolder(holder: EventAdapter.EventViewHolder, position: Int) {
        Glide.with(holder.itemView).load(eventList[position].eventImage)
            .into(holder.binding.imgEvent)
        holder.binding.tvEventName.text = eventList[position].title
        eventList[position].lag
        eventList[position].lat
        eventList[position].snippet
        val prefs = MyApp.context?.getSharedPreferences("Login", Context.MODE_PRIVATE)
        val sp = prefs?.edit()
        sp?.putString("title", eventList[position].title)
        if (sp != null) {
            putDouble(sp, "lag",eventList[position].lag)
            putDouble(sp, "lat",eventList[position].lat)

        }

        sp?.putString("snippet", eventList[position].snippet)

        sp?.commit()
        sp?.clear()
        sp?.commit()
        holder.itemView.setOnClickListener {
            onItemclick!!.invoke(eventList[position])

        }



    }
    override fun getItemCount(): Int {
        return eventList.size
    }
    fun putDouble(edit: SharedPreferences.Editor, key: String?, value: Double): SharedPreferences.Editor? {
        return edit.putLong(key, java.lang.Double.doubleToRawLongBits(value))
    }
}