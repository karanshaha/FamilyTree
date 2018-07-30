package family.service;

import com.karan.family.dao.MemberDao;
import com.karan.family.domain.Member;
import com.karan.family.exception.CustomException;
import com.karan.family.model.Input;
import com.karan.family.service.MemberServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;
import java.util.List;

public class MemberServiceImplTest {

  MemberDao memberDao;

  MemberServiceImpl memberService;

  @Before
  public void setUp() {
    memberDao = new MemberDao();
    memberService = new MemberServiceImpl();
  }

  @Test
  public void testGetMemberNameAsPerRelationForGivenName_negative() {
    Input input = createDummy("Bern", "wife");
    String actual = memberService.getMemberNameAsPerRelationForGivenName(input);
    Assert.assertEquals("No relatives found given relation 'wife' for 'Bern' .", actual);
  }

  @Test
  public void testGetMemberNameAsPerRelationForGivenName_positive() {
    Input input = createDummy("Alex", "Brothers");
    String actual = memberService.getMemberNameAsPerRelationForGivenName(input);
    Assert.assertEquals("Brothers=John,Joe", actual);
  }

  @Test
  public void testInsertMember_positive() throws CustomException {
    List<Member> memberLst = new ArrayList<>();
    Member mem = new Member();
    mem.setMemberName("Bern");
    mem.setGender("Male");

    Member mem2 = new Member();
    mem2.setMemberName("Julia");
    mem2.setGender("Female");

    memberLst.add(mem);
    memberLst.add(mem2);

    String actual = memberService.insertMember(memberLst, 116);
    Assert.assertEquals("Welcome to the family,Julia!", actual);
  }

  @Test
  public void testInsertMember_Negative() {
    List<Member> memberLst = new ArrayList<>();
    Member mem = new Member();
    mem.setMemberName("Bern");
    mem.setGender("Male");

    Member mem2 = new Member();
    mem2.setMemberName("Bern");
    mem2.setGender("Female");

    memberLst.add(mem);
    memberLst.add(mem2);

    String actual = null;
    try {
      memberService.insertMember(memberLst, 116);
    } catch (CustomException e) {
      Assert.assertEquals("Member with this name already exist!", e.getMessage());
    }
  }

  private Input createDummy(String fisrtParam, String secondParam) {
    Input input = new Input();
    input.setFirstParam(fisrtParam);
    input.setSecondParam(secondParam);
    return input;
  }

}
