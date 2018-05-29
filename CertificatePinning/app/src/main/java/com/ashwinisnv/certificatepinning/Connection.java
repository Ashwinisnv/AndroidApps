package com.ashwinisnv.certificatepinning;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import java.io.InputStream;
import java.net.URL;

import java.io.IOException;
import java.io.PrintStream;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManagerFactory;


/**
 * Created by ashwinivishwas on 4/5/18.
 */

public class Connection extends AsyncTask<Void, Void, Object> {

    private Context myContext;

    public Connection(Context context) {
        myContext = context;
    }

    @Override
    protected Object doInBackground(Void... params) {

        Object result = null;

        try {
            URL url = new URL("https://www.facebook.com");
//            URL url = new URL("https://www.google.com");
            InputStream in = getRequest(myContext, url);
            copyFromIpStreamToOpStream(in, System.out);

        } catch (Exception ex) {
            Log.e("doInBackground", ex.toString());
            // Return error if any
            result = ex;
        }

        return result;
    }

    @Override
    protected void onPostExecute(Object result) {
        if (result instanceof Exception) {
            MainActivity.displayText.setText("Sorry, Untrusted Certificate!! \n\n" + result);
            return;
        }
        MainActivity.displayText.setText("Trusted Certificate!!");
    }

    private void copyFromIpStreamToOpStream(InputStream in, PrintStream out) {
        // TODO Auto-generated method stub
        byte[] buffer = new byte[1024];
        int bytesRead = 0;
        try {
            while ((bytesRead = in.read(buffer)) != -1) {
                out.write(buffer, 0, bytesRead);
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        Log.i("TAG", String.valueOf(bytesRead));

    }

    private InputStream getRequest(Context context, URL url)
            throws IOException, KeyStoreException, NoSuchAlgorithmException,
            CertificateException, KeyManagementException {

        InputStream keyStoreInputStream = context.getResources().openRawResource(R.raw.mykeystore);
        KeyStore trustStore = KeyStore.getInstance("BKS");

        trustStore.load(keyStoreInputStream, "mysecret".toCharArray());

        TrustManagerFactory tmf = TrustManagerFactory.getInstance("X509");
        tmf.init(trustStore);

        SSLContext sslContext = SSLContext.getInstance("TLS");
        sslContext.init(null, tmf.getTrustManagers(), null);

        HttpsURLConnection urlConnection = (HttpsURLConnection) url
                .openConnection();
        urlConnection.setSSLSocketFactory(sslContext.getSocketFactory());

        return urlConnection.getInputStream();
    }
}
