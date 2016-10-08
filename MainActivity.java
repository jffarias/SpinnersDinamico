package iesous.com.spinnersdinamico;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/*
links
http://stackoverflow.com/questions/24825249/how-to-add-item-in-spinner-android
http://stackoverflow.com/questions/9310479/how-to-iterate-through-all-keys-of-shared-preferences
https://developer.android.com/guide/topics/ui/controls/spinner.html
http://www.sgoliver.net/blog/preferencias-en-android-i-shared-preferences/
https://code.tutsplus.com/tutorials/learn-java-for-android-development-working-with-arrays--mobile-2894
http://stackoverflow.com/questions/1337424/android-spinner-get-the-selected-item-change-event

Ing. Jesús Flor Farias
twitter: @Iesous_Flor
* */
public class MainActivity extends AppCompatActivity {

    String Months[] = {"January","February","March", "April",
                        "June","July", "August","September",
                        "October","November","December"};
    Spinner spinner;
    ArrayAdapter<String> adapter;
    List<String> list;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spinner = (Spinner) findViewById(R.id.spinner);
        textView = (TextView) findViewById(R.id.textView2);
        list = new ArrayList<String>();

        SharedPreferences preferences = getSharedPreferences("months", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        for(int i=0; i < Months.length; i++){
            editor.putString("month"+i,Months[i]);
        }
        editor.commit();

        //fill spinner
        SharedPreferences prefs = getSharedPreferences("months", Context.MODE_PRIVATE);
        Map<String,?> keys = prefs.getAll();

        for(Map.Entry<String,?> entry : keys.entrySet()){
            Log.d("map values",entry.getKey() + ": " +
                    entry.getValue().toString());
            list.add(entry.getValue().toString());
        }

        adapter = new ArrayAdapter<String>(getApplicationContext(),
                android.R.layout.simple_spinner_item,list);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        //add event change
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                textView.setText("Selected::"+spinner.getSelectedItem().toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }
}
