package com.jolan.juc.may29.transferValue;

/**
 * @author FC Bayern Munich
 * @date 2020-05-2020/5/29 0029 8:54
 */
public class Person {
    private Integer id;
    private String personName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public Person(String personName) {
        this.personName = personName;
    }

    public Person() {

    }
}
