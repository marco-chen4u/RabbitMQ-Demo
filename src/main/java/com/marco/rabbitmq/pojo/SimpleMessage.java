package com.marco.rabbitmq.pojo;

import java.io.Serializable;

public class SimpleMessage implements Serializable {
    // fields
    private String name;
    private String description;

    // constructor
    public SimpleMessage() {
    }

    public SimpleMessage(String name, String description) {
        this.name = name;
        this.description = description;
    }

    // methods
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "SimpleMessage{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
