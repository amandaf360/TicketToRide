package servermodel;

import java.util.ArrayList;

public class ColorAssigner
{
    private ArrayList<String> colorList;
    private int index;

    public ColorAssigner()
    {
        colorList.add("blue");
        colorList.add("red");
        colorList.add("green");
        colorList.add("yellow");
        colorList.add("black");
    }

    public String assignColor()
    {
        String assignedColor = colorList.get(index);
        index = (index + 1) % 5;
        return assignedColor;
    }
}
