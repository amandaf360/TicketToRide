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
    private Paint paint2;
    private Paint paintBlack;
    private Paint paintGrey;
    private Paint paintWhite;

    private int rectLength;
    private int rectWidth;
    private int spacing;

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

        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint2 = new Paint(Paint.ANTI_ALIAS_FLAG);
        paintBlack = new Paint(Paint.ANTI_ALIAS_FLAG);
        paintBlack.setColor(Color.BLACK);
        paintWhite = new Paint(Paint.ANTI_ALIAS_FLAG);
        paintWhite.setColor(Color.WHITE);
        paintGrey = new Paint(Paint.ANTI_ALIAS_FLAG);
        paintGrey.setColor(Color.GRAY);

        rectLength = 80;
        rectWidth = 40;
        spacing = 12;
        rect = new Rect(0, rectLength, 0, rectWidth);

        cities.add(new MapCity(1790, 1160, "Atlanta"));
        cities.add(new MapCity(2250, 510, "Boston"));
        cities.add(new MapCity(600, 50, "Calgary"));
        cities.add(new MapCity(1990, 1190, "Charleston"));
        cities.add(new MapCity(1630, 710, "Chicago"));
        cities.add(new MapCity(1170, 1300, "Dallas"));
        cities.add(new MapCity(850, 840, "Denver"));
        cities.add(new MapCity(1350, 480, "Duluth"));
        cities.add(new MapCity(750, 1310, "El Paso"));
        cities.add(new MapCity(750, 410, "Helena"));
        cities.add(new MapCity(1300, 1460, "Houston"));
        cities.add(new MapCity(1270, 860, "Kansas City"));
        cities.add(new MapCity(260, 1120, "Las Angeles"));
        cities.add(new MapCity(390, 980, "Las Vegas"));
        cities.add(new MapCity(1410, 1120, "Little Rock"));
        cities.add(new MapCity(2030, 1680, "Miami"));
        cities.add(new MapCity(2160, 260, "Montreal"));
        cities.add(new MapCity(1690, 1070, "Nashville"));
        cities.add(new MapCity(1490, 1450, "New Orleans"));
        cities.add(new MapCity(2160, 610, "New York"));
        cities.add(new MapCity(1180, 1070, "Oklahoma City"));
        cities.add(new MapCity(1240, 720, "Omaha"));
        cities.add(new MapCity(530, 1150, "Phoenix"));
        cities.add(new MapCity(1940, 670, "Pittsburgh"));
        cities.add(new MapCity(170, 320, "Portland"));
        cities.add(new MapCity(1960, 1040, "Raleigh"));
        cities.add(new MapCity(1460, 890, "Saint Louis"));
        cities.add(new MapCity(560, 740, "Salt Lake City"));
        cities.add(new MapCity(110, 840, "San Francisco"));
        cities.add(new MapCity(780, 1090, "Santa Fe"));
        cities.add(new MapCity(1640, 380, "Sault St. Marie"));
        cities.add(new MapCity(230, 180, "Seattle"));
        cities.add(new MapCity(1880, 500, "Toronto"));
        cities.add(new MapCity(300, 60, "Vancouver"));
        cities.add(new MapCity(2100, 860, "Washington"));
        cities.add(new MapCity(1060, 120, "Winnipeg"));


    }

    @Override
    protected void onDraw(Canvas canvas)
    {
        canvas.save();
        for(int i = 0; i < cities.size(); i++)
        {
            drawCity(canvas, cities.get(i).getX(), cities.get(i).getY());
        }

        paint.setColor(Color.RED);
        paint2.setColor(Color.GREEN);
        drawRoute(canvas, 980, 570, 122, 5, true);

    }
    //NOTE: You can force the UI to redraw itself with the postinvalidate method
    //NOTE: Each use of canvas.rotate() needs an instance of canvas.save() before it (to save the unrotated state)
    //and an instance of canvas.restore() afterwards (to return the canvas to an unrotated state)

    private void drawCity(Canvas canvas, float x, float y)
    {
        paint.setColor(Color.RED);
        canvas.drawCircle(x, y, 30, paint);
        paint.setColor(Color.WHITE);
        canvas.drawCircle(x, y, 26, paint);
        paint.setColor(Color.RED);
        canvas.drawCircle(x, y, 23, paint);
    }

    //PRE: Set the paint and paint2 objects to the colors of the route(s)
    private void drawRoute(Canvas canvas, float x, float y, float angle, int length, boolean doubleRoute)
    {
        canvas.save();
        canvas.rotate(angle, x, y);
        if(length % 2 == 0)
        {
            //each iteration of the for-loop makes two rectangles, one in each direction
            for(int i = 0; i < length/2; i++)
            {
                drawRectangle(canvas, x, y + i*rectLength/2.f + i*spacing/2.f, doubleRoute);
                drawRectangle(canvas, x, y - i*rectLength/2.f - i*spacing/2.f, doubleRoute);
            }
        }
        else
        {
            //this makes the first rectangle
            drawRectangle(canvas, x, y, doubleRoute);

            //this makes the rest, in sets of two
            for(int i = 1; i < (length + 1)/2; i++)
            {
                float topCenter = y + i*rectLength + i*spacing;
                drawRectangle(canvas, x, topCenter, doubleRoute);
                float bottomCenter = y - i*rectLength - i*spacing;
                drawRectangle(canvas, x, bottomCenter, doubleRoute);
            }
        }
        canvas.restore();
    }

    private void drawRectangle(Canvas canvas, float x, float y, boolean doubleRoute)
    {
        if(doubleRoute)
        {
            //set "left" rectangle
            rect.set((int)(x - rectWidth - 5*spacing/6), (int)(y - rectLength/2 - spacing/3), (int)(x - spacing/6), (int)(y + rectLength/2 + spacing/3) );
            canvas.drawRect(rect, paintBlack);
            rect.set((int)(x - rectWidth - 2*spacing/3), (int)(y - rectLength/2 - spacing/6), (int)(x - spacing/3), (int)(y + rectLength/2 + spacing/6) );
            canvas.drawRect(rect, paintWhite);
            rect.set((int)(x - rectWidth - spacing/2), (int)(y - rectLength/2), (int)(x - spacing/2), (int)(y + rectLength/2));
            canvas.drawRect(rect, paint);
            //set "right" rectangle
            rect.set((int)(x + spacing/6), (int)(y - rectLength/2 - spacing/3), (int)(x + rectWidth + 5*spacing/6), (int)(y + rectLength/2 + spacing/3) );
            canvas.drawRect(rect, paintBlack);
            rect.set((int)(x + spacing/3), (int)(y - rectLength/2 - spacing/6), (int)(x + rectWidth + 2*spacing/3), (int)(y + rectLength/2 + spacing/6) );
            canvas.drawRect(rect, paintWhite);
            rect.set((int)(x + spacing/2), (int)(y - rectLength/2), (int)(x + rectWidth + spacing/2), (int)(y + rectLength/2));
            canvas.drawRect(rect, paint2);
        }
        else
        {
            rect.set((int)(x - rectWidth/2 - spacing/3), (int)(y - rectLength/2 - spacing/3), (int)(x + rectWidth/2 + spacing/3), (int)(y + rectLength/2 + spacing/3) );
            canvas.drawRect(rect, paintBlack);
            rect.set((int)(x - rectWidth/2 - spacing/6), (int)(y - rectLength/2 - spacing/6), (int)(x + rectWidth/2 + spacing/6), (int)(y + rectLength/2 + spacing/6) );
            canvas.drawRect(rect, paintWhite);
            rect.set((int)(x - rectWidth/2), (int)(y - rectLength/2), (int)(x + rectWidth/2), (int)(y + rectLength/2) );
            canvas.drawRect(rect, paint);
        }
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

    class MapRoute
    {
        float x;
        float y;
        String name;
        
    }
}


