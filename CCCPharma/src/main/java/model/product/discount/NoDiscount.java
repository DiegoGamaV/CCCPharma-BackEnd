package model.product.discount;

public class NoDiscount implements Discount{

    public NoDiscount(){
    }

    public double getDiscount(){
        return (float) 0.0;
    }
}
