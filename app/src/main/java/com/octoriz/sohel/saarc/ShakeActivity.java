package com.octoriz.sohel.saarc;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.octoriz.sohel.saarc.Entity.Name;
import com.octoriz.sohel.saarc.Model.People;
import com.octoriz.sohel.saarc.ViewModel.ShakeViewModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ShakeActivity extends AppCompatActivity {

    private SensorManager mSensorManager;

    private ShakeEventListener mSensorListener;
    private TextView txtList,shake;
    private ShakeViewModel shakeViewModel;
    private List<People> allNames = new ArrayList<>();
    private List<People> sortedNames = new ArrayList<>();
    int totalPeople;
    People people;
    int t;
    int comb;
    int counter = 0;
    int check = 0;
    String name;
    List<String> xSplit = new ArrayList<>();
    List<String> ySplit = new ArrayList<>();
    private int combinations = -1;
    private int changer;
    //

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shake);
        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mSensorListener = new ShakeEventListener();
        txtList = findViewById(R.id.list);
        shake = findViewById(R.id.shake);
        shakeViewModel = ViewModelProviders.of(this).get(ShakeViewModel.class);

        shakeViewModel.getAllNames().observe(this, new Observer<List<Name>>() {
            @Override
            public void onChanged(List<Name> names) {
                totalPeople = names.size();
                t = totalPeople/2;
                comb = t;
                txtList.append("Total People"+String.valueOf(totalPeople)+"\n");
                int a=0,b=0,c=0,d=0,e=0,f=0,g=0,h=0;
                List<String> country1 = new ArrayList<>();
                List<String> country2 = new ArrayList<>();
                List<String> country3 = new ArrayList<>();
                List<String> country4 = new ArrayList<>();
                List<String> country5 = new ArrayList<>();
                List<String> country6 = new ArrayList<>();
                List<String> country7 = new ArrayList<>();
                List<String> country8 = new ArrayList<>();

                for(int i=0; i<names.size(); i++){

                    if(names.get(i).getCountry_id()==1){
                        a++;
                        country1.add(names.get(i).getName());
                    }
                    else if(names.get(i).getCountry_id()==2){
                        b++;
                        country2.add(names.get(i).getName());
                    }
                    else if(names.get(i).getCountry_id()==3){
                        c++;
                        country3.add(names.get(i).getName());
                    }
                    else if(names.get(i).getCountry_id()==4){
                        d++;
                        country4.add(names.get(i).getName());
                    }
                    else if(names.get(i).getCountry_id()==5){
                        e++;
                        country5.add(names.get(i).getName());
                    }
                    else if(names.get(i).getCountry_id()==6){
                        f++;
                        country6.add(names.get(i).getName());
                    }
                    else if(names.get(i).getCountry_id()==7){
                        g++;
                        country7.add(names.get(i).getName());
                    }
                    else if(names.get(i).getCountry_id()==8){
                        h++;
                        country8.add(names.get(i).getName());
                    }
                }
                allNames.add(new People(a,country1));
                allNames.add(new People(b,country2));
                allNames.add(new People(c,country3));
                allNames.add(new People(d,country4));
                allNames.add(new People(e,country5));
                allNames.add(new People(f,country6));
                allNames.add(new People(g,country7));
                allNames.add(new People(h,country8));

                //printList(allNames);

                Collections.sort(allNames, new SortByNumberOfPeople());
                printList(allNames);
            }
        });


        mSensorListener.setOnShakeListener(new ShakeEventListener.OnShakeListener() {

            public void onShake() {
                Toast.makeText(ShakeActivity.this, "Shake Detected!", Toast.LENGTH_SHORT).show();
            if(allNames.size()!=0){
                if(combinations==-1){
                    combinations = doRoomAllocating(allNames);
                    shake.setText("Shake Again for more combinations");
                }else if(combinations>1){
                    txtList.setText("");
                    doRoomMoreAllocating();
                }
                else{
                    shake.setText("No more combinations!");
                }
            }else{
                Toast.makeText(ShakeActivity.this, "List is empty", Toast.LENGTH_SHORT).show();
            }

            }
        });

    }

    private void doRoomMoreAllocating() {
        String temp;
        temp = ySplit.get(ySplit.size()-1);
        for(int i=ySplit.size()-1; i>0; i--){
            ySplit.set(i,ySplit.get(i-1));
        }
        ySplit.set(0,temp);

        for(int i=0; i<t; i++){
            int roomNo = i;

            if((totalPeople%2 == 1) && i==changer){
                txtList.append("Room No "+roomNo+". "+xSplit.get(i)+" "+ySplit.get(i)+" "+ySplit.get(t)+"\n");
            }else{
                txtList.append("Room No "+roomNo+". "+xSplit.get(i)+" "+ySplit.get(i)+"\n");
            }
        }
        ++changer;
        combinations--;
    }

    private int doRoomAllocating(List<People> allNames) {
        for(int i=0; i<8; i++){
            int possible = allNames.get(i).getPeopleCount();
            for(int j=0; j<allNames.get(i).getPeopleCount(); j++){
                if(counter!=t){
                    xSplit.add(allNames.get(i).getPeopleNames().get(j));
                    ++counter;
                    --possible;
                    if(possible == 0){
                        check = 1;
                    }
                }
                else{
                    if(possible!=0 && check == 0 && possible!= allNames.get(i).getPeopleCount()){
                        comb = t - (possible*2)-1;
                        possible = 0;
                        check = 1;
                    }
                    ySplit.add(allNames.get(i).getPeopleNames().get(j));
                }
                check = 0;
            }
        }

        changer = 0;
        txtList.append("Room Allocation\n");
        for(int i=0; i<t; i++){
            int roomNo = i;
            if(((totalPeople%2) == 1) && (i == changer)){
                txtList.append("Room No "+roomNo+". "+xSplit.get(i)+" "+ySplit.get(i)+" "+ySplit.get(t)+"\n");
            }else{
                txtList.append("Room No "+roomNo+". "+xSplit.get(i)+" "+ySplit.get(i)+"\n");
            }
            txtList.append("\n");
        }

        ++changer;
        txtList.append(comb+" Combination Possible");
        return comb;
    }

    private void printList(List<People> allNames) {
        for(int j=0; j<allNames.size(); j++){
            txtList.append(allNames.get(j).getPeopleCount()+"\n");
            for(int i=0; i<allNames.get(j).getPeopleNames().size(); i++){
                txtList.append(allNames.get(j).getPeopleNames().get(i)+"\n");
            }
        }
    }

    class SortByNumberOfPeople implements Comparator<People>
    {
        // Used for sorting in ascending order of
        // roll number
        public int compare(People a, People b)
        {
            return b.getPeopleCount() - a.getPeopleCount();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        mSensorManager.registerListener(mSensorListener,
                mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
                SensorManager.SENSOR_DELAY_UI);
    }

    @Override
    protected void onPause() {
        mSensorManager.unregisterListener(mSensorListener);
        super.onPause();
    }

}
