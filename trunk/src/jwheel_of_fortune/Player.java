package jwheel_of_fortune;


import java.io.*;

/**
 * @author James Gunter
 * Player.java
 * This class 
 */
public class Player {
    private int totalScore;
    private int currentScore;
    private String playerName;
    File playerImage;

    public Player(String playerName, File playerImage)
    {
        this.playerName = playerName;
        this.playerImage = playerImage;
    }

    public File getPlayerImage()
    {
        return playerImage;
    }

    public String getPlayerName()
    {
        return playerName;
    }
    
    public int getCurrentScore()
    {
        return currentScore;
    }

    public int getTotalScore()
    {
        return totalScore;
    }

    public void keepPoints(int points)
    {
        this.totalScore += points;
    }

    public void addPoints(int points)
    {
        this.currentScore += points;
    }
}
