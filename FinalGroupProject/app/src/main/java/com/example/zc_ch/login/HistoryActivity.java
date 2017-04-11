package com.example.zc_ch.login;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class HistoryActivity extends AppCompatActivity {
    private ListView listview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        final Database_data app = (Database_data)getApplication();
        List<History> history = new ArrayList<History>();
        history = app.getCurrent_history();
        List<History> history1 = new ArrayList<History>();
        for (int i = 0;i<history.size()&&i<7;i++){
            history1.add(history.get(history.size()-1-i));
        }
        listview = (ListView) findViewById(R.id.listview);
        MyAdapter adapter = new MyAdapter(HistoryActivity.this,0,history1);
        listview.setAdapter(adapter);
    }
}
