package domain.game;

import domain.Result;

public record Game(Player player1, Player player2) {
    public Result<Player> play() {
        int comparaison = player1.symbol().compare(player2.symbol());
        return comparaison == 1 ? Result.win(player1) : comparaison == -1 ? Result.win(player2) : Result.tie();
    }
}

