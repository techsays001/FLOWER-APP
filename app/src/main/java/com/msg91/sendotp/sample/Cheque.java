package com.msg91.sendotp.sample;

public class Cheque {

    private String image;
    private String status;
    private String user;
    private String prize;
//    private String prize1;



    public Cheque(String image,String status,String user,String prize) {

        this.image = image;
        this.status = status;
        this.user=user;
        this.prize=prize;
//        this.prize1=prize1;


    }

    public Cheque() {
    }


    public String getImage() {
        return image;
    }
    public String getStatus() {
        return status;
    }
    public String getUser() {
        return user;
    }
//    public String getPrize1() { return  prize1; }
    public String getPrize() { return  prize; }}
