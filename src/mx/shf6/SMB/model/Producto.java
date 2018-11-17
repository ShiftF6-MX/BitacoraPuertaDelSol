package mx.shf6.SMB.model;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Model class for products
 * @author CH_Developer
 *
 */
public class Producto {

	//PROPIEDADES
	private ObjectProperty<Integer> sysPk;	//Sys_PK (INT) | PrimaryKey
	private StringProperty descripcion;		//Descripción (VARCHAR)
	private ObjectProperty<Double> precio;	//Precio (DOUBLE)
	private StringProperty codigo;			//Codigo (VARCHAR)
	
	//CONSTRUCTOR SIN PARAMETROS
	public Producto() {
		this(0, "", 0.0, "");
	}//FIN CONSTRUCTOR
	
	//CONSTRUCTOR CON PARAMETROS
	public Producto(Integer sysPk, String descripcion, Double precio, String codigo) {
		this.sysPk = new SimpleObjectProperty<Integer>(sysPk);
		this.descripcion = new SimpleStringProperty(descripcion);
		this.precio = new SimpleObjectProperty<Double>(precio);
		this.codigo = new SimpleStringProperty(codigo);
	}//FIN CONSTRUCTOR

	//METODOS PARA ACCESO A "SYSPK"
	public Integer getSysPk() {
		return sysPk.get();
	}//FIN METODO

	public void setSysPk(Integer sysPk) {
		this.sysPk.set(sysPk);
	}//FIN METODO
	
	public ObjectProperty<Integer> sysPkProperty() {
		return this.sysPk;
	}//FIN METODO
	//FIN METODOS ACCESO "SYSPK"

	//METODOS PARA ACCESO A "DESCRIPCION"
	public String getDescripcion() {
		return descripcion.get();
	}//FIN METODO

	public void setDescripcion(String descripcion) {
		this.descripcion.set(descripcion);
	}//FIN METODO
	
	public StringProperty descripcionProperty() {
		return this.descripcion;
	}//FIN METODO
	//FIN METODOS "DESCRIPCION"

	//METODOS PARA ACCESO A "PRECIO"
	public Double getPrecio() {
		return precio.get();
	}//FIN METODO

	public void setPrecio(Double precio) {
		this.precio.set(precio);
	}//FIN METODO
	
	public ObjectProperty<Double> precioProperty() {
		return this.precio;
	}//FIN METODO
	//FIN METODOS "PRECIO"
	
	//METODO PARA ACCESO A "CODIGO"
	public String getCodigo() {
		return this.codigo.get();
	}//FIN METODO
	
	public void setCodigo(String codigo) {
		this.codigo.set(codigo);
	}//FIN METODO
	
	public StringProperty codigoProperty() {
		return this.codigo;
	}//FIN METODO
	//FIN METODOS "CODIGO"
	
}//FIN CLASE
