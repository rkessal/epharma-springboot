package hn.epharma.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonView;

@Entity
public class Categorie {

    @JsonView(JsonViews.Common.class)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @JsonView(JsonViews.Common.class)
    private String name;

    public Categorie() {}

    public Categorie(String name) {
        this.name = name;
    }

    // Getters and setters
    @JsonView(JsonViews.Common.class)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @JsonView(JsonViews.Common.class)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
