package br.edu.ufcg.ccc.psoft.cccpharma.CCCPharma.model.product.discount;

public class NoDiscount implements Discount{

    public NoDiscount(){
    }

    public double getDiscount(){
        return (float) 0.0;
    }
}