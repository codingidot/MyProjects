package com.exercise.go.service;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Random;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.exercise.go.mapper.Bsdmapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;



@Service
@Repository
public class BsdServiceImpl implements BsdService {
	
	@Autowired
	private Bsdmapper bsdmapper;


    //id , pw 확인
	@Override
	public HashMap<String, Object> checkIdPw(HashMap<String, Object> paramMap) {
		
		HashMap<String, Object> result = new HashMap<String, Object>();
		List<Map<String, Object>> result1= bsdmapper.checkIdPw(paramMap);
		result.put("result", result1);
		return result;
		
	}
	
	//id 확인
	@Override
	public HashMap<String, Object> checkID(HashMap<String, Object> paramMap) {
		
		HashMap<String, Object> result = new HashMap<String, Object>();
		List<Map<String, Object>> result1= bsdmapper.checkID(paramMap);
		result.put("result", result1);
		return result;
				
	}
	
	//닉네임 확인
	@Override
	public HashMap<String, Object> checkNickname(HashMap<String, Object> paramMap) {
		
		HashMap<String, Object> result = new HashMap<String, Object>();
		List<Map<String, Object>> result1= bsdmapper.checkNickname(paramMap);
		result.put("result", result1);
		return result;
				
	}

	//이메일 인증
	 @Override 
	 public HashMap<String, Object> verifyEmail(HashMap<String, Object>paramMap) {
	 
		 HashMap<String, Object> result = new HashMap<String, Object>();

		 String recipient = (String) paramMap.get("id");
	     Random random = new Random(); //랜덤 객체 생성(디폴트 시드값 : 현재시간)
	     random.setSeed(System.currentTimeMillis()); //시드값 설정을 따로 할수도 있음
	     String code = String.valueOf(random.nextInt(100));

	     // 1. 발신자의 메일 계정과 비밀번호 설정
	     final String user = "ehdgus309@gmail.com";
	     final String password = "clcxqmtwkhqrhkno";
	
	     // 2. Property에 SMTP 서버 정보 설정
	     Properties prop = new Properties();
	     prop.put("mail.smtp.host", "smtp.gmail.com");
	     prop.put("mail.smtp.port", 465);
	     prop.put("mail.smtp.auth", "true");
	     prop.put("mail.smtp.ssl.enable", "true");
	     prop.put("mail.smtp.ssl.trust", "smtp.gmail.com");
	     
	     /* 참고
	     SMTP 포트 (TLS) : 587
	     SMTP 포트 (SSL) : 465
		 */
	     
	     // 3. SMTP 서버정보와 사용자 정보를 기반으로 Session 클래스의 인스턴스 생성
	     Session session = Session.getInstance(prop, new javax.mail.Authenticator() {
	         protected PasswordAuthentication getPasswordAuthentication() {
	             return new PasswordAuthentication(user, password);
	         }
	     });
	
	     // 4. Message 클래스의 객체를 사용하여 수신자와 내용, 제목의 메시지를 작성한다.
	     // 5. Transport 클래스를 사용하여 작성한 메세지를 전달한다.
	
	     MimeMessage message = new MimeMessage(session);
	     try {
	         message.setFrom(new InternetAddress(user));
	
	         // 수신자 메일 주소
	         message.addRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
	
	         // Subject
	         message.setSubject("BSD 이메일 인증");
	
	         // Text
	         message.setText("Welcome to BSD. your code is ["+code+"]");
	
	         Transport.send(message);    // send message
	
	
	     } catch (AddressException e) {
	    	 System.out.println(e);
	    	 result.put("result", "NO"); 
	    	 return result;
	     } catch (MessagingException e) {
	    	 System.out.println(e);
	    	 result.put("result", "NO"); 
	    	 return result;
     }
	 
	 result.put("result", code); 
	 return result; 
	 }
	
	//회원가입 완료 
	@Override
	public HashMap<String, Object> insertID(HashMap<String, Object> paramMap) {
		
		HashMap<String, Object> result = new HashMap<String, Object>();
		bsdmapper.insertID(paramMap);
		result.put("result", "success");
		return result;
		
	}

	//비밀번호 변경
	@Override
	public HashMap<String, Object> updatePW(HashMap<String, Object> paramMap) {
		
		HashMap<String, Object> result = new HashMap<String, Object>();
		bsdmapper.updatePW(paramMap);
		result.put("result", "success");
		return result;
		
	}

	//영상 리스트 가져오기
	@Override
	public HashMap<String, Object> selectList(HashMap<String, Object> paramMap) {
		
		HashMap<String, Object> result = new HashMap<String, Object>();
		List<Map<String, Object>> result1= bsdmapper.selectList(paramMap);
		result.put("Vlist", result1);
		return result;
		
	}
	
	//기록 순위 가져오기
	@Override
	public HashMap<String, Object> selectRank(HashMap<String, Object> paramMap) {
		
		HashMap<String, Object> result = new HashMap<String, Object>();
		System.out.println(paramMap);
		List<Map<String, Object>> result1= bsdmapper.selectRank(paramMap);
		result.put("Rlist", result1);
		return result;
	}

	//영상 단건 가져오기
	@Override
	public HashMap<String, Object> selectDetail(HashMap<String, Object> paramMap) {
		
		HashMap<String, Object> result = new HashMap<String, Object>();
		List<Map<String, Object>> result1= bsdmapper.selectDetail(paramMap);

		result.put("Vdetail", result1);
		return result;
	}

	//업로드
	@Override
	public int insertRecord(HashMap<String, Object> params) {
		
		HashMap<String, Object> result = new HashMap<String, Object>();
		
		int contentsNewId = bsdmapper.selectContentsNewId();
		bsdmapper.insertRecord(params);
		
		return contentsNewId;
	}

	//댓글 가져오기
	@Override
	public HashMap<String, Object> selectComment(HashMap<String, Object> paramMap) {
		System.out.println(paramMap);
		HashMap<String, Object> result = new HashMap<String, Object>();
		List<Map<String, Object>> result1= bsdmapper.selectComment(paramMap);
		result.put("Clist", result1);
		return result;
	}

	//조회수 +1
	@Override
	public HashMap<String, Object> updateCnt(HashMap<String, Object> paramMap) {

		HashMap<String, Object> result = new HashMap<String, Object>();
		bsdmapper.updateCnt(paramMap);
		result.put("result", "success");
		return result;
	}

	//댓글 등록
	@Override
	public HashMap<String, Object> insertComment(HashMap<String, Object> paramMap) throws JsonMappingException, JsonProcessingException {
	    
		HashMap<String, Object> result = new HashMap<String, Object>();
		bsdmapper.insertComment(paramMap);
		result.put("result", "success");
		return result;
		
	}

	//좋아요 +1
	@Override
	public HashMap<String, Object> updateLike(HashMap<String, Object> paramMap) {
		int like = 0;
		HashMap<String, Object> result = new HashMap<String, Object>();
		like = bsdmapper.selectLikeCount(paramMap);
		paramMap.put("like", like);
		bsdmapper.insertLike(paramMap); //좋아요 기록 insert
		bsdmapper.updateLike(paramMap);
		
		result.put("result", "success");
		return result;
		
	}

	//싫어요 +1
	@Override
	public HashMap<String, Object> updateDisLike(HashMap<String, Object> paramMap) {
		int dislike = 0;
		HashMap<String, Object> result = new HashMap<String, Object>();
		dislike = bsdmapper.selectDisLikeCount(paramMap);
		paramMap.put("dislike", dislike);
		bsdmapper.insertDisLike(paramMap); //싫어요 기록 insert
		bsdmapper.updateDisLike(paramMap);
		
		result.put("result", "success");
		return result;
	}

	//투표결과 가져오기
	@Override
	public HashMap<String, Object> selectVoteResult(HashMap<String, Object> paramMap) {
		
		HashMap<String, Object> result = new HashMap<String, Object>();
		List<Map<String, Object>> result1= bsdmapper.selectVoteResult(paramMap);
		result.put("Vlist", result1);
		return result;
	}

	//인정
	@Override
	public HashMap<String, Object> insertAgree(HashMap<String, Object> paramMap) {
		
		HashMap<String, Object> result = new HashMap<String, Object>();
		bsdmapper.insertAgree(paramMap);
		result.put("result", "success");
		return result;
	}

	//해당 콘텐츠에 대한 투표를 했는지 확인
	@Override
	public HashMap<String, Object> checkAgree(HashMap<String, Object> paramMap) {
		
		HashMap<String, Object> result = new HashMap<String, Object>();
		List<Map<String, Object>> result1= bsdmapper.checkAgree(paramMap);
		result.put("Vlist", result1);
		return result;
	}

	//투표완료 처리
	@Override
	public HashMap<String, Object> finishVote(HashMap<String, Object> paramMap) {
		HashMap<String, Object> result = new HashMap<String, Object>();
		bsdmapper.finishVote(paramMap);
		result.put("result", "success");
		return result;
	}

	//영상 리스트 가져오기
	@Override
	public HashMap<String, Object> selectMyList(HashMap<String, Object> paramMap) {
		HashMap<String, Object> result = new HashMap<String, Object>();
		List<Map<String, Object>> result1= bsdmapper.selectMyList(paramMap);
		result.put("Vlist", result1);
		return result;
	}

	//게시물 삭제
	@Override
	public HashMap<String, Object> deleteContents(HashMap<String, Object> paramMap) {
		
		HashMap<String, Object> result = new HashMap<String, Object>();
		bsdmapper.deleteContents(paramMap);
		result.put("result", "success");
		return result;
	}
	
	//회원 삭제(관리자)
	@Override
	public HashMap<String, Object> deleteMember(HashMap<String, Object> paramMap) {
		
		HashMap<String, Object> result = new HashMap<String, Object>();
		bsdmapper.deleteMember(paramMap);
		result.put("result", "success");
		return result;
	}
	
	//게시물 수정
	@Override
	public void updateRecord(HashMap<String, Object> params) {

		HashMap<String, Object> result = new HashMap<String, Object>();
		bsdmapper.updateRecord(params);
		result.put("result", "success");
		
	}

	//영상 리스트 가져오기(관리자)
	@Override
	public HashMap<String, Object> selectAdminVList(HashMap<String, Object> paramMap) {
		
		HashMap<String, Object> result = new HashMap<String, Object>();
		List<Map<String, Object>> result1= bsdmapper.selectAdminVList(paramMap);
		result.put("Vlist", result1);
		return result;
	}

	//회원 목록 가져오기(관리자)
	@Override
	public HashMap<String, Object> selectAdminMList(HashMap<String, Object> paramMap) {
		System.out.println("hahahahaha   " + paramMap);
		HashMap<String, Object> result = new HashMap<String, Object>();
		List<Map<String, Object>> result1= bsdmapper.selectAdminMList(paramMap);
		result.put("Mlist", result1);
		return result;
	}



	
	 
	
}
