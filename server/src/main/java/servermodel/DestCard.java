package servermodel;

import java.io.Serializable;

public class DestCard implements Serializable
{
    private String cityOne;
    private String cityTwo;
    private int points;

    public DestCard(String cityOne, String cityTwo, int pointVal) {
        this.cityOne = cityOne;
        this.cityTwo = cityTwo;
        this.points = pointVal;
    }

    public String getCityOne() {
        return cityOne;
    }

    public void setCityOne(String cityOne) {
        this.cityOne = cityOne;
    }

    public String getCityTwo() {
        return cityTwo;
    }

    public void setCityTwo(String cityTwo) {
        this.cityTwo = cityTwo;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }
}
