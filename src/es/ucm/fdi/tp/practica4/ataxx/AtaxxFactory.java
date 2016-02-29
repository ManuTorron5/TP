package es.ucm.fdi.tp.practica4.ataxx;

import es.ucm.fdi.tp.basecode.bgame.model.GameError;
import es.ucm.fdi.tp.basecode.bgame.model.GameRules;
import es.ucm.fdi.tp.basecode.connectN.ConnectNFactory;

public class AtaxxFactory extends ConnectNFactory{
	
	private int dim;
	
	public AtaxxFactory() {
		this(5);
	}

	public AtaxxFactory(int dim) {
		if (dim < 5 || dim % 2 == 0) {
			throw new GameError("Dimension must be an odd number greater than 5: " + dim);
		} else {
			this.dim = dim;
		}
	}
	
	public GameRules gameRules() {
		return new AtaxxRules();
	}
	
}
