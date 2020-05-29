package com.jolan.juc.may29.transferValue;

/**
 * @author FC Bayern Munich
 * @date 2020-05-2020/5/29 0029 8:53
 */
public class TestTransferValue {
    public static void main(String[] args) {
        TestTransferValue testTransferValue = new TestTransferValue();
        int age = 20;
        testTransferValue.changeValue1(age);
        System.out.println("age---------" + age);

        Person person = new Person("abc");
        testTransferValue.changeValue2(person);
        System.out.println("person---------" + person.getPersonName());

        String str = "abc";
        testTransferValue.changeValue3(str);
        System.out.println("String------------" + str);
    }

    public void changeValue1(int age){
        age = 30;
    }

    public void changeValue2(Person person){
        person.setPersonName("xxx");
    }

    public void changeValue3(String str){
        str = "xxx";
    }
}
