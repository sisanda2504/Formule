package za.ac.cput.domain.users;

import jakarta.persistence.*;

/*
Admin.java
Admin model class
Author: Agnes Mabusela (230020690)
Date: 14/04/2025
 */
@Entity
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String emailAddress;
    private String password;

    protected Admin(){}

    private Admin (Builder builder){
        this.id = builder.id;
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.emailAddress = builder.emailAddress;
        this.password = builder.password;
    }

    public Long getId(){
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

    @Override
    public String toString() {
        return "Admin{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", emailAddress='" + emailAddress + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public static class Builder{

        private Long id;
        private String firstName;
        private String lastName;
        private String emailAddress;
        private String password;

        public Builder setId(Long id) {
            this.id = id;
            return this;
        }

        public Builder setFirstName(String firstName){
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
