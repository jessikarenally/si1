package agenda;

import javax.faces.bean.ManagedBean;

@ManagedBean
public class Numero {
	
	private String numero;
	private String operadora;
	
	public Numero(String nmero, String op) throws IllegalArgumentException{
		if (!noPadrao(nmero)){
			throw new IllegalArgumentException("Numero fora do padrao");
		}
		this.numero = nmero;
		this.operadora = op;
	}

	public Numero(String numero) {
		this(numero, "");
	}
	
	private boolean noPadrao(String nmero) {
		if (nmero.length() != 12) {
			if( nmero.length() != 8) return false;
		}
				
		try{
			Long.parseLong(nmero);
		}catch (Exception e){
			return false;
		}
		
		return true;		
	}

	public String getNumero() {
		return numero;
	}
	
	public String getOperadora() {
		return operadora;
	}


}
