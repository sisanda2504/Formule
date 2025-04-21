package za.ac.cput.domain;
/*
Customer.java
Customer model class
Author: Agnes Mabusela (230020690)
Date: 13/04/2025
 */
public class Customer {

    private int id;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String emailAddress;
    private String password;
    private int addressId;

    private Customer(){}

    private Customer(Builder builder){
        this.id = builder.id;
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.phoneNumber = builder.phoneNumber;
        this.emailAddress = builder.emailAddress;
        this.password = builder.password;
        this.addressId = builder.addressId;
    }

    public int getId(){
        return id;
    }

    public String getFirstName(){
        return firstName;
    }

    public String getLastName(){
        return lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmailAddress(){
        return emailAddress;
    }

    public String getPassword(){
        return password;
    }

    public int getAddressId(){
        return addressId;
    }

    @Override
    public String toString(){
       return "Customer{"+
               "id = '"+ id + '\''+
               "firstname = '"+ firstName+'\''+
               "lastname = '"+ lastName +'\''+
               "phoneNumber = '"+ phoneNumber+'\''+
               "emailAddress = '"+ emailAddress+'\''+
               "password = '" +password+'\''+
               "Address = '" +addressId +'\''+
               '}';
    }

    public static class Builder{

        private int id;
        private String firstName;
        private String lastName;
        private String phoneNumber;
        private String emailAddress;
        private String password;
        private int addressId;

        public Builder setId(int id){
            this.id = id;
            return this;
        }

        public Builder setFirstName( String firstName){
            this.firstName = firstName;
            return this;
        }

        public Builder setLastName(String lastName){
            this.lastName = lastName;
            return this;
        }

        public Builder setPhoneNumber(String phoneNumber){
            this.phoneNumber = phoneNumber;
            return this;
        }

        public Builder setEmailAddress(String emailAddress){
            this.emailAddress = emailAddress;
            return this;
        }

        public Builder setPassword(String password){
            this.password = password;
            return this;
        }

        public Builder setAddressId(int addressId){
            this.addressId = addressId;
            return this;
        }

        public Builder copy (Customer customer){
            this.id = customer.getId();
            this.firstName =  customer.getFirstName();
            this.lastName = customer.getLastName();
            this.phoneNumber = customer.getPhoneNumber();
            this.emailAddress = customer.getEmailAddress();
            this.password = customer.getPassword();
            this.addressId = customer.getAddressId();
            return this;
        }

        public Customer build(){
            return new Customer(this);
        }

    }

}
