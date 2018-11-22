package mx.shf6.SMB.model;

import java.text.DecimalFormat;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

public class DetalleVenta {

	//PROPIEDADES
	private ObjectProperty<Integer> sysPK;				//Sys_PK (INT) | PrimaryKey
	private ObjectProperty<Producto> producto;			//IProductoo (INT) | ForeingKey (Productoo.Sys_PK)
	private ObjectProperty<Double> cantidad;			//Cantidad (DOUBLE)
	private ObjectProperty<Double> precio;				//Precio (DOUBLE)
	private ObjectProperty<Double> impuesto;			//Impuesto3
	private ObjectProperty<Double> importe;				//Cantidad * Precio
	private ObjectProperty<Venta> venta;				//FK_Venta_Detalle (INT) | ForeingKey (Venta.Sys_PK)
	private DecimalFormat decimalFormat = new DecimalFormat("#.00");
	
	//CONSTRUCTOR SIN PARAMETROS
	public DetalleVenta() {
		this(0, null, 0.0, 0.0, 0.0, 0.0, null);
	}//FIN CONSTRUCTOR
	
	//CONSTRUCTOR CON PARAMETROS
	public DetalleVenta(Integer sysPK, Producto producto, Double cantidad, Double precio, Double impuesto, Double importe, Venta venta) {
		this.sysPK = new SimpleObjectProperty<Integer>(sysPK);
		this.producto = new SimpleObjectProperty<Producto>(producto);
		this.cantidad = new SimpleObjectProperty<Double>(cantidad);
		this.precio = new SimpleObjectProperty<Double>(precio);
		this.impuesto = new SimpleObjectProperty<Double>(impuesto);
		this.importe = new SimpleObjectProperty<Double>(importe);
		this.venta = new SimpleObjectProperty<Venta>(venta);	
	}//FIN CONSTRUCTOR

	//METODOS PARA ACCESO A "SYSPK"
	public Integer getSysPK() {
		return sysPK.get();
	}//FIN METODO

	public void setSysPK(Integer sysPK) {
		this.sysPK.set(sysPK);
	}//FIN METODO
	
	public ObjectProperty<Integer> sysPkProperty() {
		return this.sysPK;
	}//FIN METODO
	//FIN METODOS "SYSPK"
	
	//METODOS PARA ACCESO A "PRODUCTO"
	public Producto getProducto() {
		return producto.get();
	}//FIN METODO

	public void setProducto(Producto producto) {
		this.producto.set(producto);
	}//FIN METODO
		
	public ObjectProperty<Producto> productoProperty() {		
		return this.producto;
	}//FIN METODO
	//FIN METODOS "PRODUCTO"
	
	//METODOS PARA ACCESO A "CANTIDAD"
	public Double getCantidad() {
		double valorFinal = Double.parseDouble(decimalFormat.format(cantidad.get()));
		return valorFinal;
	}//FIN METODO

	public void setCantidad(Double cantidad) {
		double valorFinal = Double.parseDouble(decimalFormat.format(cantidad));
		this.cantidad.set(valorFinal);
	}//FIN METODO
	
	public ObjectProperty<Double> cantidadProperty() {
		return this.cantidad;
	}//FIN METODO
	//FIN METODOS "CANTIDAD"
	
	//METODOS PARA ACCESO A "PRECIO"
	public Double getPrecio() {
		double valorFinal = Double.parseDouble(decimalFormat.format(precio.get()));
		return valorFinal;
	}//FIN METODO

	public void setPrecio(Double precio) {
		double valorFinal = Double.parseDouble(decimalFormat.format(precio));
		this.precio.set(valorFinal);
	}//FIN METODO
	
	public ObjectProperty<Double> precioProperty() {
		return this.precio;
	}//FIN METODO
	//FIN METODOS "PRECIO"	
	
	//METODOS PARA ACCESO A "IMPUESTO"
	public Double getImpuesto() {
		double valorFinal = Double.parseDouble(decimalFormat.format(impuesto.get()));
		return valorFinal;
	}//FIN METODO

	public void setImpuesto(Double impuesto) {
		double valorFinal = Double.parseDouble(decimalFormat.format(impuesto));
		this.impuesto.set(valorFinal);
	}//FIN METODO
		
	public ObjectProperty<Double> impuestoProperty() {
		return this.impuesto;
	}//FIN METODO
	//FIN METODOS "IMPUESTO"
	
	//METODOS PARA ACCESO A "IMPORTE"
	public Double getImporte() {
		DecimalFormat decimalFormat = new DecimalFormat("#.00");
		double valorFinal = Double.parseDouble(decimalFormat.format(importe.get()));
		return valorFinal;
	}//FIN METODO

	public void setImporte(Double importe) {
		DecimalFormat decimalFormat = new DecimalFormat("#.00");
		double valorFinal = Double.parseDouble(decimalFormat.format(importe));
		this.importe.set(valorFinal);
	}//FIN METODO
			
	public ObjectProperty<Double> importeProperty() {
		return this.importe;
	}//FIN METODO
	//FIN METODOS "IMPORTE"
		
	//METODOS PARA ACCESO A "VENTA"
	public Venta getVenta() {
		return venta.get();
	}//FIN METODO
	
	public void setFKSaleDetail(Venta venta) {
		this.venta.set(venta);
	}//FIN METODO
	
	public ObjectProperty<Venta> ventaProperty() {
		return this.venta;
	}//FIN METODO
	//FIN METODOS "VENTA"
	
}//FIN CLASE