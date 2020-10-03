package com.example.manipulatingbitmaps;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.os.Bundle;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    ImageView myImageView;
    Bitmap myBlankBitmap;
    Bitmap woodyBitmap;
    Canvas myCanvas;
    Paint myPaint;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        int widthInPixels = 2000;
        int heightInPixels = 1000;

        myBlankBitmap = Bitmap.createBitmap(widthInPixels , heightInPixels, Bitmap.Config.ARGB_8888);
        woodyBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.woody);
        myCanvas = new Canvas(myBlankBitmap);

        myImageView = new ImageView(this);
        myPaint = new Paint();

        myCanvas.drawColor(Color.argb(255,0,0,255));

        drawRotatedBitmaps();
        drawEnlargedBitmap();
        drawShrunkenBitmap();

        myImageView.setImageBitmap(myBlankBitmap);
        setContentView(myImageView);
    }

    private void drawShrunkenBitmap() {
        woodyBitmap = Bitmap.createScaledBitmap(woodyBitmap , 50,75,false);
        myCanvas.drawBitmap(woodyBitmap , 250 , 25 ,myPaint);
    }

    private void drawEnlargedBitmap() {
        woodyBitmap = Bitmap.createScaledBitmap(woodyBitmap  , 300 , 400 ,false);
        myCanvas.drawBitmap(woodyBitmap,25,25,myPaint);
    }

    private void drawRotatedBitmaps() {
        float rotation = 0f;
        int horizontalPos = 350;
        int verticalPos = 25;

        Matrix matrix = new Matrix();

        Bitmap rotatedBitmap = Bitmap.createBitmap(100,200 , Bitmap.Config.ARGB_8888);

        for(rotation = 0 ; rotation < 360 ; rotation = rotation + 30) {
            matrix.reset();
            matrix.preRotate(rotation);
            rotatedBitmap = Bitmap.createBitmap(woodyBitmap , 0 ,0 , woodyBitmap.getWidth() -1 , woodyBitmap.getHeight() -1 , matrix, true);
            myCanvas.drawBitmap(rotatedBitmap , horizontalPos , verticalPos , myPaint);
            horizontalPos +=120;
            verticalPos+=70;
        }
    }
}