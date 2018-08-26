package leetcode;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ReverseSentenceTest {

    ReverseSentence sentenceReverser = new ReverseSentence();

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void reverse() {
        assertEquals("s'teL ekat edoCteeL tsetnoc", sentenceReverser.reverse("Let's take LeetCode contest"));
    }
}