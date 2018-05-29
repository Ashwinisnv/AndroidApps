package com.ashwinisnv.services;

import android.app.Service;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Binder;
import android.os.Environment;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Timer;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;

import java.util.TimerTask;

/**
 * Created by ashwinivishwas on 3/16/18.
 */

public class MyService extends Service {

    int counter = 0;
    public URL[] urls;

    static final int UPDATE_INTERVAL = 1000;
    private Timer timer = new Timer();

    private final IBinder binder = new MyBinder();

    public class MyBinder extends Binder {
        MyService getService() {
            return MyService.this;
        }
    }

    @Nullable
    @Override
    public IBinder onBind(Intent arg0) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        Toast.makeText(this, "Service Started", Toast.LENGTH_LONG).show();
        Object[] objUrls = (Object[]) intent.getExtras().get("URLs");
        URL[] urls = new URL[objUrls.length];
        for (int i=0; i<objUrls.length; i++) {
            urls[i] = (URL) objUrls[i];
        }
        new DoBackgroundTask().execute(urls);
        return START_STICKY;
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

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (timer != null) {
            timer.cancel();
        }
        Toast.makeText(this, "Service Destroyed", Toast.LENGTH_LONG).show();
    }

}
