package com.example.amandafails.tickettoride.app.subviews;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.support.annotation.Nullable;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;
import java.util.ArrayList;
import java.util.Map;

import ClientModel.Route;

public class TrainView extends View
{
    private Rect rect;
    private Paint paint;
    private Paint paint2;
    private Paint paintBlack;
    private Paint paintGray;
    private Paint paintWhite;

    private int rectLength;
    private int rectWidth;
    private int spacing;

    private int claimedRectLength;
    private int claimedRectWidth;

    private ArrayList<MapCity> cities;
    private ArrayList<MapRoute> routes;

    private Canvas myCanvas;

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
        routes = new ArrayList<>();

        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint2 = new Paint(Paint.ANTI_ALIAS_FLAG);
        paintBlack = new Paint(Paint.ANTI_ALIAS_FLAG);
        paintBlack.setColor(Color.BLACK);
        paintWhite = new Paint(Paint.ANTI_ALIAS_FLAG);
        paintWhite.setColor(Color.WHITE);
        paintGray = new Paint(Paint.ANTI_ALIAS_FLAG);
        paintGray.setColor(Color.GRAY);

        rectLength = 75;
        rectWidth = 25;
        spacing = 12;
        rect = new Rect(0, rectLength, 0, rectWidth);
        claimedRectLength = 40;
        claimedRectWidth = 40;

        cities.add(new MapCity(1790, 1160, "Atlanta"));
        cities.add(new MapCity(2310, 470, "Boston"));
        cities.add(new MapCity(600, 50, "Calgary"));
        cities.add(new MapCity(2010, 1250, "Charleston"));
        cities.add(new MapCity(1630, 710, "Chicago"));
        cities.add(new MapCity(1170, 1300, "Dallas"));
        cities.add(new MapCity(850, 840, "Denver"));
        cities.add(new MapCity(1350, 480, "Duluth"));
        cities.add(new MapCity(750, 1310, "El Paso"));
        cities.add(new MapCity(770, 420, "Helena"));
        cities.add(new MapCity(1300, 1460, "Houston"));
        cities.add(new MapCity(1270, 860, "Kansas City"));
        cities.add(new MapCity(210, 1160, "Las Angeles"));
        cities.add(new MapCity(410, 1000, "Las Vegas"));
        cities.add(new MapCity(1410, 1120, "Little Rock"));
        cities.add(new MapCity(1990, 1610, "Miami"));
        cities.add(new MapCity(2160, 260, "Montreal"));
        cities.add(new MapCity(1700, 1050, "Nashville"));
        cities.add(new MapCity(1490, 1450, "New Orleans"));
        cities.add(new MapCity(2160, 640, "New York"));
        cities.add(new MapCity(1180, 1070, "Oklahoma City"));
        cities.add(new MapCity(1210, 700, "Omaha"));
        cities.add(new MapCity(500, 1190, "Phoenix"));
        cities.add(new MapCity(1940, 730, "Pittsburgh"));
        cities.add(new MapCity(170, 320, "Portland"));
        cities.add(new MapCity(1990, 1040, "Raleigh"));
        cities.add(new MapCity(1480, 910, "Saint Louis"));
        cities.add(new MapCity(560, 740, "Salt Lake City"));
        cities.add(new MapCity(80, 840, "San Francisco"));
        cities.add(new MapCity(780, 1090, "Santa Fe"));
        cities.add(new MapCity(1650, 380, "Sault St. Marie"));
        cities.add(new MapCity(230, 180, "Seattle"));
        cities.add(new MapCity(1930, 470, "Toronto"));
        cities.add(new MapCity(300, 60, "Vancouver"));
        cities.add(new MapCity(2130, 860, "Washington"));
        cities.add(new MapCity(1150, 90, "Winnipeg"));

        //Total number of routes must = 77
        //each section includes doubles, but excludes doubles that have been covered by a previous section
        //red routes (total 7)
        routes.add(new MapRoute(980, 570, 124, 5, false, "red", "none", "Helena", "Omaha"));
        routes.add(new MapRoute(700, 790, 110, 3, true, "red", "yellow", "Salt Lake City", "Denver"));
        routes.add(new MapRoute(1000, 970, 125, 4, false, "red", "none", "Denver", "Oklahoma City"));
        routes.add(new MapRoute(960, 1310, 88, 4, false, "red", "none", "El Paso", "Dallas"));
        routes.add(new MapRoute(1760, 1560, 114, 6, false, "red", "none", "New Orleans", "Miami"));
        routes.add(new MapRoute(1490, 600, 130, 3, false, "red", "none", "Duluth", "Chicago"));
        routes.add(new MapRoute(2240, 560, 40, 2, true, "red", "yellow", "Boston", "New York City"));

        //yellow routes (total 5)
        routes.add(new MapRoute(500, 300, 114, 6, false, "yellow", "none", "Seattle", "Helena"));
        routes.add(new MapRoute(160, 1000, 152, 3, true, "purple", "yellow", "San Francisco", "Los Angeles"));
        routes.add(new MapRoute(960, 1180, 60, 5, false, "yellow", "none", "El Paso", "Oklahoma City"));
        routes.add(new MapRoute(1640, 1300, 44, 4, true, "yellow", "orange", "New Orleans", "Atlanta"));
        routes.add(new MapRoute(1820, 900, 36, 4, false, "yellow", "none", "Nashville", "Pittsburgh"));

        //blue routes (total 7)
        routes.add(new MapRoute(370, 530, 137, 6, false, "blue", "none", "Portland", "Salt Lake City"));
        routes.add(new MapRoute(950, 250, 45, 4, false, "blue", "none", "Helena", "Winnipeg"));
        routes.add(new MapRoute(950, 1080, 85, 3, false, "blue", "none", "Santa Fe", "Oklahoma City"));
        routes.add(new MapRoute(1420, 720, 88, 4, false, "blue", "none", "Omaha", "Chicago"));
        routes.add(new MapRoute(1380, 880, 101, 2, true, "blue", "purple", "Kansas City", "Saint Louis"));
        routes.add(new MapRoute(1900, 1410, 157, 5, false, "blue", "none", "Atlanta", "Miami"));
        routes.add(new MapRoute(2160, 450, 2, 3, false, "blue", "none", "New York City", "Montreal"));

        //green routes (total 7)
        routes.add(new MapRoute(130, 570, 7, 5, true, "green", "purple", "Portland", "San Francisco"));
        routes.add(new MapRoute(800, 630, 170, 4, false, "green", "none", "Helena", "Denver"));
        routes.add(new MapRoute(1020, 1400, 104, 6, false, "green", "none", "El Paso", "Houston"));
        routes.add(new MapRoute(1560, 820, 35, 2, true, "green", "white", "Saint Louis", "Chicago"));
        routes.add(new MapRoute(1710, 830, 63, 4, false, "green", "none", "Saint Louis", "Pittsburgh"));
        routes.add(new MapRoute(2040, 670, 67, 2, true, "green", "white", "Pittsburgh", "New York City"));
        routes.add(new MapRoute(1450, 1280, 350, 3, false, "green", "none", "Little Rock", "New Orleans"));

        //orange routes (total 6)
        routes.add(new MapRoute(1060, 440, 95, 6, false, "orange", "none", "Helena", "Duluth"));
        routes.add(new MapRoute(330, 790, 80, 5, true, "orange", "white", "San Francisco", "Salt Lake City"));
        routes.add(new MapRoute(500, 880, 31, 3, false, "orange", "none", "Las Vegas", "Salt Lake City"));
        routes.add(new MapRoute(1050, 850, 93, 4, true, "orange", "black", "Denver", "Kansas City"));
        routes.add(new MapRoute(1790, 700, 92, 3, true, "orange", "black", "Chicago", "Pittsburgh"));
        routes.add(new MapRoute(2150, 750, 7, 2, true, "orange", "black", "New York City", "Washington"));

        //purple routes (total 4)
        routes.add(new MapRoute(670, 580, 26, 3, false, "purple", "none", "Helena", "Salt Lake City"));
        routes.add(new MapRoute(1020, 750, 78, 4, false, "purple", "none", "Denver", "Omaha"));
        routes.add(new MapRoute(1640, 480, 89, 6, false, "purple", "none", "Duluth", "Toronto"));
        routes.add(new MapRoute(2020, 1460, 355, 4, false, "purple", "none", "Charleston", "Miami"));

        //White routes (total 4)
        routes.add(new MapRoute(870, 60, 91, 6, false, "white", "none", "Calgary", "Winnipeg"));
        routes.add(new MapRoute(680, 1020, 43, 5, false, "white", "none", "Denver", "Phoenix"));
        routes.add(new MapRoute(1780, 580, 58, 4, false, "white", "none", "Toronto", "Chicago"));
        routes.add(new MapRoute(1550, 1090, 80, 3, false, "white", "none", "Little Rock", "Nashville"));

        //Black routes (total 4)
        routes.add(new MapRoute(1260, 280, 155, 4, false, "black", "none", "Winnipeg", "Duluth"));
        routes.add(new MapRoute(1890, 320, 75, 5, false, "black", "none", "Sault St Marie", "Montreal"));
        routes.add(new MapRoute(1850, 1040, 86, 3, false, "black", "none", "Nashville", "Raleigh"));
        routes.add(new MapRoute(490, 1270, 107, 6, false, "black", "none", "Los Angeles", "El Paso"));

        //Gray routes (total...34?)
        routes.add(new MapRoute(1400, 220, 125, 6, false, "gray", "none", "Winnipeg", "Sault St Marie"));
        routes.add(new MapRoute(270, 120, 30, 1, true, "gray", "gray", "Vancouver", "Seattle"));
        routes.add(new MapRoute(200, 250, 30, 1, true, "gray", "gray", "Seattle", "Portland"));
        routes.add(new MapRoute(450, 60, 88, 3, false, "gray", "none", "Vancouver", "Calgary"));
        routes.add(new MapRoute(440, 130, 74, 4, false, "gray", "none", "Seattle", "Calgary"));
        routes.add(new MapRoute(680, 230, 155, 4, false, "gray", "none", "Calgary", "Helena"));
        routes.add(new MapRoute(1490, 430, 70, 3, false, "gray", "none", "Sault St Marie", "Duluth"));
        routes.add(new MapRoute(1780, 430, 105, 2, false, "gray", "none", "Sault St Marie", "Toronto"));
        routes.add(new MapRoute(2050, 370, 45, 3, false, "gray", "none", "Toronto", "Montreal"));
        routes.add(new MapRoute(2240, 360, 150, 2, true, "gray", "gray", "Montreal", "Boston"));

        routes.add(new MapRoute(1930, 600,0, 2, false, "gray", "none", "Toronto", "Pittsburgh"));
        routes.add(new MapRoute(2060, 950, 36, 2, true, "gray", "gray", "Washington", "Raleigh"));
        routes.add(new MapRoute(1960, 890, 174, 2, false, "gray", "none", "Pittsburgh", "Raleigh"));
        routes.add(new MapRoute(2030, 800, 125, 2, false, "gray", "none", "Pittsburgh", "Washington"));
        routes.add(new MapRoute(1890, 1110, 60, 2, true, "gray", "gray", "Atlanta", "Raleigh"));
        routes.add(new MapRoute(2020, 1130, 1, 2, false, "gray", "none", "Raleigh", "Charleston"));
        routes.add(new MapRoute(1890, 1210, 105, 2, false, "gray", "none", "Atlanta", "Charleston"));
        routes.add(new MapRoute(1740, 1110, 145, 1, false, "gray", "none", "Atlanta", "Nashville"));
        routes.add(new MapRoute(1590, 980, 123, 2, false, "gray", "gray", "Saint Louis", "Nashville"));
        routes.add(new MapRoute(1440, 1020, 22, 2, false, "gray", "none", "Little Rock", "Saint Louis"));

        routes.add(new MapRoute(1290, 1100, 100, 2, false, "gray", "none", "Little Rock", "Oklahoma City"));
        routes.add(new MapRoute(1290, 1220, 51, 2, false, "gray", "none", "Dallas", "Little Rock"));
        routes.add(new MapRoute(1250, 1380, 140, 1, true, "gray", "gray", "Dallas", "Houston"));
        routes.add(new MapRoute(1400, 1480, 87, 2, false, "gray", "none", "Houston", "New Orleans"));
        routes.add(new MapRoute(1180, 1190, 185, 2, true, "gray", "gray", "Dallas", "Oklahoma City"));
        routes.add(new MapRoute(1230, 970, 20, 2, true, "gray", "gray", "Oklahoma City", "Kansas City"));
        routes.add(new MapRoute(1240, 780, 165, 1, true, "gray", "gray", "Kansas City", "Omaha"));
        routes.add(new MapRoute(1270, 580, 30, 2, true, "gray", "gray", "Omaha", "Duluth"));
        routes.add(new MapRoute(820, 980, 10, 2, false, "gray", "none", "Santa Fe", "Denver"));
        routes.add(new MapRoute(770, 1200, 10, 2, false, "gray", "none", "Santa Fe", "El Paso"));

        routes.add(new MapRoute(650, 1140, 60, 3, false, "gray", "none", "Santa Fe", "Phoenix"));
        routes.add(new MapRoute(620, 1260, 110, 3, false, "gray", "none", "Phoenix", "El Paso"));
        routes.add(new MapRoute(360, 1180, 92, 3, false, "gray", "none", "Los Angeles", "Phoenix"));
        routes.add(new MapRoute(310, 1080, 49, 2, false, "gray", "none", "Los Angeles", "Las Vegas"));
    }

    @Override
    protected void onDraw(Canvas canvas)
    {
        myCanvas = canvas;
        canvas.save();
        for(int i = 0; i < cities.size(); i++)
        {
            drawCity(canvas, cities.get(i).getX(), cities.get(i).getY());
        }

        for(int i = 0; i < routes.size(); i++)
        {
            //this bit, unfortunately, must be done manually.
            switch(routes.get(i).getPaint())
            {
                case "gray":   paint.setColor(Color.GRAY); break;
                case "white":  paint.setColor(Color.WHITE); break;
                case "black":  paint.setColor(Color.BLACK); break;
                case "red":    paint.setColor(Color.RED); break;
                case "blue":   paint.setColor(Color.parseColor("cyan")); break;
                case "green":  paint.setColor(Color.GREEN); break;
                case "yellow": paint.setColor(Color.YELLOW); break;
                case "orange": paint.setColor(Color.parseColor("#FFA500")); break;
                case "purple": paint.setColor(Color.parseColor("purple")); break;
                default:break;
            }
            switch(routes.get(i).getPaint2())
            {
                case "gray":   paint2.setColor(Color.GRAY); break;
                case "white":  paint2.setColor(Color.WHITE); break;
                case "black":  paint2.setColor(Color.BLACK); break;
                case "red":    paint2.setColor(Color.RED); break;
                case "blue":   paint2.setColor(Color.parseColor("cyan")); break;
                case "green":  paint2.setColor(Color.GREEN); break;
                case "yellow": paint2.setColor(Color.YELLOW); break;
                case "orange": paint2.setColor(Color.parseColor("#FFA500")); break;
                case "purple": paint2.setColor(Color.parseColor("purple")); break;
                default:break;
            }
            drawRoute(canvas, routes.get(i).getX(), routes.get(i).getY(), routes.get(i).getAngle(), routes.get(i).getLength(), routes.get(i).isDoubleRoute());


            if(routes.get(i).getClaimedColor1() != null)
            {
                switch(routes.get(i).getClaimedColor1())
                {
                    case "gray":   paint.setColor(Color.GRAY); break;
                    case "white":  paint.setColor(Color.WHITE); break;
                    case "black":  paint.setColor(Color.BLACK); break;
                    case "red":    paint.setColor(Color.RED); break;
                    case "blue":   paint.setColor(Color.parseColor("cyan")); break;
                    case "green":  paint.setColor(Color.GREEN); break;
                    case "yellow": paint.setColor(Color.YELLOW); break;
                    case "orange": paint.setColor(Color.parseColor("#FFA500")); break;
                    case "purple": paint.setColor(Color.parseColor("purple")); break;
                    default:break;
                }
            }
            if(routes.get(i).getClaimedColor2() != null)
            {
                switch(routes.get(i).getClaimedColor2())
                {
                    case "gray":   paint2.setColor(Color.GRAY); break;
                    case "white":  paint2.setColor(Color.WHITE); break;
                    case "black":  paint2.setColor(Color.BLACK); break;
                    case "red":    paint2.setColor(Color.RED); break;
                    case "blue":   paint2.setColor(Color.parseColor("cyan")); break;
                    case "green":  paint2.setColor(Color.GREEN); break;
                    case "yellow": paint2.setColor(Color.YELLOW); break;
                    case "orange": paint2.setColor(Color.parseColor("#FFA500")); break;
                    case "purple": paint2.setColor(Color.parseColor("purple")); break;
                    default:break;
                }
            }
            if(routes.get(i).getClaimedColor2() != null || routes.get(i).getClaimedColor1() != null)
            {
                drawClaimedRoute(canvas, routes.get(i).getX(), routes.get(i).getY(), routes.get(i).getAngle(), routes.get(i).getLength(), routes.get(i).isDoubleRoute());
            }
        }

        for(int i = 0; i < cities.size(); i++)
        {
            drawCityName(canvas, cities.get(i).getX() - 30, cities.get(i).getY() - 50, cities.get(i).getName());
        }

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

    private void drawCityName(Canvas canvas, float x, float y, String text) {
        TextPaint textPaint = new TextPaint();
        textPaint.setTextSize(30);
        textPaint.setTextAlign(Paint.Align.LEFT);
        textPaint.setColor(Color.BLUE);
        textPaint.setTypeface(Typeface.create("Arial", Typeface.BOLD));
        canvas.drawText(text, x, y, textPaint);
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
                drawRectangle(canvas, x, y + (i + 0.5f)*rectLength + (i + 0.5f)*spacing, doubleRoute);
                drawRectangle(canvas, x, y - (i + 0.5f)*rectLength - (i + 0.5f)*spacing, doubleRoute);
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

    public void drawClaimedRoute(Canvas canvas, float x, float y, float angle, int length, boolean doubleRoute)
    {
        canvas.save();
        canvas.rotate(angle, x, y);

        int tempRectLength = rectLength;
        int tempRectWidth = rectWidth;

        rectLength = claimedRectLength;
        rectWidth = claimedRectWidth;

        if(length % 2 == 0)
        {
            //each iteration of the for-loop makes two rectangles, one in each direction
            for(int i = 0; i < length/2; i++)
            {
                drawRectangle(canvas, x, y + (i + 0.5f)*tempRectLength + (i + 0.5f)*spacing, doubleRoute);
                drawRectangle(canvas, x, y - (i + 0.5f)*tempRectLength - (i + 0.5f)*spacing, doubleRoute);
            }
        }
        else
        {
            //this makes the first rectangle
            drawRectangle(canvas, x, y, doubleRoute);

            //this makes the rest, in sets of two
            for(int i = 1; i < (length + 1)/2; i++)
            {
                float topCenter = y + i*tempRectLength + i*spacing;
                drawRectangle(canvas, x, topCenter, doubleRoute);
                float bottomCenter = y - i*tempRectLength - i*spacing;
                drawRectangle(canvas, x, bottomCenter, doubleRoute);
            }
        }

        rectLength = tempRectLength;
        rectWidth = tempRectWidth;

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


    public void claimRoute(Route route)
    {
        System.out.println("*" + route.getCityOne() + "*");
        System.out.println("*" + route.getCityTwo() + "*");
        paint.setColor(Color.parseColor(route.getClaimedBy().getColor()));
        for(int i = 0; i < routes.size(); i++)
        {
            System.out.println("*" + routes.get(i).getCity1() + "*");
            System.out.println("*" + routes.get(i).getCity2() + "*");
            if(routes.get(i).getCity1().equals(route.getCityOne()))
            {
                if(routes.get(i).getCity2().equals(route.getCityTwo()))
                {
                    routes.get(i).setClaimed1(true);
                    routes.get(i).setClaimedColor1(route.getClaimedBy().getColor());
                }
            }
            else if(routes.get(i).getCity2().equals(route.getCityOne()))
            {
                if(routes.get(i).getCity1().equals(route.getCityTwo()))
                {
                    routes.get(i).setClaimed1(true);
                    routes.get(i).setClaimedColor1(route.getClaimedBy().getColor());
                }
            }
        }
        postInvalidate();
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

    public class MapRoute
    {
        float x;
        float y;
        float angle;
        int length;
        boolean doubleRoute;
        String paint;
        String paint2;
        String city1;
        String city2;
        boolean claimed1;
        boolean claimed2;
        String claimedColor1;
        String claimedColor2;

        public boolean isClaimed1() {
            return claimed1;
        }

        public boolean isClaimed2() {
            return claimed2;
        }

        public String getClaimedColor1() {
            return claimedColor1;
        }

        public String getClaimedColor2() {
            return claimedColor2;
        }

        public void setClaimed1(boolean claimed1) {
            this.claimed1 = claimed1;
        }

        public void setClaimed2(boolean claimed2) {
            this.claimed2 = claimed2;
        }

        public void setClaimedColor1(String claimedColor1) {
            this.claimedColor1 = claimedColor1;
        }

        public void setClaimedColor2(String claimedColor2) {
            this.claimedColor2 = claimedColor2;
        }

        public MapRoute(float x, float y, float angle, int length, boolean doubleRoute, String paint, String paint2, String city1, String city2)
        {
            this.x = x;
            this.y = y;
            this.angle = angle;
            this.length = length;
            this.doubleRoute = doubleRoute;
            this.paint = paint;
            this.paint2 = paint2;
            this.city1 = city1;
            this.city2 = city2;
            claimed1 = false;
            claimed2 = false;
            claimedColor1 = null;
            claimedColor2 = null;
        }

        public float getX()
        {
            return x;
        }

        public String getPaint() {
            return paint;
        }

        public String getPaint2() {
            return paint2;
        }

        public float getY()
        {
            return y;
        }

        public float getAngle()
        {
            return angle;
        }

        public int getLength()
        {
            return length;
        }

        public boolean isDoubleRoute()
        {
            return doubleRoute;
        }

        public String getCity1()
        {
            return city1;
        }

        public String getCity2(){ return city2; }
    }
}


