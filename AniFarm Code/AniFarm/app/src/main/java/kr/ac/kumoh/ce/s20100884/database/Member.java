package kr.ac.kumoh.ce.s20100884.database;

import android.app.Application;

/**
 * Created by LKH on 2016-06-07.
 */
public class Member extends Application {
    private String id ;
    private String Pass;
    private String name;
    private String sex;
    private String age;
    private String email;
    private String number;
    private String address;
    private String info;

    public String getInfo() {return info; }
    public void setInfo(String info){
        this.info = info;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPass() {
        return Pass;
    }

    public void setPass(String pass) {
        Pass = pass;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public void onCreate(){
        id = null;
        super.onCreate();
    }
    @Override
    public void onTerminate(){
        super.onTerminate();
    }
}
