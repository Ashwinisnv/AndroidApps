package com.ashwinisnv.assignment2;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Logic to call URL
        final Button urlButton = findViewById(R.id.urlButton);
        final EditText urlText =  findViewById(R.id.urlText);

        urlButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                String url =  urlText.getText().toString();
                if (url.length()>0) {

                    //Add http before URL
                    if (!url.startsWith("http://") && !url.startsWith("https://"))
                        url = "http://" + url;


                    try {
                        Intent myIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                        startActivity(myIntent);
                    } catch (ActivityNotFoundException e) {
                        Toast.makeText(getApplicationContext(), "No application can handle this request. Please install a webbrowser", Toast.LENGTH_LONG).show();
                        e.printStackTrace();
                    }
                }
                else
                {
                    Toast.makeText(getApplicationContext(), "Please enter a valid URL", Toast.LENGTH_LONG).show();
                }


            }
        });

        //Logic for Ring
        final Button ringButton = findViewById(R.id.ringButton);
        final EditText ringText =  findViewById(R.id.ringText);

        ringButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                String phoneNumber =  ringText.getText().toString();
                //Check if the Phone Number entered is valid
                if (phoneNumber.length()>0 &&((!phoneNumber.startsWith("+1") && phoneNumber.length()==10) || (phoneNumber.startsWith("+1") && phoneNumber.length()==12) ))
                {
                    //Add +1 prior to phone number if not already added
                    if (!phoneNumber.startsWith("+1") )
                        phoneNumber = "+1" + phoneNumber;
                    phoneNumber="tel:"+phoneNumber;

                    try {
                        Intent myIntent = new Intent(Intent.ACTION_DIAL, Uri.parse(phoneNumber));
                        startActivity(myIntent);
                    } catch (ActivityNotFoundException e) {
                        Toast.makeText(getApplicationContext(), "No application can handle this request. Please install a Phone app",  Toast.LENGTH_LONG).show();
                        e.printStackTrace();
                    }
                }
                else
                {
                    Toast.makeText(getApplicationContext(), "Please enter a valid Phone number", Toast.LENGTH_LONG).show();
                }

            }
        });

    }

    public void finishApp(View v) {
        MainActivity.this.finish();
    }
}
