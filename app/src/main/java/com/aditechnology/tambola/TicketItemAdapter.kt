package com.aditechnology.tambola

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class TicketItemAdapter(private val numbers: MutableList<String>) :
    RecyclerView.Adapter<TicketItemAdapter.NumberViewHolder>() {
    private val clickedNumbers = mutableSetOf<String>()
    class NumberViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val numberTextView: TextView = itemView.findViewById(R.id.numberTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NumberViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_number, parent, false)
        return NumberViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: NumberViewHolder, position: Int) {
        val number = numbers[position]
        holder.numberTextView.text =""+number

        holder.itemView.setOnClickListener {

            if (number.equals("")){
                return@setOnClickListener
            }
            // Toggle the clicked state
            if (clickedNumbers.contains(number)) {
                clickedNumbers.remove(number)
                // Update UI to indicate unclicked state (e.g., change background color)
                holder.numberTextView.setBackgroundColor(Color.WHITE) // Default background color
            } else {
                clickedNumbers.add(number)
                // Update UI to indicate clicked state (e.g., change background color)
                holder.numberTextView.setBackgroundColor(Color.GRAY) // Example: set a gray background
            }
        }

        // Update the UI based on clicked state
        if (clickedNumbers.contains(number)) {
            holder.numberTextView.setBackgroundColor(Color.GRAY)
        } else {
            holder.numberTextView.setBackgroundColor(Color.WHITE)
        }

    }

    override fun getItemCount(): Int {
        return numbers.size
    }
}