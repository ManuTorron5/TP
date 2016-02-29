package es.ucm.fdi.tp.practica4.ataxx;

import java.util.ArrayList;
import java.util.List;

import es.ucm.fdi.tp.basecode.bgame.model.Board;
import es.ucm.fdi.tp.basecode.bgame.model.Game.State;
import es.ucm.fdi.tp.basecode.bgame.model.FiniteRectBoard;
import es.ucm.fdi.tp.basecode.bgame.model.GameError;
import es.ucm.fdi.tp.basecode.bgame.model.GameMove;
import es.ucm.fdi.tp.basecode.bgame.model.GameRules;
import es.ucm.fdi.tp.basecode.bgame.model.Pair;
import es.ucm.fdi.tp.basecode.bgame.model.Piece;

public class AtaxxRules implements GameRules {
	
	protected final Pair<State, Piece> gameInPlayResult = new Pair<State, Piece>(State.InPlay, null);
	
	private int dim;
	
	public AtaxxRules(int dim){
		
		if(dim<5 || dim % 2 == 0){
			throw new GameError("Dimension must be an odd number greater than 5: " + dim);
		}
		else{
			this.dim = dim;
		}
	}

	@Override
	public String gameDesc() {
		return "Ataxx " + dim + "x" + dim;
	}

	@Override
	public Board createBoard(List<Piece> pieces) {
		return new FiniteRectBoard(dim, dim);
	}

	@Override
	public Piece initialPlayer(Board board, List<Piece> playersPieces) {
		return playersPieces.get(0);
	}


	@Override
	public int minPlayers() {
		return 2;
	}

	@Override
	public int maxPlayers() {
		return 4;
	}

	@Override
	public Pair<State, Piece> updateState(Board board, List<Piece> pieces,
			Piece turn) {
		boolean acabar = true;
		for(Piece i : pieces){
			if(board.getPieceCount(i) != 0)
			for(Piece j : pieces){
				if(j != i && board.getPieceCount(j) != 0)
					acabar = false;
			}
			if(acabar)
				return new Pair<State, Piece>(State.Won, i);
		}
		
		acabar = true; Piece max = pieces.get(0);
		
		for(Piece i : pieces){
			if(!validMoves(board, pieces, i).isEmpty()){
				acabar = false;
			}
			if(board.getPieceCount(max) < board.getPieceCount(i))
				max = i;
		}
		if (acabar)
			return new Pair<State, Piece>(State.Won, max);
		
		return gameInPlayResult;
	}

	@Override
	public Piece nextPlayer(Board board, List<Piece> pieces, Piece turn) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public double evaluate(Board board, List<Piece> pieces, Piece turn) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<GameMove> validMoves(Board board, List<Piece> playersPieces, Piece turn) {
		List<GameMove> moves = new ArrayList<GameMove>();

		for (int i = 0; i < board.getRows(); i++) {
			for (int j = 0; j < board.getCols(); j++) {
				if (board.getPosition(i, j) == null) {
					moves.add(new AtaxxMove(i, j, turn));
				}
			}
		}
		return moves;
	}

}
