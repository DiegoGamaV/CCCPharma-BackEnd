package br.edu.ufcg.ccc.psoft.cccpharma.CCCPharma.model.product.discount;

public class GreatDiscount implements Discount{
    public GreatDiscount(){
    }

    public double getDiscount(){
        return (float) 0.25;
    }
}