package com.diyview.lyw.diyview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {
    private ListView lv_one;
    private ListView lv_two;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lv_one = findViewById(R.id.lv_one);
        lv_two = findViewById(R.id.lv_two);
        String[] str1 = {"1","2","3","4","5","6","7","8","9","10"};
        String[] str2 = {"A","B","C","D","E","F","G","H","I","J"};
        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item,str1);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item,str2);
        lv_one.setAdapter(adapter1);
        lv_two.setAdapter(adapter2);
    }
}
