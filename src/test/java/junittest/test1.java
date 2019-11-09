package junittest;

import static org.junit.Assert.*;

import org.junit.Test;
import sample.Arena;
public class test1 {


	@Test
	public void testGetName() {
		Arena l = new Arena("arena");
		assertEquals(l.getName(), "arena");
		
		
	}

}
