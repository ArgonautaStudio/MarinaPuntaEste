/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

import java.util.ArrayList; 
import java.util.List;

/**
 *
 * @author aleja
 */
public class Cart {
    
    List<Item> tours; 
    List<Integer> idTours;
    int pos;
    
    public Cart () 
    {
        tours = new ArrayList<>(); 
        idTours = new ArrayList<>();
        pos = 0;
    } 
    
    public void addTour(Item tour) 
    {
        tours.add(pos,tour); 
        idTours.add(pos,tour.getId());
        pos++;
    }  
    public void deleteTour (int position) 
    {
        tours.remove(position);
        idTours.remove(position);
    }
    public List getTours() 
    {
        return this.tours;
    } 
    
    public List getIdTours() 
    {
        return this.idTours;
    }
    
}
