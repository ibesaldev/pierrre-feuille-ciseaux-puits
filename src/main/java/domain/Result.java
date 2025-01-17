package domain;

public record Result<T>(T winner) {
    public static <R> Result<R> win(R winner) {
        return new Result<>(winner);
    }

    public static <R> Result<R> tie() {
        return new Result<>(null);
    }

    public boolean isTie() {
        return winner == null;
    }
}
