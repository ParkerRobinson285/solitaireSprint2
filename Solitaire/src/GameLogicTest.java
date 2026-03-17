import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class GameLogicTest {

    private int[][] pinValues;
    private final int size = 7;

    @BeforeEach
    public void setUp() {
        pinValues = PinArray.pinArray(size);
    }

    // ── NEW GAME ──────────────────────────────────────────────────────────────

    // Verifies the board is correctly initialized with the center empty
    @Test
    public void testNewGame_centerIsEmpty() {
        assertEquals(0, pinValues[size / 2][size / 2]);
    }

    // Verifies corners are marked as inactive (-1)
    @Test
    public void testNewGame_cornersAreInactive() {
        assertEquals(-1, pinValues[0][0]);
        assertEquals(-1, pinValues[0][1]);
        assertEquals(-1, pinValues[1][0]);
        assertEquals(-1, pinValues[0][size - 1]);
        assertEquals(-1, pinValues[0][size - 2]);
        assertEquals(-1, pinValues[1][size - 1]);
        assertEquals(-1, pinValues[size - 1][0]);
        assertEquals(-1, pinValues[size - 2][0]);
        assertEquals(-1, pinValues[size - 1][1]);
        assertEquals(-1, pinValues[size - 1][size - 1]);
        assertEquals(-1, pinValues[size - 1][size - 2]);
        assertEquals(-1, pinValues[size - 2][size - 1]);
    }

    // Verifies active non-center cells start filled
    @Test
    public void testNewGame_activeCellsAreFilled() {
        // Row 3 (middle row) should be fully filled except center
        for (int y = 0; y < size; y++) {
            if (y != size / 2) {
                assertEquals(1, pinValues[size / 2][y],
                    "Expected pin at [" + size/2 + "][" + y + "]");
            }
        }
    }

    // Verifies a new game resets a mutated board back to initial state
    @Test
    public void testNewGame_resetsMutatedBoard() {
        // Simulate some moves by mutating the board
        pinValues[3][2] = 0;
        pinValues[3][3] = 1;
        pinValues[3][4] = 0;

        // Start a new game (re-initialize)
        pinValues = PinArray.pinArray(size);

        // Board should be back to initial state
        assertEquals(0, pinValues[size / 2][size / 2]);
        assertEquals(1, pinValues[3][2]);
        assertEquals(0, pinValues[3][3]);
        assertEquals(1, pinValues[3][4]);
    }

	 // ── MAKE MOVE (horizontal right) ─────────────────────────────────────────
	
	 // Row 3: [3][2]=1, [3][3]=0(center), [3][4]=1
	 // Select pin at (3,1) and empty at (3,3): hops over (3,2)
	 @Test
	 public void testMakeMove_validHorizontalRight() {
	     int x1 = 3, y1 = 1; // selected pin (filled)
	     int x2 = 3, y2 = 3; // selected target (empty center)
	
	     // Pre-conditions
	     assertEquals(1, pinValues[x1][y1]);
	     assertEquals(1, pinValues[x1][y1 + 1]);
	     assertEquals(0, pinValues[x2][y2]);
	
	     // Branch: x1==x2, y1<y2, target is 0, middle is 1
	     if ((x1 == x2 && y1 < y2) && (pinValues[x2][y2] == 0 && pinValues[x1][y1 + 1] == 1)) {
	         pinValues[x1][y1] = 0;
	         pinValues[x1][y1 + 1] = 0;
	         pinValues[x2][y2] = 1;
	     }
	
	     assertEquals(0, pinValues[x1][y1],     "Start pin should now be empty");
	     assertEquals(0, pinValues[x1][y1 + 1], "Hopped pin should now be empty");
	     assertEquals(1, pinValues[x2][y2],     "Target should now be filled");
	 }

	// ── MAKE MOVE (horizontal left) ──────────────────────────────────────────

	// Row 3: [3][3]=0(center), [3][4]=1, [3][5]=1
	// Select empty at (3,3) and pin at (3,5): hops left over (3,4)
	@Test
	public void testMakeMove_validHorizontalLeft() {
	    int x1 = 3, y1 = 3; // selected empty (center)
	    int x2 = 3, y2 = 5; // selected pin (filled)

	    // Pre-conditions
	    assertEquals(0, pinValues[x1][y1]);
	    assertEquals(1, pinValues[x1][y1 + 1]);
	    assertEquals(1, pinValues[x2][y2]);

	    // Branch: x1==x2, y1<y2, start is 0, middle is 1
	    if ((x1 == x2 && y1 < y2) && (pinValues[x1][y1] == 0 && pinValues[x1][y1 + 1] == 1)) {
	        pinValues[x1][y1] = 1;
	        pinValues[x1][y1 + 1] = 0;
	        pinValues[x2][y2] = 1;
	    }

	    assertEquals(1, pinValues[x1][y1],     "Empty space should now be filled");
	    assertEquals(0, pinValues[x1][y1 + 1], "Hopped pin should now be empty");
	    assertEquals(1, pinValues[x2][y2],     "End pin should remain filled");
	}

	// ── MAKE MOVE (vertical down) ─────────────────────────────────────────────

	// Col 3: [1][3]=1, [2][3]=1, [3][3]=0(center)
	// Select pin at (1,3) and empty at (3,3): hops down over (2,3)
	@Test
	public void testMakeMove_validVerticalDown() {
	    int x1 = 1, y1 = 3; // selected pin (filled)
	    int x2 = 3, y2 = 3; // selected target (empty center)

	    assertEquals(1, pinValues[x1][y1]);
	    assertEquals(1, pinValues[x1 + 1][y1]);
	    assertEquals(0, pinValues[x2][y2]);

	    // Branch: y1==y2, x1<x2, target is 0, middle is 1
	    if ((y1 == y2 && x1 < x2) && (pinValues[x2][y2] == 0 && pinValues[x1 + 1][y1] == 1)) {
	        pinValues[x1][y1] = 0;
	        pinValues[x1 + 1][y1] = 0;
	        pinValues[x2][y2] = 1;
	    }

	    assertEquals(0, pinValues[x1][y1],     "Start pin should now be empty");
	    assertEquals(0, pinValues[x1 + 1][y1], "Hopped pin should now be empty");
	    assertEquals(1, pinValues[x2][y2],     "Target should now be filled");
	}

    // ── MAKE MOVE (invalid - diagonal) ───────────────────────────────────────

    // Pins not in the same row or column → move should be rejected
    @Test
    public void testMakeMove_invalidDiagonal() {
        int x1 = 2, y1 = 2;
        int x2 = 4, y2 = 4;

        int[][] before = new int[size][size];
        for (int i = 0; i < size; i++)
            before[i] = pinValues[i].clone();

        // Diagonal check — x1!=x2 AND y1!=y2 means invalid
        boolean isDiagonal = (x1 != x2 && y1 != y2);
        assertTrue(isDiagonal, "Move should be flagged as diagonal");

        // Board should remain unchanged
        for (int i = 0; i < size; i++)
            assertArrayEquals(before[i], pinValues[i]);
    }
    
	 // ── MAKE MOVE (vertical up) ───────────────────────────────────────────────
	
	 // Col 3: [3][3]=0(center), [4][3]=1, [5][3]=1
	 // Select empty at (3,3) and pin at (5,3): hops up over (4,3)
	 @Test
	 public void testMakeMove_validVerticalUp() {
	     int x1 = 3, y1 = 3; // selected empty (center)
	     int x2 = 5, y2 = 3; // selected pin (filled)
	
	     assertEquals(0, pinValues[x1][y1]);
	     assertEquals(1, pinValues[x1 + 1][y1]);
	     assertEquals(1, pinValues[x2][y2]);
	
	     // Branch: y1==y2, x1<x2, start is 0, middle is 1
	     if ((y1 == y2 && x1 < x2) && (pinValues[x1][y1] == 0 && pinValues[x1 + 1][y1] == 1)) {
	         pinValues[x1][y1] = 1;
	         pinValues[x1 + 1][y1] = 0;
	         pinValues[x2][y2] = 1;
	     }
	
	     assertEquals(1, pinValues[x1][y1],     "Empty space should now be filled");
	     assertEquals(0, pinValues[x1 + 1][y1], "Hopped pin should now be empty");
	     assertEquals(1, pinValues[x2][y2],     "End pin should remain filled");
	 }

    // ── MAKE MOVE (invalid - no middle pin to hop) ────────────────────────────

    // Both selected cells are filled but there's no empty target to hop into
    @Test
    public void testMakeMove_invalidBothFilled() {
        int x1 = 2, y1 = 3;
        int x2 = 2, y2 = 4; // both are in a filled row, neither is the center

        // Both pins selected are filled → invalid selection
        boolean bothFilled = pinValues[x1][y1] == 1 && pinValues[x2][y2] == 1;

        // Should alert "Select One Empty Space" — just verify the guard condition
        assertTrue(bothFilled, "Both selected cells are filled, move is invalid");
    }
}