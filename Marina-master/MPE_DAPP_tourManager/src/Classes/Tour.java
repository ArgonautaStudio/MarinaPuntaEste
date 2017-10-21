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
    public float precioAdultoUS,precioAdultoMX,precioNinoMX,precioInfanteMX,precioNinoUS,precioInfanteUS;
    public String hora1,hora2,hora3;
    public int capacidad;
    public Integer idTour;
    public String nombre;
    public String descripcion;

    
    
    public Tour (Integer id, String nombre,String descrip,int cap,String hora1,String hora2,String hora3,float precio1mx,
            float precio2mx,float precio1us, float precio2us)
    {
      this.idTour=id;
      this.nombre=nombre;
      this.descripcion=descrip;
      this.hora1=hora1;
      this.hora2=hora2;
      this.hora3=hora3;
      this.capacidad=cap;
      this.precioAdultoMX=precio1mx;
      this.precioAdultoUS=precio1us;
      this.precioNinoMX=precio2mx;
      this.precioInfanteMX=0.0f;
      this.precioNinoUS=precio2us;
      this.precioInfanteUS=0.0f;
    }
    
}
