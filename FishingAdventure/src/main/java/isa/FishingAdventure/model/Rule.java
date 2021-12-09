package isa.FishingAdventure.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Rule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "content", nullable = false)
    private String content;

    @Column(name = "isEnforced", nullable = false)
    private boolean isEnforced;

    public Rule(Integer id, String content, boolean isEnforced) {
        this.id = id;
        this.content = content;
        this.isEnforced = isEnforced;
    }

    public Rule() {
    }

    public Integer getId() {
        return this.id;
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

    public boolean isIsEnforced() {
        return this.isEnforced;
    }

    public boolean getIsEnforced() {
        return this.isEnforced;
    }

    public void setIsEnforced(boolean isEnforced) {
        this.isEnforced = isEnforced;
    }

    public boolean isEnforced() {
        return isEnforced;
    }

    public void setEnforced(boolean enforced) {
        isEnforced = enforced;
    }
}