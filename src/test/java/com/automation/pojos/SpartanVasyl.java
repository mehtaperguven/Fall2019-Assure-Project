package com.automation.pojos;

import com.google.gson.annotations.SerializedName;

import java.util.Objects;
/**
 * This class represents spartan POJO
 * Example of JSON response:
 * {
 *     "id": 393,               ->   private int id;
 *     "name": "Michael Scott", ->   private String name;
 *     "gender": "Male",        ->   private String gender;
 *     "phone": 6969696969      ->   @SerializedName("phone") private long phoneNumber;
 * }
 *   SerializedName  - an annotation that indicates this member should be serialized to JSON with
 *  * the provided name value as its field name.
 */

public class SpartanVasyl {

        private int id;
        private String name;
        private String gender;
       @SerializedName("phone")
        private Long phone;
//this constructor for Json , no id !! id would be generated automatically
    public SpartanVasyl(String name, String gender, Long phoneNumber){

        this.name=name;
        this.gender=gender;
        this.phone=phoneNumber;
    }

//below constructor for Java
    public SpartanVasyl(int id, String name, String gender, Long phoneNumber){
        this.id=id;
        this.name=name;
        this.gender=gender;
        this.phone=phoneNumber;
    }

//default constructor
    public SpartanVasyl(){

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
        return phone;
    }

    public void setPhoneNumber(Long phoneNumber) {
        this.phone = phoneNumber;
    }

    @Override
    public String toString() {
        return "Spartan{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", phoneNumber=" + phone +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SpartanVasyl spartan = (SpartanVasyl) o;
        return id == spartan.id ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, gender, phone);
    }


}
