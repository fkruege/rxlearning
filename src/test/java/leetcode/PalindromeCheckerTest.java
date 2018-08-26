package leetcode;

import org.junit.Test;

import static org.junit.Assert.*;

public class PalindromeCheckerTest {

    PalindromeChecker palindromeChecker = new PalindromeChecker();

    @Test
    public void isPalindrome() {
        assertTrue(palindromeChecker.isPalindrome("lol"));
        assertTrue(palindromeChecker.isPalindrome("lool"));
        assertTrue(palindromeChecker.isPalindrome("lo1ol"));
    }
}