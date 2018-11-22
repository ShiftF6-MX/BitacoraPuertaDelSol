package mx.shf6.SMB.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import mx.shf6.SMB.model.DetalleVenta;
import mx.shf6.SMB.model.Producto;
import mx.shf6.SMB.model.Venta;

public class DetalleVentaDAO implements ObjectDAO{

	@Override
	public boolean crear(Connection connection, Object objeto) {
		// TODO Auto-generated method stub
		return false;
	}//FIN METODO

	//METODO PARA HACER SELECT EN LA TABLA DETALLE VENTA
	@Override
	public ArrayList<Object> leer(Connection connection, String campoBusqueda, String valorBusqueda) {
		String query ="";
		DetalleVenta detalleVenta = new DetalleVenta();
		ArrayList<Object> listaDetalleVenta = new ArrayList<Object>();
		if(campoBusqueda.isEmpty() && valorBusqueda.isEmpty()) {
			query="SELECT Sys_PK, IProducto, Cantidad, Precio, Impuesto3, FK_Venta_Detalle FROM dventa ORDER BY Sys_PK;";
			try {
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery(query);
				while (resultSet.next()) {
					detalleVenta= new DetalleVenta();
					detalleVenta.setSysPK(resultSet.getInt(1));
					ProductoDAO productoDAO = new ProductoDAO();
					Producto producto = null;
					ArrayList <Object> resultadoProducto = productoDAO.leer(connection, "Sys_PK", resultSet.getString(2));
					producto = (Producto) resultadoProducto.get(0);	
					detalleVenta.setProducto(producto);
					detalleVenta.setCantidad(resultSet.getDouble(3));
					detalleVenta.setPrecio(resultSet.getDouble(4));
					detalleVenta.setImpuesto(resultSet.getDouble(5));
					detalleVenta.setImporte((detalleVenta.getCantidad() * detalleVenta.getPrecio()) + detalleVenta.getImpuesto());
					VentaDAO ventaDAO = new VentaDAO();
					Venta venta = null;
					ArrayList <Object> resultadoVenta = ventaDAO.leer(connection, "Sys_PK", resultSet.getString(6));
					venta = (Venta) resultadoVenta.get(0);		
					detalleVenta.setFKSaleDetail(venta);
					listaDetalleVenta.add(detalleVenta);
				}//FIN WHILE
			}catch (SQLException e) {
				System.out.println("Error: En método leer");
				e.printStackTrace();
			}//FIN TRY-CATCH
		}else {
			query="SELECT dventa.Sys_PK, dventa.IProducto, dventa.Cantidad, dventa.Precio, dventa.Impuesto3, dventa.FK_Venta_Detalle FROM dventa WHERE "+campoBusqueda+" = ? ORDER BY dventa.Sys_PK;";
			try {
				PreparedStatement preparedStatement = connection.prepareStatement(query);
				preparedStatement.setString(1, valorBusqueda);
				ResultSet resultSet=preparedStatement.executeQuery();
				while (resultSet.next()) {
					detalleVenta= new DetalleVenta();
					detalleVenta.setSysPK(resultSet.getInt(1));
					ProductoDAO productoDAO = new ProductoDAO();
					Producto producto = null;
					ArrayList <Object> resultadoProducto = productoDAO.leer(connection, "producto.Sys_PK", resultSet.getString(2));
					producto = (Producto) resultadoProducto.get(0);	
					detalleVenta.setProducto(producto);
					detalleVenta.setCantidad(resultSet.getDouble(3));
					detalleVenta.setPrecio(resultSet.getDouble(4));
					detalleVenta.setImpuesto(resultSet.getDouble(5));
					detalleVenta.setImporte((detalleVenta.getCantidad() * detalleVenta.getPrecio()) + detalleVenta.getImpuesto());
					VentaDAO ventaDAO = new VentaDAO();
					Venta venta = null;
					ArrayList <Object> resultadoVenta = ventaDAO.leer(connection, "venta.Sys_PK", resultSet.getString(6));
					venta = (Venta) resultadoVenta.get(0);		
					detalleVenta.setFKSaleDetail(venta);
					listaDetalleVenta.add(detalleVenta);
				}//FIN WHILE
			}catch (SQLException e) {
				System.out.println("Error: En método leer");
				e.printStackTrace();
			}//FIN TRY-CATCH
		}//FIN IF-ELSE
		return listaDetalleVenta;
	}//FIN METODO

	@Override
	public boolean modificar(Connection connection, Object objeto) {
		// TODO Auto-generated method stub
		return false;
	}//FIN METODO
	
	public boolean modificarProducto (Connection connection, int sysPkDetalleVenta, Double cantidad, Double precio, int producto) {
		String query = "UPDATE dventa "
				+ "SET Cantidad = ?, Precio = ?, IProducto = ? "
				+ "WHERE Sys_PK = ?;";		
		try {
			PreparedStatement preparedStatement = (PreparedStatement) connection.prepareStatement(query);
			preparedStatement.setDouble(1, cantidad);
			preparedStatement.setDouble(2, precio);	
			preparedStatement.setInt(3, producto);
			preparedStatement.setInt(4, sysPkDetalleVenta);
			preparedStatement.execute();
		} catch (SQLException e) {
			System.out.println("Error: En método modificar");
			e.printStackTrace();
			return false;
		}	
		return true;
	}//FIN METODO
	
	public boolean verificarFactura (Connection connection, int ventaSysPk, ArrayList<String> productosNoFacturables) {
		int contadorProductos = 0;
		String query="SELECT producto.Codigo "
				+ "FROM producto "
				+ "INNER JOIN dventa "
				+ "ON producto.Sys_PK = dventa.IProducto "
				+ "WHERE dventa.FK_Venta_Detalle = " + ventaSysPk + ";";
		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(query);
			while (resultSet.next()) {
				if (productosNoFacturables.contains(resultSet.getString(1)))
					contadorProductos++;				
			}//FIN WHILE
			if (contadorProductos == 0)
				return false;
			else 
				return true;
			}catch (SQLException e) {
				System.out.println("Error: En método verificarFactura");
				e.printStackTrace();
			}//FIN TRY-CATCH
		return false;
	}//FIN METODO

	@Override
	public boolean eliminar(Connection connection, Object objeto) {
		// TODO Auto-generated method stub
		return false;
	}//FIN METODO
}//FIN METODO