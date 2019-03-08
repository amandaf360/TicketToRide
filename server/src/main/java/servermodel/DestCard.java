package servermodel;

public class DestCard
{
    private String cityOne;
    private String cityTwo;
    private int length;

    public DestCard(String cityOne, String cityTwo, int pointVal) {
        this.cityOne = cityOne;
        this.cityTwo = cityTwo;
        this.length = pointVal;
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

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }
}
