package Ateam.turfapp;


import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

/**
 * View Model to keep a reference to the word repository and
 * an up-to-date list of all words.
 */

public class ReunionViewModel extends AndroidViewModel {

    private TurfRepository _Repository;
    // Using LiveData and caching what getAlphabetizedWords returns has several benefits:
    // - We can put an observer on the data (instead of polling for changes) and only update the
    //   the UI when the data actually changes.
    // - Repository is completely separated from the UI through the ViewModel.
    private final LiveData<List<Reunion>> _AllReunions;

    public ReunionViewModel(Application application) {
        super(application);
        _Repository = new TurfRepository(application);
        _AllReunions = _Repository.getAllReunions();
    }

    LiveData<List<Reunion>> getAllReunions() {
        return _AllReunions;
    }

    void insert(Reunion reunion) {
        _Repository.insert(reunion);
    }
}
