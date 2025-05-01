package controller;

import java.util.ArrayList;
import java.util.List;

import models.Products;

public class OrderController {
    private List<Products> products = new ArrayList<>();
    
    private static final double TAX_PERCENTAGE = 0.12; // constant values
    private static final double DISCOUNT_PERCENTAGE = 0.0;
    
    public void addProduct(Products product) { 
        products.add(product);
    }
    
    public List<Products> getAllProducts(){
        return products;
    }       
    
    public List<Products> getOrderedProducts() { // public method returns list of ordered products.
        List<Products> ordered = new ArrayList<>(); // new list for ordered products, separate from products list.
            for (Products p : products) { // for each products with quantity greater than 0 is listed into ordered list.
                if (p.getQuantity() > 0) {
                    ordered.add(p);
                }
            }
        return ordered;
    }
    
    public double calculateSubtotal() { // get list of ordered products, converts the collection into a stream, get each ordered products total price convert to double then get the sum.
        return getOrderedProducts().stream()
            .mapToDouble(Products::getTotalPrice)
            .sum();
    }
    
    public double calculateTax() {
        return calculateSubtotal() * TAX_PERCENTAGE;
    }

    public double calculateDiscount() {
        return calculateSubtotal() * DISCOUNT_PERCENTAGE;
    }

    public double calculateTotal() {
        return calculateSubtotal() + calculateTax() - calculateDiscount();
    }

    public void updateQuantity(Products product, int quantityChange){
        int newQuantity = Math.max(0, product.getQuantity() + quantityChange); // handle negative numbers
        product.setQuantity(newQuantity);
    }
}
