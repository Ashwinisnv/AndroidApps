package com.ashwinisnv.services;

import android.app.IntentService;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Binder;
import android.os.Environment;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.MalformedURLException;


/**
 * Created by ashwinivishwas on 3/18/18.
 */

public class MyIntentService extends IntentService {
    public URL[] urls;
    public AsyncTask myTask;

    private final IBinder binder = new MyBinder();

    public class MyBinder extends Binder {
        MyIntentService getService() {
            return MyIntentService.this;
        }
    }

    @Nullable
    @Override
    public IBinder onBind(Intent arg0) {
        // TODO Auto-generated method stub
        //return null;
        return binder;
    }
    public MyIntentService() {
        super(MyIntentService.class.getName());
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        URL url;
        int count = 0;
        int progress = 0;

        if (intent != null) {
            Object[] objUrls = (Object[]) intent.getExtras().get("URLs");
            Thread thread;
            //new DoBackgroundTask().execute(urls);
        }

    }

    public void stop()  {
        //unbindService(connection);
        myTask.cancel(true);
    }


    public int callAsyncTask(URL[] url)  {
        myTask = new DoBackgroundTask().execute(url);
        return 1;
    }
    public class DoBackgroundTask extends AsyncTask<URL, Integer, Long> {

        private long DownloadFileCustom(URL url) {
            InputStream input;
            OutputStream output;
            HttpURLConnection connection;
            int count = 0;
            long total = 0;

            try {
                connection = (HttpURLConnection)url.openConnection();
                connection.setInstanceFollowRedirects(false);
                connection.connect();
                if (connection.getResponseCode() != HttpURLConnection.HTTP_OK) {
                    return -1;
                }

                input = connection.getInputStream();
                String filename = url.toString().substring(url.toString().lastIndexOf('/'));
                File file = new File(Environment.getExternalStorageDirectory(), filename);

                output = new FileOutputStream(file);
                byte data[] = new byte[4096];

                while ((count = input.read(data)) != -1) {
                    if (isCancelled()) {
                        input.close();
                        return -1;
                    }
                    total += count;
                    output.write(data, 0, count);
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
            return total;
        }

        protected Long doInBackground(URL... urls) {
            int count = urls.length;
            long totalBytes=0;

            for (int i = 0; i < count; i++) {
                if (isCancelled())
                    break;
                totalBytes+=DownloadFileCustom(urls[i]);
                // calculate percentage downloaded and report its progress
                publishProgress((int) (((i+1) / (float) count) * 100));
            }
            return totalBytes;
        }

        protected void onProgressUpdate(Integer... progress) {
            Toast.makeText(getBaseContext(),
                    String.valueOf(progress[0]) + "% downloaded",
                    Toast.LENGTH_LONG).show();
        }

        protected void onPostExecute(Long result) {
            result = result/(1024*1024);
            Toast.makeText(getBaseContext(),
                    "Downloaded " + result + "MB",
                    Toast.LENGTH_LONG).show();
            stopSelf();
        }
    }

 }

