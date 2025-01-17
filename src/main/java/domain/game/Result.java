package domain.game;

public record Result(Player winner) {
    public static final Result TIE = new Result(null);

    public static Result win(Player winner) {
        return new Result(winner);
    }

    public boolean isTie() {
        return winner == null;
    }
}
