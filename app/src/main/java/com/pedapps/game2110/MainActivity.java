package com.pedapps.game2110;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.net.URL;


public class MainActivity extends Activity {

    ImageView evil;
    ImageView self;
    ImageView bullet;
    RelativeLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setUp();
    }

    public void setUp() {
        layout = (RelativeLayout) findViewById(R.id.layout);
        evil = (ImageView) findViewById(R.id.evil);
        self = (ImageView) findViewById(R.id.self);

        bullet = (ImageView) findViewById(R.id.bullet);
        bullet.setImageDrawable(getResources().getDrawable(R.drawable.bullet));

        evil.setImageDrawable(getResources().getDrawable(R.drawable.spaceship));
        layout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                self.setX(event.getX());
                return true;
            }
        });

        BulletHandler bh = new BulletHandler();
        bh.execute(bullet);
    }





 class BulletHandler extends AsyncTask<ImageView, ImageView, Void> {
     float x, y;
     float v_x, v_y;
     float THRESH = 40;

     public BulletHandler() {
         x = 0;
         y = 0;
         v_x = 5;
         v_y = 5;
     }

     protected Void doInBackground(ImageView... bullets) {
        ImageView bullet = bullets[0];
        x = bullet.getX();
        y = bullet.getY();

         while (true) {
             move(bullet);
             try {
                 Thread.sleep(100);
             } catch (InterruptedException e) {
                 e.printStackTrace();
             }
             publishProgress(bullet);
         }

    }

     public void move(ImageView bullet) {

         if (x < (MainActivity.this.layout.getWidth() - THRESH )) {
             x = x + v_x;
         }
         else {
             v_x -= -1 ;
             x += v_x;
         }
         if ()

     }

    protected void onProgressUpdate(ImageView... bullets) {
        ImageView bullet = bullets[0];
        bullet.setX(x);
        bullet.setY(y);
    }

    protected void onPostExecute(Void v) {

    }
}
}
