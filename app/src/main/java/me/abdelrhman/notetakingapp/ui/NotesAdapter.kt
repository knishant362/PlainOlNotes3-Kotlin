package me.abdelrhman.notetakingapp.ui

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_note.*
import me.abdelrhman.notetakingapp.EditorActivity
import me.abdelrhman.notetakingapp.R
import me.abdelrhman.notetakingapp.database.NoteEntity
import me.abdelrhman.notetakingapp.utils.inflate


/**
 * Created by Abdelrhman
 * on 6/21/18.
 */


class NotesAdapter(val context: Context, val notes: List<NoteEntity>) : RecyclerView.Adapter<NotesAdapter.ViewHolder>() {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(notes[position])

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesAdapter.ViewHolder = ViewHolder(parent.inflate(R.layout.item_note))

    override fun getItemCount(): Int = notes.size


    class ViewHolder(override val containerView: View?) : RecyclerView.ViewHolder(containerView), LayoutContainer {

        fun bind(note: NoteEntity) {
            tvNote.text = note.text
            noteFab.setOnClickListener {
                val intent = Intent(it.context, EditorActivity::class.java)
                intent.putExtra(EditorActivity.KEY_ID, note.id)
                it.context.startActivity(intent)
            }

        }

    }

}