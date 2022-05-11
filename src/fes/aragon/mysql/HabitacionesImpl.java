package fes.aragon.mysql;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import fes.aragon.interfaz.IBaseDatos;
import fes.aragon.modelo.Habitacion;

public class HabitacionesImpl<E> implements IBaseDatos<E> {

	public HabitacionesImpl() {

	}

	@Override
	public ArrayList<E> consulta() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<E> buscar(String patron) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insertar(E obj) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void modificar(E obj) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public E consulta(Integer id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void eliminar(Integer id) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void cerrar() throws Exception {
		Conexion.getInstancia().cerrar();
	}

	@Override
	public void eliminarProc(Integer id) throws Exception {
		// TODO Auto-generated method stub

	}

	public ArrayList<E> buscarIdHotel(Integer id) throws Exception {
		String query = "select a.id_hbt,a.numero,a.costo,a.refrigerador,a.id_htl,b.tipo"
				+ " from habitaciones a,tipos b where id_htl=" + id + " and a.id_tps=b.id_tps";
		ArrayList<E> datos = new ArrayList<>();
		Statement solicitud = Conexion.getInstancia().getCnn().createStatement();
		ResultSet resultado = solicitud.executeQuery(query);
		if (!resultado.next()) {
			System.out.println("Sin datos");
		} else {
			do {
				Habitacion hb = new Habitacion();
				hb.setId(resultado.getInt(1));
				hb.setNumero(resultado.getString(2));
				hb.setCosto(resultado.getFloat(3));
				hb.setRefrigerador(resultado.getBoolean(4));
				hb.setTipo(resultado.getString(6));
				hb.setIdHotel(id);
				datos.add((E) hb);
			} while (resultado.next());
		}
		solicitud.close();
		resultado.close();
		return datos;
	}

}
