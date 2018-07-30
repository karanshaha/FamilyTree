package com.karan.family.domain;

public enum Gender {
  FEMALE(1, "Female"),
  MALE(2, "Male");

  private final String description;
  private final int id;

  private Gender(int id, String description) {
    this.description = description;
    this.id = id;
  }

  public int getId() {
    return this.getId();
  }

  public String getDescription() {
    return description;
  }

}
