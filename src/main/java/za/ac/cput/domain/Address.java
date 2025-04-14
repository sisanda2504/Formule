package za.ac.cput.domain;
/*
Address.java
Address model class
Author: Agnes Mabusela (230020690)
Date: 14/04/2025
 */

public class Address {

    private int id;
    private int customerId;
    private String street;
    private String city;
    private String province;
    private String postalCode;
    private String country;

    private Address (){}

    private Address(Builder builder){
        this.id = builder.id;
        this.customerId = builder.customerId;
        this.street = builder.street;
        this.city = builder.city;
        this.province = builder.province;
        this.postalCode = builder.postalCode;
        this.country = builder.country;
    }

    public int  getId(){
        return id;
    }

    public int getCustomerId(){
        return customerId;
    }

    public String getStreet(){
        return street;
    }

    public String getCity(){
        return city;
    }

    public String getProvince(){
        return province;
    }

    public String getPostalCode(){
        return postalCode;
    }

    public String getCountry(){
        return country;
    }

    public static class  Builder{
        private int id;
        private int customerId;
        private String street;
        private String city;
        private String province;
        private String postalCode;
        private String country;

        public Builder setId(int id){
            this.id = id;
            return this;
        }

        public Builder setCustomerId(int customerId){
            this.customerId = customerId;
            return this;
        }

        public Builder setStreet(String street){
            this.street = street;
            return this;
        }

        public Builder setCity(String city){
            this.city = city;
            return this;
        }

        public Builder setProvince(String province){
            this.province = province;
            return this;
        }

        public Builder setPostalCode(String postalCode){
            this.postalCode = postalCode;
            return this;
        }

        public Builder setCountry(String country){
            this.country = country;
            return this;
        }

        public Builder copy(Address address){
            this.id = address.getId();
            this.customerId = address.getCustomerId();
            this.street = address.getStreet();
            this.city = address.getCity();
            this.province = address.getProvince();
            this.postalCode = address.getPostalCode();
            this.country = address.getCountry();
            return this;
        }

        public Address build(){
            return new Address(this);
        }
    }

}
