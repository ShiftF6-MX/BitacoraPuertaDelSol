package mx.shf6.SMB.model;

import java.sql.Date;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Venta {

	//PROPIEDADES
	private ObjectProperty<Integer> sysPk; 					//Sys_PK (INT) | PrimaryKey
	private ObjectProperty<Cliente> cliente; 				//ICliente (INT) | ForeingKey
	private StringProperty referencia; 						//Referencia (VARCHAR)
	private ObjectProperty<Date> fecha; 					//Fecha (DATE)    
	private ObjectProperty<Integer> statusFacturacion;		//StatusFacturacion (TINYINT)
	private ObjectProperty<Integer> statusFinanciero;		//StatusFinanciero
	private ObjectProperty<Integer> statusAdministrativo;	//StatusAdministrativo
	private StringProperty notas;							//Notas (VARCHAR)
	
	//CONSTRUCTOR SIN PARAMETROS
	public Venta() {
		this(0, null, "", null, 0, 0, 0, "");
	}//FIN METODO
	
	//CONSTRUCTOR CON PARAMETROS
	public Venta(Integer sysPk, Cliente cliente, String referencia, Date fecha, int statusFacturacion, int statusFinanciero, int statusAdministrativo, String notas) {
		this.sysPk = new SimpleObjectProperty<Integer>(sysPk);
		this.cliente = new SimpleObjectProperty<Cliente>(cliente);
		this.referencia = new SimpleStringProperty(referencia);
		this.fecha = new SimpleObjectProperty<Date>(fecha);
		this.statusFacturacion = new SimpleObjectProperty<Integer>(statusFacturacion);
		this.statusFinanciero = new SimpleObjectProperty<Integer>(statusFinanciero);
		this.statusAdministrativo = new SimpleObjectProperty<Integer>(statusAdministrativo);
		this.notas = new SimpleStringProperty(notas);
	}//FIN METODO
	
	//METODOS PARA ACCESO A "SYSPK"
	public void setSysPk(Integer sysPK) {
		this.sysPk.set(sysPK);
	}//FIN METODO
	
	public Integer getSysPk() {
		return this.sysPk.get();
	}//FIN METODO
	
	public ObjectProperty<Integer> sysPkProperty() {
		return this.sysPk;
	}//FIN METODO
	//FIN METODOS "SYSPK"
	
	//METODOS PARA ACCESO A "CLIENTE"
	public void setCliente(Cliente cliente) {
		this.cliente.set(cliente);
	}//FIN METODO
		
	public Cliente getCliente() {
		return this.cliente.get();
	}//FIN METODO
		
	public ObjectProperty<Cliente> clienteProperty() {
		return this.cliente;
	}//FIN METODO
	//FIN METODOS "CLIENTE"	
	
	//METODOS PARA ACCESO A "REFERENCIA"
	public void setReferencia(String referencia) {
		this.referencia.set(referencia);
	}//FIN METODO
	
	public String getReferencia() {
		return this.referencia.get();
	}//FIN METODO
	
	public StringProperty referenciaProperty() {
		return this.referencia;
	}//FIN METODO
	//FIN METODOS "REFERENCIA"
	
	//METODOS PARA ACCESO A "FECHA"
	public void setFecha(Date fecha) {
		this.fecha.set(fecha);
	}//FIN METODO
	
	public Date getFecha() {
		return this.fecha.get();
	}//FIN METODO
	
	public ObjectProperty<Date> fechaProperty() {
		return this.fecha;
	}//FIN METODO
	//FIN METODOS "FECHA"	
	
	//METODOS PARA ACCESO A "STATUS FACTURACION"
	public void setStatusFacturacion(Integer statusFacturacion) {
		this.statusFacturacion.set(statusFacturacion);
	}//FIN METODO
		
	public Integer getStatusFacturacion() {
		return this.statusFacturacion.get();
	}//FIN METODO
		
	public ObjectProperty<Integer> statusFacturacionProperty() {
		return this.statusFacturacion;
	}//FIN METODO
	
	public StringProperty descripcionStatusFacturacionProperty() {
		switch (this.getStatusFacturacion()) {
		case 0:
			return new SimpleStringProperty("No Aplica");
		case 1:
			return new SimpleStringProperty("Por Facturar");
		case 2:
			return new SimpleStringProperty("Parcialmente Facturado");
		case 3:
			return new SimpleStringProperty("Facturado");
		}//FIN SWTITCH
		return new SimpleStringProperty();
	}//FIN METODO
	
	public void setNumeroStatusFacturacion(String statusFacturacion) {
		switch (statusFacturacion) {
		case "No Aplica":
			this.setStatusFacturacion(0);
			break;
		case "Por Facturar":
			this.setStatusFacturacion(1);
			break;
		case "Parcialmente Facturado":
			this.setStatusFacturacion(2);
			break;
		case "Facturado":
			this.setStatusFacturacion(3);
			break;
		}//FIN WTITCH
	}//FIN METODO
	//FIN METODOS "STATUS FACTURACION"
	
	//METODOS PARA ACCESO A "STATUS FINANCIERO"
	public void setStatusFinanciero(Integer statusFinanciero) {
		this.statusFinanciero.set(statusFinanciero);
	}//FIN METODO
			
	public Integer getStatusFinanciero() {
		return this.statusFinanciero.get();
	}//FIN METODO
			
	public ObjectProperty<Integer> statusFinancieroProperty() {
		return this.statusFinanciero;
	}//FIN METODO
	
	public StringProperty descripcionStatusFinancieroProperty() {
		switch (this.getStatusFinanciero()) {
		case 0:
			return new SimpleStringProperty("No Aplica");
		case 1:
			return new SimpleStringProperty("Con Adeudo");
		case 2:
			return new SimpleStringProperty("Saldado");
		case 3:
			return new SimpleStringProperty("Consignado");
		}//FIN SWTITCH
		return new SimpleStringProperty();
	}//FIN METODO
	
	public void setNumeroStatusFinanciero(String statusFinanciero) {
		switch (statusFinanciero) {
		case "No Aplica":
			this.setStatusFinanciero(0);
			break;
		case "Con Adeudo":
			this.setStatusFinanciero(1);
			break;
		case "Saldado":
			this.setStatusFinanciero(2);
			break;
		case "Consignado":
			this.setStatusFinanciero(3);
			break;
		}//FIN WTITCH
	}//FIN METODO
	//FIN METODOS "STATUS FINANCIERO"
	
	//METODOS PARA ACCESO A "STATUS ADMINISTRATIVO"
	public void setStatusAdministrativo(Integer statusAdministrativo) {
		this.statusAdministrativo.set(statusAdministrativo);
	}//FIN METODO
				
	public Integer getStatusAdministrativo() {
		return this.statusAdministrativo.get();
	}//FIN METODO
				
	public ObjectProperty<Integer> statusAdministrativoProperty() {
		return this.statusAdministrativo;
	}//FIN METODO
	
	public StringProperty descripcionStatusAdministrativoProperty() {
		switch (this.getStatusAdministrativo()) {
		case 0:
			return new SimpleStringProperty("No Aplica");
		case 1:
			return new SimpleStringProperty("Abierto");
		case 2:
			return new SimpleStringProperty("Cerrado");
		case 3:
			return new SimpleStringProperty("Procesado");
		case 99:
			return new SimpleStringProperty("Cancelado");
		}//FIN SWTITCH
		return new SimpleStringProperty();
	}//FIN METODO
	
	public void setNumeroStatusAdministrativo(String statusAdministrativo) {
		switch (statusAdministrativo) {
		case "No Aplica":
			this.setStatusAdministrativo(0);
			break;
		case "Abierto":
			this.setStatusAdministrativo(1);
			break;
		case "Cerrado":
			this.setStatusAdministrativo(2);
			break;
		case "Procesado":
			this.setStatusAdministrativo(3);
			break;
		case "Cancelado":
			this.setStatusAdministrativo(99);
			break;
		}//FIN WTITCH
	}//FIN METODO
	//FIN METODOS "STATUS ADMINISTRATIVO"
	
	//METODOS PARA ACCESO A "NOTAS"
	public void setNotas(String notas) {
		this.notas.set(notas);
	}//FIN METODO
	
	public String getNotas() {
		return this.notas.get();
	}//FIN METODO
	
	public StringProperty notasProperty() {
		return this.notas;
	}//FIN METODO
	//FIN METODOS "NOTAS"	
	
	public String showInformacion() {
		String informacionVenta = "";
		return informacionVenta;
	}//FIN METODO
	
}//FIN CLASE
