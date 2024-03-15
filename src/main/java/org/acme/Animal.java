package org.acme;

public class Animal {
    private Integer id;
    private String name;

    public Animal() {
    }

    public Animal(Animal animal) {
        this.id = (int) (Math.random() * 5);
        this.name = animal.name;
    }

    public Animal(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Animal(String name) {
        this.id = (int) (Math.random() * 5);
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }
}