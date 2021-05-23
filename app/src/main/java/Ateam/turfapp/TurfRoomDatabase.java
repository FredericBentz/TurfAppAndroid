package Ateam.turfapp;


import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * This is the backend. The database. This used to be done by the OpenHelper.
 * The fact that this has very few comments emphasizes its coolness.  In a real
 * app, consider exporting the schema to help you with migrations.
 */

@Database(entities = {Reunion.class}, version = 1, exportSchema = false)
abstract class TurfRoomDatabase extends RoomDatabase {

    abstract ReunionDao reunionDao();

    // marking the instance as volatile to ensure atomic access to the variable
    private static volatile TurfRoomDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    static TurfRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (TurfRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            TurfRoomDatabase.class, "turf_test")
                            .addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    /**
     * Override the onCreate method to populate the database.
     * For this sample, we clear the database every time it is created.
     */
    private static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);

            databaseWriteExecutor.execute(() -> {
                // Populate the database in the background.
                // If you want to start with more words, just add them.
                ReunionDao reunionDao = INSTANCE.reunionDao();
                reunionDao.deleteAll();

                Reunion reunion = new Reunion(225210, "2018-09-01 13:00:00", "VINCENNES", "1");
                reunionDao.insert(reunion);
                reunion = new Reunion(225214, "2018-09-01 11:00:00", "SAN SEBASTIAN", "2");
                reunionDao.insert(reunion);
            });
        }
    };
}
