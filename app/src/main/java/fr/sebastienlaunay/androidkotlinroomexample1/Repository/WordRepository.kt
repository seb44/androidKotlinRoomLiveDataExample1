package fr.sebastienlaunay.androidkotlinroomexample1.Repository

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.support.annotation.WorkerThread
import fr.sebastienlaunay.androidkotlinroomexample1.Database.IWordDao
import fr.sebastienlaunay.androidkotlinroomexample1.Model.Word

class WordRepository(private val wordDAO: IWordDao) {

    // With LiveData, Room generates all necessary code to update the LiveData when the database is updated.
    // If you use LiveData independently from Room, you have to manage updating the data. LiveData has no publicly available methods to update the stored data.
    // Outside Room, you must use MutableLiveData instead of LiveData.
    // The MutableLiveData class has two public methods that allow you to set the value of a LiveData object, setValue(T) and postValue(T).
    // Usually, MutableLiveData is used in the ViewModel, and then the ViewModel only exposes immutable LiveData objects to the observers.


    val allWords: LiveData<List<Word>> = wordDAO.getAllWords()

    // Exemple of MutableLiveData use - See also all comment "Needed when we use MutableLiveData instead of LiveData" in this project
    // Needed when we use MutableLiveData instead of LiveData
    // val allWords: MutableLiveData<List<Word>> = MutableLiveData()

    @WorkerThread
    suspend fun insert(word: Word) {
        wordDAO.insert(word)

        // When we use Room with LiveData, no need to update datas (words) to display them on the UI. It is done automatically
        // Needed when we user MutableLiveData instead of LiveData
        // allWords.postValue(wordDAO.getAllWords())

    }


    // Needed when we user MutableLiveData instead of LiveData
//    @WorkerThread
//    suspend fun getWords() {
//        allWords.postValue(wordDAO.getAllWords())
//    }

}