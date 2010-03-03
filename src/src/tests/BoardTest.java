/*
 * BoardTest.java
 * 
 * 0.1 Cameron - Initial version
 */
package tests;

import static org.junit.Assert.*;
import jwheel_of_fortune.Board;

import org.junit.Before;
import org.junit.Test;

/**
 * Some basic unit tests to cover the Wheel of Fortune board.
 * 
 * @author Cameron Neblett
 * @version 0.1
 */
public class BoardTest
{
    /**
     * The name of the test dictionary file.  This dictionary should contain only two entries
     * with the same phrase but different categories.
     */
    private static final String TEST_DICTIONARY = "test_dictionary.txt";

    /**
     * The board used for testing.
     */
    private Board my_board;

    /**
     * Initializes the board with the test dictionary.
     * 
     * @throws Exception if the test dictionary does not exist
     */
    @Before
    public void setUp() throws Exception
    {
        my_board = new Board(TEST_DICTIONARY);
    }

    @Test
    public void testNewPhrase()
    {
        final String old_category = my_board.getCategory();
        final String old_phrase = my_board.getCurrentPhrase();

        my_board.newPhrase();
        assertFalse("Categories are the same!", old_category.equals(my_board.getCategory()));
        assertTrue("Phrases are different!", old_phrase.equals(my_board.getCurrentPhrase()));
    }

    @Test
    public void testGuessLetter()
    {
        assertTrue("Wrong number of letters returned!", (my_board.guessLetter('A') == 3));
        assertEquals("Wrong result provided!", "A__ ____ _A__ A__ ______ __ __",
                     my_board.getCurrentPhrase());
    }

    @Test
    public void testTryToSolve()
    {
        final String old_category = my_board.getCategory();
        assertTrue("Correct guess was rejected!",
                   my_board.tryToSolve("aLL yOur BAsE ArE BELONG to us"));
        assertFalse("New phrase not chosen after correct solution!",
                    old_category.equals(my_board.getCategory()));
    }
}
