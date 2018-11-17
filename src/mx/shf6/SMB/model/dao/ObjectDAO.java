package mx.shf6.SMB.model.dao;

import java.sql.Connection;
import java.util.ArrayList;

public interface ObjectDAO {
	public boolean crear(Connection connection, Object objeto);
	public ArrayList<Object> leer(Connection connection, String campoBusqueda, String valorBusqueda);
	public boolean modificar(Connection connection, Object objeto);
	public boolean eliminar(Connection connection, Object objeto);
}
