package fr.sebastienlaunay.androidkotlinroomexample1.Database

import android.arch.persistence.db.SupportSQLiteDatabase
import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import fr.sebastienlaunay.androidkotlinroomexample1.Model.Word
import kotlinx.coroutines.experimental.CoroutineScope
import kotlinx.coroutines.experimental.Dispatchers
import kotlinx.coroutines.experimental.IO
import kotlinx.coroutines.experimental.launch


@Database(entities = [Word::class], version = 1)
public abstract class WordRoomDatabase : RoomDatabase() {

    abstract fun wordDao(): IWordDao


    companion object {

        @Volatile
        private var INSTANCE: WordRoomDatabase? = null

   //     fun getDatabase(context: Context, scope: CoroutineScope): WordRoomDatabase {
        fun getDatabase(context: Context): WordRoomDatabase {

            val tempInstance = INSTANCE

            if (tempInstance != null)
                return tempInstance

            synchronized(this) {
                var instance = Room.databaseBuilder(context.applicationContext,WordRoomDatabase::class.java,"Word_database")
                   // .addCallback(WordDatabaseCallback(scope))
                    .build()

                INSTANCE = instance

                return instance
            }
        }


    }


/*    private class WordDatabaseCallback(private val scope: CoroutineScope) : RoomDatabase.Callback() {

        override fun onOpen(db: SupportSQLiteDatabase) {
            super.onOpen(db)
            INSTANCE?.let { database ->
                scope.launch(Dispatchers.IO) {
                    populateDatabase(database.wordDao())
                }
            }
        }

        fun populateDatabase(wordDao: IWordDao) {
            wordDao.deleteAll()

            var word = Word("Hello")
            wordDao.insert(word)
            word = Word("World!")
            wordDao.insert(word)
        }
    }*/
}