package com.automation.tests.day5;

public class Pojo {
    //Pojo==PLAIN OLD JAVA OBJECT
//we use new Class()...to generate an object
 /*
 //we do not have behaviors in JSON no methods!!
 //JSON supports different data types, like Float,  Integer, String, Boolean....
 /We can generate arrays and objects
    //below we have jSon object::
    //why do we use JSON: JSON is used for TRANSFERRING DATA between CLIENT and SERVER,
    //SERVER and SERVER
    //JS supports natively JSON, In Java we need add some ...
    {
        "id":39,
        "name":"John Simith",
        "gender":"Male",
        "Phone":123456789

    }
    //
    public class Spartan{
    private int id;
    private String name;
    private String gender;
    private Long phone;

    public Spartan(int id, String name,String gender, Long phone){
    this.id=id;
    this.name=name;
    this.gender=gender;
    this.phone=phone;

    }

    }

    Spartan spartan=new Spartan(1,"Vasyl Fomiuk", "Male",12343454567L);



  */
 //JSON is very lightweight
 //JSON is language independent
 //JSON is human readable, plain text
 //JSON is easy to read and parse
    //??????INTERVIEW QUESTION???????????
   // What kind of web services use JSON to transfer data?
    //RESTFUL WebServices, that are based on REST architecture
    //SOAP services can not use JSON, they use XML format
    //SOAP RESPONSE EEas below:::::::
    //<spartan>
    //<name> Smith John</name>
    //<gender> Male</gender>
    //<phone>123456789</phone>
    //</spartan>
    //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    //SERIALIZATION ==CONVERTING JAVA OBJECT INTO JSON OBJECT
    //CONVERT JAVA OBJECT, POJO INTO STREAM of BYTES
    //DESERIALIZATION ==CONVERTING JSON object to JAVA object
    //JACKSON is one of the best serialization converts JSON object to JAVA

}
