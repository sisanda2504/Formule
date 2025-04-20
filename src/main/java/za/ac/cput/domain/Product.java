package za.ac.cput.domain;
/*
Sisanda Madikizela;
230601774;
Product class;
Date: 15/04/2025
 */


public class Product {
    private int id;
    private String name;
    private String description;
    private double price;
    private int quantity;
    private int categoryId;
    private Brand brand;

    private Product(){

    }
    private Product(Builder builder){
        this.id = builder.id;
        this.name = builder.name;
        this.description = builder.description;
        this.price = builder.price;
        this.quantity = builder.quantity;
        this.categoryId = builder.categoryId;
        this.brand = builder.brand;

    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getCategoryId() {
        return categoryId;
    }
    public Brand getBrand() {
        return brand;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                ", categoryId=" + categoryId +
                ", brand=" + brand +
                '}';
    }

    public static class Builder {
        private int id;
        private String name;
        private String description;
        private double price;
        private int quantity;
        private int categoryId;
        private Brand brand;

        public Builder setId(int id){
            this.id = id;
            return this;
        }
        public Builder setName(String name){
            this.name = name;
            return this;
        }
        public Builder setDescription(String description){
            this.description = description;
            return this;
        }
        public Builder setPrice(double price){
            this.price = price;
            return this;
        }
        public Builder setQuantity(int quantity){
            this.quantity = quantity;
            return this;
        }
        public Builder setCategoryId(int categoryId){
            this.categoryId = categoryId;
            return this;
        }
        public Builder setBrand(Brand brand){
            this.brand = brand;
            return this;
        }
        public Builder copy(Product product){
            this.id = product.getId();
            this.name = product.getName();
            this.description = product.getDescription();
            this.price = product.getPrice();
            this.quantity = product.getQuantity();
            this.categoryId = product.getCategoryId();
            this.brand = product.getBrand();

            return this;
        }
        public Product build(){
            return new Product(this);
        }
    }
}
