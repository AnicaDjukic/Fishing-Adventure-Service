package isa.FishingAdventure.model;

import javax.persistence.*;

@Entity
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "content", nullable = false)
    private String content;

    @Column(name = "rating", nullable = false)
    private int rating;

    public Review(String content, int rating) {
        this.content = content;
        this.rating = rating;
    }

    public Review() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getRating() {
        return this.rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}