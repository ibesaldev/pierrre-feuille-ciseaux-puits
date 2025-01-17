package domain;

import domain.game.Game;
import domain.game.Player;

public record GameLoop(Strategy strat1, Strategy strat2) {

    public Result<Strategy> run() {
        Player player1 = new Player(strat1.symbols().getFirst());
        Player player2 = new Player(strat2.symbols().getFirst());

        Result<Player> result = new Game(player1, player2).play();
        Player winner = result.winner();

        if (result.isTie()) {
            return Result.tie();
        }
        if (winner.equals(player1)) {
            return Result.win(strat1);
        }
        return Result.win(strat2);
    }
}
