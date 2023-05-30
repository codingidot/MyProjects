package com.exercise.go.controller;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.exercise.go.service.BsdService;

import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/bsd")
public class BsdController {

	
	@Autowired
	private BsdService bsdService;


	
	//id , pw 확인
	@RequestMapping("/check")    
	@ResponseBody
	public HashMap<String, Object> checkIdPw( @RequestParam HashMap<String,Object> paramMap) throws Exception{
		
		HashMap<String, Object> result = new HashMap<String, Object>(); 
		result = bsdService.checkIdPw(paramMap);
		
		return result;
	
	}
	
	//id 확인
	@RequestMapping("/checkID")    
	@ResponseBody
	public HashMap<String, Object> checkID(@RequestParam HashMap<String,Object> paramMap) throws Exception{

		HashMap<String, Object> result = new HashMap<String, Object>(); 
		result = bsdService.checkID(paramMap);
		
		return result;
	
	}
	
	//닉네임 확인
	@RequestMapping("/checkNickname")    
	@ResponseBody
	public HashMap<String, Object> checkNickname(@RequestParam HashMap<String,Object> paramMap) throws Exception{

		HashMap<String, Object> result = new HashMap<String, Object>(); 
		result = bsdService.checkNickname(paramMap);
		
		return result;
	
	}
	
	//이메일 인증
	@RequestMapping("/verify")    
	@ResponseBody
	public HashMap<String, Object> verifyEmail(@RequestParam HashMap<String,Object> paramMap) throws Exception{

		HashMap<String, Object> result = new HashMap<String, Object>(); 
		result = bsdService.verifyEmail(paramMap);
		
		return result;
	
	}
	
	//회원가입 완료
	@RequestMapping("/insertID")    
	@ResponseBody
	public HashMap<String, Object> insertID(@RequestParam HashMap<String,Object> paramMap) throws Exception{

		HashMap<String, Object> result = new HashMap<String, Object>(); 
		result = bsdService.insertID(paramMap);
		
		return result;
	
	}
	
	//비밀번호 변경
	@RequestMapping("/updatePW")    
	@ResponseBody
	public HashMap<String, Object> updatePW(@RequestParam HashMap<String,Object> paramMap) throws Exception{

		HashMap<String, Object> result = new HashMap<String, Object>(); 
		result = bsdService.updatePW(paramMap);
		
		return result;
	
	}
	
	//영상 리스트 가져오기
	@RequestMapping("/selectList")    
	@ResponseBody
	public HashMap<String, Object> selectList(@RequestParam HashMap<String,Object> paramMap) throws Exception{

		HashMap<String, Object> result = new HashMap<String, Object>(); 
		result = bsdService.selectList(paramMap);
		
		return result;
	
	}
	
	
	
	//영상 리스트 가져오기(관리자)
	@RequestMapping("/selectAdminVList")    
	@ResponseBody
	public HashMap<String, Object> selectAdminVList(@RequestParam HashMap<String,Object> paramMap) throws Exception{
		System.out.println("good for you");
		HashMap<String, Object> result = new HashMap<String, Object>(); 
		result = bsdService.selectAdminVList(paramMap);
		
		return result;
	
	}

	//기록 순위 가져오기
	@RequestMapping("/selectRank")    
	@ResponseBody
	public HashMap<String, Object> selectRank(@RequestParam HashMap<String,Object> paramMap) throws Exception{

		HashMap<String, Object> result = new HashMap<String, Object>(); 
		result = bsdService.selectRank(paramMap);
		
		return result;
	
	}	
	
	//영상 단건 가져오기
	@RequestMapping("/selectDetail")    
	@ResponseBody
	public HashMap<String, Object> selectDetail(@RequestParam HashMap<String,Object> paramMap) throws Exception{
	
		HashMap<String, Object> result = new HashMap<String, Object>(); 
		result = bsdService.selectDetail(paramMap);
		
		return result;
	
	}	
	
	//업로드
	@PostMapping("/insertRecord")    
	@ResponseBody
	public HashMap<String, Object> insertRecord(@RequestParam MultipartFile[] uploadFile,  @RequestPart(value = "params")HashMap<String,Object> params) throws Exception{
		String savePath = "C:\\workSpace\\BSD\\src\\main\\resources\\static\\media";
		int contentsNum = 0;
		String fileName = (String) params.get("fileName");

		// double로 자료형 변환
		double weight = Double.parseDouble((String)params.get("weight"));
		params.put("weight", weight);
		
		String extension = fileName.substring(fileName.lastIndexOf("."), fileName.length());

		UUID uuid = UUID.randomUUID();
		String newFileName = uuid.toString() + extension;
		
		params.put("fileName", newFileName);
		
		
		for(MultipartFile multipartFile : uploadFile) {
			fileName = (String) params.get("fileName");
			File savefile = new File(savePath, newFileName);
			
			multipartFile.transferTo(savefile);
		}
		
		HashMap<String, Object> result = new HashMap<String, Object>(); 
		contentsNum = bsdService.insertRecord(params);
		result.put("result", contentsNum);
		return result;
	
	}	
	
	//게시물 수정
	@PostMapping("/updateRecord")    
	@ResponseBody
	public HashMap<String, Object> updateRecord(@RequestParam MultipartFile[] uploadFile,  @RequestPart(value = "params")HashMap<String,Object> params) throws Exception{

		String savePath = "C:\\workSpace\\BSD\\src\\main\\resources\\static\\media";
		String fileName = (String) params.get("fileName");
		
		// double로 자료형 변환
		double weight = Double.parseDouble((String)params.get("weight"));
		params.put("weight", weight);
		
		//확장자 추출
		String extension = fileName.substring(fileName.lastIndexOf("."), fileName.length());

		//새로운 파일 이름 = 랜덤 파일이름 + 확장자
		UUID uuid = UUID.randomUUID();
		String newFileName = uuid.toString() + extension;
		params.put("fileName", newFileName);
		
		//기존 파일 삭제
		String deleteFileName =(String) params.get("delFileName");
		String deletePath = "C:\\workSpace\\BSD\\src\\main\\resources\\static\\media\\" + deleteFileName;

		File deleteFile = new File(deletePath);
		deleteFile.delete();
		
		
		//파일 서버에 저장
		for(MultipartFile multipartFile : uploadFile) {
			fileName = (String) params.get("fileName");
			File savefile = new File(savePath, newFileName);
			
			multipartFile.transferTo(savefile);
		}
		
		HashMap<String, Object> result = new HashMap<String, Object>(); 
		bsdService.updateRecord(params);
		result.put("result", "success");
		return result;
	
	}
	
	//댓글 가져오기
	@RequestMapping("/selectComment")    
	@ResponseBody
	public HashMap<String, Object> selectComment(@RequestParam HashMap<String,Object> paramMap, HttpServletRequest request) throws Exception{

		HashMap<String, Object> result = new HashMap<String, Object>(); 
		result = bsdService.selectComment(paramMap);
		
		return result;
	
	}
	
	//조회수 +1
	@RequestMapping("/updateCnt")    
	@ResponseBody
	public HashMap<String, Object> updateCnt(@RequestParam HashMap<String,Object> paramMap) throws Exception{
		
		HashMap<String, Object> result = new HashMap<String, Object>(); 
		result = bsdService.updateCnt(paramMap);
		
		return result;
	
	}
	
	//댓글 등록
	@PostMapping("/insertComment")    
	@ResponseBody
	public HashMap<String, Object> insertComment( @RequestBody  HashMap<String,Object> params, HttpServletRequest request) throws Exception{

		HashMap<String, Object> result = new HashMap<String, Object>(); 
		result = bsdService.insertComment(params);
		
		return result;
	
	}
	
	//좋아요 +1
	@RequestMapping("/updateLike")    
	@ResponseBody
	public HashMap<String, Object> updateLike(@RequestParam HashMap<String,Object> paramMap) throws Exception{
		
		HashMap<String, Object> result = new HashMap<String, Object>(); 
		result = bsdService.updateLike(paramMap);
		
		return result;
	
	}
	
	//싫어요 +1
	@RequestMapping("/updateDisLike")    
	@ResponseBody
	public HashMap<String, Object> updateDisLike(@RequestParam HashMap<String,Object> paramMap) throws Exception{
		
		HashMap<String, Object> result = new HashMap<String, Object>(); 
		result = bsdService.updateDisLike(paramMap);
		
		return result;
	
	}
	
	//투표결과 가져오기
	@RequestMapping("/selectVoteResult")    
	@ResponseBody
	public HashMap<String, Object> selectVoteResult(@RequestParam HashMap<String,Object> paramMap) throws Exception{

		HashMap<String, Object> result = new HashMap<String, Object>(); 
		result = bsdService.selectVoteResult(paramMap);
		
		return result;
	
	}	
	
	//인정 
	@PostMapping("/insertAgree")    
	@ResponseBody
	public HashMap<String, Object> insertAgree(@RequestBody HashMap<String,Object> paramMap) throws Exception{

		HashMap<String, Object> result = new HashMap<String, Object>(); 
		result = bsdService.insertAgree(paramMap);
		
		return null;
	
	}
	
	//해당 콘텐츠에 대한 투표를 했는지 확인
	@RequestMapping("/checkAgree")    
	@ResponseBody
	public HashMap<String, Object> checkAgree(@RequestParam HashMap<String,Object> paramMap) throws Exception{

		HashMap<String, Object> result = new HashMap<String, Object>(); 
		result = bsdService.checkAgree(paramMap);
		
		return result;
	
	}		
	
	//투표완료 처리
	@PostMapping("/finishVote")    
	@ResponseBody
	public HashMap<String, Object> finishVote(@RequestBody HashMap<String,Object> paramMap) throws Exception{

		HashMap<String, Object> result = new HashMap<String, Object>(); 
		result = bsdService.finishVote(paramMap);
		
		return null;
	
	}
	
	//영상 리스트 가져오기
	@RequestMapping("/selectMyList")    
	@ResponseBody
	public HashMap<String, Object> selectMyList(@RequestParam HashMap<String,Object> paramMap) throws Exception{

		HashMap<String, Object> result = new HashMap<String, Object>(); 
		result = bsdService.selectMyList(paramMap);
		
		return result;
	
	}	
	
	//게시물 삭제
	@PostMapping("/deleteContents")    
	@ResponseBody
	public HashMap<String, Object> deleteContents(@RequestBody HashMap<String,Object> paramMap) throws Exception{

		HashMap<String, Object> result = new HashMap<String, Object>(); 
		result = bsdService.deleteContents(paramMap);
		
		return null;
	
	}
	
	//회원 삭제(관리자)
	@PostMapping("/deleteMember")    
	@ResponseBody
	public HashMap<String, Object> deleteMember(@RequestBody HashMap<String,Object> paramMap) throws Exception{

		HashMap<String, Object> result = new HashMap<String, Object>(); 
		result = bsdService.deleteMember(paramMap);
		
		return null;
	
	}
	
	//파일 삭제
	@PostMapping("/deleteFile")    
	@ResponseBody
	public HashMap<String, Object> deleteFile(@RequestBody HashMap<String,Object> paramMap) throws Exception{

		String savePath = "C:\\workSpace\\BSD\\src\\main\\resources\\static\\media\\" + paramMap.get("fileName");
		File savefile = new File(savePath);
		savefile.delete();
		
		return null;
	
	}
	
	//회원 목록 가져오기(관리자)
	@RequestMapping("/selectAdminMList")    
	@ResponseBody
	public HashMap<String, Object> selectAdminMList(@RequestParam HashMap<String,Object> paramMap) throws Exception{

		HashMap<String, Object> result = new HashMap<String, Object>(); 
		result = bsdService.selectAdminMList(paramMap);
		
		return result;
	
	}		
	
} 
