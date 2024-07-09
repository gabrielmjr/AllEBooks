package com.mjrfusion.app.allebooks.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.textview.MaterialTextView
import com.mjrfusion.app.allebooks.R
import com.mjrfusion.app.allebooks.ui.home.MainOptionsViewModel

class MainOptionsAdapter(
    private val context: Context,
    private val mainOptionsViewModel: MainOptionsViewModel,
    private val onClickListener: ClickListener
) : RecyclerView.Adapter<MainOptionsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(context)
                .inflate(R.layout.card_view_main_options, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return mainOptionsViewModel.mainOptions.value!!.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        mainOptionsViewModel.mainOptions.value?.get(position)?.apply {
            holder.animatedIcon.setImageResource(animatedIcon)
            holder.label.setText(label)
        }
    }

    inner class ViewHolder(private var view: View) : RecyclerView.ViewHolder(view) {
        lateinit var animatedIcon: ImageView
        lateinit var label: MaterialTextView

        init {
            initializeViews()
            setListeners()
        }

        private fun initializeViews() {
            animatedIcon = view.findViewById(R.id.icon_viewer)
            label = view.findViewById(R.id.label)
        }

        private fun setListeners() {
            view.setOnClickListener {
                onClickListener.onItemClicked(absoluteAdapterPosition)
            }
        }
    }

    interface ClickListener {
        fun onItemClicked(position: Int)
    }
}