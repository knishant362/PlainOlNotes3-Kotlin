package me.abdelrhman.notetakingapp

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import me.abdelrhman.notetakingapp.database.NoteEntity
import me.abdelrhman.notetakingapp.ui.NotesAdapter
import me.abdelrhman.notetakingapp.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {


    private val notesData: MutableList<NoteEntity> = ArrayList()
    private lateinit var mainViewModel: MainViewModel
    private lateinit var adapter: NotesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        setupRvNotes()
        initViewModel()
        fab.setOnClickListener { _ ->
            startActivity(Intent(this, EditorActivity::class.java))
        }
    }

    private fun initViewModel() {
        mainViewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)

        val notesObserver = Observer<List<NoteEntity>> {
            Log.d("MainActivity", "notesObserver")
            notesData.clear()
            notesData.addAll(it!!)
            if (!::adapter.isInitialized) {
                adapter = NotesAdapter(this, notesData)
                rvNotes.adapter = adapter
            } else {
                adapter.notifyDataSetChanged()
            }
        }

        mainViewModel.notes.observe(this, notesObserver)
    }

    private fun setupRvNotes() {
        rvNotes.setHasFixedSize(true)
        val llmanger = LinearLayoutManager(this)
        rvNotes.layoutManager = llmanger
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_add_sample_data -> {
                addSampleData()
                true
            }
            R.id.action_delete_all -> {
                deleteAllNotes()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun deleteAllNotes() {
        mainViewModel.deleteAllNotes()
    }

    private fun addSampleData() {
        mainViewModel.addSampleData()
    }
}
