package leetcode;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ReverseStringTest {

    ReverseString reverser;

    @Before
    public void setUp() throws Exception {
        reverser = new ReverseString();
    }

    @Test
    public void reverse() {
        assertEquals("olleh", reverser.reverse("hello"));
        assertEquals("dlrow", reverser.reverse("world"));
        assertEquals("", reverser.reverse(""));
        assertEquals("lol", reverser.reverse("lol"));
    }
}