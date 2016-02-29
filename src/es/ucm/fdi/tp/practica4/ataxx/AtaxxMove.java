package es.ucm.fdi.tp.practica4.ataxx;

import java.util.List;

import es.ucm.fdi.tp.basecode.bgame.model.Board;
import es.ucm.fdi.tp.basecode.bgame.model.GameError;
import es.ucm.fdi.tp.basecode.bgame.model.GameMove;
import es.ucm.fdi.tp.basecode.bgame.model.Piece;
import es.ucm.fdi.tp.basecode.connectN.ConnectNMove;

public class AtaxxMove extends ConnectNMove {
	
	private static final long serialVersionUID = 1L;
	
	public AtaxxMove() {}

	public AtaxxMove(int row, int col, Piece p) {
		super(row, col, p);
	}
	
	@Override
	public void execute(Board board, List<Piece> pieces) {
		Piece p = getPiece();

		if (board.getPieceCount(p) <= 0) {
			throw new GameError("There are no pieces of type " + p + " available");
		} else if (board.getPosition(row, col) != null) {
			throw new GameError("Position (" + row + "," + col + ") is already occupied");
		} else {
			board.setPosition(row, col, p);
			board.setPieceCount(p, board.getPieceCount(p) - 1);
		}
	}
	
	protected GameMove createMove(int row, int col, Piece p) {
		return new AtaxxMove(row, col, p);
	}
	
}
