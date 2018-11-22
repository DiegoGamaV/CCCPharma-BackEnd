package controller.comparator;

import model.product.Product;

import java.util.Comparator;

public interface ProductComparator {
    int compare(Product o1, Product o2);
}
