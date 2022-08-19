package data;
import java.io.Serializable;

public class DTO implements Serializable{
	private String code="";
    private String note="";
    private int intnote=0;							// 숫자 형식 데이터 전달할 떄 사용
    private int tmp_donate=0;						// 기부금 수령 시 클라이언트에 수령금액 전달할 용도
    private int diceturn=0;							// 던질 순서 체크
    private String id="";									// 요청자 ID 처리
    private String pw="";  								// 회원가입 시 PW 임시저장
    private int goldkey = 0;								// 황금 열쇠 +- 차감 값 결과 값 
	private int before_position=0; //전위치
	private int after_position=0; //이동후 위치
	private int playerNum=0; 			//몇번째 플레이어인지 나타내기위한 변수
	private int playerturn=0; 			// 게임 방에서 누구의 턴인지 체크
    private String roomNumber=""; //방을 선택했을때 몇번방을 선택했는지 저장하기 위한 변수. 이 정보로 roomname에서 roomNumber와 같은 방번호에 참가자 ID를 추가
	private int dice1=1; //주사위 값
	private int dice2=1; //주사위 값
    
    
    // 배열 전환용
    private String donate = "";							// 방 별 기부금함
    private String roomplayturn="";					// 방 별 유저 턴 순서 (예 0~3)  ( 게임 인포 턴 순서와 일치하면 던질 차례 )
    private String roomturn="";						// 방 별 턴 진행 수 (예 1~99...)
    private String roomname="";						// 방 이름 (배열[] 처리)
    private String gameinfo="";						// 방 별 게임 진행 정보 저장용 (배열[][][] 처리)
    private String place="";								// 방 별 지역 정보 저장용 (배열[][][] 처리)
    private String rotation="";							// 방 별 바퀴 수 체크용 (배열[][] 처리)

   // get/setter ================================
	public DTO() {
	}

	public int getTmp_donate() {
		return tmp_donate;
	}

	public void setTmp_donate(int tmp_donate) {
		this.tmp_donate = tmp_donate;
	}

	public String getDonate() {
		return donate;
	}

	public void setDonate(String donate) {
		this.donate = donate;
	}

	public int getGoldkey() {
		return goldkey;
	}

	public void setGoldkey(int goldkey) {
		this.goldkey = goldkey;
	}

	public String getRotation() {
		return rotation;
	}

	public void setRotation(String rotation) {
		this.rotation = rotation;
	}

	public int getDice1() {
		return dice1;
	}

	public void setDice1(int dice1) {
		this.dice1 = dice1;
	}

	public int getDice2() {
		return dice2;
	}

	public void setDice2(int dice2) {
		this.dice2 = dice2;
	}

	public String getRoomplayturn() {
		return roomplayturn;
	}

	public void setRoomplayturn(String roomplayturn) {
		this.roomplayturn = roomplayturn;
	}

	public String getRoomturn() {
		return roomturn;
	}

	public void setRoomturn(String roomturn) {
		this.roomturn = roomturn;
	}

	public int getDiceturn() {
		return diceturn;
	}

	public void setDiceturn(int diceturn) {
		this.diceturn = diceturn;
	}

	public int getPlayerturn() {
		return playerturn;
	}

	public void setPlayerturn(int playerturn) {
		this.playerturn = playerturn;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public int getBefore_position() {
		return before_position;
	}

	public void setBefore_position(int before_position) {
		this.before_position = before_position;
	}

	public int getAfterPosition() {
		return after_position;
	}

	public void setAfter_Position(int after_position) {
		this.after_position = after_position;
	}

	public int getPlayerNum() {
		return playerNum;
	}

	public void setPlayerNum(int playerNum) {
		this.playerNum = playerNum;
	}

	public String getRoomNumber() {
		return roomNumber;
	}

	public void setRoomNumber(String roomNumber) {
		this.roomNumber = roomNumber;
	}

	public int getdice1() {
		return dice1;
	}

	public void setdice1(int dice1) {
		this.dice1 = dice1;
	}

	public int getdice2() {
		return dice2;
	}

	public void setdice2(int dice2) {
		this.dice2 = dice2;
	}

	public String getGameinfo() {
		      return gameinfo;
	}
	
	 public void setGameinfo(String s) {
		      this.gameinfo = s;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	public int getIntnote() {
		return intnote;
	}

	public void setIntnote(int intnote) {
		this.intnote = intnote;
	}

	public String getRoomname() {
		return roomname;
	}

	public void setRoomname(String roomname) {
		this.roomname = roomname;
	}

    public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
    
    public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
   
	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

}