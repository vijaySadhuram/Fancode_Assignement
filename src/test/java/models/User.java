package models;
public class User {
    private int id;
    private String name;
    private Address address;

    // Getters
    public int getId() {
        return id;
    }

    public Address getAddress() {
        return address;
    }

    public static class Address {
        public Geo geo;

        public Geo getGeo() {
            return geo;
        }

        public static class Geo {
            public String lat;
            public String lng;
        }
    }
}

