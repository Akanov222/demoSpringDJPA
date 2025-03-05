package edu.demosql.first.repository.domain;

public class Pc {
    private Long pc_id;
    private int model;
    private int speed;
    private int ram;
    private float hd;
    private String cd;
    private float price;

    public Pc() {
    }

    public Pc( int model, int speed, int ram, float hd, String cd, float price) {
        this.model = model;
        this.speed = speed;
        this.ram = ram;
        this.hd = hd;
        this.cd = cd;
        this.price = price;
    }

    public Pc(Long pc_id, int model, int speed, int ram, float hd, String cd, float price) {
        this.pc_id = pc_id;
        this.model = model;
        this.speed = speed;
        this.ram = ram;
        this.hd = hd;
        this.cd = cd;
        this.price = price;
    }

    public Long getPc_id() {
        return pc_id;
    }

    public void setPc_id(Long pc_id) {
        this.pc_id = pc_id;
    }

    public int getModel() {
        return model;
    }

    public void setModel(int model) {
        this.model = model;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getRam() {
        return ram;
    }

    public void setRam(int ram) {
        this.ram = ram;
    }

    public float getHd() {
        return hd;
    }

    public void setHd(float hd) {
        this.hd = hd;
    }

    public String getCd() {
        return cd;
    }

    public void setCd(String cd) {
        this.cd = cd;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Pc{" +
                "pc_id=" + pc_id +
                ", model=" + model +
                ", speed=" + speed +
                ", ram=" + ram +
                ", hd=" + hd +
                ", cd='" + cd + '\'' +
                ", price=" + price +
                '}';
    }
}
