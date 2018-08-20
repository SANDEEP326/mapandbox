package com.example.zguest.mapandbox;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    EditText eLat,eLon;
    Button mSearch;
    Double dLat,dLon;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        eLat=(EditText) findViewById(R.id.editText_lat);
        eLon=(EditText) findViewById(R.id.editText_lon);
        mSearch=(Button) findViewById(R.id.button_search);

        mSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dLat=Double.parseDouble(String.valueOf(eLat.getText()));
                dLon=Double.parseDouble(String.valueOf(eLon.getText()));
                Log.e("lat","lat "+dLat);
                Log.e("lon","lon "+dLon);
                launchActivity();
            }
        });
    }
    void launchActivity(){
        Intent intent=new Intent(this, MapsActivity.class);
        Bundle data=new Bundle();
        data.putDouble("lat",dLat);
        data.putDouble("lon",dLon);
        Log.e("launch","lat "+data);
        Log.e("launch","lon "+data);
        intent.putExtra("values",data);
        startActivity(intent);
    }

}
