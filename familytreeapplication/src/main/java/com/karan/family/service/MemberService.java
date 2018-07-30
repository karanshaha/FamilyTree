package com.karan.family.service;

import com.karan.family.domain.Member;
import com.karan.family.exception.CustomException;
import com.karan.family.model.Input;
import java.util.List;

public interface MemberService {

  /**
   * Method returns the name of the family member with relation.
   * @param input
   * @return
   */
  String getMemberNameAsPerRelationForGivenName(Input input);

  /**
   * Method is used to assign family member to given allowed relation
   * by inserting the data into MemberRelation table.
   * @param memberLst
   * @param relationId
   */
  void prepareMemberRelation(List<Member> memberLst, int relationId);

  /**
   * Method is used to add family member to in Family tree.
   * @param memberLst
   * @param relationId
   * @return
   */
  String insertMember(List<Member> memberLst, int relationId) throws CustomException;

}
