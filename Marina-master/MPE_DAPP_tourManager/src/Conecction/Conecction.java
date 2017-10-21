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
            String query = "SELECT * FROM Marina_PDE_DB.login where correo = \""+email_input+"\"";
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
            Connection conn=DriverManager.getConnection(url,user,pass);
            String query = "SELECT * ,Valor FROM Marina_PDE_DB.tour, Marina_PDE_DB.Precios where Marina_PDE_DB.Precios.IdPrecio like \"dolarval\"";;
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            ArrayList<Tour> list = new ArrayList<Tour>();

            Tour item;

            while(rs.next())
            {
               item = new Tour(rs.getInt("idTour"),rs.getString("nombre"),rs.getString("descripcion"),rs.getInt("capacidad"),rs.getString("hora1"),rs.getString("hora2"),rs.getString("hora3"),rs.getFloat("precioAdultoUS")*rs.getFloat("Valor"),rs.getFloat("precioNinoUS")*rs.getFloat("Valor"),rs.getFloat("precioAdultoUS"),rs.getFloat("precioNinoUS"));
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
    
 
  
    public Prices getPrices(int tipo){
        Prices item = null;
          try
        {
            Class.forName(myDriver);
            Connection conn = DriverManager.getConnection(url, user, pass);
            String query = "SELECT precioAdultoUS,precioNinoUS,Valor FROM Marina_PDE_DB.tour, Marina_PDE_DB.Precios where Marina_PDE_DB.tour.idtour = " + tipo + " and Marina_PDE_DB.Precios.IdPrecio like \"dolarval\"";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while(rs.next())
            {
               item = new Prices(rs.getFloat("precioAdultoUS"),rs.getFloat("precioNinoUS"),rs.getFloat("precioAdultoUS")*rs.getFloat("Valor"),rs.getFloat("precioNinoUS")*rs.getFloat("Valor"));
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

    public String gethour(int i,int tipo) {
        try
        {
            Class.forName(myDriver);
            Connection conn = DriverManager.getConnection(url, user, pass);
            String query = "SELECT hora"+i+" FROM Marina_PDE_DB.tour where idtour = " + tipo;
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while(rs.next())
            {
               return rs.getTime("hora"+i).toString();
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
    
    public void updateTour(float p1,float p2,int cap,String hora1,String hora2,String hora3,int id){
        try
        {
            Class.forName(myDriver);
            Connection conn = DriverManager.getConnection(url, user, pass);
            String query = "UPDATE Marina_PDE_DB.tour SET precioAdultoUS="+ p1 +",precioNinoUS="+ p2 + ",capacidad="+ cap +",hora1='"+ hora1 +"',hora2='"+ hora2 +"',hora3='"+ hora3 +"' where idtour = " + id;
            Statement st = conn.createStatement();
            st.executeUpdate(query);
        }
        catch (ClassNotFoundException | SQLException e)
        {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
    }

    public ResultSet logInUsers() {
        try
        {
            Class.forName(myDriver);
            Connection conn = DriverManager.getConnection(url, user, pass);
            String query = "SELECT * FROM Marina_PDE_DB.login";
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
    //AGREGAR QUE SOLO MUESTRE LOS TOURS PROXIMOS A PARTIR DE HOY()
    //Y los filtrados posibles
    public ResultSet incTours() {
          try
        {
            Class.forName(myDriver);
            Connection conn = DriverManager.getConnection(url, user, pass);
            String query = "SELECT * FROM Marina_PDE_DB.fechatour JOIN Marina_PDE_DB.tour ON Marina_PDE_DB.fechatour.idtour = Marina_PDE_DB.tour.idtour and Marina_PDE_DB.fechatour.turistas > 0 and Marina_PDE_DB.fechatour.fecha > CURDATE()";
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

    public void DeleteVendor(int id) {
       try
        {
            Class.forName(myDriver);
            Connection conn = DriverManager.getConnection(url, user, pass);
            String query = "delete from Marina_PDE_DB.login where idLogIn = " + id;
            Statement st = conn.createStatement();
            st.execute(query);
        }
        catch (ClassNotFoundException | SQLException e)
        {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
    }

    public void changeDolVal(float val) {
        try
        {
            Class.forName(myDriver);
            Connection conn = DriverManager.getConnection(url, user, pass);
            String query = "UPDATE Marina_PDE_DB.Precios SET Valor="+ val +" where IdPrecio like \"dolarval\"";
            Statement st = conn.createStatement();
            st.executeUpdate(query);
        }
        catch (ClassNotFoundException | SQLException e)
        {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
    }

    public String getDolVal() {
        try
        {
            Class.forName(myDriver);
            Connection conn = DriverManager.getConnection(url, user, pass);
            String query = "select Valor from Marina_PDE_DB.Precios where IdPrecio like \"dolarval\"";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while(rs.next())
            {
               return Float.toString(rs.getFloat("Valor"));
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

    public String getPromotionCode(int i) {
    try
        {
            Class.forName(myDriver);
            Connection conn = DriverManager.getConnection(url, user, pass);
            String query = "select descripcion from Marina_PDE_DB.Precios where IdPrecio like \"prom"+i+"\"";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while(rs.next())
            {
               return rs.getString("descripcion");
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

    public String getPromotionDiscount(int i) {
            try
        {
            Class.forName(myDriver);
            Connection conn = DriverManager.getConnection(url, user, pass);
            String query = "select Valor from Marina_PDE_DB.Precios where IdPrecio like \"prom"+i+"\"";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while(rs.next())
            {
               return Float.toString(rs.getFloat("Valor")*100);
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
    
    public void changePromVal(float val,String desc,int i) {
        try
        {
            Class.forName(myDriver);
            Connection conn = DriverManager.getConnection(url, user, pass);
            String query = "UPDATE Marina_PDE_DB.Precios SET Valor = "+ val +", descripcion= \""+desc +"\" where IdPrecio like \"prom"+i+"\"";
            Statement st = conn.createStatement();
            st.executeUpdate(query);
        }
        catch (ClassNotFoundException | SQLException e)
        {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
    }

    public void changeExtra1Val(float val) {
        try
        {
            Class.forName(myDriver);
            Connection conn = DriverManager.getConnection(url, user, pass);
            String query = "UPDATE Marina_PDE_DB.Precios SET Valor = "+ val +" where IdPrecio like \"extra"+1+"\"";
            Statement st = conn.createStatement();
            st.executeUpdate(query);
        }
        catch (ClassNotFoundException | SQLException e)
        {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
    }

    public void changeExtra2Val(float val) {
        try
        {
            Class.forName(myDriver);
            Connection conn = DriverManager.getConnection(url, user, pass);
            String query = "UPDATE Marina_PDE_DB.Precios SET Valor = "+ val +" where IdPrecio like \"extra"+2+"\"";
            Statement st = conn.createStatement();
            st.executeUpdate(query);
        }
        catch (ClassNotFoundException | SQLException e)
        {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
    }

    public String getExtraDesc(int i) {
        try
        {
            Class.forName(myDriver);
            Connection conn = DriverManager.getConnection(url, user, pass);
            String query = "select descripcion from Marina_PDE_DB.Precios where IdPrecio like \"extra"+i+"\"";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while(rs.next())
            {
               return rs.getString("descripcion");
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

    public String getExtraVal(int i) {
        try
        {
            Class.forName(myDriver);
            Connection conn = DriverManager.getConnection(url, user, pass);
            String query = "select Valor from Marina_PDE_DB.Precios where IdPrecio like \"extra"+i+"\"";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while(rs.next())
            {
               return Float.toString(rs.getFloat("Valor"));
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

    public ResultSet clientesfecha(int id) {
     try
        {
            Class.forName(myDriver);
            Connection conn = DriverManager.getConnection(url, user, pass);
            String query = "SELECT * FROM Marina_PDE_DB.cliente JOIN Marina_PDE_DB.vendido ON Marina_PDE_DB.cliente.idCliente = Marina_PDE_DB.vendido.idCliente where idFechatour = "+ id;
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
}

