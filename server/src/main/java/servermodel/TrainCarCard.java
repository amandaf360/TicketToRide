package servermodel;

import java.io.Serializable;

public class TrainCarCard implements Serializable
{
    private String color;

    public TrainCarCard(String color) {
        this.color = color;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
