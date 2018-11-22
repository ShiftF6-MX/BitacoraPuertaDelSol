package mx.shf6.SMB.view;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import mx.shf6.SMB.MainApp;
import mx.shf6.SMB.model.Cliente;
import mx.shf6.SMB.model.DetalleVenta;
import mx.shf6.SMB.model.Producto;
import mx.shf6.SMB.model.Venta;
import mx.shf6.SMB.model.dao.ClienteDAO;
import mx.shf6.SMB.model.dao.DetalleVentaDAO;
import mx.shf6.SMB.model.dao.ProductoDAO;
import mx.shf6.SMB.model.dao.VentaDAO;
import mx.shf6.SMB.utilities.Notificacion;

public class Busqueda {
	
	//PROPIEDADES
	private MainApp mainApp;	
	private Cliente cliente;
	private Venta venta;
	private Producto producto;
	private Producto nuevoProducto;
	private DetalleVenta detalleVenta;
	private ClienteDAO clienteDAO;
	private VentaDAO ventaDAO;	
	private DetalleVentaDAO detalleVentaDAO;
	private ProductoDAO productoDAO;
	private ObservableList<String> clientesData;	
	private ObservableList<DetalleVenta> detalleVentaData;
	private ObservableList<Venta> ventaData;
	private ArrayList<String> productosNoFacturables;
	    
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
	@FXML public ComboBox<String> clientesCombo;
	@FXML public TextField buscarField;
	@FXML public Button buscarButton;
	@FXML public DatePicker fechaInicial;
	@FXML public DatePicker fechaFinal;
	@FXML public Label clienteField;
	@FXML public Label cantidadLabel;
	@FXML public TextField cantidadField;
	@FXML public Label precioLabel;
	@FXML public TextField precioField;
	@FXML public Label precioNeto;	
	@FXML public Label statusLabel;
	@FXML public Label nuevaCantidad;
	@FXML public Label nuevoPrecio;
	@FXML public Label nuevoPrecioNeto;
	@FXML public Label productoLabel;
	@FXML public TextField productoField;
	@FXML public Label nuevoProductoField;
	@FXML public Button modificarClienteButton;
	@FXML public Button modifcarPrecioButton;
	
	public Busqueda() {	
		
    }//FIN CONSTRUCTOR
	
	//INICIALIZA LOS COMPOMENTES QUE SE CONTROLAN EN LA INTERFAZ DE USUARIO
    @FXML
    private void initialize() {
    	this.cliente = new Cliente();
    	this.venta = new Venta();
    	this.detalleVenta = new DetalleVenta();
    	this.clienteDAO = new ClienteDAO();
    	this.producto = new Producto();
    	this.ventaDAO = new VentaDAO();
    	this.productoDAO = new ProductoDAO();
    	this.detalleVentaDAO = new DetalleVentaDAO();
    	this.ventaData = FXCollections.observableArrayList();    	
    	this.detalleVentaData = FXCollections.observableArrayList();
    	this.clientesData = FXCollections.observableArrayList();  
    	this.cantidadField.setDisable(true);
		this.precioField.setDisable(true);
		this.modifcarPrecioButton.setDisable(true);
		this.modificarClienteButton.setDisable(true);		
		this.clientesCombo.setDisable(true);
		this.productoField.setDisable(true);
    	productosNoFacturables = new ArrayList<String>();    	
    	productosNoFacturables.add("3820"); 	//Arena    	
    	productosNoFacturables.add("3821");	//Grava
    	productosNoFacturables.add("1080");	//Block
    	productosNoFacturables.add("4551");	//Block del 14
    	productosNoFacturables.add("4081");	//Tabique aparente
    	productosNoFacturables.add("3822");	//Piedra
    	productosNoFacturables.add("3960");	//Tepetate
    	productosNoFacturables.add("BR-002");
    	
    	this.cantidadField.textProperty().addListener(new ChangeListener<String>() {
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d{0,6}([\\.]\\d{0,2})?")) {
                	cantidadField.setText(oldValue);
                }//FIN IF
            }//FIN SUB-METODO
        });//FIN PROPIEDADES
    	
    	this.precioField.textProperty().addListener(new ChangeListener<String>() {
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d{0,6}([\\.]\\d{0,2})?")) {
                	precioField.setText(oldValue);
                }//FIN IF
            }//FIN SUB-METODO
        });//FIN PROPIEDADES
    	
    	this.cantidadField.textProperty().addListener((observable, oldValue, newValue) -> {
    	    this.nuevaCantidad.setText(newValue);
    	    if (nuevaCantidad.getText() == "")
    	    	this.precioNeto.setText("Nuevo precio neto: $ 0.00");
    	    if (nuevoPrecio.getText() == "")
    	    	this.precioNeto.setText("Nuevo precio neto: $ 0.00");
    	    if (nuevaCantidad.getText() != "" && nuevoPrecio.getText() != "")
    	    	try {
    	    		this.nuevoPrecioNeto.setText("Nuevo precio neto: $ " + (Double.parseDouble(this.cantidadField.getText()) * Double.parseDouble(this.precioField.getText())));
				} catch (Exception e) {
					// TODO: handle exception
				}//FIN TRY-CATCH    	    	
    	});
    	
    	this.precioField.textProperty().addListener((observable, oldValue, newValue) -> {
    	    this.nuevoPrecio.setText(newValue);
    	    if (nuevaCantidad.getText() == "")
    	    	this.precioNeto.setText("Nuevo precio neto: $ 0.00");
    	    if (nuevoPrecio.getText() == "")
    	    	this.precioNeto.setText("Nuevo precio neto: $ 0.00");
    	    if (nuevaCantidad.getText() != "" && nuevoPrecio.getText() != "")
    	    	try {
    	    		this.nuevoPrecioNeto.setText("Nuevo precio neto: $ " + (Float.parseFloat(this.cantidadField.getText()) * Float.parseFloat(this.precioField.getText())));
				} catch (Exception e) {
					// TODO: handle exception
				}//FIN TRY-CATCH	    	
    	});   	
    	
    	this.productoField.textProperty().addListener((observable, oldValue, newValue) -> {
    		this.nuevoProductoField.setText("");
    		ArrayList <Object> resultadoProducto = productoDAO.leerFacturables(mainApp.getConnection(), "Codigo", newValue, this.productosNoFacturables);    		
    		try {
    			nuevoProducto = (Producto) resultadoProducto.get(0);
			} catch (Exception e) {
				modifcarPrecioButton.setDisable(true);
				//System.out.println("No se encuentra el procucto");				
			}//FIN TRY-CATCH
			
			if (producto != null) {
				try {
					this.nuevoProductoField.setText(nuevoProducto.getDescripcion());
					if (this.nuevoProductoField.getText() != "")
						modifcarPrecioButton.setDisable(false);
					else
						modifcarPrecioButton.setDisable(true);
				} catch (Exception e) {
					
				}//FIN TRY-CATCH		
			}//FIN IF
			else {
				this.nuevoProductoField.setText("");
				if (this.nuevoProductoField.getText() == "")
					modifcarPrecioButton.setDisable(true);
			}//FIN IF-ELSE
    	});
    	
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
    	tablaDetalleVenta.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showDetalleProducto(newValue));
    	
    	cambiarColorFilaStringVenta(this.codigoVentaColumn);
    	cambiarColorFilaStringDetalle(codigoColumn);
    	cambiarColorFilaStringDetalle(servicioProductoColumn);  	
    }//FIN METODO   
    
    private void cambiarColorFilaStringVenta(TableColumn<Venta, Integer> tableColumn) {
    	tableColumn.setCellFactory(column -> {
    		return new TableCell<Venta, Integer>() {
    			@Override
    			protected void updateItem(Integer item, boolean empty) {
    				super.updateItem(item, empty); //This is mandatory
    	            if (item == null || empty) { //If the cell is empty
    	                setText(null);
    	                setStyle("");
    	            } else { //If the cell is not empty
    	                setText("" + item); //Put the String data in the cell
    	                //We get here all the info of the Person of this row
    	                venta = getTableView().getItems().get(getIndex());
    	                // Style all persons wich name is "Edgard"
    	                if (detalleVentaDAO.verificarFactura(mainApp.getConnection(), venta.getSysPk(), productosNoFacturables)) {
    	                    setTextFill(Color.WHITE); //The text in red
    	                    setStyle("-fx-background-color: green"); //The background of the cell in yellow
    	                } else {
    	                	setTextFill(Color.WHITE); //The text in red
    	                    setStyle("-fx-background-color: red"); //The background of the cell in yellow
    	                }//FIN IF-ELSE
    	            }//FIN IF-ELSE
    	        }//FIN SUB-METODO
    	    };
    	});//FIN PROPIEDADES
    }//FIN METODO
    
    private void cambiarColorFilaStringDetalle(TableColumn<DetalleVenta, String> tableColumn) {
    	tableColumn.setCellFactory(column -> {
    		return new TableCell<DetalleVenta, String>() {
    			@Override
    			protected void updateItem(String item, boolean empty) {
    				super.updateItem(item, empty); //This is mandatory
    	            if (item == null || empty) { //If the cell is empty
    	                setText(null);
    	                setStyle("");
    	            } else { //If the cell is not empty
    	                setText(item); //Put the String data in the cell
    	                //We get here all the info of the Person of this row
    	                detalleVenta = getTableView().getItems().get(getIndex());
    	                // Style all persons wich name is "Edgard"
    	                if (validarDetalleVenta(detalleVenta.getProducto().getCodigo())) {
    	                    setTextFill(Color.WHITE); //The text in red
    	                    setStyle("-fx-background-color: green"); //The background of the cell in yellow
    	                } else {
    	                	setTextFill(Color.WHITE); //The text in red
    	                    setStyle("-fx-background-color: red"); //The background of the cell in yellow
    	                }//FIN IF-ELSE
    	            }//FIN IF-ELSE
    	        }//FIN SUB-METODO
    	    };
    	});//FIN PROPIEDADES
    }//FIN METODO
    
    //BUSCA LOS USUARIOS Y/O CORREOS QUE SE ENCUENTREN EL EL TEXTFIELD DE BUSQUEDA
    @FXML
    private void buscarVenta() {
    	tablaVenta.getItems().clear();
    	tablaDetalleVenta.getItems().clear();
        boolean okClicked = true;
        if (okClicked) {
    		ArrayList <Object> resultadoSelect = ventaDAO.leer(mainApp.getConnection(), "", buscarField.getText(),fechaInicial.getValue().toString(), fechaFinal.getValue().toString());           
        	for(Object venta :resultadoSelect) {
        		ventaData.add((Venta) venta);
        	}//FIN FOR
        	this.tablaVenta.setItems(ventaData); 
        	this.modifcarPrecioButton.setDisable(true);
        	this.clientesCombo.setDisable(true);
        	this.modificarClienteButton.setDisable(true);
        	this.productoField.setDisable(true);
        }//FIN IF
    }//FIN METODO    
   
    @FXML
    private void cambiarCliente() {
		ArrayList <Object> resultadoCliente = clienteDAO.leer(mainApp.getConnection(), "nombre", clientesCombo.getValue());
		cliente= (Cliente) resultadoCliente.get(0);
    	venta = tablaVenta.getSelectionModel().getSelectedItem();    	
    	ventaDAO.modificarCliente(mainApp.getConnection(), venta, cliente.getSysPk());
    	tablaVenta.getItems().clear();
    	tablaDetalleVenta.getItems().clear();
    	clienteField.setText("");
    	fechaInicial.setValue(LocalDate.now());
    	fechaFinal.setValue(LocalDate.now());
    	clienteField.setText("");
    	clientesCombo.setValue("");
    }//FIN METODO   
    
    private void showDetalleProducto(DetalleVenta detalleVenta) {
    	if (detalleVenta != null) {
    		cantidadLabel.setText(detalleVenta.getCantidad() + "");
    		precioLabel.setText("$" + detalleVenta.getPrecio());
    		cantidadField.setText(detalleVenta.getCantidad() + "");
    		precioField.setText("" + detalleVenta.getPrecio());
    		precioNeto.setText("Precio Neto: $" + (detalleVenta.getCantidad() * detalleVenta.getPrecio()));
    		productoLabel.setText(detalleVenta.getProducto().getDescripcion());
    		this.modifcarPrecioButton.setDisable(true);
    		if (validarDetalleVenta(detalleVenta.getProducto().getCodigo())) {
    			statusLabel.setText("SE PUEDE MODIFICAR");
    			statusLabel.setTextFill(Color.GREEN);
    			this.cantidadField.setDisable(false);
    			this.precioField.setDisable(false);
    			this.modifcarPrecioButton.setDisable(false);
    			this.modificarClienteButton.setDisable(false);		
    			this.clientesCombo.setDisable(false);
    			this.productoField.setDisable(false);
    			
    		} else {
    			statusLabel.setText("NO SE PUEDE MODIFICAR");
    			statusLabel.setTextFill(Color.RED);
    			this.cantidadField.setDisable(true);
    			this.precioField.setDisable(true);
    			this.modifcarPrecioButton.setDisable(true);
    			this.modifcarPrecioButton.setDisable(true);
    			this.modificarClienteButton.setDisable(true);		
    			this.clientesCombo.setDisable(true);
    			this.productoField.setDisable(true);
    		}
    	} else {
    		cantidadLabel.setText("");
    		precioLabel.setText("");
    		cantidadField.setText("");
    		precioField.setText("");
    		precioNeto.setText("");
    		statusLabel.setText("");    
    		productoLabel.setText("");  
    	}//FIN IF-ELSE
    }//FIN METODO
    
    private void showDetalleVenta(Venta venta) {    	
        if (venta != null) {
        	detalleVentaData.clear();
        	clienteField.setText(venta.getCliente().getNombre());
        	clientesCombo.setValue(venta.getCliente().getNombre());
        	ArrayList <Object> resultadoSelect = detalleVentaDAO.leer(mainApp.getConnection(), "dventa.FK_Venta_Detalle", "" + venta.getSysPk());  
        	for(Object detalleVenta :resultadoSelect) {
        		detalleVentaData.add((DetalleVenta) detalleVenta);         		
        	}//FIN FOR
        	tablaDetalleVenta.setItems(detalleVentaData);
        	codigoColumn.setCellValueFactory(cellData -> cellData.getValue().getProducto().codigoProperty());
        	servicioProductoColumn.setCellValueFactory(cellData -> cellData.getValue().getProducto().descripcionProperty());
        	cantidadColumn.setCellValueFactory(cellData -> cellData.getValue().cantidadProperty());
        	precioColumn.setCellValueFactory(cellData -> cellData.getValue().precioProperty());
        	impuestoColumn.setCellValueFactory(cellData -> cellData.getValue().impuestoProperty());
        	importeColumn.setCellValueFactory(cellData -> cellData.getValue().importeProperty());
        	cantidadField.setDisable(true);
			precioField.setDisable(true);
			modifcarPrecioButton.setDisable(true);
        } else
        	tablaDetalleVenta.getItems().clear();
    }//FIN METODO	
    
    @FXML
    private void cambiarPrecio () {
    	detalleVenta = tablaDetalleVenta.getSelectionModel().getSelectedItem();
    	if (Double.parseDouble(cantidadField.getText()) * Double.parseDouble(precioField.getText()) == (detalleVenta.getImporte() - detalleVenta.getImpuesto())) 
    		if (this.nuevoProductoField.getText() != "") {
    			detalleVentaDAO.modificarProducto(mainApp.getConnection(), detalleVenta.getSysPK(), Double.parseDouble(cantidadField.getText()), Double.parseDouble(precioField.getText()), nuevoProducto.getSysPk());
    			Notificacion.dialogoAlerta(AlertType.INFORMATION, "Mensaje Maxicomercio", "El registro se ha modificado correctamente");
    		}    			
    		else
    			Notificacion.dialogoAlerta(AlertType.ERROR, "Mensaje Maxicomercio", "No se ha especificado el producto");
    	else {
    		Notificacion.dialogoAlerta(AlertType.ERROR, "Mensaje Maxicomercio", "La cantidad y el precio no coinciden con el precio neto");
    		System.out.println("Los valores no coinciden con la cantidad");
    	}
    }//FIN METODO
    
    private boolean validarDetalleVenta (String sysPkProducto) {    	
    	if (productosNoFacturables.contains(sysPkProducto))
    		return true;
    	else
    		return false;
    }//FIN METODO
	
	//ACCESO AL CLASE PRINCIPAL QUE CONTROLA LAS VISTAS
    public void setMainApp(MainApp mainApp) {
        //AGREGA LA LISTA OBSERVABLE CON LOS DATOS EN LA TABLA
    	this.mainApp = mainApp;
        tablaVenta.setItems(mainApp.getVentaData());
        ArrayList <Object> resultadoSelect = clienteDAO.leer(mainApp.getConnection(), "", "");           
    	for(Object cliente :resultadoSelect) {
    		clientesData.add(((Cliente) cliente).getNombre());
    	}//FIN FOR
    	clientesCombo.setItems(clientesData); 
    }//FIN METODO
    
}//FIN CLASE