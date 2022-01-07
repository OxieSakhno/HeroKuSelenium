package com.herokuapp.theinternet.base;

public class TestUtilities extends BaseTest{

    //STATIC SLEEP
    protected void sleep(long seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
