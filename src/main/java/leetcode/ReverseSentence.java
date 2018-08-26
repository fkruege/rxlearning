package leetcode;

public class ReverseSentence {

    public String reverse(String sentence) {

        ReverseString stringReverser = new ReverseString();

        String currentWord = "";
        StringBuilder reversedSentence = new StringBuilder();
        for (int i = 0; i < sentence.length(); i++) {
            if (sentence.charAt(i) == ' ') {
                if (currentWord.length() > 0) {
                    String reversedWord = stringReverser.reverse(currentWord);
                    reversedSentence.append(reversedWord);
                    currentWord = "";
                }
                reversedSentence.append(' ');
            } else {
                currentWord = currentWord + sentence.charAt(i);
            }
        }

        if (currentWord.length() > 0) {
            String reversedWord = stringReverser.reverse(currentWord);
            reversedSentence.append(reversedWord);
        }

        return reversedSentence.toString();
    }
}
