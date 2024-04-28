public class OffByN implements CharacterComparator {
    private int DIFF;
    public OffByN(int N) {
        DIFF = N;
    }

    /** off-by-n palindrome */
    @Override
    public boolean equalChars(char x, char y) {
        int diff = Math.abs(x - y);
        if (diff == DIFF) {
            return true;
        }
        return false;
    }
}
