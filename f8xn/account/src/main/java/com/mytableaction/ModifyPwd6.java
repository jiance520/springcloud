package com.mytableaction;

import com.oracle_jdbc.JdbcUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Logger;

/**
 * @version ʱ�䣺2018��5��20�� ����3:24:58
 *
 */
public class ModifyPwd6 extends HttpServlet {
	private static Logger logger = Logger.getLogger(JdbcUtil.class.getName());

	/**
	 * Constructor of the object.
	 */
	public ModifyPwd6() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request,response);
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
        String www = request.getParameter("www");
        String userName = request.getParameter("userName");
        String pwd10 = request.getParameter("pwd10");
        String pwd6 = request.getParameter("pwd6");
        String registerdate = request.getParameter("registerdate");
        String note = request.getParameter("note");
        String cardImage = request.getParameter("cardImage");
        JdbcUtil.executeUpdate("update userData set pwd10=?,pwd6=?,registerdate=?,note=?,cardImage=? where userName=? and www=?", pwd10,pwd6,registerdate,note,cardImage,userName,www);
        response.sendRedirect("password.jsp");   
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
