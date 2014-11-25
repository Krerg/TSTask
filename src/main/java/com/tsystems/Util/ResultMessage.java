package com.tsystems.Util;

/**
 * Created by sasha_000 on 23.11.2014.
 */
public class ResultMessage {
    private String message;

    public ResultMessage(String message, boolean succesful)
    {
        this.message = message;
        this.isSeccesful = succesful;
    }

    public boolean isSeccesful() {
        return isSeccesful;
    }

    public void setSeccesful(boolean isSeccesful) {
        this.isSeccesful = isSeccesful;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString(){
        return this.message;
    }

    private boolean isSeccesful=false;
}
