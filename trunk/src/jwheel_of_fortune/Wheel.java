package jwheel_of_fortune;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 * @author James Gunter
 * Wheel.java
 */
public class Wheel {

    /**
     * This makes all values on the wheel double
     */
    public static final int DOUBLE_TYPE = 2;
    /**
     * This specifies a normal wheel with values from 300-1000 with a bonus 2500
     */
    public static final int REGULAR_TYPE = 1;

    /**
     * Position on the wheel that bankrupts the player.
     */
    public static final int BANKRUPT = 0;
    /**
     * Position on the wheel and that makes them lose a turn
     */
    public static final int LOSE_A_TURN = -1;

    /**
     * List of all values on the face of the wheel
     */
    private ArrayList<Integer> values;
    /**
     * CurrentPosition the wheel is in.
     */
    private int currentPosition;

    /**
     * This is the default contrustor that makes a wheel of regular type.
     */
    public Wheel()
    {
        this(Wheel.REGULAR_TYPE);
    }

    /**
     * This constructor allows the creation of a custom wheel using a passed in
     * ArrayList<Integer> and you can optionaly have us add the Lose a Turn and 
     * Bankrupt
     * @param values - Must be an ArrayList<Integer>, contains all values on the wheel.
     * @param with_bankrupt_loseturn - Specifies whether you want to have Lose a Turn and Bankrupt on the wheel
     */
    public Wheel(ArrayList<Integer> values, boolean with_bankrupt_loseturn)
    {
        this.values = values;
        if(with_bankrupt_loseturn)
        {
            values.add(BANKRUPT);
            values.add(LOSE_A_TURN);
        }
        Collections.shuffle(values);
        currentPosition = 0;
    }

    /**
     * This Constructor allows you to specifiy the wheel type.
     * <br /><br />
     * Valid wheel types are:<br />
     * <ul>
     * <li>Wheel.REGULAR_TYPE</li>
     * <li>Wheel.DOUBLE_TYPE</li>
     * </ul>
     * @param wheelType - int specifying the wheel type
     */
    public Wheel(int wheelType)
    {
        if(wheelType !=1 && wheelType !=2)
            wheelType = 1;

        values = new ArrayList<Integer>();
        values.add(BANKRUPT);
        values.add(LOSE_A_TURN);
        for(int i=300;i<=1000;i+=50)
        {
            values.add(i * wheelType);
        }
        values.add(2500 * wheelType);
        
        Collections.shuffle(values);
        currentPosition = 0;
    }

    /**
     * Spins the wheel a random number of places ahead and then return the value
     * that it lands on. <br />
     * <br />
     * Remember to handle the case where it return a value of Wheel.BANKRUPT and
     * Wheel.LOSE_A_TURN
     * @return int - value the wheel landed on.
     */
    public int spin()
    {
        Random r = new Random();
        int move = r.nextInt(values.size());
        currentPosition = (currentPosition + move) % values.size();

        return values.get(currentPosition);
    }

}
