package com.octoriz.sohel.saarc;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.widget.TextView;

import com.octoriz.sohel.saarc.Entity.Country;
import com.octoriz.sohel.saarc.ViewModel.ShakeViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ShakeViewModel shakeViewModel;
    private TextView txtCountryList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtCountryList = findViewById(R.id.countryList);

        shakeViewModel = ViewModelProviders.of(this).get(ShakeViewModel.class);

        shakeViewModel.getCountryList().observe(this, new Observer<List<Country>>() {
            @Override
            public void onChanged(List<Country> countries) {
                for(int i=0; i<countries.size(); i++){
                    txtCountryList.append(countries.get(i).getCountry_id()+countries.get(i).getCountryName()+"\n");

                }
            }
        });
    }
}
