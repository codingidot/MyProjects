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


    //id , pw Ȯ��
	@Override
	public HashMap<String, Object> checkIdPw(HashMap<String, Object> paramMap) {
		
		HashMap<String, Object> result = new HashMap<String, Object>();
		List<Map<String, Object>> result1= bsdmapper.checkIdPw(paramMap);
		result.put("result", result1);
		return result;
		
	}
	
	//id Ȯ��
	@Override
	public HashMap<String, Object> checkID(HashMap<String, Object> paramMap) {
		
		HashMap<String, Object> result = new HashMap<String, Object>();
		List<Map<String, Object>> result1= bsdmapper.checkID(paramMap);
		result.put("result", result1);
		return result;
				
	}
	
	//�г��� Ȯ��
	@Override
	public HashMap<String, Object> checkNickname(HashMap<String, Object> paramMap) {
		
		HashMap<String, Object> result = new HashMap<String, Object>();
		List<Map<String, Object>> result1= bsdmapper.checkNickname(paramMap);
		result.put("result", result1);
		return result;
				
	}

	//�̸��� ����
	 @Override 
	 public HashMap<String, Object> verifyEmail(HashMap<String, Object>paramMap) {
	 
		 HashMap<String, Object> result = new HashMap<String, Object>();

		 String recipient = (String) paramMap.get("id");
	     Random random = new Random(); //���� ��ü ����(����Ʈ �õ尪 : ����ð�)
	     random.setSeed(System.currentTimeMillis()); //�õ尪 ������ ���� �Ҽ��� ����
	     String code = String.valueOf(random.nextInt(100));

	     // 1. �߽����� ���� ������ ��й�ȣ ����
	     final String user = "ehdgus309@gmail.com";
	     final String password = "clcxqmtwkhqrhkno";
	
	     // 2. Property�� SMTP ���� ���� ����
	     Properties prop = new Properties();
	     prop.put("mail.smtp.host", "smtp.gmail.com");
	     prop.put("mail.smtp.port", 465);
	     prop.put("mail.smtp.auth", "true");
	     prop.put("mail.smtp.ssl.enable", "true");
	     prop.put("mail.smtp.ssl.trust", "smtp.gmail.com");
	     
	     /* ����
	     SMTP ��Ʈ (TLS) : 587
	     SMTP ��Ʈ (SSL) : 465
		 */
	     
	     // 3. SMTP ���������� ����� ������ ������� Session Ŭ������ �ν��Ͻ� ����
	     Session session = Session.getInstance(prop, new javax.mail.Authenticator() {
	         protected PasswordAuthentication getPasswordAuthentication() {
	             return new PasswordAuthentication(user, password);
	         }
	     });
	
	     // 4. Message Ŭ������ ��ü�� ����Ͽ� �����ڿ� ����, ������ �޽����� �ۼ��Ѵ�.
	     // 5. Transport Ŭ������ ����Ͽ� �ۼ��� �޼����� �����Ѵ�.
	
	     MimeMessage message = new MimeMessage(session);
	     try {
	         message.setFrom(new InternetAddress(user));
	
	         // ������ ���� �ּ�
	         message.addRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
	
	         // Subject
	         message.setSubject("BSD �̸��� ����");
	
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
	
	//ȸ������ �Ϸ� 
	@Override
	public HashMap<String, Object> insertID(HashMap<String, Object> paramMap) {
		
		HashMap<String, Object> result = new HashMap<String, Object>();
		bsdmapper.insertID(paramMap);
		result.put("result", "success");
		return result;
		
	}

	//��й�ȣ ����
	@Override
	public HashMap<String, Object> updatePW(HashMap<String, Object> paramMap) {
		
		HashMap<String, Object> result = new HashMap<String, Object>();
		bsdmapper.updatePW(paramMap);
		result.put("result", "success");
		return result;
		
	}

	//���� ����Ʈ ��������
	@Override
	public HashMap<String, Object> selectList(HashMap<String, Object> paramMap) {
		
		HashMap<String, Object> result = new HashMap<String, Object>();
		List<Map<String, Object>> result1= bsdmapper.selectList(paramMap);
		result.put("Vlist", result1);
		return result;
		
	}
	
	//��� ���� ��������
	@Override
	public HashMap<String, Object> selectRank(HashMap<String, Object> paramMap) {
		
		HashMap<String, Object> result = new HashMap<String, Object>();
		System.out.println(paramMap);
		List<Map<String, Object>> result1= bsdmapper.selectRank(paramMap);
		result.put("Rlist", result1);
		return result;
	}

	//���� �ܰ� ��������
	@Override
	public HashMap<String, Object> selectDetail(HashMap<String, Object> paramMap) {
		
		HashMap<String, Object> result = new HashMap<String, Object>();
		List<Map<String, Object>> result1= bsdmapper.selectDetail(paramMap);

		result.put("Vdetail", result1);
		return result;
	}

	//���ε�
	@Override
	public int insertRecord(HashMap<String, Object> params) {
		
		HashMap<String, Object> result = new HashMap<String, Object>();
		
		int contentsNewId = bsdmapper.selectContentsNewId();
		bsdmapper.insertRecord(params);
		
		return contentsNewId;
	}

	//��� ��������
	@Override
	public HashMap<String, Object> selectComment(HashMap<String, Object> paramMap) {
		System.out.println(paramMap);
		HashMap<String, Object> result = new HashMap<String, Object>();
		List<Map<String, Object>> result1= bsdmapper.selectComment(paramMap);
		result.put("Clist", result1);
		return result;
	}

	//��ȸ�� +1
	@Override
	public HashMap<String, Object> updateCnt(HashMap<String, Object> paramMap) {

		HashMap<String, Object> result = new HashMap<String, Object>();
		bsdmapper.updateCnt(paramMap);
		result.put("result", "success");
		return result;
	}

	//��� ���
	@Override
	public HashMap<String, Object> insertComment(HashMap<String, Object> paramMap) throws JsonMappingException, JsonProcessingException {
	    
		HashMap<String, Object> result = new HashMap<String, Object>();
		bsdmapper.insertComment(paramMap);
		result.put("result", "success");
		return result;
		
	}

	//���ƿ� +1
	@Override
	public HashMap<String, Object> updateLike(HashMap<String, Object> paramMap) {
		int like = 0;
		HashMap<String, Object> result = new HashMap<String, Object>();
		like = bsdmapper.selectLikeCount(paramMap);
		paramMap.put("like", like);
		bsdmapper.insertLike(paramMap); //���ƿ� ��� insert
		bsdmapper.updateLike(paramMap);
		
		result.put("result", "success");
		return result;
		
	}

	//�Ⱦ�� +1
	@Override
	public HashMap<String, Object> updateDisLike(HashMap<String, Object> paramMap) {
		int dislike = 0;
		HashMap<String, Object> result = new HashMap<String, Object>();
		dislike = bsdmapper.selectDisLikeCount(paramMap);
		paramMap.put("dislike", dislike);
		bsdmapper.insertDisLike(paramMap); //�Ⱦ�� ��� insert
		bsdmapper.updateDisLike(paramMap);
		
		result.put("result", "success");
		return result;
	}

	//��ǥ��� ��������
	@Override
	public HashMap<String, Object> selectVoteResult(HashMap<String, Object> paramMap) {
		
		HashMap<String, Object> result = new HashMap<String, Object>();
		List<Map<String, Object>> result1= bsdmapper.selectVoteResult(paramMap);
		result.put("Vlist", result1);
		return result;
	}

	//����
	@Override
	public HashMap<String, Object> insertAgree(HashMap<String, Object> paramMap) {
		
		HashMap<String, Object> result = new HashMap<String, Object>();
		bsdmapper.insertAgree(paramMap);
		result.put("result", "success");
		return result;
	}

	//�ش� �������� ���� ��ǥ�� �ߴ��� Ȯ��
	@Override
	public HashMap<String, Object> checkAgree(HashMap<String, Object> paramMap) {
		
		HashMap<String, Object> result = new HashMap<String, Object>();
		List<Map<String, Object>> result1= bsdmapper.checkAgree(paramMap);
		result.put("Vlist", result1);
		return result;
	}

	//��ǥ�Ϸ� ó��
	@Override
	public HashMap<String, Object> finishVote(HashMap<String, Object> paramMap) {
		HashMap<String, Object> result = new HashMap<String, Object>();
		bsdmapper.finishVote(paramMap);
		result.put("result", "success");
		return result;
	}

	//���� ����Ʈ ��������
	@Override
	public HashMap<String, Object> selectMyList(HashMap<String, Object> paramMap) {
		HashMap<String, Object> result = new HashMap<String, Object>();
		List<Map<String, Object>> result1= bsdmapper.selectMyList(paramMap);
		result.put("Vlist", result1);
		return result;
	}

	//�Խù� ����
	@Override
	public HashMap<String, Object> deleteContents(HashMap<String, Object> paramMap) {
		
		HashMap<String, Object> result = new HashMap<String, Object>();
		bsdmapper.deleteContents(paramMap);
		result.put("result", "success");
		return result;
	}
	
	//ȸ�� ����(������)
	@Override
	public HashMap<String, Object> deleteMember(HashMap<String, Object> paramMap) {
		
		HashMap<String, Object> result = new HashMap<String, Object>();
		bsdmapper.deleteMember(paramMap);
		result.put("result", "success");
		return result;
	}
	
	//�Խù� ����
	@Override
	public void updateRecord(HashMap<String, Object> params) {

		HashMap<String, Object> result = new HashMap<String, Object>();
		bsdmapper.updateRecord(params);
		result.put("result", "success");
		
	}

	//���� ����Ʈ ��������(������)
	@Override
	public HashMap<String, Object> selectAdminVList(HashMap<String, Object> paramMap) {
		
		HashMap<String, Object> result = new HashMap<String, Object>();
		List<Map<String, Object>> result1= bsdmapper.selectAdminVList(paramMap);
		result.put("Vlist", result1);
		return result;
	}

	//ȸ�� ��� ��������(������)
	@Override
	public HashMap<String, Object> selectAdminMList(HashMap<String, Object> paramMap) {
		System.out.println("hahahahaha   " + paramMap);
		HashMap<String, Object> result = new HashMap<String, Object>();
		List<Map<String, Object>> result1= bsdmapper.selectAdminMList(paramMap);
		result.put("Mlist", result1);
		return result;
	}



	
	 
	
}
