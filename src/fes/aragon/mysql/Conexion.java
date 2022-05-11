package fes.aragon.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//	Patron singleton
public class Conexion {
	private String url = "jdbc:mysql://localhost:3306/hotel?serverTimezone=UTC";
	private String usuario = "root";
	private String clave = "";
	private Connection cnn = null;
	private static Conexion instancia;

	private Conexion() throws Exception {
		Class.forName("com.mysql.cj.jdbc.Driver");
		cnn = DriverManager.getConnection(url, usuario, clave);
	}

	public static Conexion getInstancia() throws Exception {
		if (instancia == null) {
			instancia = new Conexion();
		}
		return instancia;
	}

	public Connection getCnn() {
		return cnn;
	}

	public void cerrar() throws SQLException {
		this.cnn.close();
	}

}
