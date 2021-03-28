package chess.domain.gamestate;

import chess.domain.Board;
import chess.domain.Side;

public abstract class GameState implements State {
    private final Board board;
    private final Side side;

    public GameState(Board board, Side side) {
        this.board = board;
        this.side = side;
    }

    public Board board() {
        return board;
    }

    public Side side() {
        return side;
    }

    public Side changeTurn() {
        return side.changeTurn();
    }
}
