package za.ac.cput.domain.business;
/*
Category.java
Category model class
Author: Samkelisiwe Sithabile (222843152)
Date: 18/04/2025
*/

public class Category {

    private int id;
    private String name;
    private String description;

    private Category() {}
    private Category(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.description = builder.description;
    }
    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getDescription() {
        return description;
    }
    public static class Builder {
        private int id;
        private String name;
        private String description;

        public Builder setId(int id) {
            this.id = id;
            return this;
        }
        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setDescription(String description) {
            this.description = description;
            return this;
        }
        public Builder copy(Category category) {
            this.id = category.getId();
            this.name = category.getName();
            this.description = category.getDescription();
            return this;
        }
        public Category build() {
            return new Category(this);
        }
    }
    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}

