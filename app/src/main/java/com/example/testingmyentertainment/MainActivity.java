package com.example.testingmyentertainment;

import static com.example.testingmyentertainment.SetcourseModelArray.add_to_list;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.example.testingmyentertainment.Beatles.Beatles;
import com.example.testingmyentertainment.adapyer.adapter;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.ListResult;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.FirebaseStorage;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ArrayList<CourseModel> courseModelArrayList;
    private ArrayList<CourseModel> filteredList;
    private adapter adapter1, adapter2, adapter3, adapter4, adapter5;
    private GridView gridView1, gridView2, gridView3, gridView4, gridView5;
  


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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        courseModelArrayList = new ArrayList<>();
        filteredList = new ArrayList<>();
        add_to_list(courseModelArrayList);
        filteredList.addAll(courseModelArrayList);

        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference listRef = storage.getReference();
        Log.d("hafiz", listRef.getName());

        listRef.listAll()
                .addOnSuccessListener(new OnSuccessListener<ListResult>() {
                    @Override
                    public void onSuccess(ListResult listResult) {
                        for (StorageReference prefix : listResult.getPrefixes()) {
                            Log.d("hafiz", prefix.getName());
                        }

                        for (StorageReference item : listResult.getItems()) {
                            Log.d("hafizitem", item.getName());

                            item.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {
                                    String s = uri.toString();
                                    Log.d("hafizitem", s);
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception exception) {
                                    Log.e("jibon", "listAll error", exception);
                                }
                            });
                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.e("jibon", "listAll error", e);
                    }
                });

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

        gridView1 = findViewById(R.id.idGVcourses1);
        adapter1 = new adapter(this, firstList);
        gridView1.setAdapter(adapter1);

        gridView2 = findViewById(R.id.idGVcourses2);
        adapter2 = new adapter(this, secondList);
        gridView2.setAdapter(adapter2);

        gridView3 = findViewById(R.id.idGVcourses3);
        adapter3 = new adapter(this, thirdList);
        gridView3.setAdapter(adapter3);

        gridView4 = findViewById(R.id.idGVcourses4);
        adapter4 = new adapter(this, fourthList);
        gridView4.setAdapter(adapter4);

        gridView5 = findViewById(R.id.idGVcourses5);
        adapter5 = new adapter(this, fifthList);
        gridView5.setAdapter(adapter5);

        click_grid(gridView1,6,0, courseModelArrayList);
        click_grid(gridView2,6,6, courseModelArrayList);
        click_grid(gridView3,12,12, courseModelArrayList);
        click_grid(gridView4,6,24, courseModelArrayList);
        click_grid(gridView5,18,30, courseModelArrayList);

        SearchView searchView = findViewById(R.id.search_view);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filter(newText);
                return false;
            }
        });
    }

    private void filter(String text) {
        filteredList.clear();
        for (CourseModel item : courseModelArrayList) {
            if (item.getCourse_name().toLowerCase().contains(text.toLowerCase())) {
                filteredList.add(item);
            }
        }
        updateAdapters();
    }

    private void updateAdapters() {
        adapter1.updateList(filteredList);
        adapter2.updateList(filteredList);
        adapter3.updateList(filteredList);
        adapter4.updateList(filteredList);
        adapter5.updateList(filteredList);
    }
}
