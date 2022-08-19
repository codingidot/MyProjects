package com.member.my;

import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface MemberImpl {

	public void member(HttpServletRequest request, HttpServletResponse response) throws Exception;

}
