/**
 * Along a long library corridor, there is a line of seats and decorative plants. You are given a 0-indexed string
 * corridor of length n consisting of letters 'S' and 'P' where each 'S' represents a seat and each 'P' represents a
 * plant.
 *
 * One room divider has already been installed to the left of index 0, and another to the right of index n - 1.
 * Additional room dividers can be installed. For each position between indices i - 1 and i (1 <= i <= n - 1), at most
 * one divider can be installed.
 *
 * Divide the corridor into non-overlapping sections, where each section has exactly two seats with any number of plants.
 * There may be multiple ways to perform the division. Two ways are different if there is a position with a room divider
 * installed in the first way but not in the second way.
 *
 * Return the number of ways to divide the corridor. Since the answer may be very large, return it modulo 109 + 7.
 * If there is no way, return 0.
 * */
public class NumberOfWaysToDivideALongCorridor {
    // it is not even a medium level question, ot is very easy P&C question
    // if number of chairs are odd toh pairing nhi hogi toh 0 return krdo
    // 0-1 ki pairing hogi , 2-3 ki hogi and so on. toh apan ko ye find krna hai ki
    // 1 and 2 mai kitna gap hai if 3 plants aa rhe hai toh 3 ways hai, similarly 3 and 4 ke liye and 5 and 6 ke liye
    // let 1-2 ke beech p choices hai, 3-4 ke beech q choices hai and so on..
    // toh answer will be p*q*r....
    public int numberOfWays(String corridor) {
        int n = corridor.length();
        int numberOfSeats = 0;
        int mod = 1000000007;

        // find total number of seats
        for (int i = 0; i < n; i++) {
            if(corridor.charAt(i)=='S') numberOfSeats++;
        }

        // if number of chairs are odd toh pairing nhi hogi toh 0 return krdo
        // also agar 2 se kam chair hai toh bhi pairing nhi hogi
        if(numberOfSeats<2 || numberOfSeats%2==1) return 0;

        // find index of each seat
        int seatIndex[] = new int[numberOfSeats];
        int seat = 0;
        for (int i = 0; i < n; i++) {
            if (corridor.charAt(i)=='S'){seatIndex[seat] = i; seat++;}
        }

        int count = 1;

        // now multiply all choices
        for (int i = 1; i+1<numberOfSeats ; i=i+2) {
            int currentSeatIndex = seatIndex[i];
            int nextSeatIndex = seatIndex[i+1];
            int numberOfWaysToPartition = nextSeatIndex-currentSeatIndex;
            count = ((count%mod)*(numberOfWaysToPartition%mod))%mod;
        }

        return count;
    }

}
