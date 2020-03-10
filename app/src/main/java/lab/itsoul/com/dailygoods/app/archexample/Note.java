package lab.itsoul.com.dailygoods.app.archexample;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity( tableName = "note_table")
public class Note {

    @PrimaryKey(autoGenerate = true)
    private int id ;
    private int priority ;
    private String title ;
    private String description ;

    //since id is auto generated
    public Note(String title, String description, int priority) {
        this.title = title;
        this.description = description;
        this.priority = priority;
    }

    //since all the fields are private ,we need public getter


    public int getId() {
        return id;
    }

    public int getPriority() {
        return priority;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }
}
