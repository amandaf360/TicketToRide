package com.example.amandafails.tickettoride.app.subviews;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import java.util.ArrayList;

public class TrainView extends View
{
    private Rect rect;
    private Paint paint;
    private ArrayList<MapCity> cities;

    public TrainView(Context context)
    {
        super(context);
        init(null);
    }

    public TrainView(Context context, @Nullable AttributeSet attrs)
    {
        super(context, attrs);
        init(attrs);
    }

    public TrainView(Context context, @Nullable AttributeSet attrs, int defStyleAttr)
    {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    public TrainView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes)
    {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(attrs);
    }

    private void init(@Nullable AttributeSet set)
    {
        cities = new ArrayList<>();
        System.out.println("this is the init method\n");
        rect = new Rect();
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        cities.add(new MapCity(100, 100, "Atlanta"));
        cities.add(new MapCity(100, 100, "Boston"));
        cities.add(new MapCity(100, 100, "Calgary"));
        cities.add(new MapCity(100, 100, "Charleston"));
        cities.add(new MapCity(100, 100, "Chicago"));
        cities.add(new MapCity(100, 100, "Dallas"));
        cities.add(new MapCity(100, 100, "Denver"));
        cities.add(new MapCity(100, 100, "Duluth"));
        cities.add(new MapCity(100, 100, "El Paso"));
        cities.add(new MapCity(100, 100, "Helena"));
        cities.add(new MapCity(100, 100, "Houston"));
        cities.add(new MapCity(100, 100, "Kansas City"));
        cities.add(new MapCity(100, 100, "Las Angeles"));
        cities.add(new MapCity(100, 100, "Las Vegas"));
        cities.add(new MapCity(100, 100, "Little Rock"));
        cities.add(new MapCity(100, 100, "Miami"));
        cities.add(new MapCity(100, 100, "Montreal"));
        cities.add(new MapCity(100, 100, "Nashville"));
        cities.add(new MapCity(100, 100, "New Orleans"));
        cities.add(new MapCity(100, 100, "New York"));
        cities.add(new MapCity(100, 100, "Oklahoma City"));
        cities.add(new MapCity(100, 100, "Omaha"));
        cities.add(new MapCity(100, 100, "Phoenix"));
        cities.add(new MapCity(100, 100, "Pittsburgh"));
        cities.add(new MapCity(100, 100, "Portland"));
        cities.add(new MapCity(100, 100, "Raleigh"));
        cities.add(new MapCity(100, 100, "Saint Louis"));
        cities.add(new MapCity(100, 100, "Salt Lake City"));
        cities.add(new MapCity(100, 100, "San Francisco"));
        cities.add(new MapCity(100, 100, "Santa Fe"));
        cities.add(new MapCity(100, 100, "Sault St. Marie"));
        cities.add(new MapCity(100, 100, "Seattle"));
        cities.add(new MapCity(100, 100, "Toronto"));
        cities.add(new MapCity(300, 0, "Vancouver"));
        cities.add(new MapCity(100, 100, "Washington"));
        cities.add(new MapCity(100, 100, "Winnipeg"));
    }

    @Override
    protected void onDraw(Canvas canvas)
    {
        System.out.println("this is the onDraw method\n");
        canvas.save();
        for(int i = 0; i < cities.size(); i++)
        {
            drawCity(canvas, cities.get(i).getX(), cities.get(i).getY());
        }

        //rect.set(500, 50, 650, 150);
        //canvas.rotate(45, 10, 10);
        //canvas.drawRect(rect, paint);
        //canvas.restore();
    }
    //NOTE: You can force the UI to redraw itself with the postinvalidate method
    //NOTE: Each use of canvas.rotate() needs an instance of canvas.save() before it (to save the unrotated state)
    //and an instance of canvas.restore() afterwards (to return the canvas to an unrotated state)

    private void drawCity(Canvas canvas, float x, float y)
    {
        paint.setColor(Color.RED);
        canvas.drawCircle(x, y, 32, paint);
        paint.setColor(Color.WHITE);
        canvas.drawCircle(x, y, 26, paint);
        paint.setColor(Color.RED);
        canvas.drawCircle(x, y, 20, paint);
    }


    class MapCity
    {
        float x;
        float y;
        String name;

        public MapCity(float x, float y, String name)
        {
            this.x = x;
            this.y = y;
            this.name = name;
        }

        public float getX()
        {
            return x;
        }

        public float getY()
        {
            return y;
        }

        public String getName()
        {
            return name;
        }
    }
}


