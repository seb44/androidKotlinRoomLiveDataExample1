package fr.sebastienlaunay.androidkotlinroomexample1.Database

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.persistence.room.*
import fr.sebastienlaunay.androidkotlinroomexample1.Model.Word

@Dao
interface IWordDao {




    @Query("SELECT * from word_table ORDER BY word ASC")
    fun getAllWords(): LiveData<List<Word>>

    // Needed when we user MutableLiveData instead of LiveData - Need to comment the 2 above line and uncomment 2 next lines
    // @Query("SELECT * from word_table ORDER BY word ASC")
    // fun getAllWords(): List<Word>

    @Insert
    fun insert(word: Word)

    @Query("DELETE FROM word_table")
    fun deleteAll()

//    @Delete
//    fun deleteWord(word: Word)
//
//    @Update
//    fun updateWord(word: Word)
}