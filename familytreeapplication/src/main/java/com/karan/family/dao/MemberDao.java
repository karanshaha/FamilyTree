package com.karan.family.dao;

import com.karan.family.domain.Member;
import com.karan.family.domain.MemberRelation;
import com.karan.family.model.Input;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MemberDao {

  Connection conn = null;

  private Connection getConnection() {
    return JDBConfiguration.getJDBCConnection();
  }

  public List<String> getMemberNameAsPerRelationForGivenName(Input input) {
    conn = getConnection();
    StringBuffer queryStr = new StringBuffer();
    List<String> name = new ArrayList<>();
    try {
      Statement stmt = conn.createStatement();
      queryStr.append("SELECT MemberName from Member WHERE MemberId IN ( ");
      queryStr.append("SELECT mr.UserId FROM MemberRelation mr ");
      queryStr.append("INNER JOIN Member mem ON mem.MemberId = mr.MemberId ");
      queryStr.append("INNER JOIN Relation r ON r.RelationId = mr.RelationId ");
      queryStr
        .append("WHERE r.RelationName = '" + input.getSecondParam().toUpperCase() + "' AND mem.MemberName =  '" + input.getFirstParam() + "'  ) ");
      ResultSet rs = stmt.executeQuery(queryStr.toString());
      while (rs.next()) {
        // Retrieve by column name
        name.add(rs.getString("MemberName"));
        // Display values
      }

    } catch (SQLException e) {
      e.printStackTrace();
    }

    return name;
  }

  public boolean saveMember(Member member) throws Exception {
    conn = getConnection();
    StringBuffer queryStr = new StringBuffer();

    try {
      Statement stmt = conn.createStatement();
      queryStr.append("INSERT INTO Member (MemberName,Gender) VALUES ('" + member.getMemberName() + "', '" + member.getGender() + "') ");
      int updated = stmt.executeUpdate(queryStr.toString());
      conn.commit();
      stmt.close();
      if (updated == 1) {
        return true;
      }
      return false;
    } catch (Exception e) {
      return false;
    } finally {
      //conn.close();
    }
  }

  public void saveMemberRelation(MemberRelation memberRelation) throws SQLException {
    conn = getConnection();
    StringBuffer queryStr = new StringBuffer();
    try {
      Statement stmt = conn.createStatement();
      queryStr.append("INSERT INTO MemberRelation (MemberId,RelationId,UserId) VALUES ");
      queryStr.append("('" + memberRelation.getMemberId() + "', '" + memberRelation.getRelationId() + "' , '" + memberRelation.getUserId() + "' ) ");

      stmt.executeUpdate(queryStr.toString());
      conn.commit();
      stmt.close();

    } catch (Exception e) {

    } finally {
      //conn.close();
    }

  }

  public int getMemberIDByName(String memberName) {
    StringBuffer queryStr = new StringBuffer();
    int id = 0;
    conn = getConnection();
    try {
      Statement stmt = conn.createStatement();
      queryStr.append("SELECT MemberId FROM Member WHERE MemberName = '" + memberName + "' ");
      ResultSet rs = stmt.executeQuery(queryStr.toString());
      while (rs.next()) {
        // Retrieve by column name
        id = rs.getInt("MemberId");
        // Display values
      }
    } catch (Exception e) {
    }
    return id;
  }

}
