import java.util.*;
/**
 * Leah Kazenmayer
 */
public class Di
{
    Random randomInteger = new Random();
    public int roll()
    {
        int number = randomInteger.nextInt(6) + 1;
        return number;
    }
}