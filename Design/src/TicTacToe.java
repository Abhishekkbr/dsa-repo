import java.util.Arrays;
import java.util.HashMap;

public class TicTacToe {
    public enum GameStatus {
        WAITING,
        IN_PROGRESS,
        FININSHED;
    }

    static class GameState {
        final String playerX;
        String playerO;
        private String[] board;
        String currentTurn;
        int moveCount;
        GameStatus status;
        String winner;

        public GameState(String playerX) {
            this.playerX = playerX;
            this.playerO = null;
            this.board = new String[9];
            this.currentTurn = "X";
            this.moveCount = 0;
            this.status = GameStatus.WAITING;
            this.winner = null;

            Arrays.fill(this.board, " ");
        }

        String symbolFor(String sessionId) {
            if (sessionId.equals(playerX)) return "X";
            if(sessionId.equals(playerO)) return "O";
            return null;
        }

        void applyMove(int position, String symbol) {
            board[position] = symbol;
            moveCount++;
            currentTurn = symbol.equals("X") ? "O" : "X";
        }

        boolean checkWin(String symbol) {
            int[][] lines = {{0,1,2}, {3,4,5}, {6,7,8},
                    {0,3,6}, {1,4,7}, {2,5,8},
                    {0,4,8}, {2,4,6}
            };

            for (int[] line : lines) {
                if (board[line[0]].equals(symbol) &&
                board[line[1]].equals(symbol) &&
                board[line[2]].equals(symbol)) {
                    return true;
                }
            }
            return false;
        }

        boolean checkDraw() {
            return moveCount == 9;
        }

        String boardToString() {
           String ans = board[0] + " " + board[1] + " " + board[2] + "%n" +
                    board[3] + " " + board[4] + " " + board[5] + "%n" +
                    board[6] + " " + board[7] + " " + board[8] + "%n";
           return ans;
        }
    }

    private final HashMap<String, GameState> games = new HashMap<>();
    private int gameCounter = 0;

    public String createGame(String playerXSession) throws IllegalAccessException {
        if (playerXSession == null || playerXSession.isBlank()) {
            throw new IllegalAccessException("Player session Id must not be empty.");
        }

        String gameId = "game-" + (++gameCounter);
        games.put(gameId, new GameState(playerXSession));
        System.out.println("Game created: " + gameId + " | Player X: " + playerXSession);
        return gameId;
    }

    public void joinGame(String gameId, String playerOSession) {
        // CHECK 1 — game must exist
        GameState game = findGameOrThrow(gameId);

        // CHECK 2 — must be waiting for a second player
        if (game.status != GameStatus.WAITING) {
            throw new IllegalStateException(
                    "Game " + gameId + " is not available. Status: " + game.status);
        }

        // CHECK 3 — self-join prevention (bug found in code review)
        if (playerOSession.equals(game.playerX)) {
            throw new IllegalArgumentException(
                    "You cannot join your own game as the opposing player.");
        }

        // All checks passed — assign Player O and start
        game.playerO = playerOSession;
        game.status  = GameStatus.IN_PROGRESS;

        System.out.println("[TicTacToe] Player O joined: " + gameId +
                " | Player O: " + playerOSession);
    }

    public String makeMove(String gameId, String sessionId, int position) {
        // CHECK 1 — game must exist
        GameState game = findGameOrThrow(gameId);

        // CHECK 2 — game must be running
        if (game.status != GameStatus.IN_PROGRESS) {
            throw new IllegalStateException(
                    "Game is not in progress. Status: " + game.status);
        }

        // CHECK 3 — session must be a player in this game
        String symbol = game.symbolFor(sessionId);
        if (symbol == null) {
            throw new IllegalArgumentException(
                    "Session '" + sessionId + "' is not a player in game " + gameId);
        }

        // CHECK 4 — must be your turn
        if (!game.currentTurn.equals(symbol)) {
            throw new IllegalStateException(
                    "Not your turn. Current turn: " + game.currentTurn);
        }

        // CHECK 5 — position must be 0-8
        if (position < 0 || position > 8) {
            throw new IllegalArgumentException(
                    "Invalid position: " + position + ". Must be between 0 and 8.");
        }

        // CHECK 6 — cell must be empty
        if (!game.board[position].equals(" ")) {
            throw new IllegalStateException(
                    "Cell " + position + " is already taken by " + game.board[position]);
        }

        // All checks passed — apply the move
        game.applyMove(position, symbol);

        System.out.println("[TicTacToe] " + symbol + " played position " +
                position + " in game " + gameId);
        System.out.println(game.boardToString());

        // Check win BEFORE draw — win on 9th move is a WIN not a DRAW
        if (game.checkWin(symbol)) {
            game.status = GameStatus.FININSHED;
            game.winner = symbol;
            System.out.println("[TicTacToe] " + symbol + " WINS game " + gameId);
            return symbol + " wins";
        }

        // Check draw only if nobody won
        if (game.checkDraw()) {
            game.status = GameStatus.FININSHED;
            game.winner = "DRAW";
            System.out.println("[TicTacToe] DRAW in game " + gameId);
            return "Draw";
        }

        // Game continues — tell caller whose turn is next
        return "Move accepted. " + game.currentTurn + "'s turn next.";
    }

    // =========================================================
    // GET BOARD — read the current board state
    // =========================================================
    // Returns a copy of the board array — not the original.
    //
    // WHY A COPY?
    //   If we return the actual array, the caller could
    //   mutate it directly without going through makeMove().
    //   That bypasses all our validation — a security hole.
    //   Returning a copy means the internal state is safe.
    //   This is called "defensive copying."
    // =========================================================
    public String[] getBoard(String gameId) {
        GameState game = findGameOrThrow(gameId);
        return Arrays.copyOf(game.board, game.board.length);
    }

    // =========================================================
    // GET STATUS — what state is the game in?
    // =========================================================
    public GameStatus getStatus(String gameId) {
        return findGameOrThrow(gameId).status;
    }

    // =========================================================
    // GET WINNER — who won? null if game still going.
    // =========================================================
    public String getWinner(String gameId) {
        return findGameOrThrow(gameId).winner;
    }

    // =========================================================
    // PRINT BOARD — convenience method for debugging
    // =========================================================
    public void printBoard(String gameId) {
        System.out.println(findGameOrThrow(gameId).boardToString());
    }

    // =========================================================
    // PRIVATE HELPER — find game or throw clearly
    // =========================================================
    // Every public method calls this first.
    // "OrThrow" in the name signals it never returns null —
    // either it returns a valid GameState or it throws.
    //
    // WHY NOT JUST CALL games.get() EVERYWHERE?
    //   Duplicating the null-check in every method is messy
    //   and error-prone. One helper, one place to change.
    // =========================================================
    private GameState findGameOrThrow(String gameId) {
        GameState game = games.get(gameId);
        if (game == null) {
            throw new IllegalArgumentException(
                    "Game not found: '" + gameId + "'. Check the game ID.");
        }
        return game;
    }

    // =========================================================
    // MAIN — verbal test walkthrough
    // =========================================================
    // Covers all edge cases mentioned upfront.
    // What you say out loud to interviewer as you run this:
    //
    //   "I am going to walk through 7 scenarios:
    //    happy path win, draw, self-join, out of turn,
    //    cell taken, bad position, move after finish."
    // =========================================================
    public static void main(String[] args) throws IllegalAccessException {
        TicTacToe game = new TicTacToe();

        // ── SCENARIO 1: Happy path — X wins ───────────────────
        System.out.println("══ Scenario 1: X wins top row ══");
        String g1 = game.createGame("alice");
        game.joinGame(g1, "bob");
        //  X | O | .         X wins top row: 0,1,2
        //  O | . | .         O takes left col: 3,6
        //  . | . | .
        System.out.println(game.makeMove(g1, "alice", 0)); // X
        System.out.println(game.makeMove(g1, "bob",   3)); // O
        System.out.println(game.makeMove(g1, "alice", 1)); // X
        System.out.println(game.makeMove(g1, "bob",   6)); // O
        System.out.println(game.makeMove(g1, "alice", 2)); // X wins

        // ── SCENARIO 2: Draw ───────────────────────────────────
        System.out.println("\n══ Scenario 2: Draw ══");
        String g2 = game.createGame("alice");
        game.joinGame(g2, "bob");
        //  X | O | X
        //  X | O | O    No winning line for either player
        //  O | X | X
        game.makeMove(g2, "alice", 0); // X
        game.makeMove(g2, "bob",   1); // O
        game.makeMove(g2, "alice", 2); // X
        game.makeMove(g2, "bob",   4); // O
        game.makeMove(g2, "alice", 3); // X
        game.makeMove(g2, "bob",   5); // O
        game.makeMove(g2, "alice", 7); // X
        game.makeMove(g2, "bob",   6); // O
        System.out.println(game.makeMove(g2, "alice", 8)); // X → Draw

        // ── SCENARIO 3: Win on 9th move — not a draw ──────────
        System.out.println("\n══ Scenario 3: Win on 9th move ══");
        String g3 = game.createGame("alice");
        game.joinGame(g3, "bob");
        //  X | O | X
        //  O | X | O    X wins right column [2,5,8] on move 9
        //  O | X | X
        game.makeMove(g3, "alice", 0);
        game.makeMove(g3, "bob",   1);
        game.makeMove(g3, "alice", 2);
        game.makeMove(g3, "bob",   3);
        game.makeMove(g3, "alice", 4);
        game.makeMove(g3, "bob",   6);
        game.makeMove(g3, "alice", 5);
        game.makeMove(g3, "bob",   7);
        System.out.println(game.makeMove(g3, "alice", 8)); // X wins, 9th move

        // ── SCENARIO 4: Self-join rejection ───────────────────
        System.out.println("\n══ Scenario 4: Self-join rejected ══");
        String g4 = game.createGame("alice");
        try {
            game.joinGame(g4, "alice"); // alice tries to join her own game
        } catch (IllegalArgumentException e) {
            System.out.println("Correctly rejected: " + e.getMessage());
        }

        // ── SCENARIO 5: Out of turn ────────────────────────────
        System.out.println("\n══ Scenario 5: Out of turn ══");
        String g5 = game.createGame("alice");
        game.joinGame(g5, "bob");
        try {
            game.makeMove(g5, "bob", 0); // bob tries to go first (X's turn)
        } catch (IllegalStateException e) {
            System.out.println("Correctly rejected: " + e.getMessage());
        }

        // ── SCENARIO 6: Cell already taken ────────────────────
        System.out.println("\n══ Scenario 6: Cell already taken ══");
        String g6 = game.createGame("alice");
        game.joinGame(g6, "bob");
        game.makeMove(g6, "alice", 4); // alice takes center
        try {
            game.makeMove(g6, "bob", 4); // bob tries center too
        } catch (IllegalStateException e) {
            System.out.println("Correctly rejected: " + e.getMessage());
        }

        // ── SCENARIO 7: Invalid position ──────────────────────
        System.out.println("\n══ Scenario 7: Invalid position ══");
        String g7 = game.createGame("alice");
        game.joinGame(g7, "bob");
        try {
            game.makeMove(g7, "alice", -1); // negative
        } catch (IllegalArgumentException e) {
            System.out.println("Correctly rejected (-1): " + e.getMessage());
        }
        try {
            game.makeMove(g7, "alice", 99); // too large
        } catch (IllegalArgumentException e) {
            System.out.println("Correctly rejected (99): " + e.getMessage());
        }

        // ── SCENARIO 8: Move after game finished ──────────────
        System.out.println("\n══ Scenario 8: Move after game finished ══");
        try {
            game.makeMove(g1, "bob", 4); // g1 is already FINISHED
        } catch (IllegalStateException e) {
            System.out.println("Correctly rejected: " + e.getMessage());
        }

        System.out.println("\n══ All scenarios complete ══");
    }
}
