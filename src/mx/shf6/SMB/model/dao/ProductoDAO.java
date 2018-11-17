package mx.shf6.SMB.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import mx.shf6.SMB.model.Producto;


public class ProductoDAO implements ObjectDAO{

	@Override
	public boolean crear(Connection connection, Object objeto) {
		// TODO Auto-generated method stub
		return false;
	}

	//METODO PARA HACER SELECT EN LA TABLA PORTEADOR
		@Override
		public ArrayList<Object> leer(Connection connection, String campoBusqueda, String valorBusqueda) {
			String query ="";
			Producto producto = new Producto();
			ArrayList<Object> listaProducto = new ArrayList<Object>();
			if(campoBusqueda.isEmpty() && valorBusqueda.isEmpty()) {
				query="SELECT Sys_PK, Descripcion, Precio1, Codigo FROM producto ORDER BY Sys_PK;";
				try {
					Statement statement = connection.createStatement();
					ResultSet resultSet = statement.executeQuery(query);
					while (resultSet.next()) {
						producto= new Producto();
						producto.setSysPk(resultSet.getInt(1));
						producto.setDescripcion(resultSet.getString(2));
						producto.setPrecio(resultSet.getDouble(3));
						producto.setCodigo(resultSet.getString(4));
						listaProducto.add(producto);
					}
				}catch (SQLException e) {
					System.out.println("Error: En método leer");
					e.printStackTrace();
				}
			}else {
				query="SELECT Sys_PK, Descripcion, Precio1, Codigo FROM producto WHERE "+campoBusqueda+" = ? ORDER BY Sys_PK;";	
				try {
					PreparedStatement preparedStatement = connection.prepareStatement(query);
					preparedStatement.setString(1, valorBusqueda);
					ResultSet resultSet=preparedStatement.executeQuery();
					while (resultSet.next()) {
						producto= new Producto();
						producto.setSysPk(resultSet.getInt(1));
						producto.setDescripcion(resultSet.getString(2));
						producto.setPrecio(resultSet.getDouble(3));
						producto.setCodigo(resultSet.getString(4));
						listaProducto.add(producto);
					}
				}catch (SQLException e) {
					System.out.println("Error: En método leer");
					e.printStackTrace();
				}	
			}
			return listaProducto;
		}//FIN METODO

	@Override
	public boolean modificar(Connection connection, Object objeto) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean eliminar(Connection connection, Object objeto) {
		// TODO Auto-generated method stub
		return false;
	}

}
