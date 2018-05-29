package com.ashwinisnv.accelerometer;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    EditText x;
    EditText y;
    EditText z;
    TextView summary;
    EditText counter;
    public AsyncTask myTask;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void generate(View view){
        x=(EditText)findViewById(R.id.xValue);
        y=(EditText)findViewById(R.id.yValue);
        z=(EditText)findViewById(R.id.zValue);
        counter=(EditText)findViewById(R.id.counterValue);
        summary=(TextView)findViewById(R.id.summary);

       myTask = new runRandomly(this,x,y,z,counter,summary).execute();
    }

    public class runRandomly extends AsyncTask<String, List<Integer>, String>
    {

        List<Integer> ranInt = new ArrayList<>();
        Activity activity;
        EditText editX;
        EditText editY;
        EditText editZ;
        int count;
        TextView summ;
        int a=0;


        public runRandomly(Activity activity, EditText x,EditText y,EditText z,EditText counter,TextView summary)
        {
            this.activity = activity;
            editX = x;
            editY = y;
            editZ = z;
            String value = counter.getText().toString();
            count = Integer.parseInt(value);
            summ=summary;

             editX.setText("0" );
             editY.setText("0");
             editZ.setText("0");
             summ.setText("");
        }

        @Override
        protected String doInBackground(String... params) {
            // TODO Auto-generated method stub

            try{

            for( a = 1; a <= count; a++)
            {
                if (isCancelled())
                    break;

                ranInt.clear();
                Random rn = new Random();
                int i = rn.nextInt(256 + 1 + 256) - 256;
                ranInt.add(i);

                 i = rn.nextInt(256 + 1 + 256) - 256;
                 ranInt.add(i);

                 i = rn.nextInt(256 + 1 + 256) - 256;
                 ranInt.add(i);

                publishProgress(ranInt);

                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
                publishProgress(ranInt);

            } catch (Exception e) {
                System.err.println("Error: " + e.getMessage());
            }
            return null;
        }

        @Override
        protected void onPostExecute(String result)
        {
            // TODO Auto-generated method stub
            super.onPostExecute(result);
        }

        @Override
        protected void onProgressUpdate(List<Integer>... values)
        {
            // TODO Auto-generated method stub
            if(a==1){
                editX.setText(""+ranInt.get(0));
                editY.setText(""+ranInt.get(1));
                editZ.setText(""+ranInt.get(2));
            }
            else if (a==count+1){
                summ.setText(summ.getText() + "\nSimulation Count: " + (a-1) + "\n" + "X: " + editX.getText() + ", Y: " + editY.getText() + ", Z: " + editZ.getText());
                summ.setMovementMethod(new ScrollingMovementMethod());
            }
            else {
                summ.setText(summ.getText() + "\nSimulation Count: " + (a-1) + "\n" + "X: " + editX.getText() + ", Y: " + editY.getText() + ", Z: " + editZ.getText());
                summ.setMovementMethod(new ScrollingMovementMethod());
                editX.setText("" + ranInt.get(0));
                editY.setText("" + ranInt.get(1));
                editZ.setText("" + ranInt.get(2));
            }
        }
    }

    public void reset(View view)
    {
        myTask.cancel(true);
        x.setText("");
        y.setText("");
        z.setText("");
        counter.setText("");
        summary.setText("");
    }
}





