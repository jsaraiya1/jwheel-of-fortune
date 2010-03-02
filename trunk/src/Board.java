
import java.io.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

/**
 * @author James Gunter
 * Board.java
 * This class 
 */
public class Board {
    private String currentPhrase;
    private String phraseType;
    private boolean[] lettersGuessed;
    File dictionaryFile;

    public Board(File dictionaryFile)
    {
        this.dictionaryFile = dictionaryFile;
        currentPhrase = "";
        phraseType = "";
        lettersGuessed = new boolean[26];
        newPhrase();
    }

    public String getCurrentPhrase()
    {
        return currentPhrase;
    }

    public String getPhraseType()
    {
        return phraseType;
    }

    public void newPhrase()
    {
        phraseType = "";

        String newPhrase = "";
        do
        {
        FileReader in = null;
        try
        {
            Random r1 = new Random(System.currentTimeMillis());
            try
            {
                Thread.sleep(Math.abs(r1.nextLong()) % 400);
            } catch (InterruptedException ex)
            {
                Logger.getLogger(Board.class.getName()).log(Level.SEVERE, null, ex);
            }
            Random r2 = new Random(System.currentTimeMillis());
            int lineToGet = Math.abs((r1.nextInt() + r2.nextInt()) % getLinesCount());
            in = new FileReader(dictionaryFile);
            BufferedReader br = new BufferedReader(in);
            String currentLine = br.readLine();
            for(int i=0;i<lineToGet;i++)
            {
                currentLine = br.readLine();
            }
            boolean foundDelimiter = false;
            for(int i=0;i<currentLine.length();i++)
            {
                if(currentLine.charAt(i) == '|')
                {
                    foundDelimiter = true;
                }
                else if(foundDelimiter)
                {
                    newPhrase += currentLine.charAt(i);
                }
                else
                {
                    this.phraseType += currentLine.charAt(i);
                }

            }
        } catch (IOException ex)
        {
            JOptionPane.showMessageDialog(null, ex.toString(), "Error File Not Found", JOptionPane.ERROR_MESSAGE);
            return;
        }
        }while(currentPhrase.equalsIgnoreCase(newPhrase));
        this.currentPhrase = newPhrase;
    }
    /**
     * This function will try to guess a letter. It return how many of that letter
     * is in the phrase, 0 if not found, and -1 if they have already guesses that
     * letter.
     * @param letter - Letter to guess
     * @return (int) Number of times that letter appears in the phrase.<br />0 - Not Found <br />-1 - Already guess<br />
     */
    public int guessLetter(char letter)
    {
        letter = Character.toUpperCase(letter);
        if(letter>'Z' || letter < 'A')
            throw new IllegalArgumentException("letter must be a letter a-z");
        if(hasGuessed(letter))
            return -1;
        int numOfLetter = 0;
        letter = Character.toUpperCase(letter);
        lettersGuessed[letter - 'A'] = true;
        if(currentPhrase.toUpperCase().contains("" + letter))
        {
            for(int i=0;i<currentPhrase.length();i++)
            {
                if(Character.toUpperCase(currentPhrase.charAt(i)) == letter)
                    numOfLetter++;
            }
        }
        return numOfLetter;
    }
    public boolean tryToSolve(String guess)
    {
        return (guess.equalsIgnoreCase(currentPhrase));
    }

    private boolean hasGuessed(char letter)
    {
        letter = Character.toUpperCase(letter);
        return lettersGuessed[letter - 'A'];
    }

    private int getLinesCount() throws IOException {
    InputStream is = new BufferedInputStream(new FileInputStream(dictionaryFile));
    byte[] c = new byte[1024];
    int count = 0;
    int readChars = 0;
    while ((readChars = is.read(c)) != -1) {
        for (int i = 0; i < readChars; ++i) {
            if (c[i] == '\n')
                ++count;
        }
    }
    return count;
}

}
