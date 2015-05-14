package be.howest.nmct.Model;

import javax.xml.namespace.NamespaceContext;

/**
 * Created by Nikita on 16/04/2015.
 */
public class Theatre {
    private String name;
    private String address;
    private String currentMusical;
    private String stageDoor;
    private String location;

    public Theatre (String name, String address, String currentMusical, String stageDoor, String location){
        this.name = name;
        this.address = address;
        this.currentMusical = currentMusical;
        this.stageDoor = stageDoor;
        this.location = stageDoor;
    }

    public String getName(){return name;}
    public String getAddress() {
        return address;
    }
    public String getCurrentMusical() {
        return currentMusical;
    }
    public String getStageDoor() {
        return stageDoor;
    }
    public String getLocation() {
        return location;
    }

    @Override
    public String toString() {
        return name +": "+ currentMusical;
    }
}
