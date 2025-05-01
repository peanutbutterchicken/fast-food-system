package models;

public class Products { // todo: constructors, this keyword, super method explain.
     protected String name;
     protected int price;
     protected int quantity;

     public Products(String name, int price){
         this.name = name;
         this.price = price;
         this.quantity = 0;
     }
     
     public int getQuantity() { return quantity; }
     
     public int getPrice() { return price; }
     
     public String getName() { return name; }
     
     public int getTotalPrice () { return price*quantity; }
     
     public int addQuantity () {
         if(quantity>=0)return quantity++;
         return 0;
     }
     
     public int reduceQuantity() {
         if(quantity>0) return quantity--;
         return 0;
    }
     
     public void setQuantity(int quantity){
         this.quantity=quantity;
     }
     
 }
