package com.example.myapplication;

public class RowItem {

    private String heading;
    private String subHeading;
    private int smallImageName;
    private int bigImageName;

    public void setHeading( String theHeading ) {

        this.heading = theHeading;

    }

    public String getHeading() {

        return this.heading;

    }


    public void setSubHeading( String theSubHeading ) {

        this.subHeading = theSubHeading;

    }

    public String getSubHeading( ) {

        return this.subHeading;

    }

    public void setSmallImageName(int smallName) {

        this.smallImageName = smallName;

    }

    public int getSmallImageName() {

        return this.smallImageName;

    }


    public void setBigImageName(int bigName) {

        this.bigImageName = bigName;

    }

    public int getBigImageName( ) {

        return this.bigImageName;

    }
}
