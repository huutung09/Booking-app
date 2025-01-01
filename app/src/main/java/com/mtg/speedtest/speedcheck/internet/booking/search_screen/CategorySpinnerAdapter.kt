package com.mtg.speedtest.speedcheck.internet.booking.search_screen

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.mtg.speedtest.speedcheck.internet.booking.R
import com.mtg.speedtest.speedcheck.internet.booking.model.response.CategoryItem

class CategorySpinnerAdapter(
    context: Context,
    private val categories: List<CategoryItem>
) : ArrayAdapter<CategoryItem>(context, android.R.layout.simple_spinner_item, categories) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = super.getView(position, convertView, parent)
        val textView = view.findViewById<TextView>(android.R.id.text1)
        textView.text = categories[position].name ?: "Unknown"
        textView.textSize = 16f
        textView.setTextColor(ContextCompat.getColor(context, R.color.black))
        return view
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = super.getDropDownView(position, convertView, parent)
        val textView = view.findViewById<TextView>(android.R.id.text1)
        textView.text = categories[position].name ?: "Unknown"
        textView.textSize = 14f
        textView.setPadding(16, 16, 16, 16)
        return view
    }
}
