package it.unirc.bd.dao.beans;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import it.unirc.bd.dao.utils.DBManager;

public class PrenotazioneDAOP {
	private Connection conn = null;

	public boolean salva(Prenotazione p) {
		String query = "INSERT INTO prenotazione VALUES (?,?,?,?,?,?,?)";
		boolean esito = false;
		conn=DBManager.startConnection();
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1, p.getIdPrenotazione());
			ps.setInt(2, p.getCorsia());
			ps.setDate(3, p.getData());
			ps.setInt(4, p.getIdIscritto());
			ps.setString(5, p.getTipoPiscina());
			ps.setInt(6, p.getOra());
			ps.setInt(7, p.getIdDipendente());
			int tmp=ps.executeUpdate();
			if(tmp==1)
				esito=true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DBManager.closeConnection();
		return esito;
	}
	public boolean ControlloDisponibilit�Piscina(Integer p){
		  boolean risultato = false;
		  String query = "SELECT * FROM corso where Ora=?, Data=?";
		  PreparedStatement ps;
		  conn=DBManager.startConnection();
		  try{
		    ps = conn.prepareStatement(query);
		    ps.setInt(1, p.intValue());
		    ResultSet rs = ps.executeQuery();
		    if(rs.next())
		      risultato = true; //Esiste una tupla con quell'ID di conseguenza
		            //Non si pu� prenotare
		      else
		      risultato = false; //Si pu� prenotare
		    }
		      catch(SQLException e) {
		        e.printStackTrace();
		    }
		      DBManager.closeConnection();
		      return risultato;
		    }

}
