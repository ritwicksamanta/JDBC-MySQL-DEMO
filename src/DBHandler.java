import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBHandler {

	//Write the required business logic as expected in the question description
	public Connection establishConnection(){
		Properties prop = new Properties();
		Connection con = null;
		
		//fill the code
		try {
			FileInputStream fis = new FileInputStream("src/db.properties");
			prop.load(fis);
			Class.forName(prop.getProperty("db.classname"));
			con = DriverManager.getConnection(prop.getProperty("db.url"), prop.getProperty("db.username"), prop.getProperty("db.password"));
			
		}
		catch(FileNotFoundException ex) {
			System.out.println("Properties file not found");
		}
		catch(IOException ex) {
			System.out.println(ex.getMessage());
		}
		catch(SQLException ex) {
			ex.printStackTrace();
		}
		catch(ClassNotFoundException ex) {
			System.out.println(ex.getClass().getSimpleName()+":"+ex.getMessage());
		}
		catch(Exception ex) {
			System.out.println(ex.getMessage());
		}
		return con;
	}
}
