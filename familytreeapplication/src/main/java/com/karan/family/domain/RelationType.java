package com.karan.family.domain;

import com.karan.family.exception.CustomException;

public enum RelationType {
  FATHER(101, "FATHER", new int[]{106, 107}, Gender.MALE),
  MOTHER(102, "MOTHER", new int[]{106, 107}, Gender.FEMALE),
  BROTHERS(103, "BROTHERS", new int[]{105}, Gender.MALE),
  SISTERS(105, "SISTERS", new int[]{103}, Gender.FEMALE),
  SONS(106, "SONS", new int[]{101, 102}, Gender.MALE),
  DAUGHTERS(107, "DAUGHTERS", new int[]{101, 102}, Gender.FEMALE),
  COUSINS(108, "COUSINS", new int[]{0}, Gender.MALE),
  GRANDMOTHER(109, "GRANDMOTHER", new int[]{111, 112}, Gender.FEMALE),
  GRANDFATHER(110, "GRANDFATHER", new int[]{111, 112}, Gender.MALE),
  GRANDSONS(111, "GRANDSONS", new int[]{110, 109}, Gender.MALE),
  GRANDAUGHTERS(112, "GRANDAUGHTERS", new int[]{110, 109}, Gender.FEMALE),
  AUNTS(113, "AUNTS", new int[]{0}, Gender.FEMALE),
  UNCLES(114, "UNCLES", new int[]{0}, Gender.MALE),
  HUSBAND(115, "HUSBAND", new int[]{116}, Gender.MALE),
  WIFE(116, "WIFE", new int[]{115}, Gender.FEMALE);

  private String relation;
  private int id;
  private int[] allowedRelation;
  private Gender gender;

  private RelationType(int id, String relation, int[] allowedRelation, Gender gender) {
    this.relation = relation;
    this.id = id;
    this.allowedRelation = allowedRelation;
    this.gender = gender;
  }

  public static boolean isAllowedRelation(RelationType r1, RelationType r2) {
    for (int id : r1.getAllowedRelation()) {
      if (id == r2.getId()) {
        return true;
      }
    }
    return false;
  }

  public int getId() {
    return this.id;
  }

  public Gender getGender() {
    return gender;
  }

  public String getRelation() {
    return relation;
  }

  public int[] getAllowedRelation() {
    return allowedRelation;
  }

  public static RelationType getValue(String desc) throws CustomException {
    for (RelationType relationType : RelationType.values()) {
      if (desc.equalsIgnoreCase(relationType.getRelation())) {
        return relationType;
      }
    }
    throw new CustomException("Please give correct RelationType");
  }
}
