package leetcode;

public class ReverseString {

    public static void main(String[] args) {


    }

    public String reverse(String input) {
        int length = input.length();

        char[] reversedChar = new char[length];
        int reversedIndex = 0;
        for (int i = length - 1; i >= 0; i--) {
            reversedChar[reversedIndex++] = input.charAt(i);
        }

        return new String(reversedChar);
    }


    public String reverse2(String input) {
        StringBuilder sb = new StringBuilder(input);
        return sb.reverse().toString();
    }

}
