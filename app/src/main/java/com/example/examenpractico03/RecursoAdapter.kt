package com.example.examenpractico03

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView.OnItemClickListener
import android.widget.TextView
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import java.text.FieldPosition

class RecursoAdapter(private val recursos: List<Recurso>) : RecyclerView.Adapter<RecursoAdapter.ViewHolder>() {
    private var onItemClick: OnItemClickListener? = null

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tituloTextVew: TextView = view.findViewById(R.id.tvTitulo)
        val descripcionTextView: TextView = view.findViewById(R.id.tvDescripcion)
        val tipoTextView: TextView = view.findViewById(R.id.tvTipoArchivo)
        val enlaceTextView: TextView = view.findViewById(R.id.tvEnlace)
        val urlImageView: ImageView = view.findViewById(R.id.imageView)

        fun bind(imageUrl: String?){
            Picasso.get().load(imageUrl).into(urlImageView)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recurso_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val recurso = recursos[position]
        holder.tituloTextVew.text = recurso.titulo
        holder.descripcionTextView.text = recurso.descripcion
        holder.tipoTextView.text = recurso.tipo
        holder.enlaceTextView.text = recurso.enlace

        //Agrega el escuchador de clics a la vista del elemento de la lista
        holder.itemView.setOnClickListener{
            onItemClick?.onItemClick(recurso)
        }
    }

    override fun getItemCount(): Int {
        return recursos.size
    }

    fun setOnclickListener(listener: OnItemClickListener){
        onItemClick = listener
    }

    interface OnItemClickListener{
        fun onItemClick(recurso: Recurso)
    }
}