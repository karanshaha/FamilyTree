package com.karan.family.controller;

import com.karan.family.domain.Member;
import com.karan.family.domain.RelationType;
import com.karan.family.exception.CustomException;
import com.karan.family.model.Input;
import com.karan.family.service.MemberService;
import com.karan.family.service.MemberServiceImpl;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FamilyController {

  Scanner sc;
  String inputParam;
  String[] splittedStr;

  /**
   * This method is used to take the inputs from the
   * Console and depending upon the valid inputs will
   * display the relative.
   *
   * @return @{@link String} Name of the family member
   * @throws CustomException
   */
  public String displayRelation() throws CustomException {
    System.out.println("Input must be in as shown format : Person=Alex Relation=brothers");
    sc = new Scanner(System.in);
    inputParam = sc.nextLine();
    splittedStr = inputParam.split(" ");

    if (splittedStr.length != 2) {
      throw new CustomException("Invalid input please give input in specified Format"); // Throw exception
    }
    if (splittedStr[0].startsWith("Person") && splittedStr[1].startsWith("Relation")) {

      Input inputParams = new Input();

      String firstInput[] = splittedStr[0].split("=");
      inputParams.setFirstParam(firstInput[1]);

      String secondInput[] = splittedStr[1].split("=");
      inputParams.setSecondParam(secondInput[1]);

      return getNameOfMemberAsPerRelationForGivenName(inputParams);
    }
    throw new CustomException("Invalid input please give input in specified Format");
  }

  /**
   * This method is used to take the inputs from the
   * Console and depending upon the valid inputs will
   * add the relative to the family tree relative.
   *
   * @return @{@link String} Name of the member added to Family.
   * @throws CustomException
   */
  public String addRelation() throws CustomException {
    System.out.println("Input must be in as shown format : Husband=Bern Wife=Julia");

    sc = new Scanner(System.in);
    inputParam = sc.nextLine();
    splittedStr = inputParam.split(" ");

    RelationType relationTypeOne = RelationType.getValue(splittedStr[0].split("=")[0]);
    RelationType relationTypeTwo = RelationType.getValue(splittedStr[1].split("=")[0]);
    List<Member> memberLst;

    if (splittedStr.length != 2) {
      throw new CustomException("Invalid input please give input in specified Format"); // Throw exception
    }

    if (relationTypeOne != null && relationTypeTwo != null && RelationType.isAllowedRelation(relationTypeOne, relationTypeTwo)) {
      memberLst = new ArrayList<>();
      Member member1 = new Member();

      member1.setMemberName(splittedStr[0].split("=")[1]);
      member1.setGender(relationTypeOne.getGender().getDescription());
      memberLst.add(member1);

      Member member2 = new Member();
      member2.setGender(relationTypeOne.getGender().getDescription());
      member2.setMemberName(splittedStr[1].split("=")[1]);

      memberLst.add(member2);
      return insertMember(memberLst, relationTypeTwo.getId());
    }
    if(!RelationType.isAllowedRelation(relationTypeOne, relationTypeTwo)){
      throw new CustomException("Relation invalid.Please give valid relation.");
    }
    throw new CustomException("Invalid input please give input in specified Format");
  }

  public String getNameOfMemberAsPerRelationForGivenName(Input input) {
    MemberService memberService = new MemberServiceImpl();
    return memberService.getMemberNameAsPerRelationForGivenName(input);
  }

  public String insertMember(List<Member> member, int relationId) throws CustomException {
    MemberService memberService = new MemberServiceImpl();
    return memberService.insertMember(member, relationId);
  }

}
