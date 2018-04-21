package main.resources;

public enum Resource {

    WOOD("Wood"),
    STONE("Stone");


    private String name;

    Resource(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
