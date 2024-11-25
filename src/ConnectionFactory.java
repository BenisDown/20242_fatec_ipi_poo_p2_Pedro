import java.sql.*;
public class ConnectionFactory{
  //string de conexão
  //jdbc:postgresql://localhost:5432/pessoas
  //exemplo: https://www.google.com:80/search
  private static final String HOST = "localhost";
  private static final String PORT = "5432";
  private static final String DB = "20242_fatec_ipi_poo_pessoas_v2";
  private static final String USER = "postgres";
  //pesqureturn c; você resolve a questão da senha chumbada no código
  private static final String PASSWORD = "123456";
  

  public static Connection conectar() {
    Connection connection = null;
    try {
      Class.forName("org.postgresql.Driver");
      var url = String.format(
        "jdbc:postgresql://%s:%s/%s",
        HOST, PORT, DB
      );
      connection = DriverManager.getConnection(url, USER, PASSWORD);
    } catch (ClassNotFoundException e) {
      System.err.println("PostgreSQL JDBC Driver not found.");
      e.printStackTrace();
    } catch (SQLException e) {
      System.err.println("Connection failed.");
      e.printStackTrace();
    }
    return connection;
  }
}