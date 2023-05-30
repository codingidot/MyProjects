package com.exercise.go.service;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;




public interface BsdService {
	//id, pw 확인
	HashMap<String, Object> checkIdPw(HashMap<String, Object> paramMap);
	
	//이메일 인증
	HashMap<String, Object> verifyEmail(HashMap<String, Object> paramMap);
	
	//id 확인
	HashMap<String, Object> checkID(HashMap<String, Object> paramMap);

	//닉네임 확인
	HashMap<String, Object> checkNickname(HashMap<String, Object> paramMap);
	
	//회원가입 완료
	HashMap<String, Object> insertID(HashMap<String, Object> paramMap);

	//비밀번호 변경
	HashMap<String, Object> updatePW(HashMap<String, Object> paramMap);

	//영상 리스트 가져오기
	HashMap<String, Object> selectList(HashMap<String, Object> paramMap);

	//기록 순위 가져오기
	HashMap<String, Object> selectRank(HashMap<String, Object> paramMap);
	
	//영상 단건 가져오기
	HashMap<String, Object> selectDetail(HashMap<String, Object> paramMap);

	//업로드
	int insertRecord(HashMap<String, Object> params);

	//댓글 가져오기
	HashMap<String, Object> selectComment(HashMap<String, Object> paramMap);

	//조회수 +1
	HashMap<String, Object> updateCnt(HashMap<String, Object> paramMap);

	//댓글 등록
	HashMap<String, Object> insertComment(HashMap<String, Object> paramMap) throws JsonMappingException, JsonProcessingException;

	//좋아요 +1
	HashMap<String, Object> updateLike(HashMap<String, Object> paramMap);

	//싫어요 +1
	HashMap<String, Object> updateDisLike(HashMap<String, Object> paramMap);

	//투표결과 가져오기
	HashMap<String, Object> selectVoteResult(HashMap<String, Object> paramMap);

	//인정
	HashMap<String, Object> insertAgree(HashMap<String, Object> paramMap);

	//해당 콘텐츠에 대한 투표를 했는지 확인
	HashMap<String, Object> checkAgree(HashMap<String, Object> paramMap);

	//투표완료 처리
	HashMap<String, Object> finishVote(HashMap<String, Object> paramMap);

	//영상 리스트 가져오기
	HashMap<String, Object> selectMyList(HashMap<String, Object> paramMap);

	//게시물 삭제
	HashMap<String, Object> deleteContents(HashMap<String, Object> paramMap);

	//게시물 수정
	void updateRecord(HashMap<String, Object> params);

	//영상 리스트 가져오기(관리자)
	HashMap<String, Object> selectAdminVList(HashMap<String, Object> paramMap);

	//회원 목록 가져오기(관리자)
	HashMap<String, Object> selectAdminMList(HashMap<String, Object> paramMap);

	//회원 삭제(관리자)
	HashMap<String, Object> deleteMember(HashMap<String, Object> paramMap);



}
