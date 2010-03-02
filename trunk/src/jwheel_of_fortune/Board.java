package jwheel_of_fortune;

/*
 * Board.java
 * 
 * 0.1 James - Initial version
 * 0.2 Cameron - Changed file to be loaded into memory at initialization.  Throws exceptions to
 *               separate model from interface and allow GUI to handle errors.  Renamed fields
 *               and added comments to aid future editing.
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

/**
 * A board used in Wheel of Fortune.  Reads phrases from a dictionary and then chooses random
 * phrases for players to guess.
 * 
 * @author James Gunter
 * @author Cameron Neblett
 * @version 0.2
 */
public class Board
{
    private static final String DEFAULT_FILE = "dictionary.txt";

    /**
     * A random number generator to be used wherever necessary.
     */
    private static final Random RANDOM = new Random();

    /**
     * The phrase currently being guessed.
     */
    private String my_phrase;

    /**
     * The current phrase with only the guessed letters and punctuation.
     */
    private String my_guessed_phrase;

    /**
     * The category of the phrase.  (Person, Place, Thing, whatever...)
     */
    private String my_category;

    /**
     * The letters guessed so far.
     */
    private boolean[] my_guesses;

    /**
     * The set of phrases to choose from.  A list of 2-element string arrays, with the first
     * element being the category and the second element being the phrase itself.
     */
    private List<String[]> my_phrase_list;

    /**
     * A constructor that loads the default dictionary file.
     * 
     * @throws FileNotFoundException if the default file does not exist
     */
    public Board() throws FileNotFoundException
    {
        this(new File(DEFAULT_FILE));
    }

    /**
     * A constructor that takes a dictionary file and loads it into memory.
     * 
     * @param dictionary_file The file to read
     * @throws FileNotFoundException if dictionary_file does not exist
     */
    public Board(final File dictionary_file) throws FileNotFoundException
    {
        my_phrase_list = new ArrayList<String[]>();
        loadFile(dictionary_file);
        my_guesses = new boolean[26];
        newPhrase();
    }

    /**
     * Picks a new phrase and starts a new board.
     */
    public void newPhrase()
    {
        // Remove the phrase so that it doesn't get repeated later
        final String[] next = my_phrase_list.remove(RANDOM.nextInt(my_phrase_list.size()));

//TODO: Devise appropriate behavior when phrase list becomes exhausted
        my_category = next[0];
        my_phrase = next[1];
        for (int i = 0; i < my_guesses.length; i++)
        {
            my_guesses[i] = false;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < my_phrase.length(); i++)
        {
            final char letter = my_phrase.charAt(i);
            if (letter < 'A' || letter > 'Z')
            {
                // This is some form of punctuation (space, apostrophe, whatever)
                sb.append(letter);
            }
            else
            {
                // This is a letter, and we want a blank character
                sb.append('_');
            }
        }
        my_guessed_phrase = sb.toString();
    }

    /**
     * Loads a provided file into the phrase list.
     * 
     * @param the_file The file to load
     * @throws FileNotFoundException if the_file does not exist
     */
    private void loadFile(final File the_file) throws FileNotFoundException
    {
        final Scanner scan = new Scanner(the_file);

        while (scan.hasNextLine())
        {
//TODO: Error checking!
            final String str = scan.nextLine();
            String[] phrase = new String[2];
            for (int i = 0; i < str.length(); i++)
            {
                if (str.charAt(i) == '|')
                {
                    phrase[0] = str.substring(0, i);
                    phrase[1] = str.substring(i + 1).toUpperCase();
                    break;
                }
            }
            my_phrase_list.add(phrase);
        }
    }

    /**
     * Returns the current phrase as it has been guessed so far.  Letters left to guess are
     * provided as underscores.
     * 
     * @return The phrase with guessed letters filled in
     */
    public String getCurrentPhrase()
    {
        return my_guessed_phrase;
    }

    public String getCategory()
    {
        return my_category;
    }

    /**
     * Evaluates how many occurrences of a letter are in the current phrase.
     * 
     * @param letter The guessed letter
     * @return The number of times that letter appears in the phrase, or -1 if the letter has
     *         already been guessed.
     */
    public int guessLetter(char letter)
    {
        letter = Character.toUpperCase(letter);
        if(letter > 'Z' || letter < 'A')
        {
            //throw new IllegalArgumentException("letter must be a letter a-z");
            return 0;   // Do we really need to throw an exception? - Cameron
        }
        if(my_guesses[letter - 'A'])
            return -1;
        int count = 0;
        my_guesses[letter - 'A'] = true;
        for(int i = 0; i < my_phrase.length(); i++)
        {
            if(my_phrase.charAt(i) == letter)
            {
                count++;
                // Damn you, immutable strings!!! - Cameron
                my_guessed_phrase = my_guessed_phrase.substring(0, i) + letter +
                                    my_guessed_phrase.substring(i + 1);
            }
        }
        return count;
    }

    /**
     * Checks is a provided string is the current phrase being guessed.
     * 
     * <BR><BR>NOTE: Punctuation is not ignored
     * 
     * @param guess The guessed phrase
     * @return true if the phrase is correct, false otherwise
     */
    public boolean tryToSolve(final String guess)
    {
        return (guess.equalsIgnoreCase(my_phrase));
    }
}
