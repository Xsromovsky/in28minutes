package com.example.rest.webservices.in28minutes.version;

public class PersonV2 {
    private Name name;

    public PersonV2(){

    }

    public PersonV2(Name name){
        super();
        this.name = name;
    }

    public Name getName() {
        return name;
    }

    public void setName(Name name) {
        this.name = name;
    }
}
