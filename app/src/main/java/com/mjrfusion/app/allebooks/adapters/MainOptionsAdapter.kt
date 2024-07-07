package com.mjrfusion.app.allebooks.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.textview.MaterialTextView
import com.mjrfusion.app.allebooks.R
import com.mjrfusion.app.allebooks.ui.home.MainOptionsViewModel

class MainOptionsAdapter(
    private val context: Context,
    private val mainOptionsViewModel: MainOptionsViewModel,
    private val onClickListener: OnClickListener
) : RecyclerView.Adapter<MainOptionsAdapter.ViewHodel>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHodel {
        return ViewHodel(
            LayoutInflater.from(context)
                .inflate(R.layout.card_view_main_options, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return mainOptionsViewModel.mainOptions.value!!.size
    }

    override fun onBindViewHolder(holder: ViewHodel, position: Int) {

    }

    inner class ViewHodel(private var view: View) : RecyclerView.ViewHolder(view) {
        lateinit var animatedIcon: ImageView
        lateinit var label: MaterialTextView

        init {
            view.setOnClickListener {
                onClickListener.onItemClicked(absoluteAdapterPosition)
            }
        }
    }

    interface OnClickListener {
        fun onItemClicked(position: Int)
    }
}