package server;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import data.DTO;

public class server {
	// 게임 정보 저장할 전역 변수 준비=========================================
	static String code = "";	 										//	분류 코드 임시 저장
	static String [][] rotation = new String [10][4];			// 방 별 유저 바퀴 수 체크
	static String [] donate = new String [10];					// 기부금 방 별 저장
	static String [] roomname = new String [10];				// 방제목		
	static String [] roomturn = new String [10];				// 방 별 턴 진행 수
	static String [] roomplayturn = new String [10];			// 방 별 유저 턴 순서   ( 게임 인포 턴 순서와 일치하면 던질 차례)
	static String [][][] place = new String [10][3][32];		 	// 방번호 // 소유자, 구매가격, 통행료
	static String [][][] gameinfo = new String [10][7][4]; 	// 방번호 // 아이디, 턴순서, 게임머니, 플레이위치
	
	// 네트워크, OOS 관리 객체 준비=========================================
	static HashMap<String, Socket> userSocketMap = new HashMap<String, Socket>();											// 클라이언트의 접속 ID, 소켓정보 기록
	static HashMap<Socket, String> socketUserMap = new HashMap<Socket, String>();											// 클라이언트의 소켓정보, 접속 ID 기록
	static HashMap<String, ObjectOutputStream> userOOSMap = new HashMap <String, ObjectOutputStream>();		//	클라이언트의 접속 ID, OOS 기록
	static Socket thisClient;									//	현재 접속된 클라이언트 소켓 정보
	
	
	// TASK 객체 준비
	static DTO dto = new DTO();													//	DTO 객체 준비
	static s_T0000 T0000 = new s_T0000();				// 이상원			// 로그인 기능 										// https://github.com/Hx2DEV/marble/issues/26
	static s_T0003 T0003 = new s_T0003();				// 민지홍			// 방 목록 갱신 기능								// https://github.com/Hx2DEV/marble/issues/3
	static s_T0004 T0004 = new s_T0004();				// 조동현			// 방 생성 기능										// https://github.com/Hx2DEV/marble/issues/6
	static s_T0018 T0018 = new s_T0018(); 			// 이상원			// 회원가입 기능 									// https://github.com/Hx2DEV/marble/issues/27
	static s_T0006 T0006 = new s_T0006();				// 조동현			// 방 참가 기능										// https://github.com/Hx2DEV/marble/issues/22
	static s_T0008_1 T0008_1 = new s_T0008_1();		// 양은지			// 지역 구매 기능 추가 							// https://github.com/Hx2DEV/marble/issues/8
	static s_T0008_2 T0008_2 = new s_T0008_2();		// 양은지			// 지역 구매 기능 추가 							// https://github.com/Hx2DEV/marble/issues/8
	static s_T0009	T0009 = new s_T0009();				// 이상원, 전호형	// 게임 시작 기능									// https://github.com/Hx2DEV/marble/issues/7
	static s_T0010 T0010 = new s_T0010();				// 전호형			// 턴 분배 기능										// https://github.com/Hx2DEV/marble/issues/9
	static s_T0012 T0012 = new s_T0012();				// 양은지			// 통행료 지불 기능 추가						// https://github.com/Hx2DEV/marble/issues/19
	static s_T0013 T0013 = new s_T0013();				// 전호형			// 게임 승/패 처리 기능							// https://github.com/Hx2DEV/marble/issues/20
	static s_T0015 T0015 = new s_T0015();				// 조동현			// 주사위 굴리기 기능							// https://github.com/Hx2DEV/marble/issues/2
	static s_T0017 T0017 = new s_T0017();				// 양은지			// 통행료 지불 기능 추가						// https://github.com/Hx2DEV/marble/issues/24
	static s_T0020 T0020 = new s_T0020();				// 양은지			// 황금열쇠 기능 추가							// https://github.com/Hx2DEV/marble/issues/28
	static s_T0023 T0023 = new s_T0023();				// 조동현			// 수령처에서 적립된 금액 수령 기능 추가	// https://github.com/Hx2DEV/marble/issues/31
	static s_T0024 T0024 = new s_T0024();				// 이인호			// 무인도 기능 추가								// https://github.com/Hx2DEV/marble/issues/32
	
	
	public static void main(String[] args) {
		System.out.println("버전 220524 1620");
		
		// 배열 초기화 시작============!
		for (int i = 0; i < 10; i++) {
			roomname[i] = "null";
		}
		
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 7; j++) {
				for (int k = 0; k < 4; k++) {
					gameinfo[i][j][k] = "null";
				}
			}
		}
		// 배열 초기화 종료============!
		
		
		
		try (
			ServerSocket server = new ServerSocket()) {
			InetSocketAddress ipep = new InetSocketAddress(9999); 				// 9999 포트 준비
			server.bind(ipep);																	// 서버 인스턴스에 소켓 포트 정보 bind
			System.out.println("[서버 시작]");
	
			ExecutorService receiver = Executors.newCachedThreadPool(); 		// 클라이언트로 부터 메시지 수신 대기 스래드 풀
		
			// 클라이언트로 부터 수신 시작 ==============================================================
			while (true) {
				try {
					Socket socket = server.accept(); 										// 클라이언트로 부터 접속 대기한다. (블록킹)
					System.out.println("[연결 수락] : " + socket.getRemoteSocketAddress() + ": " + Thread.currentThread().getName());

					receiver.execute(() -> {	// 클라이언트 스래드 풀 시작
						try (
							Socket thisClient = socket;
							ObjectInputStream ois = new ObjectInputStream(thisClient.getInputStream());
							ObjectOutputStream oos = new ObjectOutputStream(thisClient.getOutputStream());
						){
							while (true) { // 받기
								dto = (DTO)ois.readObject();											// 전송된 객체 수신
								code = dto.getCode();													// 분류 코드 확인
								//코드에 따른 동작 실행====================================
								switch(code) {
								
								case "T0000" : 
									// [T0000] 로그인 기능 추가
									// https://github.com/Hx2DEV/marble/issues/26
									// 작업자 이상원
									// 작업자 전호형
									//DTO.setRoomInfo_all(roomInfo);				
									dto = T0000.s_T0000_send(dto, oos);
										if (dto.getIntnote()==1) {
											userOOSMap.put(dto.getId(), oos);				// ID를 키 값으로 OOS 저장
											socketUserMap.put(thisClient,dto.getId());		// 소켓을 키 값으로 ID 저장
											userSocketMap.forEach((key, value) -> {		// 접속자 전체 정보 출력
											System.out.println("아이디: "+ key + " / 소켓 값: " + value);});
										}
									break;
									
								
								case "T0003" :
									// [T0003] 방 목록 갱신 기능 추가
									// https://github.com/Hx2DEV/marble/issues/3
									// 작업자 민지홍
									// 수정자 전호형
									T0003.s_T0003_send(dto, oos, roomname, gameinfo);
									break;

									
								case "T0004" :
									// [T0004] 방 생성 기능 추가
									// https://github.com/Hx2DEV/marble/issues/6
									// 작업자 민지홍
									// 수정자 전호형			
									dto = T0004.s_T0004_send(dto, oos, roomname, gameinfo);		// 작업 시작
									gameinfo = gameinfo_refresh(dto.getGameinfo());					// 작업한 게임방 정보 서버에 최신화
									roomname = roomname_refresh(dto.getRoomname());				// 작업한 방이름 정보 서버에 최신화
									break;
								
									
								case "T0006" :
									// [T0006] 방 참가 기능 추가
									// https://github.com/Hx2DEV/marble/issues/22
									// 작업자 조동현
									// 수정자 전호형					
									dto = T0006.s_T0006_send(dto, userOOSMap, roomname, gameinfo);
									gameinfo = gameinfo_refresh(dto.getGameinfo());			// 작업한 게임방 정보 서버에 최신화
									break;
								
								
								case "T0008_1" :
									// [T0008] 지역 구매 기능 추가1
									// https://github.com/Hx2DEV/marble/issues/8
									// 양은지
									// 전호형
									dto = T0008_1.s_T0008_1_send(dto, oos, place);
									place = place_refresh(dto.getPlace());								// 작업한 지역 정보 서버에 최신화	
									break;
									
									
								case "T0008_2" :
									// [T0008] 지역 구매 기능 추가2
									// https://github.com/Hx2DEV/marble/issues/8
									// 양은지
									// 전호형
									dto = T0008_2.s_T0008_2_send(dto, userOOSMap, gameinfo, place);
									place = place_refresh(dto.getPlace());								// 작업한 지역 정보 서버에 최신화	
									gameinfo = gameinfo_refresh(dto.getGameinfo());				// 작업한 게임방 정보 서버에 최신화
									break;
									
								
								case "T0009" :
									// [T0009] 게임 시작 기능 추가
									// https://github.com/Hx2DEV/marble/issues/7
									// 작업자 민지홍, 전호형
									dto = T0009.s_T0009_send(dto, userOOSMap, gameinfo, place);
									if(dto.getIntnote()!=0){
										gameinfo = gameinfo_refresh(dto.getGameinfo());				// 작업한 게임방 정보 서버에 최신화
										place = place_refresh(dto.getPlace());								// 작업한 지역 정보 서버에 최신화	
									}
									break;
									
									
								case "T0010" :
									// [T0010] 턴 분배 기능 추가
									// https://github.com/Hx2DEV/marble/issues/9
									// 작업자 전호형
									dto = T0010.s_T0010_send(dto, userOOSMap, gameinfo, roomturn, roomplayturn);
									roomplayturn = roomplayturn_refresh(dto.getRoomplayturn());	// 작업한 유저 턴 정보 서버에 최신화
									roomturn = roomturn_refresh(dto.getRoomturn());					// 작업한 방 턴 정보 서버에 최신화
									break;
									
								
								case "T0012" :
									// [T0012] 통행료 지불 기능 추가
									// https://github.com/Hx2DEV/marble/issues/19
									// 작업자 양은지
									// 수정자 전호형
									dto = T0012.s_T0012_send(dto, userOOSMap, gameinfo, place);
									gameinfo = gameinfo_refresh(dto.getGameinfo());					// 작업한 게임방 정보 서버에 최신화
									break;
									
									
								case "T0013" :
									// [T0013] 게임 승/패 처리 기능
									// https://github.com/Hx2DEV/marble/issues/20
									// 작업자 전호형
									T0013.s_T0013_send(dto, userOOSMap, gameinfo, place);
									break;

								case "T0015" :
									// [T0015] 주사위 굴리기 기능 추가
									// https://github.com/Hx2DEV/marble/issues/2
									// 작업자 조동현
									// 수정자 전호형
									dto = T0015.s_T0015_send(dto, userOOSMap, gameinfo, roomplayturn);
									gameinfo = gameinfo_refresh(dto.getGameinfo());					// 작업한 게임방 정보 서버에 최신화
									roomplayturn = roomplayturn_refresh(dto.getRoomplayturn());	// 작업한 유저 턴 정보 서버에 최신화
									break;
													
									
								case "T0017" :
									// [T0017] 출발점 통과시 월급 지급 기능 추가
									// https://github.com/Hx2DEV/marble/issues/24
									// 작업자 양은지
									// 수정자 전호형
									dto = T0017.s_T0017_send(dto, userOOSMap, gameinfo, rotation);
									gameinfo = gameinfo_refresh(dto.getGameinfo());					// 작업한 게임방 정보 서버에 최신화
									rotation = rotation_refresh(dto.getRotation());						// 바퀴 수 저장
									break;
									
									
								case "T0018" :
									// [T0018] 회원가입 기능 추가
									// https://github.com/Hx2DEV/BLACKJACK/issues/20
									// 작업자 이상원
									// 작업자 전호형
									T0018.s_T0018_send(dto, oos);
									break;
									
								
								case "T0020" :
									// [T0020] 황금열쇠 기능 추가
									// https://github.com/Hx2DEV/marble/issues/28
									// 작업자 양은지
									// 수정자 전호형
									dto = T0020.s_T0020_send(dto, userOOSMap, gameinfo, donate);
									gameinfo = gameinfo_refresh(dto.getGameinfo());					// 작업한 게임방 정보 서버에 최신화
									donate = donate_refresh(dto.getDonate());							// 작업한 기부금 정보 서버에 최신화
									break;
									
									
								case "T0023" :
									// [T0023] 수령처에서 적립된 금액 수령 기능 추가
									// https://github.com/Hx2DEV/marble/issues/31
									// 작업자 조동현
									// 수정자 전호형
									dto = T0023.s_T0023_send(dto, userOOSMap, gameinfo, donate);
									break;
							
									
								case "T0024" :
									// [T0024] 무인도 기능 추가
									// https://github.com/Hx2DEV/marble/issues/32
									// 작업자 이인호
									// 수정자 전호형
									T0024.s_T0024_send(dto, oos);
									break;
								}
								//코드에 따른 동작 종료====================================
							} 
						} catch (SocketTimeoutException e){
						} catch (EOFException e){
						} catch (IOException e){
						} catch (ClassNotFoundException e) {
						} finally {																	// 접속 종료 (비정상 종료 포함)
							String tmp_id = socketUserMap.get(socket);				// 소켓 정보를 통해 유저 아이디 임시 저장
							userOOSMap.remove(tmp_id);									// 아이디를 키 값으로 OOS 삭제
							socketUserMap.remove(socket);								// 소켓을 키 값으로 아이디 삭제
							System.out.println("[접속 종료] "+tmp_id);
						}
					});
				} catch (Exception e) { }
			}		
		} catch (Exception e) { }
	}

	// String -> 배열화 메소드 준비===========================

	// roomname String -> Array[]
	private static String[] roomname_refresh(String str_roomname) { 
		String[] roomname = str_roomname.split("/");
		return roomname;
	}
	
	 // donate String -> Array[]
	private static String[] donate_refresh(String str_donate) {
		String[] donate = str_donate.split("/");
		return donate;
	}

	// roomturn String -> Array[]
	private static String[] roomturn_refresh(String str_roomturn) { 
		String[] roomturn = str_roomturn.split("/");
		return roomturn;
	}
	
	// roomplayturn String -> Array[]
	private static String[] roomplayturn_refresh(String str_roomplayturn) { 
		String[] roomplayturn = str_roomplayturn.split("/");
		return roomplayturn;
	}
	
	// rotation String -> Array[][][]
	private static String[][] rotation_refresh(String str_rotation) { 
		String[] tmp_roation = str_rotation.split("/");
		String[][] arry_rotaion = new String[10][4];
		int tmp_roation_index = 0;
		for(int i=0 ; i<10; i++) {
			for (int j = 0; j < 4; j++) {
				arry_rotaion[i][j] = tmp_roation[tmp_roation_index];
			}
		}
		return arry_rotaion;
	}
	
	// gameinfo String -> Array[][][]
	private static String[][][] gameinfo_refresh(String gameinfo) {	
		String[] tmp_gameinfo = gameinfo.split("/");
		String[][][] gi = new String[10][7][4];
		int cnt = 0;
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 7; j++) {
				for (int k = 0; k < 4; k++) {
					gi[i][j][k] = tmp_gameinfo[cnt];
					cnt ++;
				}
			}
		}
		return gi;
	}
	
	// place String -> Array[][][]
	private static String[][][] place_refresh(String place) {	
		String[] tmp_place = place.split("/");
		String[][][] pl = new String[10][3][32];
		int cnt = 0;
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 3; j++) {
				for (int k = 0; k < 32; k++) {
					pl[i][j][k] = tmp_place[cnt];
					cnt ++;
				}
			}
		}
		return pl;
	}
}