package com.oracle_jdbc;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Logger;

/**
 * @version ʱ�䣺2018-5-2 ����2:20:24
 *��Ե�����ҳ�����ǵ���������ݲ���
 */
public class PwdBase extends JdbcUtil{
	private static Logger logger = Logger.getLogger(PwdBase.class.getName());
	public PwdBase() {
		super();
	}
	//	����ע��ʱ��ʧȥ����ʱ�������ݿ�������������û�е�ǰ�û����û�����ע��
	public boolean queryCard(String cardId){
		ResultSet rs = JdbcUtil.executeQueryRS("select * from carddata where cardId=?", cardId);
		boolean flag = false;
		try {
			flag=rs.next();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return flag;
	}
//	(���ظ��Ͳ�������)��ע��ɹ���ҳ����תʱ������в������ݣ������ע�ᣬ����false,ͬʱ�������ݵ�map���ǣ�
	public boolean register(String cardId,String password){
		ResultSet rs = JdbcUtil.executeQueryRS("select * from carddata where cardId=?", cardId);
		boolean flag = false;
		int num = 0;
		try {
			flag = rs.next();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		if(flag==false){
			num = JdbcUtil.executeUpdate("insert into carddata values(?,?)",cardId,password);
			logger.info("ִ��SQL�������-ע��ɹ�");
			return flag;
		}
		else{
			logger.info("û��ִ��SQL�������-ע��ʧ��"+flag);
			return flag;
		}
	}
	public boolean load(String cardId,String password){
		ResultSet rs = JdbcUtil.executeQueryRS("select * from carddata where cardId=? and password=?", cardId,password);
		boolean flag = false;
		try {
			flag = rs.next();
		} catch (SQLException e) {
			e.printStackTrace();
			e.printStackTrace();
		}//�����ѯ����Ӧ���ʺ����룬����true����½�ɹ�
		logger.info("��½��֤"+flag);
		return flag;
	}
	public void save(String cardId,String www,String userName,String pwd10,String pwd6,String note){
		int num = 0;
		ResultSet rs = JdbcUtil.executeQueryRS("select * from wwwData where www=?", www);
		try {
			if(rs.next()==false){
				JdbcUtil.executeUpdate("insert into wwwData(www,cardId) values(?,?)", www,cardId);
				logger.info("ִ���˲���"+cardId+www);
			}
			else{
				logger.info("�Ѵ��ڲ�ִ�в���"+cardId+www);
			}
			rs = JdbcUtil.executeQueryRS(" select * from userData where userName=? and www=? ",userName,www);
			if(rs.next()==false){
				JdbcUtil.executeUpdate("insert into userData(userName,www,pwd10,pwd6,note,registerdate) values(?,?,?,?,?,to_char(sysdate,'yyyy-mm-dd hh24:mi:ss'))",userName,www,pwd10,pwd6,note);
				logger.info("ִ���˲���"+userName+www+pwd10+pwd6);
			}
			else{
				num = JdbcUtil.executeUpdate(" update userData set pwd10=?,pwd6=?,note=?,registerdate=to_char(sysdate,'yyyy-mm-dd hh24:mi:ss') ",pwd10,pwd6,note);
				logger.info("�Ѵ��ڲ�ִ�в��룬ֻ�������룬���ڱ�ע,"+pwd10+pwd6);
			}
			num = JdbcUtil.executeUpdate("insert into modifyrecord(recordId,userName,www,oldpwd10,oldpwd6,oldmodifydate) values(recordId.nextval-1,?,?,?,?,to_char(sysdate,'yyyy-mm-dd hh24:mi:ss'))",userName,www,pwd10,pwd6);
			logger.info("ִ���˲����޸ļ�¼"+userName+www+pwd10+pwd6);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public List<HashMap> queryUser(String www){
		List<HashMap> list = JdbcUtil.exectueQuery("select * from userdata where www=?", www);
		logger.info("ִ��SQL����ѯ�û��ɹ�");
		return list;
	}
	public List<HashMap> queryRecord(String userName,String www){
		List<HashMap> list = JdbcUtil.exectueQuery("select * from modifyrecord where userName=? and www=?",userName,www);
		logger.info("ִ��SQL����ѯ��¼�ɹ�");
		return list;
	}
	public HashMap<String,Object> queryNote(String userName,String www){
		HashMap<String,Object> map = JdbcUtil.queryOne("select * from userData where userName=? and www=?", userName,www);
		logger.info("ִ��SQL����ѯ��ע�ɹ�");
		return map;
	}
//		public static void main(String[] args) {
//			PasswordInfo pi = new PasswordInfo();
//			pi.queryUser("www.hao123.com");
//			pi.queryRecord("jiance520","www.hao123.com");
//			pi.save("430523198311070056", "www.hao123.com", "jiance520", "DqMnfdJe5wX", "041600");
//	}
}
