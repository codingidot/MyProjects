package com.exercise.go.service;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;




public interface BsdService {
	//id, pw Ȯ��
	HashMap<String, Object> checkIdPw(HashMap<String, Object> paramMap);
	
	//�̸��� ����
	HashMap<String, Object> verifyEmail(HashMap<String, Object> paramMap);
	
	//id Ȯ��
	HashMap<String, Object> checkID(HashMap<String, Object> paramMap);

	//�г��� Ȯ��
	HashMap<String, Object> checkNickname(HashMap<String, Object> paramMap);
	
	//ȸ������ �Ϸ�
	HashMap<String, Object> insertID(HashMap<String, Object> paramMap);

	//��й�ȣ ����
	HashMap<String, Object> updatePW(HashMap<String, Object> paramMap);

	//���� ����Ʈ ��������
	HashMap<String, Object> selectList(HashMap<String, Object> paramMap);

	//��� ���� ��������
	HashMap<String, Object> selectRank(HashMap<String, Object> paramMap);
	
	//���� �ܰ� ��������
	HashMap<String, Object> selectDetail(HashMap<String, Object> paramMap);

	//���ε�
	int insertRecord(HashMap<String, Object> params);

	//��� ��������
	HashMap<String, Object> selectComment(HashMap<String, Object> paramMap);

	//��ȸ�� +1
	HashMap<String, Object> updateCnt(HashMap<String, Object> paramMap);

	//��� ���
	HashMap<String, Object> insertComment(HashMap<String, Object> paramMap) throws JsonMappingException, JsonProcessingException;

	//���ƿ� +1
	HashMap<String, Object> updateLike(HashMap<String, Object> paramMap);

	//�Ⱦ�� +1
	HashMap<String, Object> updateDisLike(HashMap<String, Object> paramMap);

	//��ǥ��� ��������
	HashMap<String, Object> selectVoteResult(HashMap<String, Object> paramMap);

	//����
	HashMap<String, Object> insertAgree(HashMap<String, Object> paramMap);

	//�ش� �������� ���� ��ǥ�� �ߴ��� Ȯ��
	HashMap<String, Object> checkAgree(HashMap<String, Object> paramMap);

	//��ǥ�Ϸ� ó��
	HashMap<String, Object> finishVote(HashMap<String, Object> paramMap);

	//���� ����Ʈ ��������
	HashMap<String, Object> selectMyList(HashMap<String, Object> paramMap);

	//�Խù� ����
	HashMap<String, Object> deleteContents(HashMap<String, Object> paramMap);

	//�Խù� ����
	void updateRecord(HashMap<String, Object> params);

	//���� ����Ʈ ��������(������)
	HashMap<String, Object> selectAdminVList(HashMap<String, Object> paramMap);

	//ȸ�� ��� ��������(������)
	HashMap<String, Object> selectAdminMList(HashMap<String, Object> paramMap);

	//ȸ�� ����(������)
	HashMap<String, Object> deleteMember(HashMap<String, Object> paramMap);



}
