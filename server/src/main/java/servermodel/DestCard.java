package servermodel;

public class DestCard
{
    private String cityOne;
    private String cityTwo;
    private int pointVal;

    public DestCard(String cityOne, String cityTwo, int pointVal) {
        this.cityOne = cityOne;
        this.cityTwo = cityTwo;
        this.pointVal = pointVal;
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

    public int getPointVal() {
        return pointVal;
    }

    public void setPointVal(int pointVal) {
        this.pointVal = pointVal;
    }
}
