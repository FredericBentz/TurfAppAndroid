package Ateam.turfapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;


public class MainActivity extends AppCompatActivity {

    public static final int NEW_REUNION_ACTIVITY_REQUEST_CODE = 1;

    private ReunionViewModel _ReunionViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        final ReunionListAdapter adapter = new ReunionListAdapter(new ReunionListAdapter.ReunionDiff());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Get a new or existing ViewModel from the ViewModelProvider.
        _ReunionViewModel = new ViewModelProvider(this).get(ReunionViewModel.class);

        // Add an observer on the LiveData returned by getAlphabetizedReunions.
        // The onChanged() method fires when the observed data changes and the activity is
        // in the foreground.
        _ReunionViewModel.getAllReunions().observe(this, Reunions -> {
            // Update the cached copy of the Reunions in the adapter.
            adapter.submitList(Reunions);
        });

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, NewReunionActivity.class);
            startActivityForResult(intent, NEW_REUNION_ACTIVITY_REQUEST_CODE);
        });
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == NEW_REUNION_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {
//            Reunion reunion = new Reunion(data.getStringExtra(NewReunionActivity.EXTRA_REPLY));
//            _ReunionViewModel.insert(reunion); // todo
        } else {
            Toast.makeText(
                    getApplicationContext(),
                    R.string.empty_not_saved,
                    Toast.LENGTH_LONG).show();
        }
    }
}
