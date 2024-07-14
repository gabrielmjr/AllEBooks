package com.mjrfusion.app.allebooks.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mjrfusion.app.allebooks.R
import com.mjrfusion.app.allebooks.documents_manager.model.Document

class PdfDocumentsAdapter(
    private val context: Context,
    var pdfDocuments: ArrayList<Document>,
    private val clickListener: ClickListener
) : RecyclerView.Adapter<PdfDocumentsAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(context)
                .inflate(R.layout.cardview_document, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        pdfDocuments[position].apply {
            holder.also {
                it.docName.text = displayName
                it.docSize.text = size.toString()
//                it.thumbnail.setImageBitmap(thumbnail)
            }
        }
    }

    override fun getItemCount(): Int {
        return pdfDocuments.size
    }

    inner class ViewHolder(view: View?) : RecyclerView.ViewHolder(view!!) {
        val docName: TextView = view!!.findViewById(R.id.doc_name)
        val docSize: TextView = view!!.findViewById(R.id.doc_size)
        val thumbnail: ImageView = view!!.findViewById(R.id.thumbnail)

        init {
            setOnClickListener(view!!)
        }

        private fun setOnClickListener(view: View) {
            view.setOnClickListener {
                clickListener.onDocumentClicked(absoluteAdapterPosition)
            }
        }
    }

    interface ClickListener {
        fun onDocumentClicked(position: Int)
    }
}
