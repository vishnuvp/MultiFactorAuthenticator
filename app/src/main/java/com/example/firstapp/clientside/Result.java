package com.example.firstapp.clientside;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.firstapp.R;

/**
 * Created by vishnu on 13/9/15.
 */
public class Result extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.result_layout);
        Bundle extras = getIntent().getExtras();
        TextView tx = (TextView) findViewById(R.id.TVResult);
        tx.setText(extras.getString("msg"));
    }

    public void logoutHandler(View view) {
        Intent intent = new Intent(this, LoginLayout.class);
        startActivity(intent);
        finish();
    }
}
