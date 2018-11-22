package controller.comparator;

import model.product.Product;

public class PriceComparator implements ProductComparator{

    @Override
    public int compare(Product o1, Product o2) {
        if (o1.getPrice() < o1.getPrice())
            return -1;
        else if (o1.getPrice() > o2.getPrice())
            return 1;
        else
            return 0;
    }
}
