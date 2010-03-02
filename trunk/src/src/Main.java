/*
 * Main.java
 * 
 * 0.1 James - Initial version
 * 0.2 Cameron - Added exception checking to conform to new Board design.  Basic commenting.
 *               Added test for completed phrase and selecting new phrase.
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Runs a basic command line version of Wheel of Fortune
 * 
 * @author James Gunter
 * @author Cameron Neblett
 * @version 0.2
 */
public class Main
{
    /**
     * Starts the command line input.
     * 
     * @param args The command line arguments [IGNORED]
     */
    public static void main(final String[] args)
    {
        Scanner is = new Scanner(System.in);
        try
        {
            Board b = new Board(new File("dictionary.txt"));
            char guess = ' ';

            do
            {
                System.out.println("type: " + b.getCategory());
                System.out.println(b.getCurrentPhrase());

                System.out.print("Enter a guess: ");
                guess = is.next().charAt(0);
                int letters = b.guessLetter(guess);
                System.out.printf("There were %d %s's in that phrase.", letters, guess);
                if (!b.getCurrentPhrase().contains("_"))
                {
                    System.out.println("You guessed the phrase!");
                    b.newPhrase();
                }
            }
            while(guess != '0');
        }
        catch (final FileNotFoundException e)
        {
            System.err.println("Dictionary file not found!");
        }
    }
}
