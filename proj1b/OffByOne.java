public class OffByOne implements CharacterComparator {

    /** off-by-1 palindrome */
    @Override
    public boolean equalChars(char x, char y) {
        int diff = Math.abs(x - y);
        if (diff == 1) {
            return true;
        }
        return false;
    }

}
