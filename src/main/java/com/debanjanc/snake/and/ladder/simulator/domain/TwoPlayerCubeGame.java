package com.debanjanc.snake.and.ladder.simulator.domain;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import com.debanjanc.snake.and.ladder.simulator.service.OneDiceOneMove;
import com.google.common.collect.ImmutableList;

public class TwoPlayerCubeGame extends Game {

	private TwoPlayerCubeGame(Builder builder) {
		super(builder.board, ImmutableList.of(builder.firstPlayer, builder.secondPlayer), new CubeDice(), 1);

	}

	public static class Builder {

		private Board board;
		private Player firstPlayer;
		private Player secondPlayer;

		public static Builder getInstance() {
			return new Builder();
		}

		public Builder board(Board board) {
			this.board = board;
			return this;
		}

		public Builder firstPlayer(Player firstPlayer) {
			this.firstPlayer = firstPlayer;
			return this;
		}

		public Builder secondPlayer(Player secondPlayer) {
			this.secondPlayer = secondPlayer;
			return this;
		}

		public TwoPlayerCubeGame build() {
			return new TwoPlayerCubeGame(this);
		}
	}
	
	//TODO Breaks SRP should be Refactored out.
	public void play() {
		Collection<Player> players = this.getPlayers();
		Board board = this.getBoard();

		boolean isWinnerDetermined = false;
		do {
			for (Player player : players) {
				OneDiceOneMove newMove = new OneDiceOneMove(this, player);
				newMove.call();
				if (newMove.getAfterPostion().getNumber() == board.size()) {
					isWinnerDetermined = true;
					break;
				}

			}

		} while (!isWinnerDetermined);
		
		/*
		 * at this point allMoves can be used to undo operation
		 */
	}

}
