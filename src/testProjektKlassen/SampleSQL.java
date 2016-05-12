package testProjektKlassen;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Logger;

public class SampleSQL {
	
	private static final Logger log = Logger.getLogger(SampleLog4j.class);

	public static void main(String[] args) throws ClassNotFoundException {

		Class.forName("org.sqlite.JDBC");

		Connection connection = null;

		try {
			// create a database connection
			connection = DriverManager.getConnection("jdbc:sqlite:sample.sqlite");

			// 
			
			Statement statement = connection.createStatement();
			statement.setQueryTimeout(30); // set timeout to 30 sec.

			statement.executeUpdate("DROP TABLE IF EXISTS customer");
			statement.executeUpdate("CREATE TABLE customer (id INTEGER, name STRING)");

			int ids[] = { 1, 2, 3, 4, 5 };
			String names[] = { "Peter", "Pallar", "William", "Paul", "James Bond" };

			for (int i = 0; i < ids.length; i++) {
				statement.executeUpdate("INSERT INTO customer values(' " + ids[i] + "', '" + names[i] + "')");
			}

			// statement.executeUpdate("UPDATE person SET name='Peter' WHERE id='1'");
			// statement.executeUpdate("DELETE FROM person WHERE id='1'");

			ResultSet resultSet = statement.executeQuery("SELECT * from customer");

			while (resultSet.next()) {
				// iterate & read the result set
				System.out.print("id = " + resultSet.getInt("id"));
				System.out.print("  name = " + resultSet.getString("name"));
				System.out.println();
			}

		}

		catch (SQLException e) {
			System.err.println(e.getMessage());
		} finally {
			try {
				if (connection != null)
					connection.close();
			} catch (SQLException e) { // Use SQLException class instead.
				System.err.println(e);
			}
		}
	}
}