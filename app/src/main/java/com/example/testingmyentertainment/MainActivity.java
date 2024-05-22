package com.example.testingmyentertainment;

import static com.example.testingmyentertainment.SetcourseModelArray.add_to_list;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import androidx.appcompat.widget.SearchView;


import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.testingmyentertainment.Beatles.Beatles;
//import com.example.testingmyentertainment.Classical.Folk_song;
import com.example.testingmyentertainment.Musics.Mohiner_Ghoraguli;
import com.example.testingmyentertainment.Musics.mohinerghoraguli;
import com.example.testingmyentertainment.adapyer.CourseGVAdapter;
import com.example.testingmyentertainment.adapyer.DetailActivity;
import com.example.testingmyentertainment.adapyer.DetailActivity1;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.ListResult;
import com.google.firebase.storage.StorageReference;
//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {




    private void click_grid(GridView gridView, int length, int start, ArrayList<CourseModel> courseModelArrayList){
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = null;

               for(int i=0; i<length; i++){
                   if(position == i){
                       intent = new Intent(MainActivity.this, Beatles.class);
                       CourseModel c = courseModelArrayList.get(i+start);
                       intent.putExtra("name", c.getCourse_name());
                   }
               }
               startActivity(intent);
            }
        });


    }


    private  void addSearchview(SearchView searchView, ArrayList<CourseModel> courseModelArrayList){
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // Search for the query in courseModelArrayList
                for (CourseModel course : courseModelArrayList) {
                    if (course.getCourse_name().equalsIgnoreCase(query)) {
                        // If a match is found, decide which activity to start based on the name
                        Intent intent = null;
                        switch (course.getCourse_name()) {
                            case "Beatles":
                                intent = new Intent(MainActivity.this, DetailActivity.class);
                                break;
                            case "AC/DC":
                                intent = new Intent(MainActivity.this, DetailActivity1.class);
                                break;
                            // Add other cases for different course names and their corresponding activities
                        }
                        if (intent != null) {
                            startActivity(intent);
                            return true; // Return true indicating query handled
                        }
                        break; // Exit the loop once a match is found or handled
                    }
                }
                Toast.makeText(MainActivity.this, "No match found", Toast.LENGTH_SHORT).show();
                return false; // Return false if no match found
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                // Optional: Implement real-time search filtering if required
                return false;
            }
        });
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayList<CourseModel> courseModelArrayList = new ArrayList<CourseModel>();


//


        add_to_list(courseModelArrayList);
        SearchView searchView = findViewById(R.id.search_view);


        addSearchview(searchView, courseModelArrayList);



        // Create a storage reference from our app








        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference listRef = storage.getReference();
//        listRef = listRef.child("Musics");
        Log.d("hafiz", listRef.getName());

        listRef.listAll()
                .addOnSuccessListener(new OnSuccessListener<ListResult>() {
                    @Override
                    public void onSuccess(ListResult listResult) {
                        for (StorageReference prefix : listResult.getPrefixes()) {
                            // All the prefixes under listRef.
                            // You may call listAll() recursively on them.
                            Log.d("hafiz", prefix.getName());
                        }

                        for (StorageReference item : listResult.getItems()) {
                            // All the items under listRef.
                            Log.d("hafizitem", item.getName());

                                item.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                    @Override
                                    public void onSuccess(Uri uri) {
                                        // Got the download URL for 'users/me/profile.png'
                                        String s = uri.toString();
                                        Log.d("hafizitem", s);

                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception exception) {
                                        // Handle any errors
                                        Log.e("jibon", "listAll error", exception);

                                    }
                                });

                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        // Uh-oh, an error occurred!
//
                        Log.e("jibon", "listAll error", e);
                    }
                });































        // Ensure you do not go out of bounds when creating subLists
        // Assuming each GridView should show up to 4 items.
        // Assuming each of the first two GridViews should show up to 4 items,
// and the third GridView should show up to 8 items.
        int size = courseModelArrayList.size();
        int firstChunkSize = 6; // Maximum items for the first and second lists
        int thirdChunkSize = 12; // Maximum items for the third list
        int fourthChunkSize = 6; // Maximum items for the fourth list
        int fifthChunkSize = 18; // Maximum items for the fifth list

        ArrayList<CourseModel> firstList = new ArrayList<>(courseModelArrayList.subList(0, Math.min(firstChunkSize, size)));

        ArrayList<CourseModel> secondList = new ArrayList<>();
        if (size > firstChunkSize) {
            secondList = new ArrayList<>(courseModelArrayList.subList(firstChunkSize, Math.min(2 * firstChunkSize, size)));
        }

        ArrayList<CourseModel> thirdList = new ArrayList<>();
        if (size > 2 * firstChunkSize) {
            thirdList = new ArrayList<>(courseModelArrayList.subList(2 * firstChunkSize, Math.min(2 * firstChunkSize + thirdChunkSize, size)));
        }

        ArrayList<CourseModel> fourthList = new ArrayList<>();
        if (size > 2 * firstChunkSize + thirdChunkSize) {
            fourthList = new ArrayList<>(courseModelArrayList.subList(2 * firstChunkSize + thirdChunkSize, Math.min(2 * firstChunkSize + thirdChunkSize + fourthChunkSize, size)));
        }

        ArrayList<CourseModel> fifthList = new ArrayList<>();
        if (size > 2 * firstChunkSize + thirdChunkSize + fourthChunkSize) {
            fifthList = new ArrayList<>(courseModelArrayList.subList(2 * firstChunkSize + thirdChunkSize + fourthChunkSize, Math.min(2 * firstChunkSize + thirdChunkSize + fourthChunkSize + fifthChunkSize, size)));
        }






        GridView gridView1 = findViewById(R.id.idGVcourses1);
        CourseGVAdapter adapter1 = new CourseGVAdapter(this, firstList);
        gridView1.setAdapter(adapter1);

        GridView gridView2 = findViewById(R.id.idGVcourses2);
        CourseGVAdapter adapter2 = new CourseGVAdapter(this, secondList);
        gridView2.setAdapter(adapter2);

        GridView gridView3 = findViewById(R.id.idGVcourses3);
        CourseGVAdapter adapter3 = new CourseGVAdapter(this, thirdList);
        gridView3.setAdapter(adapter3);

        GridView gridView4 = findViewById(R.id.idGVcourses4); // Ensure this ID matches your layout
        CourseGVAdapter adapter4 = new CourseGVAdapter(this, fourthList);
        gridView4.setAdapter(adapter4);

        GridView gridView5 = findViewById(R.id.idGVcourses5); // Ensure this ID matches your layout
        CourseGVAdapter adapter5 = new CourseGVAdapter(this, fifthList);
        gridView5.setAdapter(adapter5);




//        gridView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Intent intent = null;
//
//                if (position == 0) {
//                    intent = new Intent(MainActivity.this, mohinerghoraguli.class);
//                } else if (position == 1) {
//                    intent = new Intent(MainActivity.this, Mohiner_Ghoraguli.class);
//                } else if (position == 2) {
//                    intent = new Intent(MainActivity.this, Folk_song.class);
//                }else if (position == 3) {
//                    intent = new Intent(MainActivity.this, DetailActivity.class);
//                }else if (position == 4) {
//                    intent = new Intent(MainActivity.this, DetailActivity.class);
//                }else if (position == 5) {
//                    intent = new Intent(MainActivity.this, Mohiner_Ghoraguli.class);
//                }
//                else {
//                    intent = new Intent(MainActivity.this, DetailActivity1.class);
//                    intent.putExtra("EXTRA_POSITION", position);
//                }
//
//                if (intent != null) {
//                    startActivity(intent);
//                }
//            }
//        });

        click_grid(gridView1,6,0, courseModelArrayList);
        click_grid(gridView2,6,6, courseModelArrayList);
        click_grid(gridView3,12,12, courseModelArrayList);
        click_grid(gridView4,6,24, courseModelArrayList);
        click_grid(gridView5,18,30, courseModelArrayList);



//        gridView2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Intent intent = null;
//
//                // Since this is gridView2, positions should start from 0 for this grid's list
//                if (position == 0) {
//                    intent = new Intent(MainActivity.this, Mohiner_Ghoraguli.class);
//                } else if (position == 1) {
//                    intent = new Intent(MainActivity.this, Beatles.class);
//                } else if (position == 2) {
//                    intent = new Intent(MainActivity.this, DetailActivity1.class);
//                } else if (position == 3) {
//                    intent = new Intent(MainActivity.this, DetailActivity1.class);
//                } else if (position == 4) {
//                    intent = new Intent(MainActivity.this, DetailActivity1.class);
//                } else if (position == 5) {
//                    intent = new Intent(MainActivity.this, DetailActivity1.class);
//                } else {
//                    // Assuming this logic is for additional positions if any.
//                    intent = new Intent(MainActivity.this, DetailActivity1.class);
//                    intent.putExtra("EXTRA_POSITION", position);
//                }
//
//                if (intent != null) {
//                    startActivity(intent);
//                }
//            }
//        });
//
//
//
//        gridView3.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Intent intent = null;
//
//                if (position == 0) {
//                    intent = new Intent(MainActivity.this, DetailActivity.class);
//                } else if (position == 1) {
//                    intent = new Intent(MainActivity.this, DetailActivity1.class);
//                } else if (position == 2) {
//                    intent = new Intent(MainActivity.this, DetailActivity1.class);
//                } else if (position == 3) {
//                    intent = new Intent(MainActivity.this, DetailActivity1.class);
//                } else if (position == 4) {
//                    intent = new Intent(MainActivity.this, DetailActivity1.class);
//                } else if (position == 5) {
//                    intent = new Intent(MainActivity.this, DetailActivity1.class);
//                }
//                else {
//                    intent = new Intent(MainActivity.this, DetailActivity1.class);
//                    intent.putExtra("EXTRA_POSITION", position);
//                }
//
//                if (intent != null) {
//                    startActivity(intent);
//                }
//            }
//        });
//
//        gridView3.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Intent intent = null;
//
//                if (position == 0) {
//                    intent = new Intent(MainActivity.this, DetailActivity.class);
//                } else if (position == 1) {
//                    intent = new Intent(MainActivity.this, DetailActivity1.class);
//                } else if (position == 2) {
//                    intent = new Intent(MainActivity.this, DetailActivity1.class);
//                } else if (position == 3) {
//                    intent = new Intent(MainActivity.this, DetailActivity1.class);
//                } else if (position == 4) {
//                    intent = new Intent(MainActivity.this, DetailActivity1.class);
//                } else if (position == 5) {
//                    intent = new Intent(MainActivity.this, DetailActivity1.class);
//                }
//                else {
//                    intent = new Intent(MainActivity.this, DetailActivity1.class);
//                    intent.putExtra("EXTRA_POSITION", position);
//                }
//
//                if (intent != null) {
//                    startActivity(intent);
//                }
//            }
//        });
//
//
//        gridView4.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Intent intent = null;
//
//                if (position == 0) {
//                    intent = new Intent(MainActivity.this, Mohiner_Ghoraguli.class);
//                } else if (position == 1) {
//                    intent = new Intent(MainActivity.this, DetailActivity1.class);
//                } else if (position == 2) {
//                    intent = new Intent(MainActivity.this, DetailActivity1.class);
//                } else if (position == 3) {
//                    intent = new Intent(MainActivity.this, DetailActivity1.class);
//                } else if (position == 4) {
//                    intent = new Intent(MainActivity.this, DetailActivity1.class);
//                } else if (position == 5) {
//                    intent = new Intent(MainActivity.this, DetailActivity1.class);
//                }
//                else {
//                    intent = new Intent(MainActivity.this, DetailActivity1.class);
//                    intent.putExtra("EXTRA_POSITION", position);
//                }
//
//                if (intent != null) {
//                    startActivity(intent);
//                }
//            }
//        });
//
//
//
//        gridView5.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Intent intent = null;
//
//                if (position == 0) {
//                    intent = new Intent(MainActivity.this, Mohiner_Ghoraguli.class);
//                } else if (position == 1) {
//                    intent = new Intent(MainActivity.this, DetailActivity1.class);
//                } else if (position == 2) {
//                    intent = new Intent(MainActivity.this, DetailActivity1.class);
//                } else if (position == 3) {
//                    intent = new Intent(MainActivity.this, DetailActivity1.class);
//                } else if (position == 4) {
//                    intent = new Intent(MainActivity.this, DetailActivity1.class);
//                } else if (position == 5) {
//                    intent = new Intent(MainActivity.this, DetailActivity1.class);
//                } if (position == 6) {
//                    intent = new Intent(MainActivity.this, DetailActivity.class);
//                } else if (position == 7) {
//                    intent = new Intent(MainActivity.this, DetailActivity1.class);
//                } else if (position == 8) {
//                    intent = new Intent(MainActivity.this, DetailActivity1.class);
//                } else if (position == 9) {
//                    intent = new Intent(MainActivity.this, DetailActivity1.class);
//                } else if (position == 10) {
//                    intent = new Intent(MainActivity.this, DetailActivity1.class);
//                } else if (position == 11) {
//                    intent = new Intent(MainActivity.this, DetailActivity1.class);
//                } if (position == 12) {
//                    intent = new Intent(MainActivity.this, DetailActivity.class);
//                } else if (position == 13) {
//                    intent = new Intent(MainActivity.this, DetailActivity1.class);
//                } else if (position == 14) {
//                    intent = new Intent(MainActivity.this, DetailActivity1.class);
//                } else if (position == 15) {
//                    intent = new Intent(MainActivity.this, DetailActivity1.class);
//                } else if (position == 16) {
//                    intent = new Intent(MainActivity.this, DetailActivity1.class);
//                } else if (position == 17) {
//                    intent = new Intent(MainActivity.this, DetailActivity1.class);
//                }
//                else {
//                    intent = new Intent(MainActivity.this, DetailActivity1.class);
//                    intent.putExtra("EXTRA_POSITION", position);
//                }
//
//                if (intent != null) {
//                    startActivity(intent);
//                }
//            }
//        });







    }
}