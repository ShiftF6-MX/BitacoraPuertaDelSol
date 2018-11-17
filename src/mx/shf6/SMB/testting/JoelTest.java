package mx.shf6.SMB.testting;

import java.util.ArrayList;

import mx.shf6.SMB.model.dao.ClienteDAO;
import mx.shf6.SMB.model.dao.DetalleVentaDAO;
import mx.shf6.SMB.model.dao.ProductoDAO;
import mx.shf6.SMB.model.dao.VentaDAO;
import mx.shf6.SMB.utilities.ConnectionDB;
import mx.shf6.SMB.model.Venta;
import mx.shf6.SMB.model.Cliente;
import mx.shf6.SMB.model.DetalleVenta;
import mx.shf6.SMB.model.Producto;



public class JoelTest {
	static ConnectionDB connectionDB =  new ConnectionDB("shiftf6db", "192.168.0.216", "conn01" , "Simons83Mx");
	
	public static void testVentaLeer() throws Exception {   
		VentaDAO ventaDAO = new VentaDAO();
    	ArrayList <Object> resultadoSelect = ventaDAO.leer(connectionDB.conectarMySQL(), "", "");      
    	for(Object venta: resultadoSelect) {
    		System.out.println(((Venta) venta).showInformacion());
    	}    	    	
    }
	public static void testClienteLeer() throws Exception {   
		ClienteDAO clienteDAO = new ClienteDAO();
    	ArrayList <Object> resultadoSelect = clienteDAO.leer(connectionDB.conectarMySQL(), "", "");      
    	for(Object cliente: resultadoSelect) {
    		System.out.println(((Cliente) cliente).showInformacion());
    	}    	    	
    }
	
	public static void testProductoLeer() throws Exception {   
		ProductoDAO productoDAO = new ProductoDAO();
    	ArrayList <Object> resultadoSelect = productoDAO.leer(connectionDB.conectarMySQL(), "", "");      
    	for(Object producto: resultadoSelect) {
    		System.out.println(((Producto) producto));
    	}    	    	
    }
	public static void testDetalleVentaLeer() {   
		DetalleVentaDAO detalleVentaDAO = new DetalleVentaDAO();
    	ArrayList <Object> resultadoSelect = detalleVentaDAO.leer(connectionDB.conectarMySQL(), "", "");      
    	for(Object detalleVenta: resultadoSelect) {
    		System.out.println(((DetalleVenta) detalleVenta));
    	}    	    	
    }
	public static void main(String[] args) throws Exception{
		testVentaLeer();
    }

	

}
