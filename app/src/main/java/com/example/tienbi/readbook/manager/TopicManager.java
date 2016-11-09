package com.example.tienbi.readbook.manager;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

import com.example.tienbi.readbook.App;
import com.example.tienbi.readbook.mode.Story;
import com.example.tienbi.readbook.mode.Topic;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

/**
 * Created by TienBi on 18/09/2016.
 */
public class TopicManager {
    String DATABASE_NAME = "dbReadStory.sqlite";
    private static final String DB_PATH_SUFFIX = "/databases/";
    SQLiteDatabase database = null;
    Context context;

    private static TopicManager instance;

    public static TopicManager getInstance() {
        if (instance == null) {
            instance = new TopicManager();
        }
        return instance;
    }

    public ArrayList<Topic> getTopics() {
        return topics;
    }

    private ArrayList<Topic> topics;
    private ArrayList<Story> stories;

    public TopicManager() {
        context = App.getContext();
        xuLySaoChepCSDL();
        topics = new ArrayList<>();
        stories = new ArrayList<>();
        loadStory();
        loadTopic();

        saveData();
    }

    private void saveData() {
        ArrayList<Story> s;
        for (int i = 0; i < topics.size(); i++) {
            s = new ArrayList<>();
            for (int j = 0; j < stories.size(); j++) {
                if (stories.get(j).getIdtopic().equals(topics.get(i).getIdtopic()))
                    s.add(stories.get(j));
            }
            topics.get(i).setStories(s);
        }
    }

    private void loadTopic() {
        Cursor cursor = database.rawQuery("Select * from tblTopic", null);
        topics.clear();
        while (cursor.moveToNext()) {
            String idtopic = cursor.getString(0);
            String topic = cursor.getString(1);
            String imageid = cursor.getString(2);
            topics.add(new Topic(idtopic, topic, imageid));
        }
        cursor.close();
    }

    private void loadStory() {
        //mo csdl
        database = context.openOrCreateDatabase(DATABASE_NAME, Context.MODE_PRIVATE, null);
        //Cursor cursor = database.query("Contact",null,null,null,null,null,null);
        Cursor cursor = database.rawQuery("Select * from tblStory", null);
        stories.clear();
        while (cursor.moveToNext()) {
            String idstory = cursor.getString(0);
            String title = cursor.getString(1);
            String content = cursor.getString(2);
            int like = cursor.getInt(3);
            String idtopic = cursor.getString(4);
            stories.add(new Story(idstory, title, content, like, idtopic));
        }
        cursor.close();
    }

    private void xuLySaoChepCSDL() {
        File dbFile = context.getDatabasePath(DATABASE_NAME);
        if (!dbFile.exists()) {
            try {
                copyDataBaseFromAsset();
                Toast.makeText(context, "Sao chép thành công", Toast.LENGTH_SHORT).show();
            } catch (Exception e) {
                Toast.makeText(context, e.toString(), Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void copyDataBaseFromAsset() {
        try {
            InputStream myInput = context.getAssets().open("data/" + DATABASE_NAME);
            String outFileName = layDuongDanLuuTru();
            File f = new File(context.getApplicationInfo().dataDir + DB_PATH_SUFFIX);
            if (!f.exists()) {
                f.mkdir();
            }
            OutputStream myOutput = new FileOutputStream(outFileName);
            byte[] buffer = new byte[1024];
            int length;
            while ((length = myInput.read(buffer)) > 0) {
                myOutput.write(buffer, 0, length);
            }
            myOutput.flush();
            myOutput.close();
            myInput.close();
        } catch (Exception ex) {
            Log.e("Loi", ex.toString());
        }
    }

    private String layDuongDanLuuTru() {
        return App.getContext().getApplicationInfo().dataDir + DB_PATH_SUFFIX + DATABASE_NAME;
    }

    public ArrayList<Story> loadStoryFavourite() {
        //mo csdl
        database = context.openOrCreateDatabase(DATABASE_NAME, Context.MODE_PRIVATE, null);
        //Cursor cursor = database.query("Contact",null,null,null,null,null,null);
        Cursor cursor = database.rawQuery("Select * from tblStory where like=1", null);
        ArrayList<Story> s = new ArrayList<>();
        while (cursor.moveToNext()) {
            String idstory = cursor.getString(0);
            String title = cursor.getString(1);
            String content = cursor.getString(2);
            int like = cursor.getInt(3);
            String idtopic = cursor.getString(4);
            s.add(new Story(idstory, title, content, like, idtopic));
        }
        cursor.close();
        return s;
    }

    public void xulyLike(String idStory, int like) {
        ContentValues row = new ContentValues();
        row.put("like", like);
        database.update("tblStory", row, "idstory=?", new String[]{idStory + ""});
    }
}
