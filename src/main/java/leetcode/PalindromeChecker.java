package leetcode;

public class PalindromeChecker {

    boolean isPalindrome(String s) {
        StringBuilder sb = new StringBuilder(s);
        if (s.length() % 2 == 0) {
            // even
        } else {
            int deleteIndex = s.length() / 2;
            sb.deleteCharAt(deleteIndex);
        }

        String reversed = sb.reverse().toString();
        String normal = sb.toString();

        return reversed.equals(normal);
    }
}
