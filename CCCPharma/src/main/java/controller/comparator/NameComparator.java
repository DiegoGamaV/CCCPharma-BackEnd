package controller.comparator;

import model.product.Product;

import java.util.Comparator;

public class NameComparator implements ProductComparator {

    @Override
    public int compare(Product o1, Product o2) {
        return o1.getName().compareTo(o2.getName());
    }
}
