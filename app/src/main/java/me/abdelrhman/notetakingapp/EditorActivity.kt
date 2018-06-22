package me.abdelrhman.notetakingapp

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_editor.*
import kotlinx.android.synthetic.main.content_editor.*
import me.abdelrhman.notetakingapp.database.NoteEntity
import me.abdelrhman.notetakingapp.viewmodel.EditViewModel

class EditorActivity : AppCompatActivity() {


    companion object {
        val KEY_ID = "KEY_ID"
        val KEY_EDITING = "KEY_EDITING"
    }

    private lateinit var viewModel: EditViewModel
    private var newNote: Boolean = false
    private var editing: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editor)
        setSupportActionBar(toolbar)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_check)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        if (savedInstanceState != null) {
            editing = savedInstanceState.getBoolean(KEY_EDITING)
        }
        initViewModel()

    }

    override fun onSaveInstanceState(outState: Bundle?) {
        outState?.putBoolean(KEY_EDITING, true)
        super.onSaveInstanceState(outState)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        if (!newNote) {
            menuInflater.inflate(R.menu.menu_editor, menu)
        }
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {


        return when (item?.itemId) {
            android.R.id.home -> {
                saveAndReturn()
                true
            }
            R.id.action_delete -> {
                deleteNote()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun deleteNote() {
        viewModel.deleteNote()
        finish()
    }

    private fun saveAndReturn() {
        viewModel.saveNote(etNoteText.text.toString())
        finish()

    }

    override fun onBackPressed() {
        saveAndReturn()
    }

    private fun initViewModel() {
        viewModel = ViewModelProviders.of(this).get(EditViewModel::class.java)
        viewModel.liveNote.observe(this, Observer<NoteEntity> {
            if (it != null && !editing)
                etNoteText.setText(it.text)
        })

        if (!intent.hasExtra(KEY_ID)) {
            title = "New Note"
            newNote = true

        } else {
            title = "Edit Note"
            val id = intent.extras.getInt(KEY_ID)
            viewModel.loadData(id)
        }
    }
}
