package com.notice.my;
import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface NoticeImpl {
	public void notice(HttpServletRequest request, HttpServletResponse response) throws Exception;
}
