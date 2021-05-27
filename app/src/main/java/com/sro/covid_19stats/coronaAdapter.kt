package com.sro.newsapp

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.sro.covid_19stats.R

class coronaAdapter(
    private val state: Array<String>,
    private val confirm: ArrayList<String>,
    private val active: java.util.ArrayList<String>,
    private val recovered: java.util.ArrayList<String>,
    private val deceased: java.util.ArrayList<String>
) :
    RecyclerView.Adapter<coronaAdapter.myViewHolder>() {
    class myViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textView: TextView = itemView.findViewById(R.id.confirmedi)
        val textView2: TextView = itemView.findViewById(R.id.activei)
        val textView3: TextView = itemView.findViewById(R.id.recoveredi)
        val textView4: TextView = itemView.findViewById(R.id.deceasedi)
        val textView1: TextView = itemView.findViewById(R.id.statei)
        val View: View = itemView.findViewById(R.id.view)

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): myViewHolder {
        val v: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false)
        return myViewHolder(v)
    }

    override fun onBindViewHolder(holder: myViewHolder, position: Int) {
        holder.textView.text = confirm.get(position)
        holder.textView2.text = active.get(position)
        holder.textView3.text = recovered.get(position)
        holder.textView4.text = deceased.get(position)
        holder.textView1.text = state[position]
    }

    override fun getItemCount(): Int {
        Log.d("coplr", confirm.size.toString())
        return confirm.size
    }
}