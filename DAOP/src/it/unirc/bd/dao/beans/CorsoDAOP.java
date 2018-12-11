package it.unirc.bd.dao.beans;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import it.unirc.bd.dao.utils.DBManager;


public class CorsoDAOP {
	private static Connection conn = null;





	//----------------INSERISCI CORSO----------------------
	public boolean salvaCorso(Corso c){
		String query = "INSERT INTO dipendente VALUES (?, ?,?,?,?,?)";
		boolean esito=false;
		conn=DBManager.startConnection();
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1, c.getIdCorso().intValue());
			ps.setInt(2, c.getGiorni().intValue() );
			ps.setInt(3, c.getOra().intValue());
			ps.setString(4, c.getTipo());
			ps.setInt(5, c.getAllenatore1().intValue());
			ps.setInt(6, c.getAllenatore2().intValue());
			int tmp=ps.executeUpdate();
			if (tmp==1)
				esito=true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DBManager.closeConnection();
		return esito;
	}



	//-----------------CONTROLLO DINAMICO ID CORSO -------------------RITORNA VERO SE ESISTE O NON � UN VALORE ACCETTABILE FALSO ALTRIMENTI
	public boolean ControlloDinamicoIdCorso(Integer ID) {
		boolean risultato =false;
		//----INSERIRE UN CONTROLLO CHE MI PERMETTA DI VEDERE SE L'OGETTO PASSATO � VUOTO E RESTITUISCA SUBITO UN VALORE
		//INSERISCO QUESTO CONTROLLO PRIMA DEL RESTO DEL CONDICE PERCH� SENNO PARTIREBBE UNA QUERY CON DEI VALORI INCOMPATIBILI
		//ANZICHE PASSARE AL METODO UN TIPO INT PASSARE AL METODO UN TIPO INTEGER (OGETTO) CHE PU� ESSERE ANCHE NULL
		if (ID==null || Integer.toString(ID.intValue()).equals("")) {
			risultato=true;
			return risultato;
		}						
		String query = "SELECT * FROM corso WHERE idCorso = ?";
		PreparedStatement ps;
		conn=DBManager.startConnection();
		try {
			ps = conn.prepareStatement(query);
			ps.setInt(1, ID.intValue());
			ResultSet rs = ps.executeQuery();
			if(rs.next())
				risultato=true;	//ESISTE UNA TUPLA CON QUELL'ID
			else
				risultato=false;//NON ESISTE UNA TUPLA CON QUELL'ID
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DBManager.closeConnection();
		return risultato;
	}

	
	
	
	//-----------------CONTROLLO PRESENZA ALLENATORE -------------------ritorna true se non sono validi gli allenatori immessi
		/*public boolean ControlloPresenzaAllenatore(Integer ID1, Integer ID2) {
			boolean risultato =false;
			//----INSERIRE UN CONTROLLO CHE MI PERMETTA DI VEDERE SE L'OGETTO PASSATO � VUOTO E RESTITUISCA SUBITO UN VALORE
			//INSERISCO QUESTO CONTROLLO PRIMA DEL RESTO DEL CONDICE PERCH� SENNO PARTIREBBE UNA QUERY CON DEI VALORI INCOMPATIBILI
			//ANZICHE PASSARE AL METODO UN TIPO INT PASSARE AL METODO UN TIPO INTEGER (OGETTO) CHE PU� ESSERE ANCHE NULL
			if (ID==null || Integer.toString(ID.intValue()).equals("")) {
				risultato=true;
				return risultato;
			}						
			String query = "SELECT * FROM corso WHERE idCorso = ?";
			PreparedStatement ps;
			conn=DBManager.startConnection();
			try {
				ps = conn.prepareStatement(query);
				ps.setInt(1, ID.intValue());
				ResultSet rs = ps.executeQuery();
				if(rs.next())
					risultato=true;	//ESISTE UNA TUPLA CON QUELL'ID
				else
					risultato=false;//NON ESISTE UNA TUPLA CON QUELL'ID
			} catch (SQLException e) {
				e.printStackTrace();
			}
			DBManager.closeConnection();
			return risultato;
		}*/
	

}