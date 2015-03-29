/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cfg.uapa.java.servicios;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.cfg.uapa.java.entidades.Pais;

/**
 *
 * @author acer
 */
public class ServicioPais {

    private static ServicioPais INSTANCIA = null;

    public static ServicioPais getInstancia() {
        if (INSTANCIA == null) {
            INSTANCIA = new ServicioPais();
        }
        return INSTANCIA;
    }

    public List<Pais> getPaises() throws SQLException {

       List<Pais> paises = new ArrayList<>();

        try (PreparedStatement stmt = Coneccion.getInstancia().getConeccion().prepareStatement("select * from pais")) {
            try (ResultSet rs = stmt.executeQuery()) {

                while (rs.next()) {
                    Pais pais = new Pais();
                    pais.setId(rs.getInt(1));
                    pais.setDescripcion(rs.getString(2));

                    paises.add(pais);
                 }
            }

        } catch (SQLException ex) {
            Logger.getLogger(ServicioPais.class.getName()).log(Level.SEVERE, null, ex);          
        }

        return paises;
    }

}
