package servermodel;

import java.io.Serializable;

public class DestPointsInfo implements Serializable
{
    private String username;
    private int positivePoints;
    private int negativePoints;

    public DestPointsInfo(String username, int positivePoints, int negativePoints) {
        this.username = username;
        this.positivePoints = positivePoints;
        this.negativePoints = negativePoints;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getPositivePoints() {
        return positivePoints;
    }

    public void setPositivePoints(int positivePoints) {
        this.positivePoints = positivePoints;
    }

    public int getNegativePoints() {
        return negativePoints;
    }

    public void setNegativePoints(int negativePoints) {
        this.negativePoints = negativePoints;
    }
}
