package domain.loop;

import domain.Result;
import domain.game.Game;
import domain.game.Player;

public record GameLoop(Strategy strat1, Strategy strat2) {

    private Result<Strategy> run(Player player1, Player player2) {

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

    public EndedGameLoop run() {
        return run(1);
    }

    public EndedGameLoop run(int nbOfGames) {

        int nbOfWins1 = 0, nbOfWins2 = 0, nbOfTies = 0;

        for (int i = 0; i < nbOfGames; i++) {
            Player player1 = new Player(strat1.selectSymbol());
            Player player2 = new Player(strat2.selectSymbol());
            Result<Strategy> result = this.run(player1, player2);
            if (result.isTie()) {
                nbOfTies++;
            } else if (result.winner().equals(strat1)) {
                nbOfWins1++;
            } else {
                nbOfWins2++;
            }
        }

        return new EndedGameLoop(nbOfWins1, nbOfWins2, nbOfTies, strat1, strat2);
    }
}
