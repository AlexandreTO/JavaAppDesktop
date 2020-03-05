package Swing;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest {
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue() {

        // assertEquals(2, Class.calculer(1, 1));
        assertEquals(1, Class.testCnx());
        assertNotNull(Class.testCnx());
    }
}
