package com.mjrt.app.allebooks.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mjrt.app.allebooks.R
import com.mjrt.app.allebooks.documents_manager.documents_manager.Document

class PdfDocumentsAdapter(
    private val context: Context,
    var pdfDocuments: ArrayList<Document>
) : RecyclerView.Adapter<PdfDocumentsAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(
                context
            ).inflate(R.layout.cardview_document, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        pdfDocuments[position].apply {
            holder.also {
                it.docName.text = title
                it.docSize.text = size.toString()
//                it.thumbnail.setImageBitmap(thumbnail)
            }
        }
    }
    override fun getItemCount(): Int {
        return pdfDocuments.size
    }

    inner class ViewHolder(view: View?) : RecyclerView.ViewHolder(view!!) {
        val docName: TextView
        val docSize: TextView
        val thumbnail: ImageView

        init {
            docName = view!!.findViewById(R.id.doc_name)
            docSize = view.findViewById(R.id.doc_size)
            thumbnail = view.findViewById(R.id.thumbnail)
        }
    }
}
