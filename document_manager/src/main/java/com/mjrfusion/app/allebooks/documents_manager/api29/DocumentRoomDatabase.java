package com.mjrfusion.app.allebooks.documents_manager.api29;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.mjrfusion.app.allebooks.documents_manager.model.Document;
import com.mjrfusion.app.allebooks.utils.converter.DateConverter;
import com.mjrfusion.app.allebooks.utils.converter.SizeConverter;
import com.mjrfusion.app.allebooks.utils.converter.UriConverter;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Document.class}, version = 1, exportSchema = false)
@TypeConverters({DateConverter.class, UriConverter.class, SizeConverter.class})
public abstract class DocumentRoomDatabase extends RoomDatabase {
    public abstract DocumentDao documentDao();

    private static volatile DocumentRoomDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 5;
    public static final ExecutorService databaseWriterExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    static DocumentRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null)
            synchronized (DocumentRoomDatabase.class) {
            if (INSTANCE == null)
                INSTANCE = Room.databaseBuilder(context, DocumentRoomDatabase.class,
                        "documents_database.db").build();
            }
        return INSTANCE;
    }
}
