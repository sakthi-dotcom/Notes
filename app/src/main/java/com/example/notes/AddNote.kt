package com.example.notes

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.notes.Models.Note
import android.widget.Toast
import com.example.notes.databinding.ActivityAddNoteBinding
import java.text.SimpleDateFormat
import java.util.*

class AddNote : AppCompatActivity() {

    private lateinit var binding: ActivityAddNoteBinding
    private lateinit var  update_note:Note
    private lateinit var old_note:Note

    var isUpdate = false

    @SuppressLint("SimpleDateFormat")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        try{
            old_note = intent.getSerializableExtra("current_note") as Note
            binding.etTitle.setText(old_note.title)
            binding.etNote.setText(old_note.note)
            isUpdate = true
        }
        catch (e:Exception){
            e.printStackTrace()
        }
        binding.imgCheck.setOnClickListener {
            val title = binding.etTitle.text.toString()
            val updatedNote = binding.etNote.text.toString()

            if (title.isNotEmpty() || updatedNote.isNotEmpty()){
                val formatter = SimpleDateFormat("dd-MMMM-yyyy")

                if(isUpdate)
                {
                    update_note = Note(
                        old_note.id,title,updatedNote,formatter.format(Date())
                    )
                }
                else
                {
                    update_note = Note(
                        null,title,updatedNote,formatter.format(Date())
                    )
                }

                val intent = Intent()
                intent.putExtra("note",update_note)
                setResult(Activity.RESULT_OK,intent)
                finish()
            }
            else{
                Toast.makeText(this@AddNote,"Please add some Data",Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
        }
        binding.imgBackArrow.setOnClickListener {
            onBackPressed()
        }
    }
}