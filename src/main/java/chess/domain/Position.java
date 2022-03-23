package chess.domain;

import java.util.Objects;

public class Position {

    private static final int MIN_RANK_RANGE = 1;
    private static final int MAX_RANK_RANGE = 8;
    private static final char MIN_FILE_RANGE = 'a';
    private static final char MAX_FILE_RANGE = 'h';
    private static final int WHITE_PAWN_FIRST_RANK = 2;
    private static final int BLACK_PAWN_FIRST_RANK = 7;
    private static final int KNIGHT_MOVE_DISTANCE = 3;

    private final int rank;
    private final char file;

    public Position(final int rank, final char file) {
        char lowerCaseFile = Character.toLowerCase(file);
        validatePositionRange(rank, lowerCaseFile);
        this.rank = rank;
        this.file = lowerCaseFile;
    }

    private void validatePositionRange(final int rank, final char file) {
        if (rank < MIN_RANK_RANGE || rank > MAX_RANK_RANGE) {
            throw new IllegalArgumentException("잘못된 범위입니다.");
        }
        if (file < MIN_FILE_RANGE || file > MAX_FILE_RANGE) {
            throw new IllegalArgumentException("잘못된 범위입니다.");
        }
    }

    public boolean isFirstTurnOfPawn() {
        return rank == WHITE_PAWN_FIRST_RANK || rank == BLACK_PAWN_FIRST_RANK;
    }

    public boolean isMoveForward(final Position destination) {
        if (file != destination.file) {
            return false;
        }
        return destination.rank - rank > 0;
    }

    public boolean isMoveLinear(final Position destination) {
        final int fileDistance = Math.abs(file - destination.file);
        final int rankDistance = Math.abs(rank - destination.rank);
        return fileDistance == 0 && rankDistance > 0 || fileDistance > 0 && rankDistance == 0;
    }

    public boolean isMoveDiagonal(final Position destination) {
        final int fileDistance = Math.abs(file - destination.file);
        final int rankDistance = Math.abs(rank - destination.rank);
        return fileDistance + rankDistance != 0 && fileDistance == rankDistance;
    }

    public boolean isMoveOfKnight(final Position destination) {
        final int fileDistance = Math.abs(file - destination.file);
        final int rankDistance = Math.abs(rank - destination.rank);
        if (fileDistance + rankDistance != KNIGHT_MOVE_DISTANCE) {
            return false;
        }
        return fileDistance != 0 && rankDistance != 0;
    }

    public int calculateDistance(Position destination) {
        final int fileDistance = Math.abs(file - destination.file);
        final int rankDistance = Math.abs(rank - destination.rank);
        return fileDistance + rankDistance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Position position = (Position) o;
        return rank == position.rank && file == position.file;
    }

    @Override
    public int hashCode() {
        return Objects.hash(rank, file);
    }
}
