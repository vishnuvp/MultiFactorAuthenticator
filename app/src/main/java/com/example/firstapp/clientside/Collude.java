package com.example.firstapp.clientside;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.firstapp.R;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;

/**
 * Created by vishnu on 13/9/15.
 */
public class Collude extends Activity{

    String response;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.collusion_layout);

        Bundle extras = getIntent().getExtras();
        EditText v = (EditText)findViewById(R.id.TxtSMSToken);
        v.setText(extras.getString("SMSShare"));

        v = (EditText)findViewById(R.id.TxtAppToken);
        v.setText(extras.getString("AppShare"));
    }

    public void clickHandler(View view) {


        new Thread(new Runnable() {
            @Override
            public void run() {
                ArrayList<NameValuePair> postParameters = new ArrayList<NameValuePair>();
                postParameters.add(new BasicNameValuePair("1",
                        ((EditText)findViewById(R.id.TxtEmailToken)).getText().toString().trim()));
                postParameters.add(new BasicNameValuePair("2",
                        ((EditText)findViewById(R.id.TxtSMSToken)).getText().toString().trim()));
                postParameters.add(new BasicNameValuePair("3",
                        ((EditText)findViewById(R.id.TxtAppToken)).getText().toString().trim()));
                String ip = getString(R.string.ip);


                try {
                    response = SimpleHttpClient
                            .executeHttpPost(
                                    ip + "/reconstruct.php",
                                    postParameters);
                    response = response.trim();
                    //System.out.println(res + res.equals("success"));

                } catch (Exception e) {
                    e.printStackTrace();
                    response = e.getMessage();
                }
            }}).start();

        try {
            Thread.sleep(1000);
            Intent intent = new Intent(this, Result.class);
            intent.putExtra("msg",response);
            startActivity(intent);
            finish();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }



    /*    Intent intent = new Intent(this,Result.class);
        startActivity(intent);
        finish();
*/
}



