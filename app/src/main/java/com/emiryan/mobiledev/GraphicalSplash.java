package com.emiryan.mobiledev;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Bundle;
import android.view.View;

public class GraphicalSplash extends AppCompatActivity {

    private int changeCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DrawView drawView = new DrawView(this);
        drawView.setOnTouchListener((v, event) -> {
            drawView.changeColor();
            changeCount++;
            if(changeCount == 5) {
                startActivity(new Intent(this, MainActivity.class));
            }
            return true;
        });
        setContentView(drawView);
    }

    class DrawView extends View {

        private int mainColor;
        private int[] colors = {Color.YELLOW, Color.RED, Color.WHITE};
        private int colorIdx = 0;

        public DrawView(Context context) {
            super(context);
            mainColor = Color.YELLOW;
        }

        public void changeColor() {
            if(colorIdx < 3) {
                mainColor = colors[colorIdx++];
            }
        }

        @Override
        protected void onDraw(Canvas canvas) {
            canvas.drawRGB(0,0,0);
            Paint paint = new Paint();
            paint.setColor(mainColor);
            canvas.drawOval(225, 400, 825, 1000, paint);
            paint.setStrokeWidth(4);
            paint.setColor(Color.BLACK);
            paint.setStyle(Paint.Style.FILL_AND_STROKE);
            paint.setAntiAlias(true);
            int x = 200;
            int y = 620;
            Path path = new Path();
            path.moveTo(x, y);
            path.lineTo(x, y + 200);
            path.lineTo(x + 200, y + 100);
            path.moveTo(x, y + 200);
            path.lineTo(x + 200, y + 100);
            path.close();
            canvas.drawPath(path, paint);

            canvas.drawOval(300, 550, 400, 650, paint);

            invalidate();
        }
    }
}