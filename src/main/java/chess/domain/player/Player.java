package chess.domain.player;

import chess.domain.Position;
import chess.domain.piece.Piece;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Player {

    private final Set<Piece> pieces;

    public Player(List<Piece> pieces) {
        this.pieces = new HashSet<>(pieces);
    }

    public boolean hasPiece(final Position position) {
        return pieces.stream()
                .anyMatch(piece -> piece.exist(position));
    }

    public List<Piece> findAll() {
        return pieces.stream()
                .collect(Collectors.toUnmodifiableList());
    }

    public Position move(final Position current, final Position destination) {
        return findPiece(current)
                .move(current, destination);
    }

    public Position capture(final Position current, final Position destination) {
        return findPiece(current)
                .capture(current, destination);
    }

    public void remove(final Position position) {
        pieces.remove(findPiece(position));
    }

    private Piece findPiece(final Position current) {
        return pieces.stream()
                .filter(piece -> piece.exist(current))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("체스말이 존재하지 않습니다."));
    }
}
