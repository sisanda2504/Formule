package za.ac.cput.domain.users;

import jakarta.persistence.*;
import za.ac.cput.domain.generic.Address;

/*
Customer.java
Customer model class
Author: Agnes Mabusela (230020690)
Date: 13/04/2025
 */
@Entity
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String emailAddress;
    private String password;

    @OneToOne
    private Address address;

    protected Customer(){}

    private Customer(Builder builder){
        this.id = builder.id;
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.phoneNumber = builder.phoneNumber;
        this.emailAddress = builder.emailAddress;
        this.password = builder.password;
        this.address = builder.address;
    }

    public Integer getId(){
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

    public Address getAddress(){
        return address;
    }

    @Override
    public String toString(){
       return "Customer{"+
               "id = '"+ id + '\''+
               ", firstName = '"+ firstName+'\''+
               ", lastName = '"+ lastName +'\''+
               ", phoneNumber = '"+ phoneNumber+'\''+
               ", emailAddress = '"+ emailAddress+'\''+
               ", password = '" +password+'\''+
               ", address = '" +address +'\''+
               '}';
    }

    public static class Builder{

        private Integer id;
        private String firstName;
        private String lastName;
        private String phoneNumber;
        private String emailAddress;
        private String password;
        private Address address;

        public Builder setId(Integer id){
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

        public Builder setAddress(Address address){
            this.address = address;
            return this;
        }

        public Builder copy (Customer customer){
            this.id = customer.getId();
            this.firstName =  customer.getFirstName();
            this.lastName = customer.getLastName();
            this.phoneNumber = customer.getPhoneNumber();
            this.emailAddress = customer.getEmailAddress();
            this.password = customer.getPassword();
            this.address = customer.getAddress();
            return this;
        }

        public Customer build(){
            return new Customer(this);
        }

    }

}
