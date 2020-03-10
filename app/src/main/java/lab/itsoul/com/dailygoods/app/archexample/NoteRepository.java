package lab.itsoul.com.dailygoods.app.archexample;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class NoteRepository {
   private INoteDao noteDao ;
   LiveData<List<Note>> allNotes ;

   NoteRepository(Application application) {
       //Create the database
       NoteDatabase database = NoteDatabase.getInstance(application.getApplicationContext());
       //get the Dao from the database
       /**
        * here it is possible beacuse
        * Room instanciates the abstract class Note database
        * and implements the abstract function of Dao
        */
       noteDao = database.noteDao();
       allNotes = noteDao.getAllNotes();
   }

   public void insertNote (Note note) {
        new InsertNoteAsync(this.noteDao).doInBackground(note);
   }

    public void updateNote (Note note) {
       new updateNoteAsync(this.noteDao).doInBackground(note);
    }

    public void deleteNote (Note note) {
        new deleteNoteAsync(this.noteDao).doInBackground(note);
    }

    public LiveData<List<Note>> getAllNotes ( ) {
        return this.allNotes ;
    }

    public void deleteAllnotes ( ) {

    }

    private static class InsertNoteAsync extends AsyncTask<Note,Void , Void>{
       private INoteDao dao ;

       InsertNoteAsync(INoteDao dao){
           this.dao = dao ;
       }

        @Override
        protected Void doInBackground(Note... notes) {
           dao.insert(notes[0]);
           return null;
        }
    }

    private static class updateNoteAsync extends AsyncTask<Note,Void , Void>{
        private INoteDao dao ;

        updateNoteAsync(INoteDao dao){
            this.dao = dao ;
        }

        @Override
        protected Void doInBackground(Note... notes) {
            dao.insert(notes[0]);
            return null;
        }
    }

    private static class deleteNoteAsync extends AsyncTask<Note,Void , Void>{
        private INoteDao dao ;

        deleteNoteAsync(INoteDao dao){
            this.dao = dao ;
        }

        @Override
        protected Void doInBackground(Note... notes) {
            dao.insert(notes[0]);
            return null;
        }
    }

    private static class deleteAllNoteAsync extends AsyncTask<Void,Void , Void>{
        private INoteDao dao ;

        deleteAllNoteAsync(INoteDao dao){
            this.dao = dao ;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            dao.deleteAll();
            return null;
        }
    }
}
