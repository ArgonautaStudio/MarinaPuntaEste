/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Conecction;

import java.sql.*;
import java.util.ArrayList;
import Classes.Tour;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import Classes.Prices;

/**
 *
 * @author Roman
 */
public class Conecction {
    private String myDriver = "org.gjt.mm.mysql.Driver";
    private int port = 2083;
    private String host = "160.153.65.130";
    private String db = "Marina_PDE_DB";
    private String user = "ADMIN_MPESTE";
    private String pass = "Configuracion1@";
    private String url = String.format("jdbc:mysql://%s/%s?", host , db);
      
    //constructor
    public Conecction()
    {
    }
    
    public boolean logIn(String email_input, String pass_input)
    {
        try
        {
            Class.forName(myDriver);
            Connection conn = DriverManager.getConnection(url, user, pass);
            String query = "SELECT user,password FROM Marina_PDE_DB.login where user = \""+email_input+"\"";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            
            if (rs == null || !rs.first()) 
            {
                return false;
            }
            else
            {
                String pass = rs.getString("password");
                if (pass.equals(pass_input))
                {
                    st.close();
                    return true;
                }
                else 
                {
                    st.close();
                    return false;
                }
            }
        }
        catch (ClassNotFoundException | SQLException e)
        {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
            return false;
        }
    }
    
    public boolean IsUserAvailable(String email_input)
    {
        try
        {
            Class.forName(myDriver);
            Connection conn = DriverManager.getConnection(url, user, pass);
            String query = "SELECT user FROM Marina_PDE_DB.login where user = \""+email_input+"\"";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            //User disponible
            if (rs == null || !rs.first()) 
            {
                return true;
            }
            
            else
            {
                return false;
            }
        }
        catch (ClassNotFoundException | SQLException e)
        {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
            return false;
        }
    }
    
    public boolean IsAdminPass(String pass_input)
    {
        try
        {
            Class.forName(myDriver);
            Connection conn = DriverManager.getConnection(url, user, pass);
            String query = "SELECT password FROM Marina_PDE_DB.login where nivel = 0";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            //NO HAY ADMINS WTF
            if (rs == null) 
            {
                return false;
            }
            
            else
            {
                //REVISAR ENTRE TODO EL RS DE ADMINS SI LA PASS COINCIDE CON ALGUNO
                while(rs.next())
                {
                    if (rs.getString("password").equals(pass_input))
                        return true;
                }
                return false;
            }
        }
        catch (ClassNotFoundException | SQLException e)
        {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
            return false;
        }
    }
    
    public boolean SignIn(String email_input,String pass_input,int tipo){
        
        try{
            Connection con=DriverManager.getConnection(url,user,pass);
            //Revisar si AdminPass es correcta , si no hay otro usuario con ese nombre
            String query = "INSERT INTO `login`(`idLogIn`, `user`, `password`, `nivel`) VALUES (0,\""+email_input+"\",\""+pass_input+"\","+tipo+")";
            Statement st = con.createStatement();
            st.executeUpdate(query);
            return true;
        }
        catch(Exception e){
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
            System.out.println("1ERR");
            return false;
        }
        
    }
    
    //public int GetIDFecha
            
    //public int getIDCliente
    
     public boolean NewClient(String correo,String nombre,String apellido, String telefono, String direccion){
        
        try{
            Connection con=DriverManager.getConnection(url,user,pass);
            String query = "INSERT INTO `cliente`(`idCliente`, `correo`, `nombre`, `apellido`, `telefono`, `direccion`) VALUES (0,\""+correo+"\",\""+nombre+"\",\""+apellido+"\",\""+telefono+"\",\""+direccion+"\")";
            Statement st = con.createStatement();
            st.executeUpdate(query);
            return true;
        }
        catch(Exception e){
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
            return false;
        }
        
    }
    //Conseguir el idCliente que se crea o continua comprando Conseguir el id del tour haciendo la busqueda con la fecha
     public boolean NewVendidos(int idCliente,int idfecha,int cant1,int cant2,int cant3,float preciototal){
        
        try{
            Connection con=DriverManager.getConnection(url,user,pass);
            String query = "INSERT INTO `vendido`(`idVendido`, `idCliente`, `idFechatour`, `cantNino`, `cantAdultos`, `cantInfantes`, `precioTotal`) VALUES (0,\""+idCliente+"\",\""+idfecha+"\",\""+cant1+"\","+ "\""+cant2+"\",\""+cant3+"\",\""+preciototal+"\")";
            Statement st = con.createStatement();
            st.executeUpdate(query);
            return true;
        }
        catch(Exception e){
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
            return false;
        }
        
    }
    
    public ResultSet logInData(String email_input)
    {
        try
        {
            Class.forName(myDriver);
            Connection conn = DriverManager.getConnection(url, user, pass);
            String query = "SELECT * FROM Marina.login where correo = \""+email_input+"\"";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            return rs;
        }
        catch (ClassNotFoundException | SQLException e)
        {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
            return null;
        }
    }
    
    public ArrayList<Tour> getTours()
    {
        try
        {
            Class.forName(myDriver);
            Connection conn = DriverManager.getConnection(url, user, pass);
            String query = "SELECT * FROM Marina.tour";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            ArrayList<Tour> list = new ArrayList<Tour>();

            Tour item;

            while(rs.next())
            {
               item = new Tour(rs.getInt("idTour"),rs.getString("nombre"),rs.getString("descripcion"),rs.getBytes("multimedia"));
               
               list.add(item);
            }
            return list;
        }
        catch (ClassNotFoundException | SQLException e)
        {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
            return null;
        }
    }    
    
    public String [] getDates(int type){
        try
        {
            Class.forName(myDriver);
            Connection conn = DriverManager.getConnection(url, user, pass);
            String []names = new String[1];
            String query = "SELECT distinct fecha FROM Marina.fechatour where idtour = " + type;
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while(rs.next())
            {
               Date fecha = rs.getDate("fecha");
               names[0]=fecha.toString();
            }
            return names;
        }
        catch (ClassNotFoundException | SQLException e)
        {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
            return null;
        }
        
    }
  
    public Prices getPrices(int tipo){
        Prices item = null;
          try
        {
            Class.forName(myDriver);
            Connection conn = DriverManager.getConnection(url, user, pass);
            String query = "SELECT precioAdultoMX,precioNinoMX,precioInfanteMX,precioAdultoUS,precioNinoUS,precioInfanteUS FROM Marina_PDE_DB.tour where idtour = " + tipo;
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while(rs.next())
            {
               item = new Prices(rs.getFloat("precioAdultoMX"),rs.getFloat("precioNinoMX"),rs.getFloat("precioInfanteMX"),rs.getFloat("precioAdultoUS"),rs.getFloat("precioNinoUS"),rs.getFloat("precioInfanteUS"));
            }
            return item;
        }
        catch (ClassNotFoundException | SQLException e)
        {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
            return null;
        }
    }

    public String TourName(int tipo) {
        try
        {
            Class.forName(myDriver);
            Connection conn = DriverManager.getConnection(url, user, pass);
            String query = "SELECT nombre FROM Marina_PDE_DB.tour where idtour = " + tipo;
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while(rs.next())
            {
               return rs.getString("nombre");
            }
            return "";
        }
        catch (ClassNotFoundException | SQLException e)
        {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
            return "";
        }
    }
}
