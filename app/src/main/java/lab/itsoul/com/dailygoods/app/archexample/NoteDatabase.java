package lab.itsoul.com.dailygoods.app.archexample;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.List;

@Database(entities = {Note.class}, version = 1)
public abstract class NoteDatabase extends RoomDatabase {

    private static NoteDatabase instance;

    //the data access object (DAO) has to be abstract
    public abstract INoteDao noteDao();

    public static synchronized NoteDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(), NoteDatabase.class, "note_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(callback)
                    .build();
        }
        return instance;
    }

    private static RoomDatabase.Callback callback = new Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbTaskAsync((NoteDatabase) db).execute();
        }
    };

    private static class PopulateDbTaskAsync extends AsyncTask<Void, Void, Void> {
        INoteDao noteDao ;

        PopulateDbTaskAsync(NoteDatabase database){
            noteDao = database.noteDao() ;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            noteDao.insert( new Note("Go to bazr", "buy things for keto diet" , 1));
            noteDao.insert( new Note("Go to NAamaz", "buy things for keto diet" , 2));
            noteDao.insert( new Note("Go to gym", "buy things for keto diet" , 3));
            noteDao.insert( new Note("Go to chittagong", "buy things for keto diet" , 4));
            return null;
        }
    }


}
