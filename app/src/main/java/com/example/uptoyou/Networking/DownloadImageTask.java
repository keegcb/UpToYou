package com.example.uptoyou.Networking;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

import java.io.InputStream;
import java.nio.channels.AsynchronousChannelGroup;

public class DownloadImageTask  extends AsyncTask<String, Void, Bitmap> {
    ImageView bmImage;

    public DownloadImageTask(ImageView bmImage){
        this.bmImage = bmImage;
    }

    @Override
    protected Bitmap doInBackground(String... urls) {
        String urldisplay = urls[0];
        Bitmap mIcon1 = null;
        try{
            InputStream in = new java.net.URL(urldisplay).openStream();
            mIcon1 = BitmapFactory.decodeStream(in);
        }
        catch (Exception e){
            Log.e("Error: ", e.getMessage());
            e.printStackTrace();
        }
        return mIcon1;
    }

    protected void onPostExecute(Bitmap result){
        bmImage.setImageBitmap(result);
    }
}
