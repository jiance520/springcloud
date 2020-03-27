package com.oracle_jdbc;

import java.util.HashMap;
import java.util.List;

/**
 * @version ʱ�䣺2018��5��12�� ����9:07:35
 *�ӿ�
 */
public interface ITableDao {
	//������
	List<HashMap> getAll();
	//�鵥��
	HashMap<String,Object> getOne(String sql, Object... params);
	//����
	int insert(String sql, Object... params);
	//�޸�
	int update(String sql, Object... params);
	//ɾ��
	int delete(String sql, Object... params);
}
