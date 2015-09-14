package com.example.firstapp.clientside;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.firstapp.R;

/**
 * Created by vishnu on 8/9/15.
 */

public class StatusCheck extends Activity{

    private String response;
    TextView MailStatus;// = (TextView) findViewById(R.id.TVMailStatus);
    TextView SMSStatus;// = (TextView) findViewById(R.id.TVSMSStatus);
    TextView AppStatus;// = (TextView) findViewById(R.id.TVAppStatus)// ;
    TextView TVStatus;
    private Context context = this;
    private String sendRequest(final String page) {

        try {
            response = SimpleHttpClient
                    .executeHttpGet(
                            getString(R.string.ip) + page);

        } catch (Exception e) {
            response = e.getMessage();
        }
        return response;
    }



    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.status_layout);
        MailStatus = (TextView) findViewById(R.id.TVMailStatus);
        SMSStatus = (TextView) findViewById(R.id.TVSMSStatus);
        AppStatus = (TextView) findViewById(R.id.TVAppStatus);
        TVStatus = (TextView) findViewById(R.id.TVStatus);
        // System.out.println("Intent is working...Intent is working...Intent is working...Intent is working...Intent is working...Intent is working...Intent is working...");
        String[] urls = {"/generateSecret.php","/sendemail.inc.php", "/sendsms.inc.php", "/sendapptoken.inc.php" };
        new Communicate().execute(urls);
    }

    private class Communicate extends AsyncTask<String,Integer, Void> {

        String[] responses = new String[4];
        @Override
        protected Void doInBackground(String... strings) {

            int counter = 0;
            System.out.println(strings.length);
            for(String str: strings) {
                responses[counter] = sendRequest(str);
                publishProgress(counter++);
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            switch(values[0]) {
                case 0: TVStatus.setText(responses[0]);
                    break;
                case 1: MailStatus.setText("First secret share sent to your email!");
                    break;
                case 2: SMSStatus.setText("Second secret share sent to your mobile!");
                    break;
                case 3: AppStatus.setText("Third secret share for this app: " + responses[3]);
            }

        }

        @Override
        protected void onPostExecute(Void aVoid) {

            for(String response:responses) {
                if(response.trim().equals("fail")) {
                    return;
                }
            }
            Button BtnNext = new Button(StatusCheck.this);
            BtnNext.setText("Enter Shares");
            BtnNext.setOnClickListener(loadCollusionScreen(BtnNext));
            RelativeLayout CurrentLayout = (RelativeLayout) findViewById(R.id.StatusLayout);
            RelativeLayout.LayoutParams LOParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
            LOParams.addRule(RelativeLayout.BELOW, R.id.TVAppStatus);

            CurrentLayout.addView(BtnNext,LOParams);

        }

        private View.OnClickListener loadCollusionScreen(final Button button) {
            return new View.OnClickListener() {
                public void onClick(View v) {
                    Intent intent = new Intent(context, Collude.class);
                    intent.putExtra("SMSShare",responses[2].trim());
                    intent.putExtra("AppShare", responses[3].trim());
                    startActivity(intent);
                    finish();
                }
            };

        }
    }

}


