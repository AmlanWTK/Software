package com.example.testingmyentertainment;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
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
import com.example.testingmyentertainment.Classical.Folk_song;
import com.example.testingmyentertainment.Musics.Mohiner_Ghoraguli;
import com.example.testingmyentertainment.Musics.mohinerghoraguli;
import com.example.testingmyentertainment.adapyer.CourseGVAdapter;
import com.example.testingmyentertainment.adapyer.DetailActivity;
import com.example.testingmyentertainment.adapyer.DetailActivity1;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayList<CourseModel> courseModelArrayList = new ArrayList<CourseModel>();

        // First, add items to your courseModelAehfhfjjy
        courseModelArrayList.add(new CourseModel("Classical", R.drawable.classical));
        courseModelArrayList.add(new CourseModel("AlternativeRock", R.drawable.alternativerock));
        courseModelArrayList.add(new CourseModel("Folk", R.drawable.folk));
        courseModelArrayList.add(new CourseModel("Jazz", R.drawable.jazz));
        courseModelArrayList.add(new CourseModel("Electrical", R.drawable.electrical));
        courseModelArrayList.add(new CourseModel("Rock", R.drawable.rock));

        courseModelArrayList.add(new CourseModel("AC/DC", R.drawable.acdc));
        courseModelArrayList.add(new CourseModel("Beatles", R.drawable.beatles));
        courseModelArrayList.add(new CourseModel("ColdPlay", R.drawable.coldplay));
        courseModelArrayList.add(new CourseModel("Linkin Park", R.drawable.linkin));
        courseModelArrayList.add(new CourseModel("Pink Floyd", R.drawable.pinkfloyd));
        courseModelArrayList.add(new CourseModel("Metalica", R.drawable.metalica));


        courseModelArrayList.add(new CourseModel("Manna Dey", R.drawable.manna));
        courseModelArrayList.add(new CourseModel("Hemant Kumar", R.drawable.hemanta));
        courseModelArrayList.add(new CourseModel("Kishore Kumar", R.drawable.kisore));
        courseModelArrayList.add(new CourseModel("Shyamal Mitra", R.drawable.shyamal));
        courseModelArrayList.add(new CourseModel("Kabir Suman", R.drawable.kabir));
        courseModelArrayList.add(new CourseModel("Mohammed Rafi", R.drawable.rafi));

        courseModelArrayList.add(new CourseModel("Anupam Roy", R.drawable.anupam));
        courseModelArrayList.add(new CourseModel("Shreya Ghoshal", R.drawable.shreya));
        courseModelArrayList.add(new CourseModel("Lata Mangeshkar", R.drawable.lata));
        courseModelArrayList.add(new CourseModel("Arijit Singh", R.drawable.arijit));
        courseModelArrayList.add(new CourseModel("Anjan Datta", R.drawable.anjan));
        courseModelArrayList.add(new CourseModel("Nachiketa Chakraborty", R.drawable.nachi));


        courseModelArrayList.add(new CourseModel("Mohiner Ghoraguli", R.drawable.mohiner));
        courseModelArrayList.add(new CourseModel("Fossils", R.drawable.fossil));
        courseModelArrayList.add(new CourseModel("When Chai Meets Toast", R.drawable.cha));
        courseModelArrayList.add(new CourseModel("The Local Train", R.drawable.thelocaltrain));
        courseModelArrayList.add(new CourseModel("Cactus", R.drawable.cactus));
        courseModelArrayList.add(new CourseModel("Paarvaaz", R.drawable.paarvaaz));



        courseModelArrayList.add(new CourseModel("Shayan Chowdhury Arnob", R.drawable.arnab));
        courseModelArrayList.add(new CourseModel("Artcell", R.drawable.artcell));
        courseModelArrayList.add(new CourseModel("Ashes", R.drawable.ashes));
        courseModelArrayList.add(new CourseModel("Aurthahin", R.drawable.aurthohin));
        courseModelArrayList.add(new CourseModel("Avash", R.drawable.avash));
        courseModelArrayList.add(new CourseModel("Ayub Bachchu", R.drawable.ayubbacchu));


        courseModelArrayList.add(new CourseModel("Black", R.drawable.black));
        courseModelArrayList.add(new CourseModel("Highway", R.drawable.highway));
        courseModelArrayList.add(new CourseModel("Joler Gan", R.drawable.jolergan));
        courseModelArrayList.add(new CourseModel("Moushumi Bhowmik", R.drawable.mausumi));
        courseModelArrayList.add(new CourseModel("Meghdol", R.drawable.meghdol));
        courseModelArrayList.add(new CourseModel("Miles", R.drawable.miles));


        courseModelArrayList.add(new CourseModel("Nagar James Baul", R.drawable.nagabaul));
        courseModelArrayList.add(new CourseModel("Nemesis", R.drawable.nemesis));
        courseModelArrayList.add(new CourseModel("Shayan", R.drawable.shayan));
        courseModelArrayList.add(new CourseModel("Shonar Bangla Circus", R.drawable.shonarbangla));
        courseModelArrayList.add(new CourseModel("Shironamhin", R.drawable.shoronamhin));
        courseModelArrayList.add(new CourseModel("Warfaze", R.drawable.warfaze));


        SearchView searchView = findViewById(R.id.search_view);
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




        gridView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = null;

                if (position == 0) {
                    intent = new Intent(MainActivity.this, mohinerghoraguli.class);
                } else if (position == 1) {
                    intent = new Intent(MainActivity.this, Mohiner_Ghoraguli.class);
                } else if (position == 2) {
                    intent = new Intent(MainActivity.this, Folk_song.class);
                }else if (position == 3) {
                    intent = new Intent(MainActivity.this, DetailActivity.class);
                }else if (position == 4) {
                    intent = new Intent(MainActivity.this, DetailActivity.class);
                }else if (position == 5) {
                    intent = new Intent(MainActivity.this, Mohiner_Ghoraguli.class);
                }
                else {
                    intent = new Intent(MainActivity.this, DetailActivity1.class);
                    intent.putExtra("EXTRA_POSITION", position);
                }

                if (intent != null) {
                    startActivity(intent);
                }
            }
        });


        gridView2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = null;

                // Since this is gridView2, positions should start from 0 for this grid's list
                if (position == 0) {
                    intent = new Intent(MainActivity.this, Mohiner_Ghoraguli.class);
                } else if (position == 1) {
                    intent = new Intent(MainActivity.this, Beatles.class);
                } else if (position == 2) {
                    intent = new Intent(MainActivity.this, DetailActivity1.class);
                } else if (position == 3) {
                    intent = new Intent(MainActivity.this, DetailActivity1.class);
                } else if (position == 4) {
                    intent = new Intent(MainActivity.this, DetailActivity1.class);
                } else if (position == 5) {
                    intent = new Intent(MainActivity.this, DetailActivity1.class);
                } else {
                    // Assuming this logic is for additional positions if any.
                    intent = new Intent(MainActivity.this, DetailActivity1.class);
                    intent.putExtra("EXTRA_POSITION", position);
                }

                if (intent != null) {
                    startActivity(intent);
                }
            }
        });



        gridView3.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = null;

                if (position == 0) {
                    intent = new Intent(MainActivity.this, DetailActivity.class);
                } else if (position == 1) {
                    intent = new Intent(MainActivity.this, DetailActivity1.class);
                } else if (position == 2) {
                    intent = new Intent(MainActivity.this, DetailActivity1.class);
                } else if (position == 3) {
                    intent = new Intent(MainActivity.this, DetailActivity1.class);
                } else if (position == 4) {
                    intent = new Intent(MainActivity.this, DetailActivity1.class);
                } else if (position == 5) {
                    intent = new Intent(MainActivity.this, DetailActivity1.class);
                }
                else {
                    intent = new Intent(MainActivity.this, DetailActivity1.class);
                    intent.putExtra("EXTRA_POSITION", position);
                }

                if (intent != null) {
                    startActivity(intent);
                }
            }
        });

        gridView3.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = null;

                if (position == 0) {
                    intent = new Intent(MainActivity.this, DetailActivity.class);
                } else if (position == 1) {
                    intent = new Intent(MainActivity.this, DetailActivity1.class);
                } else if (position == 2) {
                    intent = new Intent(MainActivity.this, DetailActivity1.class);
                } else if (position == 3) {
                    intent = new Intent(MainActivity.this, DetailActivity1.class);
                } else if (position == 4) {
                    intent = new Intent(MainActivity.this, DetailActivity1.class);
                } else if (position == 5) {
                    intent = new Intent(MainActivity.this, DetailActivity1.class);
                }
                else {
                    intent = new Intent(MainActivity.this, DetailActivity1.class);
                    intent.putExtra("EXTRA_POSITION", position);
                }

                if (intent != null) {
                    startActivity(intent);
                }
            }
        });


        gridView4.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = null;

                if (position == 0) {
                    intent = new Intent(MainActivity.this, Mohiner_Ghoraguli.class);
                } else if (position == 1) {
                    intent = new Intent(MainActivity.this, DetailActivity1.class);
                } else if (position == 2) {
                    intent = new Intent(MainActivity.this, DetailActivity1.class);
                } else if (position == 3) {
                    intent = new Intent(MainActivity.this, DetailActivity1.class);
                } else if (position == 4) {
                    intent = new Intent(MainActivity.this, DetailActivity1.class);
                } else if (position == 5) {
                    intent = new Intent(MainActivity.this, DetailActivity1.class);
                }
                else {
                    intent = new Intent(MainActivity.this, DetailActivity1.class);
                    intent.putExtra("EXTRA_POSITION", position);
                }

                if (intent != null) {
                    startActivity(intent);
                }
            }
        });



        gridView5.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = null;

                if (position == 0) {
                    intent = new Intent(MainActivity.this, Mohiner_Ghoraguli.class);
                } else if (position == 1) {
                    intent = new Intent(MainActivity.this, DetailActivity1.class);
                } else if (position == 2) {
                    intent = new Intent(MainActivity.this, DetailActivity1.class);
                } else if (position == 3) {
                    intent = new Intent(MainActivity.this, DetailActivity1.class);
                } else if (position == 4) {
                    intent = new Intent(MainActivity.this, DetailActivity1.class);
                } else if (position == 5) {
                    intent = new Intent(MainActivity.this, DetailActivity1.class);
                } if (position == 6) {
                    intent = new Intent(MainActivity.this, DetailActivity.class);
                } else if (position == 7) {
                    intent = new Intent(MainActivity.this, DetailActivity1.class);
                } else if (position == 8) {
                    intent = new Intent(MainActivity.this, DetailActivity1.class);
                } else if (position == 9) {
                    intent = new Intent(MainActivity.this, DetailActivity1.class);
                } else if (position == 10) {
                    intent = new Intent(MainActivity.this, DetailActivity1.class);
                } else if (position == 11) {
                    intent = new Intent(MainActivity.this, DetailActivity1.class);
                } if (position == 12) {
                    intent = new Intent(MainActivity.this, DetailActivity.class);
                } else if (position == 13) {
                    intent = new Intent(MainActivity.this, DetailActivity1.class);
                } else if (position == 14) {
                    intent = new Intent(MainActivity.this, DetailActivity1.class);
                } else if (position == 15) {
                    intent = new Intent(MainActivity.this, DetailActivity1.class);
                } else if (position == 16) {
                    intent = new Intent(MainActivity.this, DetailActivity1.class);
                } else if (position == 17) {
                    intent = new Intent(MainActivity.this, DetailActivity1.class);
                }
                else {
                    intent = new Intent(MainActivity.this, DetailActivity1.class);
                    intent.putExtra("EXTRA_POSITION", position);
                }

                if (intent != null) {
                    startActivity(intent);
                }
            }
        });







    }
}