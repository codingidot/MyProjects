package com.exercise.go.mapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface Bsdmapper{ 
	//아이디, 비번 확인
	 List<Map<String, Object>> checkIdPw(HashMap<String, Object> paramMap);

	//아이디 확인 
	List<Map<String, Object>> checkID(HashMap<String, Object> paramMap);
	
	//닉네임 확인
	List<Map<String, Object>> checkNickname(HashMap<String, Object> paramMap);

	//회원가입 완료 
	void insertID(HashMap<String, Object> paramMap);

	//비밀번호 변경
	void updatePW(HashMap<String, Object> paramMap);

	//영상 리스트 가져오기
	List<Map<String, Object>> selectList(HashMap<String, Object> paramMap);

	//기록 순위 가져오기
	List<Map<String, Object>> selectRank(HashMap<String, Object> paramMap);
	
	//영상 단건 가져오기
	List<Map<String, Object>> selectDetail(HashMap<String, Object> paramMap);

	//업로드
	void insertRecord(HashMap<String, Object> params);

	//생성될 컨턴츠 아이디 가져오기
	int selectContentsNewId();

	//댓글 가져오기
	List<Map<String, Object>> selectComment(HashMap<String, Object> paramMap);

	//조회수 +1
	void updateCnt(HashMap<String, Object> paramMap);

	//댓글 등록
	void insertComment(HashMap<String, Object> paramMap);
	
	//좋아요 +1
	void updateLike(HashMap<String, Object> paramMap);
	
	//좋아요 기록 insert
	void insertLike(HashMap<String, Object> paramMap);
	
	//싫어요 기록 insert
	void insertDisLike(HashMap<String, Object> paramMap);

	//싫어요 +1
	void updateDisLike(HashMap<String, Object> paramMap);

	//투표결과 가져오기
	List<Map<String, Object>> selectVoteResult(HashMap<String, Object> paramMap);

	//인정
	void insertAgree(HashMap<String, Object> paramMap);

	//해당 콘텐츠에 대한 투표를 했는지 확인
	List<Map<String, Object>> checkAgree(HashMap<String, Object> paramMap);

	//현재 좋아요 개수 조회
	int selectLikeCount(HashMap<String, Object> paramMap);

	//현재 싫어요 개수 조회
	int selectDisLikeCount(HashMap<String, Object> paramMap);

	//투표완료 처리
	void finishVote(HashMap<String, Object> paramMap);

	//영상 리스트 가져오기
	List<Map<String, Object>> selectMyList(HashMap<String, Object> paramMap);

	//게시물 삭제
	void deleteContents(HashMap<String, Object> paramMap);

	//게시물 수정
	void updateRecord(HashMap<String, Object> params);

	//영상 리스트 가져오기(관리자)
	List<Map<String, Object>> selectAdminVList(HashMap<String, Object> paramMap);

	//회원 목록 가져오기(관리자)
	List<Map<String, Object>> selectAdminMList(HashMap<String, Object> paramMap);

	//회원 삭제(관리자)
	void deleteMember(HashMap<String, Object> paramMap);





	

	
}
