package com.example.rest.webservices.in28minutes.version;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonVersioningController {

    @GetMapping("/v1/person")
    public PersonV1 personV1(){
        return new PersonV1("samuel sromo");
    }

    @GetMapping(value = "/person/param", params = "param-version=1")
    public PersonV1 paramV1(){
        return new PersonV1("samuel sromo");
    }

    @GetMapping(value = "/person/param", params = "param-version=2")
    public PersonV2 paramV2(){
        return new PersonV2(new Name("samo", "sromovsky"));
    }

    @GetMapping(value = "/person/header", headers = "version=1")
    public PersonV1 headerV1(){
        return new PersonV1("samuel sromo");
    }

    @GetMapping(value = "/person/header", headers = "version=2")
    public PersonV2 headerV2(){
        return new PersonV2(new Name("samo", "sromovsky"));
    }

    @GetMapping(value = "/person/produces", produces = "application/vnd.company.app-v1+json")
    public PersonV1 producesV1(){
        return new PersonV1("samuel sromo");
    }

    @GetMapping(value = "/person/produces", produces = "application/vnd.company.app-v2+json")
    public PersonV2 producesV2(){
        return new PersonV2(new Name("samo", "sromovsky"));
    }
}
