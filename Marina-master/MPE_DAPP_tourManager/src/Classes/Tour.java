/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;


/**
 *
 * @author Roman
 */
public class Tour {
    Integer idTour;
    String nombre;
    String descripcion;
    byte[] multimedia;
    
    
    public Tour (Integer id, String nombre,String descrip, byte[] multi)
    {
      this.idTour=id;
      this.nombre=nombre;
      this.descripcion=descrip;
      this.multimedia=multi;
    }
    
}
