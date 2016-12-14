
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * This class basically performs DB connection check by getting parameters from user.
 * 
 * @author oisleyen
 *
 */
public class DBConnectionClient {

	public static void main(String[] argv) {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String[] propertyArr = {"hostname","database","user","password"};
		Map<String,String> propertiesMap = new HashMap();

		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return;
		}

		for (int i = 0; i < propertyArr.length; i++) {
			System.out.println("Enter the "+propertyArr[i]+" value:");
			String s = null;
			try {
				s = br.readLine();
				System.out.println("\n");
			} catch (IOException e) {
				e.printStackTrace();
			}

			if (s == null) {
				NullPointerException npe = new NullPointerException();
				throw npe;
			} else {
				propertiesMap.put(propertyArr[i], s);
			} 
		}

		Connection connection = null;

		try {
			connection = DriverManager
					.getConnection("jdbc:mysql://"+propertiesMap.get("hostname")+"/"+propertiesMap.get("database"),propertiesMap.get("user"), propertiesMap.get("password"));

		} catch (SQLException e) {
			System.out.println("Connection Failed! Check output console");
			e.printStackTrace();
			return;
		}

		if (connection != null) {
			System.out.println("You made it, take control your database now!");
		} else {
			System.out.println("Failed to make connection!");
		}
	}
}
