package mx.shf6.SMB.view;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import mx.shf6.SMB.MainApp;
import mx.shf6.SMB.model.Cliente;
import mx.shf6.SMB.model.DetalleVenta;
import mx.shf6.SMB.model.Venta;
import mx.shf6.SMB.model.dao.ClienteDAO;
import mx.shf6.SMB.model.dao.DetalleVentaDAO;
import mx.shf6.SMB.model.dao.VentaDAO;
import mx.shf6.SMB.utilities.ConnectionDB;

public class Busqueda {
	//INICIO DE LA CONEXION DE LA BASE DE DATOS
	static ConnectionDB connectionDB =  new ConnectionDB("shiftf6db", "192.168.0.216", "conn01" , "Simons83Mx");
	    
    //COMPONENTES INTERFAZ DE USUARIO
	@FXML private TableView<Venta> tablaVenta;
	@FXML private TableColumn<Venta, Integer> codigoVentaColumn;
	@FXML private TableColumn<Venta, String> clienteColumn;
	@FXML private TableColumn<Venta, String> referenciaVentaColumn;
	@FXML private TableColumn<Venta, Date> fechaVentaColumn;
	@FXML private TableColumn<Venta, String> statusFacturacionColumn;
	@FXML private TableColumn<Venta, String> statusFinancieroColumn;
	@FXML private TableColumn<Venta, String> statusAdministrativoColumn;	
	@FXML private TableView<DetalleVenta> tablaDetalleVenta;
	@FXML private TableColumn<DetalleVenta, String> codigoColumn;
	@FXML private TableColumn<DetalleVenta, String> servicioProductoColumn;
	@FXML private TableColumn<DetalleVenta, Double> cantidadColumn;
	@FXML private TableColumn<DetalleVenta, Double> precioColumn;
	@FXML private TableColumn<DetalleVenta, Double> impuestoColumn;
	@FXML private TableColumn<DetalleVenta, Double> importeColumn;
	@FXML public TextField buscarField;
	@FXML public Button buscarButton;
	@FXML public DatePicker fechaInicial;
	@FXML public DatePicker fechaFinal;
	@FXML public Label clienteField;
	@FXML public ComboBox<String> clientesCombo;
	
	private ObservableList<DetalleVenta> detalleVentaData = FXCollections.observableArrayList();
	
	public Busqueda() {	
		
    }//FIN CONSTRUCTOR
	
	//INICIALIZA LOS COMPOMENTES QUE SE CONTROLAN EN LA INTERFAZ DE USUARIO
    @FXML
    private void initialize() {
    	
    	ClienteDAO clienteDAO = new ClienteDAO();
        ObservableList<String> clientesData = FXCollections.observableArrayList();
		ArrayList <Object> resultadoSelect = clienteDAO.leer(connectionDB.conectarMySQL(), "", "");           
    	for(Object cliente :resultadoSelect) {
    		clientesData.add(((Cliente) cliente).getNombre());
    	}//FIN FOR
    	clientesCombo.setItems(clientesData); 
        //INICIALIZA LAS COLUMNAS DEL TABLEVIEW    	
        //SE AGREGAN DATOS A LAS CELDAS DESDE INFORMACION DE LA BASE DE DATOS
    	fechaInicial.setValue(LocalDate.now());
    	fechaFinal.setValue(LocalDate.now());
    	codigoVentaColumn.setCellValueFactory(cellData -> cellData.getValue().sysPkProperty());
    	clienteColumn.setCellValueFactory(cellData -> cellData.getValue().getCliente().nombreProperty()); 
    	referenciaVentaColumn.setCellValueFactory(cellData -> cellData.getValue().referenciaProperty());
    	fechaVentaColumn.setCellValueFactory(cellData -> cellData.getValue().fechaProperty());
    	statusFacturacionColumn.setCellValueFactory(cellData -> cellData.getValue().descripcionStatusFacturacionProperty());
    	statusFinancieroColumn.setCellValueFactory(cellData -> cellData.getValue().descripcionStatusFinancieroProperty());
    	statusAdministrativoColumn.setCellValueFactory(cellData -> cellData.getValue().descripcionStatusAdministrativoProperty());
    	tablaVenta.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showDetalleVenta(newValue));
    }
       
    
    //BUSCA LOS USUARIOS Y/O CORREOS QUE SE ENCUENTREN EL EL TEXTFIELD DE BUSQUEDA
    @FXML
    private void buscarVenta() {
    	tablaVenta.setItems(null);
        boolean okClicked = true;
        if (okClicked) {
            VentaDAO ventaDAO = new VentaDAO();
            ObservableList<Venta> ventaData = FXCollections.observableArrayList();
    		ArrayList <Object> resultadoSelect = ventaDAO.leer(connectionDB.conectarMySQL(), "", buscarField.getText(),fechaInicial.getValue().toString(), fechaFinal.getValue().toString());           
        	for(Object venta :resultadoSelect) {
        		ventaData.add((Venta) venta);
        	}//FIN FOR
        	tablaVenta.setItems(ventaData); 
        }//FIN IF
    }//FIN METODO    
   
    @FXML
    private void cambiarCliente() {
    	Venta venta = null;
    	VentaDAO ventaDAO = new VentaDAO();
    	ClienteDAO clienteDAO = new ClienteDAO();
    	Cliente cliente = null;
		ArrayList <Object> resultadoCliente = clienteDAO.leer(connectionDB.conectarMySQL(), "nombre", clientesCombo.getValue());
		cliente= (Cliente) resultadoCliente.get(0);
    	venta = tablaVenta.getSelectionModel().getSelectedItem();    	
    	ventaDAO.modificarCliente(connectionDB.conectarMySQL(), venta, cliente.getSysPk());
    	tablaVenta.getItems().clear();
    	tablaDetalleVenta.getItems().clear();
    	clienteField.setText("");
    	fechaInicial.setValue(LocalDate.now());
    	fechaFinal.setValue(LocalDate.now());
    	clienteField.setText("");
    	clientesCombo.setValue("");
    }//FIN METODO    	
    
    private void showDetalleVenta(Venta venta) {    	
        if (venta != null) {
        	detalleVentaData.clear();
        	clienteField.setText(venta.getCliente().getNombre());
        	clientesCombo.setValue(venta.getCliente().getNombre());
        	DetalleVentaDAO detalleVentaDAO = new DetalleVentaDAO();
        	ArrayList <Object> resultadoSelect = detalleVentaDAO.leer(connectionDB.conectarMySQL(), "dventa.FK_Venta_Detalle", "" + venta.getSysPk());  
        	for(Object detalleVenta :resultadoSelect) {
        		detalleVentaData.add((DetalleVenta) detalleVenta);         		
        	}
        	tablaDetalleVenta.setItems(detalleVentaData);
        	codigoColumn.setCellValueFactory(cellData -> cellData.getValue().getProducto().codigoProperty());
        	servicioProductoColumn.setCellValueFactory(cellData -> cellData.getValue().getProducto().descripcionProperty());
        	cantidadColumn.setCellValueFactory(cellData -> cellData.getValue().cantidadProperty());
        	precioColumn.setCellValueFactory(cellData -> cellData.getValue().precioProperty());
        	impuestoColumn.setCellValueFactory(cellData -> cellData.getValue().impuestoProperty());
        	importeColumn.setCellValueFactory(cellData -> cellData.getValue().importeProperty());
        } else
        	tablaDetalleVenta.getItems().clear();
    }//FIN METODO
	
	
	//ACCESO AL CLASE PRINCIPAL QUE CONTROLA LAS VISTAS
    public void setMainApp(MainApp mainApp) {
        //AGREGA LA LISTA OBSERVABLE CON LOS DATOS EN LA TABLA
        tablaVenta.setItems(mainApp.getVentaData());
    }//FIN METODO
}//FIN CLASE
