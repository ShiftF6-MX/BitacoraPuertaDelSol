package mx.shf6.SMB.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import mx.shf6.SMB.model.Cliente;
import mx.shf6.SMB.model.Venta;

public class VentaDAO implements ObjectDAO {

	@Override
	public boolean crear(Connection connection, Object objeto) {
		// TODO Auto-generated method stub
		return false;
	}//FIN METODO
	
	//METODO PARA HACER SELECT EN LA TABLA VENTAS
	public ArrayList<Object> leer(Connection connection, String campoBusqueda, String valorBusqueda, String fechaInicial, String fechaFinal) {
		String query ="";
		Venta venta = new Venta();
		ArrayList<Object> listaVenta = new ArrayList<Object>(); 
		if(campoBusqueda.isEmpty() && valorBusqueda.isEmpty()) {
			query="SELECT venta.Sys_PK, venta.ICliente, venta.Referencia, venta.Fecha, venta.StatusFacturacion, "
					+ "venta.StatusFinanciero, venta.StatusAdministrativo, venta.Notas "
					+ "FROM venta LEFT JOIN ut_cfd "
					+ "ON venta.Sys_PK = ut_cfd.uf_IVenta "
					+ "WHERE ut_cfd.uf_IVenta IS NULL AND "
					+ "fecha BETWEEN '" + fechaInicial + "' AND '" + fechaFinal + "' "
					+ "AND Documento= 4 "
					+ "ORDER BY Fecha DESC";
			try {
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery(query);
				while (resultSet.next()) {
					venta = new Venta();
					venta.setSysPk(resultSet.getInt(1));
					ClienteDAO clienteDAO = new ClienteDAO();
					Cliente cliente = null;
					ArrayList <Object> resultadoCliente = clienteDAO.leer(connection, "Sys_PK", resultSet.getString(2));
					cliente= (Cliente) resultadoCliente.get(0);
					venta.setCliente(cliente);
					venta.setReferencia(resultSet.getString(3));
					venta.setFecha(resultSet.getDate(4));		
					venta.setStatusFacturacion(resultSet.getInt(5));
					venta.setStatusFinanciero(resultSet.getInt(6));
					venta.setStatusAdministrativo(resultSet.getInt(7));
					venta.setNotas(resultSet.getString(8));
					listaVenta.add(venta);
				}//FIN WHILE
			}catch (SQLException e) {
				System.out.println("Error: En método leer");
				e.printStackTrace();
			}//FIN TRY-CATCH
		}else {
			if(campoBusqueda.isEmpty()) {
				query="SELECT venta.Sys_PK, venta.ICliente, venta.Referencia, venta.Fecha, venta.StatusFacturacion, "
						+ "venta.StatusFinanciero, venta.StatusAdministrativo, venta.Notas "
						+ "FROM venta "
						+ "LEFT JOIN ut_cfd "
						+ "ON venta.Sys_PK = ut_cfd.uf_IVenta "
						+ "INNER JOIN cliente "
						+ "ON cliente.Sys_PK = venta.ICliente "
						+ "WHERE ut_cfd.uf_IVenta IS NULL AND "
						+ "venta.Documento = 4  AND "
						+ "fecha BETWEEN '"+fechaInicial+"' AND '"+fechaFinal+"' AND "
						+ "cliente.nombre like '%"+valorBusqueda+"%' or "
						+ "venta.Referencia like '%"+valorBusqueda+"%' "
						+ "ORDER BY fecha DESC";
				try {
					Statement statement = connection.createStatement();
					ResultSet resultSet = statement.executeQuery(query);
					while (resultSet.next()) {
						venta = new Venta();
						venta.setSysPk(resultSet.getInt(1));
						ClienteDAO clienteDAO = new ClienteDAO();
						Cliente cliente = null;
						ArrayList <Object> resultadoCliente = clienteDAO.leer(connection, "Sys_PK", resultSet.getString(2));
						cliente= (Cliente) resultadoCliente.get(0);
						venta.setCliente(cliente);
						venta.setReferencia(resultSet.getString(3));
						venta.setFecha(resultSet.getDate(4));		
						venta.setStatusFacturacion(resultSet.getInt(5));
						venta.setStatusFinanciero(resultSet.getInt(6));
						venta.setStatusAdministrativo(resultSet.getInt(7));
						venta.setNotas(resultSet.getString(8));
						listaVenta.add(venta);
					}//FIN WHILE
				}catch (SQLException e) {
					System.out.println("Error: En método leer");
					e.printStackTrace();
				}//FIN TRY-CATCH	
			}else {
				query="SELECT venta.Sys_PK, venta.ICliente, venta.Referencia, venta.Fecha, "
						+ "venta.StatusFacturacion, venta.StatusFinanciero, "
						+ "venta.StatusAdministrativo, venta.Notas "
						+ "FROM venta LEFT JOIN ut_cfd "
						+ "ON venta.Sys_PK = ut_cfd.uf_IVenta "
						+ "WHERE ut_cfd.uf_IVenta IS NULL AND "+campoBusqueda+" = ? "
						+ "ORDER BY Fecha DESC and "
						+ "fecha BETWEEN '"+fechaInicial+"' AND '"+fechaFinal+"' AND "
						+ "Documento= 4;";		
				try {
					PreparedStatement preparedStatement = connection.prepareStatement(query);
					preparedStatement.setString(1, valorBusqueda);
					ResultSet resultSet=preparedStatement.executeQuery();
					while (resultSet.next()) {
						venta = new Venta();
						venta.setSysPk(resultSet.getInt(1));
						ClienteDAO clienteDAO = new ClienteDAO();
						Cliente cliente = null;
						ArrayList <Object> resultadoCliente = clienteDAO.leer(connection, "Sys_PK", resultSet.getString(2));
						cliente= (Cliente) resultadoCliente.get(0);
						venta.setCliente(cliente);
						venta.setReferencia(resultSet.getString(3));
						venta.setFecha(resultSet.getDate(4));			
						venta.setStatusFacturacion(resultSet.getInt(5));
						venta.setStatusFinanciero(resultSet.getInt(6));
						venta.setStatusAdministrativo(resultSet.getInt(7));
						venta.setNotas(resultSet.getString(8));
						listaVenta.add(venta);
					}//FIN WHILE
				}catch (SQLException e) {
					System.out.println("Error: En método leer");
					e.printStackTrace();
				}//FIN TRY-CATCH
			}//FIN IF-ELSE
		}//FIN IF-ELSE
		return listaVenta;
	}//FIN METODO
		

	public boolean modificarCliente(Connection connection, Object Venta, int clienteFk) {
		String query = "UPDATE venta "
				+ "SET ICliente = ? "
				+ "WHERE Sys_PK = ?";
		try {
			Venta venta = (Venta)Venta;			
			PreparedStatement preparedStatement = (PreparedStatement) connection.prepareStatement(query);
			preparedStatement.setInt(1, clienteFk);
			preparedStatement.setInt(2, venta.getSysPk());
			preparedStatement.execute();
			VentaDAO ventaDAO = new VentaDAO();
			ventaDAO.modificar(connection, venta);
			return true;
		} catch (SQLException e) {
			System.out.println("Error: En método modificar");
			e.printStackTrace();
			return false;
		}//FIN TRY-CATCH		
	}//FIN METODO

	@Override
	public boolean eliminar(Connection connection, Object objeto) {
		// TODO Auto-generated method stub
		return false;
	}//FIN METODO
	
	//METODO PARA HACER SELECT EN LA TABLA VENTAS SIN LOS PARAMETROS DE FECHA INICIAL, FECHA FINAL Y OPCION
	@Override
	public ArrayList<Object> leer(Connection connection, String campoBusqueda, String valorBusqueda) {
		Venta venta = new Venta();
		ArrayList<Object> listaVenta = new ArrayList<Object>();
		String query;
		query="SELECT venta.Sys_PK, venta.ICliente, venta.Referencia, venta.Fecha, venta.StatusFacturacion, "
				+ "venta.StatusFinanciero, venta.StatusAdministrativo, venta.Notas "
				+ "FROM venta "
				+ "LEFT JOIN ut_cfd "
				+ "ON venta.Sys_PK = ut_cfd.uf_IVenta "
				+ "WHERE ut_cfd.uf_IVenta IS NULL AND " + campoBusqueda + " = ? "
				+ "ORDER BY venta.Fecha DESC;";	
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, valorBusqueda);
			ResultSet resultSet=preparedStatement.executeQuery();
			while (resultSet.next()) {
				venta = new Venta();
				 venta.setSysPk(resultSet.getInt(1));
				 ClienteDAO clienteDAO = new ClienteDAO();
				 Cliente cliente = null;
				 ArrayList <Object> resultadoCliente = clienteDAO.leer(connection, "cliente.Sys_PK", resultSet.getString(2));
				 cliente= (Cliente) resultadoCliente.get(0);
				 venta.setCliente(cliente);
				 venta.setReferencia(resultSet.getString(3));
				 venta.setFecha(resultSet.getDate(4));				 
				 venta.setStatusFacturacion(resultSet.getInt(5));
				 venta.setStatusFinanciero(resultSet.getInt(6));
				 venta.setStatusAdministrativo(resultSet.getInt(7));
				 venta.setNotas(resultSet.getString(8));
				 listaVenta.add(venta);
			}//FIN WHILE
		}catch (SQLException e) {
			System.out.println("Error: En método leer");
			e.printStackTrace();	
		}//FIN TRY-CATCH
		return listaVenta;
	}//FIN METODO

	@Override
	public boolean modificar(Connection connection, Object objeto) {
		// TODO Auto-generated method stub
		return false;
	}//FIN METODO

}//FIN CLASE