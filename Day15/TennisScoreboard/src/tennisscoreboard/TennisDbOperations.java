package tennisscoreboard;

import java.sql.Connection;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TennisDbOperations {
	
	Statement stmt ;
	Connection cnx ;
	private Integer matchId;
	
	public TennisDbOperations(){
		try {
			cnx = DriverManager.getConnection("jdbc:mysql://localhost:3306/tennis-scoreboard",
					"prodapt",
					"we1c@me1");
//			stmt = cnx.createStatement();
//			ResultSet rs = stmt.executeQuery("SELECT * FROM notices n, contact c where n.contact_id ="
//					+ "c.id ORDER BY date_created DESC LIMIT");
//			
//			System.out.println("Connection Succed");
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void addMatch(String player1Name, String player2Name) {
	    String addMatchQuery = "INSERT INTO matches (player1_name, player2_name) VALUES (?, ?)";
	    String lastInsertIdQuery = "SELECT LAST_INSERT_ID()";
	    try (PreparedStatement statement = cnx.prepareStatement(addMatchQuery,Statement.RETURN_GENERATED_KEYS)) {
	        statement.setString(1, player1Name);
	        statement.setString(2, player2Name);
	        statement.executeUpdate();
	        
	        ResultSet generatedKeys = statement.getGeneratedKeys();
	        if (generatedKeys.next()) {
	            this.matchId = generatedKeys.getInt(1); // Retrieve the last inserted ID
	            System.out.println("MatchId" + "  "+this.matchId);
	            // Now you can use the lastInsertedId as needed
	        }
	        
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
	
	public void getMatchId() {
		
	}
	
	public void addPoint(String servingPlayer, int serveWinner) {
	    String addPointQuery = "INSERT INTO points (match_id, serving_player, serve_winner) VALUES (?, ?, ?)";

	    try (PreparedStatement statement = cnx.prepareStatement(addPointQuery)) {
	        statement.setInt(1, this.matchId);
	        statement.setString(2, servingPlayer);
	        statement.setInt(3, serveWinner);
	        statement.executeUpdate();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
	
	public List<String> retrieveMatches() {
	    List<String> matchesList = new ArrayList<>();

	    String retrieveMatchesQuery = "SELECT * FROM matches";
	    
	    try (PreparedStatement statement = cnx.prepareStatement(retrieveMatchesQuery)) {
	        try (ResultSet resultSet = statement.executeQuery()) {
	            while (resultSet.next()) {
	                int matchId = resultSet.getInt("match_id");
	                String player1Name = resultSet.getString("player1_name");
	                String player2Name = resultSet.getString("player2_name");
	                
	                String matchInfo = "Match ID: " + matchId + ", Player 1: " + player1Name + ", Player 2: " + player2Name;
	                matchesList.add(matchInfo);
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return matchesList;
	}

	public List<Integer> retrieveServeWinners(int matchId) {
	    List<Integer> serveWinners = new ArrayList<>();

	    String retrieveServeWinnersQuery = "SELECT serve_winner FROM points WHERE match_id = ?";
	    
	    try (PreparedStatement statement = cnx.prepareStatement(retrieveServeWinnersQuery)) {
	        statement.setInt(1, matchId);
	        
	        try (ResultSet resultSet = statement.executeQuery()) {
	            while (resultSet.next()) {
	                int serveWinner = resultSet.getInt("serve_winner");
	                serveWinners.add(serveWinner);
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return serveWinners;
	}


}
