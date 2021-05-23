package Ateam.turfapp;



import android.app.Application;
import androidx.lifecycle.LiveData;

import java.util.List;

/**
 * Abstracted Repository as promoted by the Architecture Guide.
 */

class TurfRepository {

    private ReunionDao _ReunionDao;
    private LiveData<List<Reunion>> _AllReunions;

    // Note that in order to unit test the WordRepository, you have to remove the Application
    // dependency. This adds complexity and much more code, and this sample is not about testing.
    TurfRepository(Application application) {
        TurfRoomDatabase db = TurfRoomDatabase.getDatabase(application);
        _ReunionDao = db.reunionDao();
        _AllReunions = _ReunionDao.getOrderedReunions();;
    }

    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    LiveData<List<Reunion>> getAllReunions() {
        return _AllReunions;
    }

    // You must call this on a non-UI thread or your app will throw an exception. Room ensures
    // that you're not doing any long running operations on the main thread, blocking the UI.
    void insert(Reunion reunion) {
        TurfRoomDatabase.databaseWriteExecutor.execute(() -> {
            _ReunionDao.insert(reunion);
        });
    }
}
