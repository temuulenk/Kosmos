package main.world.harvestable;

import main.resources.Resource;

import java.util.ArrayList;

public class Loot {

    private ArrayList<Loot> potentialLoot;

    private Resource resource;
    private int min;
    private int max;
    private double dropRate;

    public Loot() {
        this.potentialLoot = new ArrayList<>();
    }

    public Loot(Resource resource, int min, int max, double dropRate) {
        this.potentialLoot = new ArrayList<>();
        this.resource = resource;
        this.min = min;
        this.max = max;
        this.dropRate = dropRate;
    }

    public void addResource(Resource resource, int min, int max, double dropRate) {
        potentialLoot.add(new Loot(resource, min, max, dropRate));
    }

    public void addResrouce(Resource resource, int min, int max) {
        potentialLoot.add(new Loot(resource, min, max, 1));
    }

    public ArrayList<Resource> getLoot() {
        ArrayList<Resource> droppedLoot = new ArrayList<>();
        double rate = Math.random();

        for(Loot loot : potentialLoot) {
            if(loot.dropRate < rate) {
                int count = (int) (Math.random() * (max - min) + min);
                for(int i=0; i<count; i++) {
                    droppedLoot.add(loot.resource);
                }
            }
        }

        return droppedLoot;
    }

}
