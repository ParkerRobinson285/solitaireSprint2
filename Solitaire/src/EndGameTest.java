import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class EndGameTest {

    // ── WIN ──────────────────────────────────────────────────────────────────
    // Exactly 1 pin remaining, no valid moves exist → "win"
    @Test
    public void testWinWithOnePinRemaining() {
        int[][] board = {
            {0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0},
            {0, 0, 1, 0, 0},
            {0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0}
        };
        assertEquals("win", EndGame.winOrLose(board));
    }

    // ── LOSE ─────────────────────────────────────────────────────────────────
    // Multiple pins remain but no valid moves exist → "lose"
    @Test
    public void testLoseWithMultiplePinsAndNoMoves() {
        int[][] board = {
            {1, 0, 0, 0, 0},
            {0, 0, 0, 0, 0},
            {0, 0, 0, 0, 1},
            {0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0}
        };
        assertEquals("lose", EndGame.winOrLose(board));
    }

    // ── NONE (horizontal move) ────────────────────────────────────────────────
    // Pattern [1][1][0] found in a row → valid move exists → "none"
    @Test
    public void testNoneWithHorizontalMoveAvailable_110() {
        int[][] board = {
            {0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0},
            {0, 1, 1, 0, 0},   // cols 1,2,3 → [1][1][0]
            {0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0}
        };
        assertEquals("none", EndGame.winOrLose(board));
    }

    // ── NONE (horizontal move) ────────────────────────────────────────────────
    // Pattern [0][1][1] found in a row → valid move exists → "none"
    @Test
    public void testNoneWithHorizontalMoveAvailable_011() {
        int[][] board = {
            {0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0},
            {0, 0, 1, 1, 0},   // cols 1,2,3 → [0][1][1]
            {0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0}
        };
        assertEquals("none", EndGame.winOrLose(board));
    }

    // ── NONE (vertical move) ──────────────────────────────────────────────────
    // Pattern [1][1][0] found in a column → valid move exists → "none"
    @Test
    public void testNoneWithVerticalMoveAvailable_110() {
        int[][] board = {
            {0, 0, 1, 0, 0},   // row 0, col 2
            {0, 0, 1, 0, 0},   // row 1, col 2  → [1][1][0] vertically
            {0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0}
        };
        assertEquals("none", EndGame.winOrLose(board));
    }

    // ── NONE (vertical move) ──────────────────────────────────────────────────
    // Pattern [0][1][1] found in a column → valid move exists → "none"
    @Test
    public void testNoneWithVerticalMoveAvailable_011() {
        int[][] board = {
            {0, 0, 0, 0, 0},
            {0, 0, 1, 0, 0},   // row 1, col 2  → [0][1][1] vertically
            {0, 0, 1, 0, 0},   // row 2, col 2
            {0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0}
        };
        assertEquals("none", EndGame.winOrLose(board));
    }
}