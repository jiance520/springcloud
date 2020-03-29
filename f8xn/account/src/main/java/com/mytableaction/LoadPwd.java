package com.mytableaction;

import com.oracle_jdbc.JdbcUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.logging.Logger;

/**
 * @version ʱ�䣺2018��5��18�� ����1:12:31
 *
 */
public class LoadPwd extends HttpServlet {
	private static Logger logger = Logger.getLogger(JdbcUtil.class.getName());

	/**
	 * Constructor of the object.
	 */
	public LoadPwd() {
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
	    HashMap<String,Object> map = null;
	    String cardId = request.getParameter("cardId");
	    String password = request.getParameter("password");
	    	    
	    map = JdbcUtil.queryOne("select * from carddata where cardid=? and password=?", cardId,password);
	    if(map!=null){
			logger.info("��½�ɹ�");
			HttpSession session=request.getSession();//-------------
			session.setAttribute("cardId",cardId);
	    	response.sendRedirect("password.jsp");
	    }
	    else
	    {
			logger.info("��½ʧ��");
	    	response.sendRedirect("load.jsp?flagid=3");
	    }
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
