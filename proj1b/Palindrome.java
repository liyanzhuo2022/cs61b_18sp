public class Palindrome {

    /**Given a String, wordToDeque should return a Deque where
     * the characters appear in the same order as in the String. */
    public Deque<Character> wordToDeque(String word) {
        Deque<Character> deque = new LinkedListDeque<>();
        for (int i = 0; i < word.length(); i++) {
            deque.addLast(word.charAt(i));
        }
        return deque;
    }

    /** The isPalindrome method should return true if the
     * given word is a palindrome, and false otherwise.*/
    public boolean isPalindrome(String word) {
        Deque<Character> deque = wordToDeque(word);
        String reversed = "";
        for (int i = 0; i < word.length(); i++) {
            reversed += deque.removeLast();
        }

        return word.equals(reversed);
    }

    /** off-by-1 palindrome */
    public boolean isPalindrome(String word, CharacterComparator cc) {
        Deque<Character> deque = wordToDeque(word);
        return isPalindrome(deque, cc);
    }

    // helper method for recursion
    private boolean isPalindrome(Deque<Character> deque, CharacterComparator cc) {
        if (deque.size() == 0 || deque.size() == 1) {
            return true;
        } else {
            char front = deque.removeFirst();
            char behind = deque.removeLast();
            if (!cc.equalChars(front, behind)) {
                return false;
            }
            return isPalindrome(deque, cc);
        }
    }

}
