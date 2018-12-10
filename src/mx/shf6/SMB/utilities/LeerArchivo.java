package mx.shf6.SMB.utilities;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class LeerArchivo {
    
    //VARIABLES
    private static List<String> content;
    private static List<String> contentProducts;
    public static String nameDB;
    public static String hostDB;
    public static String userDB;
    public static String passwordDB;
    public static String claveEquipo;
    
    public static void leerArchivo(){
        try {
        	//PUT CONNECTIONDATA FILE ON C:\MaxicomercioTools\ PATH
            LeerArchivo.content = Files.readAllLines(Paths.get("C:\\MaxicomercioTools\\ConnectionData.dat"));
            LeerArchivo.nameDB = content.get(2);
            LeerArchivo.hostDB = content.get(4);
            LeerArchivo.userDB = content.get(6);
            LeerArchivo.passwordDB = content.get(8);
        } catch (IOException | IndexOutOfBoundsException ioe) {
            Notificacion.dialogoException(ioe);
        }//END TRY/CATCH
    }//END METHOD
    
    /**
     *
     * @return
     */
    public static ArrayList<String> leerCodigoProductos(){
    	try {
    		//PUT CONNECTIONDATA FILE ON C:\MaxicomercioTools\ PATH
    		ArrayList <String> productosNoFacturables = new ArrayList<String>();
            LeerArchivo.contentProducts = Files.readAllLines(Paths.get("C:\\MaxicomercioTools\\ProductosNoFacturables.dat"));
            for (int i = 0 ; i < contentProducts.size(); i++) {
            	if (contentProducts.get(i).startsWith("#") == false)
            		productosNoFacturables.add(contentProducts.get(i));
            }//FIN FOR
            return productosNoFacturables;
        } catch (IOException | IndexOutOfBoundsException ioe) {
            Notificacion.dialogoException(ioe);
        }//END TRY/CATCH
		return null;
    }//FIN METODO
    
    public static String aCadena() {
        String datos = "Base de Datos: " + nameDB + "\n"
                + "Host: " + hostDB + "\n"
                + "Usuario: " + userDB + "\n"
                + "Contraseña: " + passwordDB;
        return datos;
    }//END METHOD    
    
}//END CLASS
