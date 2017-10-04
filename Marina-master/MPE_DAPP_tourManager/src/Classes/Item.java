/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

import java.sql.*;

/**
 *
 * @author aleja
 */
public class Item 
{
    Integer id, cantidadTotal, cantidadNinos, cantidadAdultos, precioTotal; 
    String nombreTour; 
    Date fecha; 
    Time hora;
    
    public void setId(Integer identificacion) 
    {
        this.id = identificacion; 
    } 
    public Integer getId() 
    {
        return this.id;
    } 
    public void setCantidad(Integer cantidadTotal) 
    {
        this.cantidadTotal = cantidadTotal; 
    } 
    public Integer getCantidad() 
    {
        return this.cantidadTotal;
    }
    public void setNinos(Integer ninos) 
    {
        this.cantidadNinos = ninos; 
    } 
    public Integer getNinos() 
    {
        return this.cantidadNinos;
    }
    public void setPrecioTotal(Integer precio) 
    {
        this.precioTotal = precio; 
    } 
    public Integer getPrecioTotal() 
    {
        return this.precioTotal;
    } 
    public void setNombre(String nombre) 
    {
        this.nombreTour = nombre; 
    } 
    public String getNombre() 
    {
        return this.nombreTour;
    }
    public void setDate(Date date) 
    {
        this.fecha = date; 
    } 
    public Date getDate() 
    {
        return this.fecha;
    } 
    public void setHour(Time hour) 
    {
        this.hora = hour; 
    } 
    public Time getHour() 
    {
        return this.hora;
    }
}
