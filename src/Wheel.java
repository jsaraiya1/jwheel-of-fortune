/**
 * @author James Gunter
 * Wheel.java
 * This class 
 */
public class Wheel {
    //static variables to map to values in the values array;
    public static final int BANKRUPT = 0;
    public static final int LOSE_A_TURN = -1;
    private int[] values;
    private int currentPosition;

    public Wheel()
    {
        values = new int[50];
        currentPosition = 0;
    }

    public int spin()
    {
        int landedOn = 1;
        currentPosition = 1;
        return values[1];
    }

}
