package com.msg91.sendotp.sample;

public class Cheque2 {

    private String image;
    private String status;
    private String user;
    private String prize;



    public Cheque2(String image, String status, String user, String prize) {

        this.image = image;
        this.status = status;
        this.user=user;
        this.prize=prize;

    }

    public Cheque2() {
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
    public String getPrize() { return  prize; }}
