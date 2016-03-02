package es.ucm.fdi.tp.practica4.ataxx;

import java.util.List;

import es.ucm.fdi.tp.basecode.bgame.model.Board;
import es.ucm.fdi.tp.basecode.bgame.model.GameError;
import es.ucm.fdi.tp.basecode.bgame.model.GameMove;
import es.ucm.fdi.tp.basecode.bgame.model.Piece;
import es.ucm.fdi.tp.basecode.connectN.ConnectNMove;

public class AtaxxMove extends ConnectNMove {

	private static final long serialVersionUID = 1L;

	public AtaxxMove() {
	}

	public AtaxxMove(int row, int col, Piece p) {
		super(row, col, p);
	}

	@Override
	public void execute(Board board, List<Piece> pieces) {
		Piece p = getPiece();
		

		if (board.getPieceCount(p) <= 0) {
			throw new GameError("There are no pieces of type " + p
					+ " available");
		} else if (board.getPosition(row, col) != null) {
			throw new GameError("Position (" + row + "," + col
					+ ") is already occupied");
		} else {
			board.setPosition(row, col, p);
			// Check other pieces in surrounding positions and convert them into
			// the p piece.
			for (int i = -1; i < 2; i++) {
				for (int j = -1; j < 2; j++) {
					if (row + i > -1 && row + i < board.getRows()
							&& col + j < board.getCols() && col + j > -1) {
						for (Piece q : pieces) {
							if (board.getPosition(row + i, col + j) != null && 
									board.getPosition(row + i, col + j).equals(q)) {
								board.setPosition(row + i, col + j, p);
								board.setPieceCount(q,
										board.getPieceCount(q) - 1);
								board.setPieceCount(p,
										board.getPieceCount(p) + 1);
							}
						}
					}
				}
			}
		}
	}

	protected GameMove createMove(int row, int col, Piece p) {
		return new AtaxxMove(row, col, p);
	}

}
