
import java.io.*;

/**
 * @author James Gunter
 * Board.java
 * This class 
 */
public class Board {
    private String currentPhrase;
    private boolean[] lettersgussed;
    File dictionaryFile;

    public Board(File dictionaryFile)
    {
        this.dictionaryFile = dictionaryFile;
        newPhrase();
    }

    public String getCurrentPhrase()
    {
        return currentPhrase;
    }

    public void newPhrase()
    {
        //get new phrase from dictionary
        currentPhrase = "The Dog Ran";
    }
    public int guessLetter(char letter)
    {
        int numOfLetter = 1;
        return numOfLetter;
    }
    public boolean tryToSolve(String guess)
    {
        return (guess.equalsIgnoreCase(currentPhrase));
    }
}
