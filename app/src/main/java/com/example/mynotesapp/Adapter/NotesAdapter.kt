package com.example.mynotesapp.Adapter

import android.content.Context
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.mynotesapp.MainActivity
import com.example.mynotesapp.Models.Note
import com.example.mynotesapp.R
import kotlin.random.Random

class NotesAdapter(private val context:Context, val listener: MainActivity):RecyclerView.Adapter<NotesAdapter.NoteViewHolder>(){

     private val NotesList= ArrayList<Note>()
     private val fullist=ArrayList<Note>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        return NoteViewHolder(
            LayoutInflater.from(context).inflate(R.layout.list_item,parent,false)
        )
    }

    override fun getItemCount(): Int {
        return NotesList.size
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val currentNote=NotesList[position]
        holder.title.text=currentNote.title
        holder.title.isSelected=true
        holder.note.text=currentNote.note
        holder.date.text=currentNote.date
        holder.date.isSelected=true

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            holder.notes_layout.setCardBackgroundColor(holder.itemView.resources.getColor(randomColor(),null))
        }
        holder.notes_layout.setOnClickListener{
            listener.onItemCLicked(NotesList[holder.adapterPosition])
        }
      holder.notes_layout.setOnLongClickListener{
          listener.onLongItemClicked(NotesList[holder.adapterPosition],holder.notes_layout)
          true
      }
    }
    fun updateList(newList:List<Note>){
        fullist.clear()
        fullist.addAll(newList)
        NotesList.clear()
        NotesList.addAll(fullist)
        notifyDataSetChanged()
    }
    fun filterList(search:String){
        NotesList.clear()
        for(item in fullist){
            if(item.title?.lowercase()?.contains(search.lowercase())==true  ||
                    item.note?.lowercase()?.contains(search.lowercase())==true){
                   NotesList.add(item)
            }
        }
        notifyDataSetChanged()
    }

    fun randomColor():Int{

        val colorList=ArrayList<Int>()
        colorList.add(R.color.NotesColor1)
        colorList.add(R.color.NotesColor2)
        colorList.add(R.color.NotesColor3)
        colorList.add(R.color.NotesColor4)
        colorList.add(R.color.NotesColor5)
        colorList.add(R.color.NotesColor6)

        val seed=System.currentTimeMillis().toInt()
        val randomIndex= Random(seed).nextInt(colorList.size)
        return colorList[randomIndex]


    }
    inner class NoteViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
      val notes_layout=itemView.findViewById<CardView>(R.id.card_layout)
        val title=itemView.findViewById<TextView>(R.id.tv_title)
        val note=itemView.findViewById<TextView>(R.id.tv_note)
        val date=itemView.findViewById<TextView>(R.id.tv_date)
    }
    interface noteItemClicked{
        fun onItemCLicked(note:Note)
        fun onLongItemClicked(note:Note,cardView:CardView)
    }
}