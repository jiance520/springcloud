package com.action;

import com.oracle_jdbc.JdbcUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.logging.Logger;


/**
 * @version ʱ�䣺2018��5��18�� ����1:21:55
 *
 */
public class CheckUser extends HttpServlet {
	private static Logger logger = Logger.getLogger(JdbcUtil.class.getName());

	/**
	 * Constructor of the object.
	 */
	public CheckUser() {
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
		doPost(request,response);
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
        String id1 = request.getParameter("cardId");
        map = JdbcUtil.queryOne("select * from carddata where cardid=? ", id1);
		request.setAttribute("id1", id1);
        if(map!=null){
    		logger.info("�û��Ѵ���"+id1);
        	response.sendRedirect("register.jsp?flagid=1&id1="+id1);//flagid����������󣬿���ʹ��${param.flagid}ȡֵ��д��map��ֵ
        }
        else
        {
    		logger.info("�û���ע��");
        	response.sendRedirect("register.jsp?flagid=0&id1="+id1);
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
