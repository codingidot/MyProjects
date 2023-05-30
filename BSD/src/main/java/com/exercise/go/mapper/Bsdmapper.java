package com.exercise.go.mapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface Bsdmapper{ 
	//���̵�, ��� Ȯ��
	 List<Map<String, Object>> checkIdPw(HashMap<String, Object> paramMap);

	//���̵� Ȯ�� 
	List<Map<String, Object>> checkID(HashMap<String, Object> paramMap);
	
	//�г��� Ȯ��
	List<Map<String, Object>> checkNickname(HashMap<String, Object> paramMap);

	//ȸ������ �Ϸ� 
	void insertID(HashMap<String, Object> paramMap);

	//��й�ȣ ����
	void updatePW(HashMap<String, Object> paramMap);

	//���� ����Ʈ ��������
	List<Map<String, Object>> selectList(HashMap<String, Object> paramMap);

	//��� ���� ��������
	List<Map<String, Object>> selectRank(HashMap<String, Object> paramMap);
	
	//���� �ܰ� ��������
	List<Map<String, Object>> selectDetail(HashMap<String, Object> paramMap);

	//���ε�
	void insertRecord(HashMap<String, Object> params);

	//������ ������ ���̵� ��������
	int selectContentsNewId();

	//��� ��������
	List<Map<String, Object>> selectComment(HashMap<String, Object> paramMap);

	//��ȸ�� +1
	void updateCnt(HashMap<String, Object> paramMap);

	//��� ���
	void insertComment(HashMap<String, Object> paramMap);
	
	//���ƿ� +1
	void updateLike(HashMap<String, Object> paramMap);
	
	//���ƿ� ��� insert
	void insertLike(HashMap<String, Object> paramMap);
	
	//�Ⱦ�� ��� insert
	void insertDisLike(HashMap<String, Object> paramMap);

	//�Ⱦ�� +1
	void updateDisLike(HashMap<String, Object> paramMap);

	//��ǥ��� ��������
	List<Map<String, Object>> selectVoteResult(HashMap<String, Object> paramMap);

	//����
	void insertAgree(HashMap<String, Object> paramMap);

	//�ش� �������� ���� ��ǥ�� �ߴ��� Ȯ��
	List<Map<String, Object>> checkAgree(HashMap<String, Object> paramMap);

	//���� ���ƿ� ���� ��ȸ
	int selectLikeCount(HashMap<String, Object> paramMap);

	//���� �Ⱦ�� ���� ��ȸ
	int selectDisLikeCount(HashMap<String, Object> paramMap);

	//��ǥ�Ϸ� ó��
	void finishVote(HashMap<String, Object> paramMap);

	//���� ����Ʈ ��������
	List<Map<String, Object>> selectMyList(HashMap<String, Object> paramMap);

	//�Խù� ����
	void deleteContents(HashMap<String, Object> paramMap);

	//�Խù� ����
	void updateRecord(HashMap<String, Object> params);

	//���� ����Ʈ ��������(������)
	List<Map<String, Object>> selectAdminVList(HashMap<String, Object> paramMap);

	//ȸ�� ��� ��������(������)
	List<Map<String, Object>> selectAdminMList(HashMap<String, Object> paramMap);

	//ȸ�� ����(������)
	void deleteMember(HashMap<String, Object> paramMap);





	

	
}
