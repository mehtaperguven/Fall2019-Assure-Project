package com.automation.pojos;

import com.google.gson.annotations.SerializedName;

import java.util.Objects;

public class Spartan {

        private int id;
        private String name;
        private String gender;
        @SerializedName("phone")
        private Long phoneNumber;
//this constructor for Json , no id !! id would be generated automatically
    public Spartan(String name,String gender, Long phoneNumber){

        this.name=name;
        this.gender=gender;
        this.phoneNumber=phoneNumber;
    }

//below constructor for Java
    public Spartan(int id, String name,String gender, Long phoneNumber){
        this.id=id;
        this.name=name;
        this.gender=gender;
        this.phoneNumber=phoneNumber;
    }

//default constructor
    public Spartan(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(Long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "Spartan{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", phoneNumber=" + phoneNumber +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Spartan spartan = (Spartan) o;
        return id == spartan.id ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, gender, phoneNumber);
    }


}
