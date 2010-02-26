
import java.io.*;
import java.util.*;
import javax.swing.*;

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
        FileReader in = null;
        try
        {
            Random r = new Random(System.currentTimeMillis());
            int lineToGet = r.nextInt() % getLinesCount();
            in = new FileReader(dictionaryFile);
            BufferedReader br = new BufferedReader(in);
            String currentLine = br.readLine();
            for(int i=0;i<lineToGet;i++)
            {
                currentLine = br.readLine();
            }
            this.currentPhrase = currentLine;
        } catch (IOException ex)
        {
            JOptionPane.showMessageDialog(null, ex.toString(), "Error File Not Found", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
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
