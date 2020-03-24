package com.sparta.team.model;

public abstract class Rabbit implements RabbitInterface {
    @Override
    public boolean isMature() {
        return false;
    }

    @Override
    public void incrementAge() {

    }
}

