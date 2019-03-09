package ClientModel;

public class PlayerHandTrains
{
    private int numWhite;
    private int numBlack;
    private int numOrange;
    private int numGreen;
    private int numPurple;
    private int numYellow;
    private int numBlue;
    private int numRed;
    private int numLocomotives;
    private int totalCards;

    public PlayerHandTrains(int numWhite, int numBlack, int numOrange, int numGreen, int numPurple, int numYellow, int numBlue, int numRed, int numLocomotives)
    {
        this.numWhite = numWhite;
        this.numBlack = numBlack;
        this.numOrange = numOrange;
        this.numGreen = numGreen;
        this.numPurple = numPurple;
        this.numYellow = numYellow;
        this.numBlue = numBlue;
        this.numRed = numRed;
        this.numLocomotives = numLocomotives;
        totalCards = 0;
    }

    public int getNumWhite()
    {
        return numWhite;
    }

    public void setNumWhite(int numWhite)
    {
        this.numWhite = numWhite;
    }

    public int getNumBlack()
    {
        return numBlack;
    }

    public void setNumBlack(int numBlack)
    {
        this.numBlack = numBlack;
    }

    public int getNumOrange()
    {
        return numOrange;
    }

    public void setNumOrange(int numOrange)
    {
        this.numOrange = numOrange;
    }

    public int getNumGreen()
    {
        return numGreen;
    }

    public void setNumGreen(int numGreen)
    {
        this.numGreen = numGreen;
    }

    public int getNumPurple()
    {
        return numPurple;
    }

    public void setNumPurple(int numPurple)
    {
        this.numPurple = numPurple;
    }

    public int getNumYellow()
    {
        return numYellow;
    }

    public void setNumYellow(int numYellow)
    {
        this.numYellow = numYellow;
    }

    public int getNumBlue()
    {
        return numBlue;
    }

    public void setNumBlue(int numBlue)
    {
        this.numBlue = numBlue;
    }

    public int getNumRed()
    {
        return numRed;
    }

    public void setNumRed(int numRed)
    {
        this.numRed = numRed;
    }

    public int getNumLocomotives()
    {
        return numLocomotives;
    }

    public void setNumLocomotives(int numLocomotives)
    {
        this.numLocomotives = numLocomotives;
    }

    public void addCard(TrainCarCard trainCarCard)
    {
        addCard(trainCarCard.getColor());
    }

    public void addCard(String color)
    {
        if(color.equals("red"))
        {
            numRed++;
            totalCards++;
        }
        else if(color.equals("orange"))
        {
            numOrange++;
            totalCards++;
        }else if(color.equals("yellow"))
        {
            numYellow++;
            totalCards++;
        }else if(color.equals("green"))
        {
            numGreen++;
            totalCards++;
        }else if(color.equals("blue"))
        {
            numBlue++;
            totalCards++;
        }else if(color.equals("purple"))
        {
            numPurple++;
            totalCards++;
        }else if(color.equals("black"))
        {
            numBlack++;
            totalCards++;
        }else if(color.equals("white"))
        {
            numWhite++;
            totalCards++;
        }else if(color.equals("locomotive"))
        {
            numLocomotives++;
            totalCards++;
        }
    }

    public int getTotalCards()
    {
        return totalCards;
    }
}
