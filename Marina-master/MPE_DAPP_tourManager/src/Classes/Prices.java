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
public class Prices {
    public float priceMXadulto,priceMXnino,priceMXinfante,priceUSadulto,priceUSnino,priceUSinfante;

    public Prices(float a,float b,float c,float d){
        priceMXadulto = c;
        priceMXnino = d;
        priceMXinfante = 0.0f;
        priceUSadulto = a;
        priceUSnino = b;
        priceUSinfante = 0.0f;
    }
}
