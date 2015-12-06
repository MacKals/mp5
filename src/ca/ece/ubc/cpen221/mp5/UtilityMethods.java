package ca.ece.ubc.cpen221.mp5;

public abstract class UtilityMethods {

    /**
     * Converts a long to an int, provided the long can be converted without
     * losing any of its value
     * 
     * @param l
     *            long value we wish to convert to
     * @return the int corresponding to the long passed in
     * @throws IllegalArgumentException
     *             if the long cannot fit into the size of an int.
     */
    public static int safeLongToInt(long l) {
        if (l < Integer.MIN_VALUE || l > Integer.MAX_VALUE) {
            throw new IllegalArgumentException(l + " cannot be cast to int without changing its value.");
        }
        return (int) l;
    }
    
}
