package si1.lab3.model;

import java.io.IOException;
import java.sql.Time;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.naming.TimeLimitExceededException;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.validation.Validator;

import org.primefaces.component.calendar.Calendar;
import org.w3c.dom.ls.LSResourceResolver;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;

public class Tarefa implements Comparable<String> {
	private String nome;
	private String descricao;
	private String horaLimite;
	private Date date; 
	private String horaLimiteFormata;
	private String dataFormatada;
	private boolean tarefaCompleta;
	
	public Tarefa(String nome)throws IllegalArgumentException {
		this(nome,null, "", "");
	}

	
	public Tarefa(String nome, Date data, String descricao, String horaLimite) throws IllegalArgumentException{
		if(nome.equals("")) throw new IllegalArgumentException("O nome da tarefa nao pode ser vazio");
		if(!isRegular(horaLimite)) throw new IllegalArgumentException("A hora limite não é uma hora válida");
		if(!verificaValidadeData(data)) throw new IllegalArgumentException("Voce nao pode criar uma tarefa com data anterior a atual");
		
		this.nome = nome;
		this.descricao = descricao;
		this.horaLimite = horaLimite;
		this.date = data;
		this.tarefaCompleta = false;
		
		if(date != null && !horaLimite.equals("")){
			adicionaHoraLimite(horaLimite);
			horaLimiteFormata = horaLimite;			
		}
		
		if(data == null)	dataFormatada = "";
		else dataFormatada = String.valueOf(date.getDate()) +  String.valueOf(date.getMonth()) + String.valueOf(date.getYear());
		
	}

	private boolean verificaValidadeData(Date data) {
		Date dataAtual = new Date();
		if(data == null) return true;
		
		if(!diaValido(data)) return false;
		
		if((dataAtual.getYear()) > data.getYear()){
			return false;
		}else if ((dataAtual.getYear() ) == data.getYear()){
			if(dataAtual.getMonth() > data.getMonth()){
				return false;
			}else if(dataAtual.getMonth() == data.getMonth()){
				if(dataAtual.getDate() > data.getDate()) return false;
			}			
		}
		return true;		
	}


	private boolean diaValido(Date data) {
	
		if(data.getMonth() > 11 || data.getMonth() <0 ){ return false;
		}else if(data.getMonth() == 1){
			if((data.getYear()) % 400  == 0 || ((data.getYear()) % 4 == 0 && (data.getYear()) % 100  != 0 ))
				if(data.getDate() >29 || data.getDate() <= 0) return false;			
		}else if(mes31dias(data)){
			if(data.getDate() >31 || data.getDate() <= 0) return false;
		}else{
			if(data.getDate() >30 || data.getDate() <= 0) return false;
		}
		
		if(data.getYear() < 0) return false;
		
		return true;
	}


	private boolean mes31dias(Date data) {
		int[] meses31 = {0,2,4,6,7,9,11};
		for(int i: meses31){
			if(data.getMonth() == i )
				return true;
		}
		return false;
	}


	private boolean isRegular(String horaLimite) {
		if(horaLimite.equals("")) return true;
		String[] hora = horaLimite.split(":");
		
		return((hora.length == 3) && verificaFormatNumero(hora));
	}


	private boolean verificaFormatNumero(String[] hora) {
		int[] padrao = {23,59,59};
		int numero;
		for(int i = 0; i < hora.length; i++){
			try{
				numero = Integer.parseInt(hora[i]);
			}catch(NumberFormatException e){
				return false;
			}

			if(!(numero >= 0 && numero <= padrao[i])) return false;
		}
		return true;
	}

	private void adicionaHoraLimite(String horaLimite) {		
		date.setHours(Integer.parseInt(horaLimite.split(":")[0]));
		date.setMinutes(Integer.parseInt(horaLimite.split(":")[1]));
		date.setSeconds(Integer.parseInt(horaLimite.split(":")[2]));
	}

	private Date criaData(String data) {
		if(data.equals("")) return null;
		return new Date();
	}

	public String getNome() {
		return nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
		
	}

	public Date getData() {
		return date;
	}

	public void setData(Date date) throws TimeLimitExceededException{
		if(date.compareTo(new Date()) > 0){
			throw new TimeLimitExceededException();
		}
		this.date = date;
		if(date == null)	dataFormatada = "";
		else dataFormatada = String.valueOf(date.getDate()) +  String.valueOf(date.getMonth()) + String.valueOf(date.getYear());
	}

	public String getHoraLimite() {
			return horaLimite;
	}


	public void setHoraLimite(String horaLimite) {
		this.horaLimite = horaLimite;
		this.horaLimiteFormata = horaLimiteFormata;
	}


	public String getHoraLimiteFormata() {
		horaLimiteFormata = horaLimite;
		return horaLimiteFormata;
	}


	public void setHoraLimiteFormata(String horaLimiteFormata) {
		this.horaLimiteFormata = horaLimiteFormata;
	}


	public String getDataFormatada() {
		if(date == null) return "";
		dataFormatada = String.format("%d/%02d/%d", date.getDate(), date.getMonth()+1, date.getYear()+1900);
		return dataFormatada;
	}


	public void setDataFormatada(String dataFormatada) {
		this.dataFormatada = dataFormatada;
	}


	@Override
	public int compareTo(String data) {
		return dataFormatada.compareTo(data);
	}


	public boolean getTarefaCompleta() {
		return tarefaCompleta;
	}


	public void setTarefaCompleta(boolean tarefaCompleta) {
		this.tarefaCompleta = tarefaCompleta;
	}


	public void tarefaCompleta() {
		this.tarefaCompleta = true;
	}
	
	
	

}
