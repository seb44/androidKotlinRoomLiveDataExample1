package fr.sebastienlaunay.androidkotlinroomexample1

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import kotlinx.android.synthetic.main.activity_new_word.*

class NewWordActivity : AppCompatActivity() {

    private lateinit var editWordView: EditText

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_word)

        button_save.setOnClickListener {
            val replyIntent = Intent()


            TextUtils.isEmpty(edit_word.text).let {

                when(it)
                {
                    true -> {
                        setResult(Activity.RESULT_CANCELED, replyIntent)
                    }

                    false -> {
                        replyIntent.putExtra(EXTRA_REPLY, edit_word.text.toString())
                        setResult(Activity.RESULT_OK, replyIntent)
                    }
                }
            }



/*
            if (TextUtils.isEmpty(edit_word.text)) {
                setResult(Activity.RESULT_CANCELED, replyIntent)
            } else {
                //val word = editWordView.text.toString()
                val word = edit_word.text.toString()
                replyIntent.putExtra(EXTRA_REPLY, word)
                setResult(Activity.RESULT_OK, replyIntent)
            }
*/
            finish()
        }
    }

    companion object {
        const val EXTRA_REPLY = "com.example.android.wordlistsql.REPLY"
    }
}