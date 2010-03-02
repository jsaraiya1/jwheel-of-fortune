
import java.io.File;
import java.util.Random;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * @author James Gunter
 * TCSS 143
 * Assignment 2
 * Main.java
 * This class
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner is = new Scanner(System.in);
        Board b = new Board(new File("dictionary.txt"));
        char guess = ' ';
        
        do{
            System.out.println("type: " + b.getPhraseType());
            System.out.println(b.getCurrentPhrase());

            System.out.print("Enter a guess: ");
            guess = is.next().charAt(0);
            is.reset();
            int letters = b.guessLetter(guess);
            System.out.println("There were " + letters + " " + guess + "'s in that phrase.");
        }while(guess != '0');
    }

}
