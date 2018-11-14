package fr.sebastienlaunay.androidkotlinroomexample1.ViewModel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import fr.sebastienlaunay.androidkotlinroomexample1.Database.WordRoomDatabase
import fr.sebastienlaunay.androidkotlinroomexample1.Model.Word
import fr.sebastienlaunay.androidkotlinroomexample1.Repository.WordRepository
import kotlinx.coroutines.experimental.*
import kotlinx.coroutines.experimental.android.Main
import kotlin.coroutines.experimental.CoroutineContext

class WordViewModel(application: Application): AndroidViewModel(application) {

    private var parentJob = Job()

    private val coroutineContext: CoroutineContext
        get() = parentJob + Dispatchers.Main

    private val scope = CoroutineScope(coroutineContext)

    private val repository: WordRepository


    var allWords: LiveData<List<Word>>

    // Needed when we use MutableLiveData instead of LiveData
    //var allWords: MutableLiveData<List<Word>> = MutableLiveData()

    init {
    //    val wordsDao = WordRoomDatabase.getDatabase(application,scope).wordDao()
        val wordsDao = WordRoomDatabase.getDatabase(application).wordDao()
        repository = WordRepository(wordsDao)
        allWords = repository.allWords
    }

    fun insert(word: Word) = scope.launch(Dispatchers.IO){
        repository.insert(word)
    }

    // Needed when we use MutableLiveData instead of LiveData
//    fun getAllWord() = scope.launch(Dispatchers.IO) {
//      repository.getWords()
//    }


    override fun onCleared() {
        super.onCleared()
        parentJob.cancel()
    }
}