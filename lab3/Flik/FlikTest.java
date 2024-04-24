import static org.junit.Assert.*;
import org.junit.Test;

public class FlikTest {

    @Test
    public void testIsSameNumber(){
        assertTrue(Flik.isSameNumber(127, 127));
        assertTrue(Flik.isSameNumber(128, 128));
        assertTrue(Flik.isSameNumber(500, 500));
    }

}
