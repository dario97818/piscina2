package it.unirc.bd.dao.beans;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;

import it.unirc.bd.dao.utils.DBManager;

public class IscrittoDAOP {
	private Connection conn=null;


	protected Iscritto recordToIscritto(ResultSet rs) throws SQLException{
		Iscritto res=new Iscritto();
		res.setIdIscritto(rs.getInt("idIscritto"));
		res.setNome(rs.getString("nome"));
		res.setCognome(rs.getString("cognome"));
		res.setSesso(rs.getString("Sesso"));
		res.setCellulare(rs.getString("Cellulare"));
		res.setDataNascita(rs.getDate("DatadiNascita"));
		res.setMatricolaFIN(rs.getInt("MatricolaFIN"));
		return res;
	}
	//----RICERCA PER MatricoolaFin----
	//-----------------RICERCA PER ID -------------------
	public Iscritto getAtleta(Iscritto i) {
		String query = "SELECT * FROM iscritto WHERE MatricolaFin = ?";
		Iscritto res = null;
		PreparedStatement ps;
		conn=DBManager.startConnection();
		try {
			ps = conn.prepareStatement(query);
			ps.setInt(1, i.getMatricolaFIN());
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				res=new Iscritto();
				res.setIdIscritto(rs.getInt("idIscritto"));
				res.setNome( rs.getString("Nome") );
				res.setCognome(rs.getString("Cognome"));
				res.setSesso( rs.getString("Sesso") );
				res.setCellulare( rs.getString("Cellulare") );
				res.setDataNascita(rs.getDate("DataDiNascita"));
				res.setMatricolaFIN( rs.getInt("MatricolaFin") );

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DBManager.closeConnection();
		return res;
	} 

	
	//-----------------RICERCA PER SESSO -------------------
		public Vector<Iscritto> RicercaPerSesso(String sesso) {
			Vector<Iscritto> risultato=new Vector<Iscritto>();
			String query = "SELECT * FROM iscritto WHERE Sesso = ?";
			Iscritto res = null;
			PreparedStatement ps;
			conn=DBManager.startConnection();
			try {
				ps = conn.prepareStatement(query);
				ps.setString(1, sesso);
				ResultSet rs = ps.executeQuery();
				while(rs.next()){
					res=new Iscritto();
					res.setIdIscritto(rs.getInt("idIscritto"));
					res.setNome( rs.getString("Nome") );
					res.setCognome(rs.getString("Cognome"));
					res.setSesso( rs.getString("Sesso") );
					res.setCellulare( rs.getString("Cellulare") );
					res.setDataNascita(rs.getDate("DataDiNascita"));
					res.setMatricolaFIN( rs.getInt("MatricolaFin") );
					risultato.add(res);

				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			DBManager.closeConnection();
			return risultato;
		} 
	
	
	//----RICERCA PER DATA----
	public Vector<Iscritto> RicercaPerData(Date data, int tipo){
	
			Vector<Iscritto> risultato=new Vector<Iscritto>();
			
			String query = "SELECT * FROM iscritto;";
			if (tipo==0)
				query="SELECT * FROM iscritto where DataDiNascita<?;";
			else if (tipo==1)
				query="SELECT * FROM iscritto where DataDiNascita=?;";
			else if (tipo==2)
				query="SELECT * FROM iscritto where DataDiNascita>?;";
			Iscritto res;
			PreparedStatement ps;
			conn=DBManager.startConnection();
			try {
				
					
				ps = conn.prepareStatement(query);
				ps.setString(1, data.toString());
				ResultSet rs = ps.executeQuery();
				
				while(rs.next()){

					res=new Iscritto();
					res.setIdIscritto(rs.getInt("idIscritto"));
					res.setNome( rs.getString("Nome") );
					res.setCognome(rs.getString("Cognome"));
					res.setSesso( rs.getString("Sesso") );
					res.setCellulare( rs.getString("Cellulare") );
					res.setDataNascita(rs.getDate("DataDiNascita"));
					res.setMatricolaFIN( rs.getInt("MatricolaFin") );
					risultato.add(res);
					
					
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			DBManager.closeConnection();
			
			
			System.out.println("RISULTATI TROVATI: "+risultato.size());
			System.out.println("_______________________________________________________________________________________________");
			return risultato;

		}
	
	
	
	
	//----RICERCA PER NOME/COGNOME----
		public Vector<Iscritto> RicercaPerNomeCognome(String nome, String cognome, boolean isNome, boolean isCognome){
				Vector<Iscritto> risultato=new Vector<Iscritto>();
				boolean completo=false;
				String query = "SELECT * FROM iscritto;";
				if (isNome && isCognome )
					query="SELECT * FROM iscritto where Nome=? and Cognome=?;";
				else if (!isNome && isCognome)
					query="SELECT * FROM iscritto where Cognome=?;";
				else if (isNome && !isCognome) {
					query="SELECT * FROM iscritto where Nome=?;";
					completo=true;
				}
				ResultSet rs=null;
				Iscritto res;
				PreparedStatement ps;
				conn=DBManager.startConnection();
				try {
					
					
					if (isNome && isCognome ) {
						ps = conn.prepareStatement(query);
						ps.setString(1, nome);
						ps.setString(2,cognome);
						rs = ps.executeQuery();
					}
					else if (!isNome && isCognome) {
						ps = conn.prepareStatement(query);
						ps.setString(1, cognome);
						rs = ps.executeQuery();
					}
					else if (isNome && !isCognome) {
						ps = conn.prepareStatement(query);
						ps.setString(1, nome);
						rs = ps.executeQuery();
					}
					
					
					while(rs.next()){

						res=new Iscritto();
						res.setIdIscritto(rs.getInt("idIscritto"));
						res.setNome( rs.getString("Nome") );
						res.setCognome(rs.getString("Cognome"));
						res.setSesso( rs.getString("Sesso") );
						res.setCellulare( rs.getString("Cellulare") );
						res.setDataNascita(rs.getDate("DataDiNascita"));
						res.setMatricolaFIN( rs.getInt("MatricolaFin") );
						risultato.add(res);
						
						
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
				DBManager.closeConnection();
				
				
				System.out.println("RISULTATI TROVATI: "+risultato.size());
				System.out.println("_______________________________________________________________________________________________");
				return risultato;

			}
	
	
	
	
	


	//---INSERIMENTO ISCRITTO----
	public boolean salvaIscritto(Iscritto i) {
		boolean isAtleta=true;
		String query;
		if (i.getMatricolaFIN()==null)
			isAtleta=false;
		if (isAtleta)
			query = "INSERT INTO iscritto VALUES (?,?,?,?,?,?,?)";
		else
			query = "INSERT INTO `piscina`.`iscritto` (`idIscritto`, `Nome`, `Cognome`, `Sesso`,`Cellulare`, `DataDiNascita`) VALUES (?,?,?,?,?,?);";
		boolean esito=false;
		conn=DBManager.startConnection();
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1, i.getIdIscritto());
			ps.setString(2, i.getNome() );
			ps.setString(3, i.getCognome());
			ps.setString(4, i.getSesso());
			ps.setString(5, i.getCellulare());
			ps.setDate(6, i.getDataNascita());

			if(isAtleta)
				ps.setInt(7, i.getMatricolaFIN());

			int tmp=ps.executeUpdate();
			if (tmp==1)
				esito=true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DBManager.closeConnection();
		return esito;
	}
	//----CONTROLLO DINAMICO ID ISCRITTO----
	public boolean ControlloDinamicoIdIscritto(int id) {
		String query = "SELECT * FROM iscritto WHERE idIscritto = ?";
		boolean risultato = false;
		PreparedStatement ps;
		conn=DBManager.startConnection();
		try {
			ps = conn.prepareStatement(query);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if(rs.next())
				risultato = true; //ESISTE GIA UNA TUPLA CON QUELL'ID
			else
				risultato = false; //NON ESISTE GIA UNA TUPLA
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		DBManager.closeConnection();
		return risultato;
	}


	/*//----RESTITUZIONE DI TUTTI GLI ATLETI----PER LA COMBOBOX----NON CANCELLARE PER SICUREZZA
	public DefaultComboBoxModel<String> getAtleticb(){
		DefaultComboBoxModel<String> risultato = new DefaultComboBoxModel<String>();
		String query = "SELECT * FROM piscina.iscritto WHERE MatricolaFin!='null';";
		Iscritto res = new Iscritto();
		PreparedStatement ps;
		conn=DBManager.startConnection();
		try {
			ps = conn.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				res=new Iscritto();
				res.setIdIscritto(rs.getInt("idIscritto"));
				res.setNome( rs.getString("Nome") );
				res.setCognome( rs.getString("Cognome") );
				res.setSesso( rs.getString("Sesso") );
				res.setCellulare( rs.getString("Cellulare") );
				res.setDataNascita( rs.getDate("DataDiNascita"));
				res.setMatricolaFIN(rs.getInt("MatricolaFin"));

				risultato.addElement(res.toStringAtleta());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DBManager.closeConnection();
		return risultato;

}		*/



	//----RESTITUZIONE DI TUTTI GLI ATLETI----PER LA COMBOBOX----PROVA CON OGETTO ISCRITTO----
	public DefaultComboBoxModel<Iscritto> getAtleticb(){
		DefaultComboBoxModel<Iscritto> risultato = new DefaultComboBoxModel<Iscritto>();
		String query = "SELECT * FROM piscina.iscritto WHERE MatricolaFin!='null';";
		Iscritto res = new Iscritto();
		PreparedStatement ps;
		conn=DBManager.startConnection();
		try {
			ps = conn.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				res=new Iscritto();
				res.setIdIscritto(rs.getInt("idIscritto"));
				res.setNome( rs.getString("Nome") );
				res.setCognome( rs.getString("Cognome") );
				res.setSesso( rs.getString("Sesso") );
				res.setCellulare( rs.getString("Cellulare") );
				res.setDataNascita( rs.getDate("DataDiNascita"));
				res.setMatricolaFIN(rs.getInt("MatricolaFin"));

				risultato.addElement(res);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DBManager.closeConnection();
		return risultato;

	}		
	public Vector<Iscritto> getAll() {
		String query = "SELECT * FROM iscritto";
		Vector<Iscritto> list = new Vector<Iscritto>();
		PreparedStatement ps;
		conn=DBManager.startConnection();
		try {
			ps = conn.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			Iscritto res=null;
			while(rs.next()){
				list.add(recordToIscritto(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DBManager.closeConnection();
		return list;
	} 
	public Vector<Iscritto> getTuttiAtleti() {
		String query = "SELECT * FROM iscritto WHERE MatricolaFIN IS NOT NULL";
		Vector<Iscritto> list = new Vector<Iscritto>();
		PreparedStatement ps;
		conn=DBManager.startConnection();
		try {
			ps = conn.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			Iscritto res=null;
			while(rs.next()){
				list.add(recordToIscritto(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DBManager.closeConnection();
		return list;
	} 
}

