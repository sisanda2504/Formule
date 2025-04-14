package za.ac.cput.domain;
/*
Admin.java
Admin model class
Author: Agnes Mabusela (230020690)
Date: 14/04/2025
 */

public class Admin {

    private int id;
    private String firstName;
    private String lastName;
    private String emailAddress;
    private String password;


    private Admin(){}

    private Admin (Builder builder){

        this.id = builder.id;
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.emailAddress = builder.emailAddress;
        this.password = builder.password;
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

    public String getEmailAddress(){
        return emailAddress;
    }

    public String getPassword(){
        return password;
    }

    public static class Builder{

        private int id;
        private String firstName;
        private String lastName;
        private String emailAddress;
        private String password;

        public Builder setId(int id) {
            this.id = id;
            return this;
        }

        public Builder setFirstName(String firstname){
            this.firstName = firstName;
            return this;
        }

        public Builder setLastName(String lastName){
            this.lastName = lastName;
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

        public Builder copy (Admin admin){
            this.id = admin.getId();
            this.firstName = admin.getFirstName();
            this.lastName = admin.getLastName();
            this.emailAddress = admin.getEmailAddress();
            this.password = admin.getPassword();
            return this;
        }

        public Admin build(){
            return new Admin(this);
        }

    }

}
