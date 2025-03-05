package edu.demosql.first.repository.domain;

public class Printer {

    private Long printer_id;
    private int model;
    private String color;
    private String type;
    private Long price;

    public Printer() {
    }

    public Printer(int model, String color, String type, Long price) {
        this.model = model;
        this.color = color;
        this.type = type;
        this.price = price;
    }

    public Printer(Long printer_id, int model, String color, String type, Long price) {
        this.printer_id = printer_id;
        this.model = model;
        this.color = color;
        this.type = type;
        this.price = price;
    }

    public Long getPrinter_id() {
        return printer_id;
    }

    public void setPrinter_id(Long printer_id) {
        this.printer_id = printer_id;
    }

    public int getModel() {
        return model;
    }

    public void setModel(int model) {
        this.model = model;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }
}
