package com.octoriz.abids.saarc;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.octoriz.abids.saarc.Entity.Country;
import com.octoriz.abids.saarc.Preference.Preference;
import com.octoriz.abids.saarc.ViewModel.ShakeViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    public static final String EXTRA_ID = "ID";

    private ShakeViewModel shakeViewModel;
    private TextView txtCountryList;
    private AppCompatImageView afgan1, bd2, bhutan3, india4, maldives5, nepal6, pak7, sri8;
    private AppCompatButton btnDone, btnDelete;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        txtCountryList = findViewById(R.id.countryList);
        btnDone = findViewById(R.id.btnDone);
        btnDelete = findViewById(R.id.btnDelete);

        afgan1 = findViewById(R.id.flgAfgan1);
        bd2 = findViewById(R.id.flgBd2);
        bhutan3 = findViewById(R.id.flgBhutan3);
        india4 = findViewById(R.id.flgIndia4);
        maldives5 = findViewById(R.id.flgMaldives5);
        nepal6 = findViewById(R.id.flgNepal6);
        pak7 = findViewById(R.id.flgPak7);
        sri8 = findViewById(R.id.flgSri8);

        shakeViewModel = ViewModelProviders.of(this).get(ShakeViewModel.class);

        shakeViewModel.getCountryList().observe(this, new Observer<List<Country>>() {
            @Override
            public void onChanged(List<Country> countries) {
//                for(int i=0; i<countries.size(); i++){
//                    txtCountryList.append(countries.get(i).getCountry_id()+countries.get(i).getCountryName()+"\n");
//
//                }
            }
        });

        afgan1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddActivity.class);
                intent.putExtra("id",1);
                intent.putExtra("name","Afganistan");
                startActivity(intent);
            }
        });

        bd2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddActivity.class);
                intent.putExtra("id",2);
                intent.putExtra("name","Bangladesh");
                startActivity(intent);
            }
        });

        bhutan3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddActivity.class);
                intent.putExtra("id",3);
                intent.putExtra("name","Bhutan");
                startActivity(intent);
            }
        });

        india4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddActivity.class);
                intent.putExtra("id",4);
                intent.putExtra("name","India");
                startActivity(intent);
            }
        });

        maldives5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddActivity.class);
                intent.putExtra("id",5);
                intent.putExtra("name","Maldives");
                startActivity(intent);
            }
        });

        nepal6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddActivity.class);
                intent.putExtra("id",6);
                intent.putExtra("name","Nepal");
                startActivity(intent);
            }
        });

        pak7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddActivity.class);
                intent.putExtra("id",7);
                intent.putExtra("name","Pakistan");
                startActivity(intent);
            }
        });

        sri8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddActivity.class);
                intent.putExtra("id",8);
                intent.putExtra("name","Srilanka");
                startActivity(intent);
            }
        });

        btnDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, ShakeActivity.class));
            }
        });
        
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                shakeViewModel.deleteAllNames();
                Preference.deleteSharedPref(MainActivity.this);
                Toast.makeText(MainActivity.this, "Deleted all the data", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
