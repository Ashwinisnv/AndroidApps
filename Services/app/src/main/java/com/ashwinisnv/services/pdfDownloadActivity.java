package com.ashwinisnv.services;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.AsyncTask;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.net.MalformedURLException;
import java.net.URL;

public class pdfDownloadActivity extends AppCompatActivity {
    EditText text1,text2,text3,text4,text5;

    private MyIntentService serviceBinder;//Demo2
    Intent i; //Demo2
    public AsyncTask myTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdf_download);

        text1 = findViewById(R.id.pdf1Value);
        text2 = findViewById(R.id.pdf2Value);
        text3 = findViewById(R.id.pdf3Value);
        text4 = findViewById(R.id.pdf4Value);
        text5 = findViewById(R.id.pdf5Value);

        // set up the URLs
        text1.setText("https://www.cisco.com/c/dam/en_us/about/annual-report/2016-annual-report-full.pdf");
        text2.setText("https://www.cisco.com/c/dam/en_us/about/ac79/docs/innov/IoE_Economy.pdf");
        text3.setText("https://www.cisco.com/c/dam/en_us/solutions/industries/docs/gov/everything-for-cities.pdf");
        text4.setText("http://www.verizonenterprise.com/resources/reports/rp_DBIR_2017_Report_execsummary_en_xg.pdf");
        text5.setText("http://www.verizonenterprise.com/resources/reports/rp_DBIR_2017_Report_en_xg.pdf");

    }

    //Demo2
//    @Override
//    protected void onStart() {
//
//        super.onStart();
//        //Demo2(Bound Service)
//        i = new Intent(this, MyIntentService.class);
//        bindService(i, connection, Context.BIND_AUTO_CREATE);
//    }
//
//    //Demo2
//    @Override
//    protected void onStop() {
//
//        super.onStop();
//        //Demo2(Bound Service)
//        unbindService(connection);
//        stopService(new Intent(getBaseContext(), MyIntentService.class));
//    }

    public void startService(View view) {

        String url1 = text1.getText().toString();
        String url2 = text2.getText().toString();
        String url3 = text3.getText().toString();
        String url4 = text4.getText().toString();
        String url5 = text5.getText().toString();

        //Demo 1(Unbound Service)
        Intent intent = new Intent(getBaseContext(), MyService.class);
        try {
            URL[] urls = new URL[]{
                    new URL(url1),new URL(url2),new URL(url3),new URL(url4),new URL(url5)};

            intent.putExtra("URLs", urls);
           // i.putExtra("URLs", urls); //demo2
        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        startService(intent);


        //Demo2(Bound Service)
//        URL[] urls = new URL[0];
//        try {
//            urls = new URL[]{
//                    new URL("https://www.cisco.com/c/dam/en_us/about/annual-report/2016-annual-report-full.pdf"),
//                    new URL("https://www.cisco.com/c/dam/en_us/about/ac79/docs/innov/IoE_Economy.pdf"),
//                    new URL("https://www.cisco.com/c/dam/en_us/solutions/industries/docs/gov/everything-for-cities.pdf"),
//                    new URL("http://www.verizonenterprise.com/resources/reports/rp_DBIR_2017_Report_execsummary_en_xg.pdf"),
//                    new URL("http://www.verizonenterprise.com/resources/reports/rp_DBIR_2017_Report_en_xg.pdf")};
//            } catch (MalformedURLException e) {
//                e.printStackTrace();
//            }
//        serviceBinder.callAsyncTask(urls);
    }


    //Demo2
//    private ServiceConnection connection = new ServiceConnection() {
//        public void onServiceConnected(ComponentName className, IBinder service) {
//            //---called when the connection is made---
//            serviceBinder = ((MyIntentService.MyBinder)service).getService();
//
//            try {
//                URL[] urls = new URL[] {
//                        new URL("https://www.cisco.com/c/dam/en_us/about/annual-report/2016-annual-report-full.pdf"),
//                        new URL("https://www.cisco.com/c/dam/en_us/about/ac79/docs/innov/IoE_Economy.pdf"),
//                        new URL("https://www.cisco.com/c/dam/en_us/solutions/industries/docs/gov/everything-for-cities.pdf"),
//                        new URL("http://www.verizonenterprise.com/resources/reports/rp_DBIR_2017_Report_execsummary_en_xg.pdf"),
//                        new URL("http://www.verizonenterprise.com/resources/reports/rp_DBIR_2017_Report_en_xg.pdf")};
//                serviceBinder.urls = urls;
//
//            } catch (MalformedURLException e) {
//                // TODO Auto-generated catch block
//                e.printStackTrace();
//            }
//        }
//        public void onServiceDisconnected(ComponentName className) {
//            //---called when the service disconnects---
//            serviceBinder = null;
//        }
//    };
}
