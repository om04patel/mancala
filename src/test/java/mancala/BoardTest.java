package mancala;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BoardTest {

    private Board board;
    private Player playerOne;
    private Player playerTwo;

    @BeforeEach
    void setUp() {
        board = new Board();
        playerOne = new Player("Player One");
        playerTwo = new Player("Player Two");
    }

    @Test
    void testCaptureStones() throws PitNotFoundException {
        // Assuming captureStones is working correctly
        // You may need to modify this test based on your actual implementation
        int stoppingPoint = 6; // Example stopping point
        int capturedStones = board.captureStones(stoppingPoint);
        assertEquals(0, capturedStones); // Initial assumption, update based on your implementation
    }

    @Test
    void testDistributeStones() throws PitNotFoundException {
        // Assuming distributeStones is working correctly
        // Modify this test based on your actual implementation

        // Set up the board with a known state for testing
        board.resetBoard();
        board.registerPlayers(playerOne, playerTwo);
        // Add stones to a specific pit for testing
        int startingPoint = 0; // Example starting point
        board.getPits().get(startingPoint).addStone();
        // Call distributeStones and expect that stones are added to pits
        int stonesAdded = board.distributeStones(startingPoint);
        // The expected behavior depends on your specific rules
        // Adjust the expected value based on your implementation
        assertTrue(stonesAdded > 0);
    }

    @Test
    void testGetNumStones() throws PitNotFoundException {
        // Assuming getNumStones is working correctly
        // You may need to modify this test based on your actual implementation
        int pitNum = 3; // Example pit number
        int numStones = board.getNumStones(pitNum);
        assertEquals(4, numStones); // Initial assumption, update based on your implementation
    }

    @Test
    void testIsSideEmpty() throws PitNotFoundException {
        // Assuming isSideEmpty is working correctly
        // You may need to modify this test based on your actual implementation
        int pitNum = 2; // Example pit number
        assertFalse(board.isSideEmpty(pitNum)); // Initial assumption, update based on your implementation
    }

    @Test
    void testResetBoard() {
        // Assuming resetBoard is working correctly
        // You may need to modify this test based on your actual implementation
        assertDoesNotThrow(() -> board.resetBoard()); // Initial assumption, update based on your implementation
    }

    @Test
    void testRegisterPlayers() {
        // Assuming registerPlayers is working correctly
        // You may need to modify this test based on your actual implementation
        assertDoesNotThrow(() -> board.registerPlayers(playerOne, playerTwo)); // Initial assumption, update based on your implementation
    }
}
