package com.example.testingmyentertainment.Beatles;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ToggleButton;

import com.example.testingmyentertainment.Musics.Mohiner_Ghoraguli;
import com.example.testingmyentertainment.Musics.MusicPlayer;
import com.example.testingmyentertainment.Musics.PlayerActivity;
import com.example.testingmyentertainment.R;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Arrays;
import java.util.HashSet;
import androidx.appcompat.widget.SearchView;

public class Beatles extends AppCompatActivity {

    private ListView listView;
    private SearchView searchView;
    private ArrayAdapter<String> adapter;
    private MusicPlayer musicPlayer;
    private HashSet<String> favoriteSongs = new HashSet<>();




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mohinerghoraguli);




        String[] songNames = {"Across The Universe", "And I Love Her","Let It Be ","Lucy In The Sky","Ob-La-Di,Ob-Li-Da","Oh!Darling","A day In Life","Come Together","Don't Let Me Down","Eleanor Rigby","Hello, Goodbye","Help!","Here Comes The Sun","Hey Jude","I Feel Fine","I want To Hold Your Hand","Something",
                "Strawberry Fields","While My Guiter Gently Weeps","Yellow Submarine"};
        String[] songUrls = {
                "https://firebasestorage.googleapis.com/v0/b/onlinemusic-9f1a8.appspot.com/o/Beatles%2FAcross%20The%20Universe%20(Remastered%202009).mp3?alt=media&token=4e94647c-7314-43d8-9bd7-309073a7a586",
                "https://firebasestorage.googleapis.com/v0/b/onlinemusic-9f1a8.appspot.com/o/Beatles%2FAnd%20I%20Love%20Her%20(Remastered%202009).mp3?alt=media&token=1c6d9f53-22b9-4809-a9e9-082b73983eb2",
                "https://firebasestorage.googleapis.com/v0/b/onlinemusic-9f1a8.appspot.com/o/Beatles%2FLet%20It%20Be%20(Remastered%202009).mp3?alt=media&token=af0fd9ba-bf78-4724-bacb-c5fafe04611c",
                "https://firebasestorage.googleapis.com/v0/b/onlinemusic-9f1a8.appspot.com/o/Beatles%2FLucy%20In%20The%20Sky%20With%20Diamonds%20(Remastered%202009).mp3?alt=media&token=4e18f354-2fc0-4b87-9ff3-5964c7c5092d",
                "https://firebasestorage.googleapis.com/v0/b/onlinemusic-9f1a8.appspot.com/o/Beatles%2FOb-La-Di%2C%20Ob-La-Da%20(Remastered%202009).mp3?alt=media&token=fad661c7-98bb-4b49-b0fb-bea66b3b8c7b",
                "https://firebasestorage.googleapis.com/v0/b/onlinemusic-9f1a8.appspot.com/o/Beatles%2FOh!%20Darling%20(Remastered%202009).mp3?alt=media&token=05ff9c23-7afc-4d5a-be61-b80969d9e128",
                "https://firebasestorage.googleapis.com/v0/b/onlinemusic-9f1a8.appspot.com/o/Beatles%2FThe%20Beatles%20-%20A%20Day%20In%20The%20Life.mp3?alt=media&token=b4aa962e-d8aa-4a53-8ff4-59ebeb52bb75",
                "https://firebasestorage.googleapis.com/v0/b/onlinemusic-9f1a8.appspot.com/o/Beatles%2FThe%20Beatles%20-%20Come%20Together.mp3?alt=media&token=7fbbd1e9-9429-4504-be4e-b7325d8c656e",
                "https://firebasestorage.googleapis.com/v0/b/onlinemusic-9f1a8.appspot.com/o/Beatles%2FThe%20Beatles%20-%20Don't%20Let%20Me%20Down.mp3?alt=media&token=38efc564-9620-4ee6-807c-6d0fd3609e7f",
                "https://firebasestorage.googleapis.com/v0/b/onlinemusic-9f1a8.appspot.com/o/Beatles%2FThe%20Beatles%20-%20Eleanor%20Rigby%20(From%20Yellow%20Submarine).mp3?alt=media&token=c3953b0c-ad3b-49ae-9e52-ffd2a6bd6b45",
                "https://firebasestorage.googleapis.com/v0/b/onlinemusic-9f1a8.appspot.com/o/Beatles%2FThe%20Beatles%20-%20Hello%2C%20Goodbye.mp3?alt=media&token=9485c32a-89bb-40bd-81cb-500c6ccc535b",
                "https://firebasestorage.googleapis.com/v0/b/onlinemusic-9f1a8.appspot.com/o/Beatles%2FThe%20Beatles%20-%20Help!.mp3?alt=media&token=a663bd1c-26ab-4fe0-b23e-2b7baea716cb",
                "https://firebasestorage.googleapis.com/v0/b/onlinemusic-9f1a8.appspot.com/o/Beatles%2FThe%20Beatles%20-%20Here%20Comes%20The%20Sun%20(2019%20Mix).mp3?alt=media&token=8c86a85c-e54a-4066-a6c1-6ab70853f2af",
                "https://console.firebase.google.com/project/onlinemusic-9f1a8/storage/onlinemusic-9f1a8.appspot.com/files/~2FBeatles",
                "https://firebasestorage.googleapis.com/v0/b/onlinemusic-9f1a8.appspot.com/o/Beatles%2FThe%20Beatles%20-%20I%20Feel%20Fine.mp3?alt=media&token=26fcdcad-ab92-4394-84b5-e5f41031f261",
                "https://firebasestorage.googleapis.com/v0/b/onlinemusic-9f1a8.appspot.com/o/Beatles%2FThe%20Beatles%20-%20I%20Want%20To%20Hold%20Your%20Hand%20-%20Performed%20Live%20On%20The%20Ed%20Sullivan%20Show%202964.mp3?alt=media&token=162beaa9-711c-4ffe-822c-48384e9eb8fb",
                "https://firebasestorage.googleapis.com/v0/b/onlinemusic-9f1a8.appspot.com/o/Beatles%2FThe%20Beatles%20-%20Something.mp3?alt=media&token=bbb91441-9874-4f40-a734-5ab94b8e247a",
                "https://firebasestorage.googleapis.com/v0/b/onlinemusic-9f1a8.appspot.com/o/Beatles%2FThe%20Beatles%20-%20Strawberry%20Fields%20Forever.mp3?alt=media&token=0af8eabe-107c-4057-a09d-3f44fc29df93",
                "https://firebasestorage.googleapis.com/v0/b/onlinemusic-9f1a8.appspot.com/o/Beatles%2FThe%20Beatles%20-%20While%20My%20Guitar%20Gently%20Weeps.mp3?alt=media&token=843d3d2e-b9bc-4b3a-979f-3cee51eae0bb",
                "https://firebasestorage.googleapis.com/v0/b/onlinemusic-9f1a8.appspot.com/o/Beatles%2FThe%20Beatles%20-%20Yellow%20Submarine.mp3?alt=media&token=ac6084ff-3fdd-4f87-a617-7902068e22cd"



        };

//        FirebaseApp.initializeApp(this);

        musicPlayer = new MusicPlayer(songNames, songUrls);

        listView = findViewById(R.id.listView);
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Favorites");




        Button btnShowFavorites = findViewById(R.id.btnShowFavorites);
        btnShowFavorites.setOnClickListener(v -> {
            Intent intent = new Intent(Beatles.this, FavoriteSongsActivity.class);
            intent.putExtra("songNames", songNames); // pass array of song names
            intent.putExtra("songUrls", songUrls); // pass array of song URLs
            startActivity(intent);
        });



        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.list_item_songbeatles, R.id.item_song_title, songNames) {
            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                ToggleButton toggleButton = view.findViewById(R.id.favorite_toggle);
                String song = getItem(position);
                boolean isFavorite = favoriteSongs.contains(song);

                // Temporarily remove the listener
                toggleButton.setOnCheckedChangeListener(null);

                toggleButton.setChecked(isFavorite);

                // Re-apply the listener
                toggleButton.setOnCheckedChangeListener((buttonView, isChecked) -> {
                    if (isChecked) {
                        favoriteSongs.add(song);
                    } else {
                        favoriteSongs.remove(song);
                    }

                    // Save the favorites status to SharedPreferences
                    SharedPreferences prefs = getContext().getSharedPreferences("Favorites", MODE_PRIVATE);
                    SharedPreferences.Editor editor = prefs.edit();
                    editor.putStringSet("FavoriteSongs", favoriteSongs);
                    editor.apply();
                });

                return view;
            }
        };
        listView.setAdapter(adapter);


        searchView = findViewById(R.id.search_view);



        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return true;
            }
        });


        listView.setOnItemClickListener((parent, view, position, id) -> {
            String selectedItem = adapter.getItem(position); // Get the item from the adapter, not directly from array
            if (selectedItem != null) {
                int actualIndex = Arrays.asList(songNames).indexOf(selectedItem);
                if (actualIndex >= 0 && actualIndex < songUrls.length) {
                    Intent intent = new Intent(Beatles.this, PlayerActivity.class);
                    intent.putExtra("songNames", songNames);
                    intent.putExtra("songUrls", songUrls);
                    intent.putExtra("currentIndex", actualIndex);
                    startActivity(intent);
                } else {
                    Log.e("Bealtles", "Selected position is out of bounds: " + actualIndex);
                }
            }
        });


    }
}