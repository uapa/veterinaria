package org.cfg.uapa.java.servicios;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 *
 * @author ecabrerar
 */
public class Coneccion {

    private static  Coneccion INSTANCIA=null;

    private Coneccion() {  } 
    
    
    public static Coneccion getInstancia() {

        if(INSTANCIA==null){
            INSTANCIA = new Coneccion();
        }
        
        return INSTANCIA;
    }

    public Connection getConeccion() {
        
       //Establish connection to MySQL database
       
        Connection con = null;

        try {
           Logger.getLogger(getClass().getName()).info(String.format("ANtes!!!"));
        
              Context ctx  = new InitialContext();
            DataSource ds = (DataSource) ctx.lookup("java:/comp/env/jdbc/petcare");
            
            Logger.getLogger(getClass().getName()).info(String.format("Connection established !!!"));
        
            con = ds.getConnection();
            Logger.getLogger(getClass().getName()).info(String.format("Connection established !!!"));
        
        } catch (NamingException | SQLException ex) {
            Logger.getLogger(Coneccion.class.getName()).log(Level.SEVERE, null, "Error conexion DataSource"+ ex);
        }

        return con;
    }

}
