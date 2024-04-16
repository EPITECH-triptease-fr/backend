package com.epitech.triptease.security.oauth2;

public enum OAuth2Provider {

    LOCAL("LOCAL"), GOOGLE("GOOGLE");

    private String name;

    OAuth2Provider(String name){
        this.name = name;
    }

    @Override
    public String toString() {
        return this.name;
    }

}
