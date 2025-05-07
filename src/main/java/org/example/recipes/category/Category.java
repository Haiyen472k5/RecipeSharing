package org.example.recipes.category;

import jakarta.persistence.*;

@Entity
@Table(name = "categories")
public class Category {

    @Id
    @Column(name = "name", length = 100)
    private String name;

    @Column(name = "usage_count", nullable = false)
    private long usageCount;

    public Category() {}

    public Category(String name) {
        this.name = name;
        this.usageCount = 1;
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public long getUsageCount() { return usageCount; }
    public void setUsageCount(long usageCount) { this.usageCount = usageCount; }

    public void increment() { this.usageCount++; }
}
