/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cfg.uapa.java.modelos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.MessageFormat;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import org.cfg.uapa.java.entidades.Usuario;
import org.cfg.uapa.java.modelos.Coneccion;

public class ModeloUsuario {

    public List<Usuario> getUsuarios() {

        List<Usuario> usuarios = new ArrayList<Usuario>();

        try {
            
            Statement stmt = Coneccion.getInstancia().getConeccion().createStatement();
                
            ResultSet rs = stmt.executeQuery("select * from cliente");

                while (rs.next()) {

                    Usuario usuario = new Usuario();
                    usuario.setCodigo(rs.getInt("id"));
                    usuario.setNombres(rs.getString("nombre"));
                    usuario.setApellidos(rs.getString("apellido"));
                    usuario.setUsuario(rs.getString("usuario"));
                    usuario.setPass(rs.getString("clave"));
                    usuarios.add(usuario);
                }
            

        } catch (SQLException e) {
            System.out.println("Error en el SQl");
        }

        return usuarios;

    }

    public Usuario checkUsuario(String usuario, String clave) {

        Connection con = Coneccion.getInstancia().getConeccion();
        Usuario usuario1 = null;

        try (PreparedStatement pstmt = con.prepareStatement("select * from cliente where usuario = ? and clave= ?")) {

            pstmt.setString(1, usuario);
            pstmt.setString(2, clave);

            try (ResultSet rs = pstmt.executeQuery()) {
                System.out.println("Usuario: " + usuario + " Pass : " + clave);

                if (rs.next()) {

                    usuario1 = new Usuario();
                    usuario1.setCodigo(rs.getInt("id"));
                    usuario1.setNombres(rs.getString("nombre"));
                    usuario1.setApellidos(rs.getString("apellido"));
                    usuario1.setUsuario(rs.getString("usuario"));
                    usuario1.setPass(rs.getString("clave"));

                }
            }

        } catch (SQLException e) {
            Logger.getLogger(getClass().getName()).info(MessageFormat.format("Error en el SQl{0}", e.getMessage()));
            return null;
        }

        return usuario1;
    }

}