package com.karan.family.service;

import com.karan.family.dao.MemberDao;
import com.karan.family.domain.Member;
import com.karan.family.domain.MemberRelation;
import com.karan.family.exception.CustomException;
import com.karan.family.model.Input;
import org.h2.util.StringUtils;
import java.sql.SQLException;
import java.util.List;

public class MemberServiceImpl implements MemberService {

  MemberDao memberDao;

  public MemberDao getMemberDao() {
    return new MemberDao();
  }

  public String getMemberNameAsPerRelationForGivenName(Input input) {
    memberDao = getMemberDao();
    List<String> memberList = memberDao.getMemberNameAsPerRelationForGivenName(input);
    StringBuffer sb = new StringBuffer();
    for (String name : memberList) {
      if (StringUtils.isNullOrEmpty(sb.toString())) {
        sb.append(name);
      } else {
        sb.append(",");
        sb.append(name);
      }

    }
    if (StringUtils.isNullOrEmpty(sb.toString())) {
      return "No relatives found given relation '" + input.getSecondParam() + "' for '" + input.getFirstParam() + "' .";
    }
    String finalStr = input.getSecondParam() + "=" + sb.toString();
    return finalStr;
  }

  @Override
  public String insertMember(List<Member> member, int relationId) throws CustomException {
    memberDao = new MemberDao();
    boolean addedMember = false;
    try {
      addedMember = memberDao.saveMember(member.get(1));
    } catch (Exception e) {
      e.printStackTrace();
    }
    if (addedMember) {
      prepareMemberRelation(member, relationId);
      return "Welcome to the family," + member.get(1).getMemberName() + "!";
    }
    throw new CustomException("Member with this name already exist!");
  }

  @Override
  public void prepareMemberRelation(List<Member> memberLst, int relationId) {
    memberDao = new MemberDao();
    MemberRelation memberRelation = new MemberRelation();
    int memberId = memberDao.getMemberIDByName(memberLst.get(0).getMemberName());
    int userId = memberDao.getMemberIDByName(memberLst.get(1).getMemberName());

    if (memberId > 0) {
      memberRelation.setMemberId(memberId);
      memberRelation.setUserId(userId);
      memberRelation.setRelationId(relationId);

      try {
        memberDao.saveMemberRelation(memberRelation);
      } catch (SQLException e) {
        System.out.println("Error saving data to MemberRelation");
      }
    }

  }

}
