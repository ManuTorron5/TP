package es.ucm.fdi.tp.basecode.bgame.control;

import java.util.List;
import java.util.Scanner;

import es.ucm.fdi.tp.basecode.bgame.model.Board;
import es.ucm.fdi.tp.basecode.bgame.model.GameMove;
import es.ucm.fdi.tp.basecode.bgame.model.GameRules;
import es.ucm.fdi.tp.basecode.bgame.model.Piece;

/**
 * A class that implements a player that inputs its moves by console. It asks
 * the user to select one from this generated by
 * {@link GameRules#validMoves(Board, List, Piece)}.
 * 
 * <p>
 * Clase que implementa un jugador que introduce sus jugadas por consola. Pide
 * al usuario que seleccione uno de los generados por
 * {@link GameRules#validMoves(Board, List, Piece)}.
 */
public class ConsolePlayerFromListOfMoves extends Player {

	private static final long serialVersionUID = 1L;

	/**
	 * A scanner used to read the user's input.
	 * 
	 * <p>
	 * Scanner utilizado para leer los comandos del usuario.
	 */
	private Scanner in;

	/**
	 * Constructs a console player that allows the user to choose from a list of
	 * moves.
	 * 
	 * <p>
	 * Construye un jugador de modo consola que permite al usuario elegir de una
	 * lista de movimientos.
	 * 
	 * @param in
	 *            A scanner to be used to read the user's input.
	 *            <p>
	 *            Scanner que se va a utilizar para leer la entrada del usuario.
	 */
	public ConsolePlayerFromListOfMoves(Scanner in) {
		this.in = in;
	}

	@Override
	public GameMove requestMove(Piece p, Board board, List<Piece> pieces, GameRules rules) {

		// generate the valid moves:
		List<GameMove> availableMoves = rules.validMoves(board, pieces, p);

		// check if the game support generating valid moves
		if (availableMoves == null) {
			throw new UnsupportedOperationException(
					"The game '" + rules.gameDesc() + "' does not support the generation of vlaid moves.");
		}

		// print a text describing all available moves.
		//
		System.out.println();
		System.out.println("The valid moves are:");
		for (int i = 0; i < availableMoves.size(); i++) {
			System.out.println(" " + (i + 1) + "." + availableMoves.get(i));
		}

		// read the user's choice
		Integer n = null;
		do {
			System.out.print("Please type a move number:");
			try {
				n = Integer.parseInt(in.nextLine());
			} catch (NumberFormatException e) {
			}
		} while (n == null || n < 1 || n > availableMoves.size());

		// return the user's choice
		return availableMoves.get(n - 1);
	}

}
