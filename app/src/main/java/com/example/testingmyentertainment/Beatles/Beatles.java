package com.example.testingmyentertainment.Beatles;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
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
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.ListResult;
import com.google.firebase.storage.StorageReference;

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

        String[] songNames1 = new String[30];
        String[] songUrls1 = new String[30];

        Intent i = getIntent();
        String nn = i.getStringExtra("name");

        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference listRef = storage.getReference();
        listRef = listRef.child("Musics");

        if (nn != null && !nn.isEmpty()) {
            listRef = listRef.child(nn);
            Log.d("hafiz2", listRef.getName());
            final int[] nindex = {0};
            final int[] uindex = {0};

            listRef.listAll()
                    .addOnSuccessListener(new OnSuccessListener<ListResult>() {
                        @Override
                        public void onSuccess(ListResult listResult) {
                            for (StorageReference item : listResult.getItems()) {
                                String name = item.getName();
                                Log.d("hafizitem2", name);
                                songNames1[nindex[0]] = name;
                                nindex[0]++;
                                Log.d("nindex", String.valueOf(nindex[0]));

                                item.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                    @Override
                                    public void onSuccess(Uri uri) {
                                        String s = uri.toString();
                                        Log.d("hafizitem2", s);
                                        songUrls1[uindex[0]] = s;
                                        uindex[0]++;
                                        Log.d("uindex", String.valueOf(nindex[0]));
                                        if (nindex[0] == uindex[0]) {
                                            int sn = nindex[0];
                                            Log.d("sn", String.valueOf(sn));
                                            int su = uindex[0];
                                            String[] songNames = new String[sn];
                                            String[] songUrls = new String[su];
                                            for (int i = 0; i < sn; i++) {
                                                songNames[i] = songNames1[i];
                                                songUrls[i] = songUrls1[i];
                                            }
                                            musicPlayer = new MusicPlayer(songNames, songUrls);

                                            listView = findViewById(R.id.listView);

                                            Button btnShowFavorites = findViewById(R.id.btnShowFavorites);
                                            btnShowFavorites.setOnClickListener(v -> {
                                                Intent intent = new Intent(Beatles.this, FavoriteSongsActivity.class);
                                                intent.putExtra("songNames", songNames);
                                                intent.putExtra("songUrls", songUrls);
                                                startActivity(intent);
                                            });

                                            ArrayAdapter<String> adapter = new ArrayAdapter<String>(Beatles.this, R.layout.list_item_songbeatles, R.id.item_song_title, songNames) {
                                                @NonNull
                                                @Override
                                                public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                                                    View view = super.getView(position, convertView, parent);
                                                    ToggleButton toggleButton = view.findViewById(R.id.favorite_toggle);
                                                    String song = getItem(position);
                                                    boolean isFavorite = favoriteSongs.contains(song);

                                                    toggleButton.setOnCheckedChangeListener(null);
                                                    toggleButton.setChecked(isFavorite);
                                                    toggleButton.setOnCheckedChangeListener((buttonView, isChecked) -> {
                                                        if (isChecked) {
                                                            favoriteSongs.add(song);
                                                        } else {
                                                            favoriteSongs.remove(song);
                                                        }

                                                        SharedPreferences prefs = getSharedPreferences("Favorites", MODE_PRIVATE);
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
                                                String selectedItem = adapter.getItem(position);
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
                                }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception exception) {
                                        Log.e("jibon2", "listAll error", exception);
                                    }
                                });
                            }
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.e("jibon2", "listAll error", e);
                        }
                    });
        } else {
            Log.e("Beatles", "Child name (nn) is null or empty");
            // Handle the case where nn is null or empty, perhaps by displaying an error message or taking appropriate action
        }
    }

}