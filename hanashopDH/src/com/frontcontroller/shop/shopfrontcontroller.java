package com.frontcontroller.shop;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.basket.my.BasketDelete;
import com.basket.my.BasketEnroll;
import com.basket.my.BasketImpl;
import com.basket.my.BasketPrint;
import com.member.my.MemberDelete;
import com.member.my.MemberGetInfo;
import com.member.my.MemberIdCheck;
import com.member.my.MemberImpl;
import com.member.my.MemberJoinus;
import com.member.my.MemberLogin;
import com.member.my.MemberPoint;
import com.member.my.MemberPrint;
import com.member.my.MemberUpdate;
import com.member.my.MemberWithdraw;
import com.notice.my.Notice;
import com.notice.my.NoticeDelete;
import com.notice.my.NoticeEnroll;
import com.notice.my.NoticeImpl;
import com.notice.my.NoticeInfo;
import com.notice.my.NoticeUpdate;
import com.orderlist.my.Orderlist;
import com.orderlist.my.OrderlistImpl;
import com.orderlist.my.OrderlistPrint;
import com.pay.my.PayAtBasket;
import com.pay.my.PayAtBasket2;
import com.pay.my.PayAtDetail;
import com.pay.my.PayImpl;
import com.product.my.ProductCategory;
import com.product.my.ProductDelete;
import com.product.my.ProductEnroll;
import com.product.my.ProductImpl;
import com.product.my.ProductPrint;
import com.product.my.ProductSearchItem;
import com.product.my.ProductShowAll;
import com.product.my.ProductShowBest;
import com.product.my.ProductShowDetail;
import com.product.my.ProductSort;
import com.product.my.ProductUpdate;
import com.product.my.ProductUpdate2;
import com.qna.my.QnaAdmin;
import com.qna.my.QnaComunity;
import com.qna.my.QnaDelivery;
import com.qna.my.QnaElse;
import com.qna.my.QnaImpl;
import com.qna.my.QnaReaction;
import com.qna.my.QnaReactionForm;
import com.qna.my.QnaRefund;
import com.qna.my.QnaView;
import com.qna.my.QnaWarehouse;
import com.qna.my.QnaWrite;
import com.qna.my.QnaWrite2;
import com.qna.my.QnacomInfo;
import com.qna.my.Qnalist;
import com.qna.my.qnaDelete;
import com.qna.my.qnaUpdate;
import com.qna.my.qnaUpdateForm;
import com.review.my.ReviewDelete;
import com.review.my.ReviewDownbutton;
import com.review.my.ReviewImpl;
import com.review.my.ReviewMyboard;
import com.review.my.ReviewMyboardInfo;
import com.review.my.ReviewMyboardUpdate;
import com.review.my.ReviewProduct;
import com.review.my.ReviewSortlike;
import com.review.my.ReviewUpbutton;
import com.review.my.ReviewUpdate;
import com.review.my.ReviewUpdateForm;
import com.review.my.ReviewWrite;
import com.wishlist.my.WishInsert;
import com.wishlist.my.WishToBasket;
import com.wishlist.my.WishlistImpl;
import com.wishlist.my.WishlistPrint;
import com.wishlist.my.wishDelete;



/**
 * Servlet implementation class shopfrontcontroller
 */

@MultipartConfig(//form enctype이 multipart이면 서블릿에서도 이렇게 설정해줘야함 
	      //location="/tmp",  //fileSizeThreshold이상의 데이터면 여기 디스크에 저장, 절대경로로 나타내야한다
	      //절대경로는 서비스를 실행하는 리눅스와 윈도우즈에 차이가 있으므로
	      //차라리 설정을 안하고 자바가 지정된 임시 디렉토리를 사용하는게 좋음
	         
	      fileSizeThreshold=1024*1024, //1킬로바이트 곱하기 1킬로바이트= 1메가 바이트
	      maxFileSize=1024*1024*50, //파일 하나사이즈의 최대 크기
	      maxRequestSize=1024*1024*50*5 //request하는 데이터의 최대크기
	      
	         )

@WebServlet("*.do")
public class shopfrontcontroller extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public shopfrontcontroller() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		request.setCharacterEncoding("UTF-8"); 
		response.setCharacterEncoding("UTF-8");
		HttpSession session=request.getSession(true);
		
		System.out.println(request.getRequestURI());  
		System.out.println(request.getContextPath());  
		
		String reqURL = request.getRequestURL().toString();
		
		int start = reqURL.lastIndexOf("/");
		
		String path = reqURL.substring(start); 
		System.out.println("path : "+ path);
		
		String str = null;
		
			
		  ProductImpl h1 = null;
	      ReviewImpl h2 = null;
	      MemberImpl h3 = null;
	      PayImpl h4=null;
	      NoticeImpl h5=null;
	      OrderlistImpl h6=null;
	      BasketImpl h7=null;
		  QnaImpl h9 = null;
		  WishlistImpl h11=null;
	    
		
		
		switch(path) {
		
		// 은지
				case "/UpdateInfo.do":
					// 마이페이지 회원정보 수정 
					
					System.out.println("회원정보 수정 서버도착");
					h3 = new MemberUpdate();
					try {
						h3.member(request, response);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					str = "getInfo.do";
					
					
					break;
					
				
				case "/Imsiupdate.do":
					//  mypage form 에서 임시로 거쳐가는 회원수정 case
					
					str="UpdateInfo.jsp";
					
				break;
					
				case "/Imsiwithdraw.do":
					// mypage form 에서 임시로 거쳐가는 회원탈퇴 case
					
					str="withdraw.jsp";
				
					break;
					
					
				case "/getInfo.do":
					// 수정된 회원정보 출력 
					
					System.out.println("회원정보 수정 출력서버 도착");
					h3 = new MemberGetInfo();
					try {
						h3.member(request, response);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					
					str = "getAllInfo.jsp"; 
					break;
				
			
				
					
					
				case "/withdraw.do":
					// 회원 탈퇴
					
					System.out.println("회원 탈퇴 서버 도착");
					h3 = new MemberWithdraw();
					try {
						h3.member(request, response);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					
					str="withdraw2.jsp";
					break;
					
				
				
					
				case "/orderlist.do":
					// 주문 내역
					
					System.out.println("주문 내역 서버 도착");
					h6 = new Orderlist();
					try {
						h6.Orderlist(request, response);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				str="orderlist.jsp";
					break;
					
					
					
					
				case "/myboard.do":
					// 내가 작성한 게시글 보기
					

					System.out.println("내 작성글 서버 도착");
					h2 = new ReviewMyboard();
					try {
						h2.review(request, response);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					
				str="myboard.jsp";
					break;
					
					
				
				case "/boardinfo.do":
					// 내가 작성한 게시글 상세보기
					
					
					System.out.println("내 작성글 서버 도착");
					h2 = new ReviewMyboardInfo();
					try {
						h2.review(request, response);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					str="boardinfo.jsp";
					break;
					
					
				case "/notice.do":
					// 공지사항 보기
					
					System.out.println("notice 서버 도착");
					h5 = new Notice();
					
					try {
						h5.notice(request, response);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				str="notice.jsp";
					break;
					
				
					
				case "/community.do":
					// 헤더에서 community 로 가기
					
					str="community.jsp";
			
					break;
				
				case "/noticeinfo.do":
					// 상세 공지사항 내용 보기
					
					System.out.println("notice 서버 도착");
					h5 = new NoticeInfo();
					
					try {
						h5.notice(request, response);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					str="noticeinfo.jsp";
					break;
					
				
				case "/Imsimypage.do" : 
					
					// 헤더에서 마이페이지 이동
					
					str="c0008.jsp";
					
					break;
					
					
					
				case "/myreviewupdate.do":
					// 마이페이지 내작성글에서 리뷰 수정하기 
					
					System.out.println("myreviewupdate 서버 도착");
					
					
					h2 = new ReviewMyboardUpdate();
					
					try {
						h2.review(request, response);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					str="myboard.do";
					break;
					
					
					
				case "/ImsiUpdatereview.do":
					// 리뷰수정창에서 전에 작성한 리뷰 내용을 불러오기 위해 리뷰출력 case로 이동하기
					
					System.out.println("ImsiUpdatereview 서버 도착");
					
					System.out.println("내 작성글 서버 도착");
					h2 = new ReviewMyboardInfo();
					try {
						h2.review(request, response);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					str="ReviewMyboardUpdate.jsp";
					break;
					
					
					
					
				case "/myreviewdelete.do":
					// 마이페이지 리뷰상세에서 리뷰삭제하기
					
					System.out.println("myreview delete 서버 도착");
					
					h2 = new ReviewDelete();
					try {
						h2.review(request, response);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					str="myboard.do";
					break;
					
					
					
				case "/joinus.do":
					System.out.println("join us 서버 도착");
					
					
					h3 = new MemberJoinus();
					try {
						h3.member(request, response);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					str="joinfinish.jsp";
					break;
					
					
				case "/idCheck.do":
					
					System.out.println("여기왔니?");
					
					str="idcheck.jsp";
					break;
					
					
				   case "/idCheckaction.do":
				         // 아이디 중복 체크하는 케이스
				         System.out.println("idcheck action 서버 도착");
				         
				         h3 = new MemberIdCheck();
				         
				         try {
				            h3.member(request, response);
				         } catch (Exception e) {
				            // TODO Auto-generated catch block
				            e.printStackTrace();
				         }
				         
				         str="joinus.jsp";
				      
				         break;
				 
					
				   case "/joinus2.do":   
					   // 헤더에서 joinus 
					   
				         System.out.println("헤더에서 joinus선택");
				         request.setAttribute("result","0");
				         str="joinus.jsp";
				         break;
					
				         
				         
				         
				   case "/Logout.do" :
					   // 헤더에서 로그아웃
					   
					   System.out.println("헤더에서 logout 선택");
					  
				
						session.invalidate();
						
						str="mainpage.do";
				         
				         break;
				         
				         
				   case "/noticedelete.do":
					   // 관리자 계정 시 공지 삭제 기능 
					   
					   System.out.println("notice delete 서버 도착");
						
						h5 = new NoticeDelete();
						try {
							h5.notice(request, response);
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
						str="notice.do";
						break;
						
				         
						
				   case "/ImsiUpdatenotice.do":
						// 리뷰수정창에서 전에 작성한 공지사항 내용을 불러오기 위해 리뷰출력 case로 이동하기
						
						System.out.println("ImsiUpdatereview 서버 도착");
						
						
						h5 = new NoticeInfo();
						try {
							h5.notice(request, response);
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
						str="NoticeUpdate.jsp";
						break;
						
							
						
				   case "/noticeupdate.do":
						// 마이페이지 내작성글에서 리뷰 수정하기 
						
						System.out.println("noticeupdate 서버 도착");
						
						
						h5 = new NoticeUpdate();
						
						try {
							h5.notice(request, response);
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
						str="notice.do";
						break;
							
						
						
				   case "/comqna.do":
					   	// 커뮤니티 Q&A 에서 목록 출력
					   
					   
					   System.out.println("comqna 서버 도착");
					   

						h9 = new QnaComunity();
						
						try {
							h9.qna(request, response);
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
					str="comqna.jsp";
						break;
								
						
						
				   case "/delivery.do":
					   	// 커뮤니티 Q&A에서 배송목록 출력
					   
					   
					   System.out.println("comqna 서버 도착");
					   

						h9 = new QnaDelivery();
						
						try {
							h9.qna(request, response);
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
					str="delivery.jsp";
						break;
								
						
						
						
				   case "/qnawrite2.do" :
						System.out.println("case문 실행");
						h9 = new QnaWrite2();
						try {
							h9.qna(request, response);
						} catch(Exception e ) {
							e.printStackTrace();
						}
						str = "comqna.do";
						break;	
						
						
						
				   case "/qnawrite3.do" :
						System.out.println("case문 실행");
						h9 = new QnaWrite2();
						try {
							h9.qna(request, response);
						} catch(Exception e ) {
							e.printStackTrace();
						}
						str = "delivery.do";
						break;	
						
						
				   case "/comqnainfo.do" :
					   
						// Q&A 상세 내용 출력
						
						
						System.out.println("comqnainfo 서버 도착");
						System.out.println("case문 실행");
						h9 = new QnacomInfo();
						try {
							h9.qna(request, response);
						} catch(Exception e ) {
							e.printStackTrace();
						}
						str = "comqnainfo.jsp";
						break;
						
						
				   case "/comdeliveryinfo.do":
					   
					   System.out.println("comdeliveryinfo 서버 도착");
						System.out.println("case문 실행");
						h9 = new QnacomInfo();
						try {
							h9.qna(request, response);
						} catch(Exception e ) {
							e.printStackTrace();
						}
						str = "comdelivery.jsp";
						break;
						
						
				   case "/comqnaanswer.do":
					   
					   // 커뮤니티 Q&A 에서 답변달기
					   
					   System.out.println("comqnaAdmin 서버 도착");
						h9 = new QnaAdmin();
						
						try {
							h9.qna(request, response);
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					
						str = "comqna.do";
						
						break;
						
					
				   case "/comqnaanswer2.do":
					   
					   // 커뮤니티 Q&A 에서 답변달기
					   
					   System.out.println("comqnaAdmin 서버 도착");
						h9 = new QnaAdmin();
						
						try {
							h9.qna(request, response);
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					
						str = "delivery.do";
						
						break;
					
						
						

				   case "/warehouse.do":
					   	// 커뮤니티 Q&A에서 배송목록 출력
					   
					   
					   System.out.println("warehouse 서버 도착");
					   

						h9 = new QnaWarehouse();
						
						try {
							h9.qna(request, response);
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
					str="warehouse.jsp";
						break;
								
						
						
						
				   case "/qnawrite4.do" :
						System.out.println("case문 실행");
						h9 = new QnaWrite2();
						try {
							h9.qna(request, response);
						} catch(Exception e ) {
							e.printStackTrace();
						}
						str = "warehouse.do";
						break;	
						
						
						
						
				   case "/warehouseinfo.do" :
					   
						// Q&A 상세 내용 출력
						
						
						System.out.println("warehouse 서버 도착");
						System.out.println("case문 실행");
						h9 = new QnacomInfo();
						try {
							h9.qna(request, response);
						} catch(Exception e ) {
							e.printStackTrace();
						}
						str = "warehouseinfo.jsp";
						break;

						
						
				   case "/comqnaanswer3.do":
					   
					   // 커뮤니티 Q&A 에서 답변달기
					   
					   System.out.println("comqnaAdmin 서버 도착");
						h9 = new QnaAdmin();
						
						try {
							h9.qna(request, response);
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					
						str = "warehouse.do";
						
						break;
				 
		
						
						
						   case "/refund.do":
							   	// 커뮤니티 Q&A에서 배송목록 출력
							   
							   
							   System.out.println("refund 서버 도착");
							   

								h9 = new QnaRefund();
								
								try {
									h9.qna(request, response);
								} catch (Exception e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
								
							str="refund.jsp";
								break;
										
								
								
								
						   case "/qnawrite5.do" :
								System.out.println("case문 실행");
								h9 = new QnaWrite2();
								try {
									h9.qna(request, response);
								} catch(Exception e ) {
									e.printStackTrace();
								}
								str = "refund.do";
								break;	
								
								
								
								
						   case "/refundinfo.do" :
							   
								// Q&A 상세 내용 출력
								
								
								System.out.println("comqnainfo 서버 도착");
								System.out.println("case문 실행");
								h9 = new QnacomInfo();
								try {
									h9.qna(request, response);
								} catch(Exception e ) {
									e.printStackTrace();
								}
								str = "refundinfo.jsp";
								break;
		
								
								
						   case "/comqnaanswer4.do":
							   
							   // 커뮤니티 Q&A 에서 답변달기
							   
							   System.out.println("comqnaAdmin 서버 도착");
								h9 = new QnaAdmin();
								
								try {
									h9.qna(request, response);
								} catch (Exception e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							
								str = "refund.do";
								
								break;
								
								
						   case "/else.do":
							   	// 커뮤니티 Q&A에서 배송목록 출력
							   
							   
							   System.out.println("refund 서버 도착");
							   

								h9 = new QnaElse();
								
								try {
									h9.qna(request, response);
								} catch (Exception e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
								
							str="else.jsp";
								break;
										
								
								
								
						   case "/qnawrite6.do" :
								System.out.println("case문 실행");
								h9 = new QnaWrite2();
								try {
									h9.qna(request, response);
								} catch(Exception e ) {
									e.printStackTrace();
								}
								str = "else.do";
								break;	
								
								
								
								
						   case "/elseinfo.do" :
							   
								// Q&A 상세 내용 출력
								
								
								System.out.println("comqnainfo 서버 도착");
								System.out.println("case문 실행");
								h9 = new QnacomInfo();
								try {
									h9.qna(request, response);
								} catch(Exception e ) {
									e.printStackTrace();
								}
								str = "elseinfo.jsp";
								break;
		
								
								
						   case "/comqnaanswer5.do":
							   
							   // 커뮤니티 Q&A 에서 답변달기
							   
							   System.out.println("comqnaAdmin 서버 도착");
								h9 = new QnaAdmin();
								
								try {
									h9.qna(request, response);
								} catch (Exception e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							
								str = "else.do";
								
								break;
			
								
								
								//하나
								
								
//								case "/start.do":
								//메인페이지
								case "/mainpage.do" :
									System.out.println("case문(mainpage.do) 실행");
									h1 = new ProductShowAll();
									try {
										h1.product(request, response);
									} catch (Exception e) {
										e.printStackTrace();
									}
									str = "mainpage.jsp";
									break;

									
								//헤더에서 best 누르면 정렬해는 기능
								case "/selectbest.do" :
									System.out.println("case문(selectbest.do) 실행");
									h1 = new ProductShowBest();
									try {
										h1.product(request, response);
									} catch (Exception e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
									str = "categorypage.jsp";
									break;
									
								//헤더에서 각 카테고리 클릭시 리스트 출력	
								case "/selectcategory.do" :
									System.out.println("selectcategory.do실행");
									h1 = new ProductCategory();
									try {
										h1.product(request, response);
									} catch (Exception e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
									str = "categorypage.jsp";
									break;
									
								// 인기순, 높은가격, 낮은가격순 정렬
								case "/productsort.do" :
									System.out.println("productsort.do실행");
									h1 = new ProductSort();
									try {
										h1.product(request, response);
									} catch (Exception e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
									str = "categorypage.jsp";
									break;
								
								//상품 상세정보페이지
								case "/showDetail.do" :
									System.out.println("case문(showDetail.do) 실행");
									h1 = new ProductShowDetail();
									try {
										h1.product(request, response);
									} catch (Exception e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
									str = "/detailpage.jsp";
									break;
									
								//검색창 이용해서 검색했을때 아이템 보여주는 기능
								case "/searchItem.do" :
									System.out.println("case문(searchItem.do)실행 ");
//									String item = request.getParameter("item");
									h1 = new ProductSearchItem();
									try {
										h1.product(request, response);
									} catch (Exception e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
									str = "categorypage.jsp";
									break;
									
								// 상세페이지에서 후기보기 누르면 나타내주는 페이지
								case "/productReview.do" :
									System.out.println("case문(productReview.do)실행 ");
									h2 = new ReviewProduct();
									try {
										h2.review(request, response);
									} catch (Exception e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
									str = "productreviewpage.jsp";
									break;
								
								// 후기페이지에 후기작성 눌렀을때 작성해주는 페이지
								case "/reivewWriteForm.do" :
									System.out.println("case문(reivewWriteForm.do)실행 ");
									str = "reviewwriteform.jsp";
									break;
										
								// 후기 작성하는페이지에서 '후기작성'시 등록해주는 기능
								case "/ReviewWrite.do" :
									System.out.println("case문(ReviewWrite.do)실행 ");
									h2 = new ReviewWrite();
									try {
										h2.review(request, response);
									} catch (Exception e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
									str = "productReview.do";
									break;
									
								//리뷰삭제
								case "/ReviewDelete.do" :
									System.out.println("case문(ReviewDelete.do)실행 ");
									h2 = new ReviewDelete();
									try {
										h2.review(request, response);
									} catch (Exception e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
									str = "productReview.do";
									break;
									
								//리뷰내용 수정버튼 눌렀을때 그 수정하는 창으로 이동
								case "/ReviewUpdateForm.do" :
									System.out.println("case문(ReviewUpdateForm.do)실행 ");
									h2 = new ReviewUpdateForm();
									try {
										h2.review(request, response);
									} catch (Exception e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
									str = "reviewupdateform.jsp";
									break;				
									
								 //리뷰수정눌렀을떄
								case "/ReviewUpdate.do" :
									System.out.println("case문(ReviewUpdate.do)실행 ");
									h2 = new ReviewUpdate();
									try {
										h2.review(request, response);
									} catch (Exception e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
									str = "productReview.do";
									break;
									
								// 후기보기 페이지에서 도움된다는 버튼 눌렀을떄 작동
								case "/ReviewUpbutton.do":
									System.out.println("ReviewUpbutton실행");
									h2 = new ReviewUpbutton();
									try {
										h2.review(request, response);
									} catch (Exception e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
//									str = "productreviewpage.jsp";
									str = "productReview.do";
									// 혹은 저기 결과를 받는 페이지에서 다시한번 넘기는 방식을 사용한다면 어떻게 받을 수 있을지도 모르겠음
									
									break;
									
								// 후기보기 페이지에서 도움안되요 버튼 눌렀을떄 작동
								case "/ReviewDownbutton.do":
									System.out.println("ReviewDownbutton실행");
									h2 = new ReviewDownbutton();
									try {
										h2.review(request, response);
									} catch (Exception e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
//									str = "productreviewpage.jsp";
									str = "productReview.do";
									break;
									
								// 후기 인기순로 정렬
								case "/ReviewSortlike.do":
									System.out.println("ReviewSortlike실행");
									h2 = new ReviewSortlike();
									try {
										h2.review(request, response);
									} catch (Exception e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
									str = "productreviewpage.jsp";
									break;

									
									
								//상품 상세페이지에서 QNA 버튼 눌렀을때 이동
								case "/qnalist.do" :
									System.out.println("case문(qnalist.do) 실행");
									h9 = new Qnalist();
									try {
										h9.qna(request, response);
									} catch(Exception e ) {
										e.printStackTrace();
									}
									
									str = "qnalist.jsp";
									break;
									
								// 상품문의하기시 페이지이동처리
								case "/qnawriteForm.do" :
									System.out.println("case문 실행");
									str = "qnawriteForm.jsp";
									break;
									
								// 상품문의 등록	
								case "/qnawrite.do" :
									System.out.println("case문 실행");
									h9 = new QnaWrite();
									try {
										h9.qna(request, response);
									} catch(Exception e ) {
										e.printStackTrace();
									}
									str = "qnalist.do";
									break;
									
								// 상품문의내용보기
								case "/qnaView.do" :
									System.out.println("case문 실행");
									h9 = new QnaView();
									try {
										h9.qna(request, response);
									} catch(Exception e ) {
										e.printStackTrace();
									}
									str = "/qnaView.jsp";
									break;
									
									
								// 상품문의 보기 했을때 비밀번호 확인페이지
								case "/qnapwCheckForm.do" :
									System.out.println("case문 실행 qnapwCheckForm.do");
									str = "qnapwCheckForm.jsp";
									break;
									
								
								// 상품내용에 대한 관리자 답변페이지
								case "/qnareactionForm.do" :
									System.out.println("case문 실행 qnareactionForm.do");
									h9 = new QnaReactionForm();
									try {
										h9.qna(request, response);
									} catch(Exception e ) {
										e.printStackTrace();
									}
									str = "qnareactionForm.jsp";
									break;
								
								// 관리자 답변페이지에서 답변을 등록했을경우
								case "/qnareaction.do" :
									System.out.println("case문 실행 qnareaction.do");
									h9 = new QnaReaction();
									try {
										h9.qna(request, response);
									} catch(Exception e ) {
										e.printStackTrace();
									}
									str = "/qnalist.do";
									break;
									
									
									
								// 상품문의 내용에서 삭제처리 했을떄
								case "/qnadelete.do" :
									System.out.println("case문 실행 qnareaction.do");
									h9 = new qnaDelete();
									try {
										h9.qna(request, response);
									} catch(Exception e ) {
										e.printStackTrace();
									}
									str = "/qnalist.do";
									break;
									
									
								// 상품문의 내용에서 수정버튼 눌렀을 때
								case "/qnaupdateForm.do" :
									System.out.println("case문 실행 qnaupdateForm.do");
									h9 = new qnaUpdateForm();
									try {
										h9.qna(request, response);
									} catch(Exception e ) {
										e.printStackTrace();
									}
									str = "/qnaUpdateForm.jsp";
									break;
									
								// 수정Form에서 수정버튼 눌렀을때
								case "/qnaupdate.do" :
									System.out.println("case문 실행 qnaupdateForm.do");
									h9 = new qnaUpdate();
									try {
										h9.qna(request, response);
									} catch(Exception e ) {
										e.printStackTrace();
									}
									str = "/qnalist.do";
									break;
									
									
	
				
				
					
				/// 동현
				//아이디 비밀번호 치고 서버로 넘어와서 아이디와 비밀번호 일치하는지 확인
			case "/LoginForm.do" :
				System.out.println("아이디와 비밀번호 서버 도착");
				h3 = new MemberLogin();
				
				try {
					h3.member(request, response);	
					str = "LoginAction.do";
				} catch (Exception e) {
					e.printStackTrace();
				}
			
			
				
				break;
				
			//로그인 성공 여부를 result로 받음	
			case "/LoginAction.do" :
				System.out.println("로그인 성공여부 서바 도착 ");
				String result1=(String)request.getAttribute("result");
				request.setAttribute("result2", result1);
				System.out.println("result 결과는 >>>  "+result1);
			
				if(result1.equals("1") || result1.equals("2")) {
					//아이디 불일치             //비밀번호 불일치
				
					str="loginAction.jsp";
				} else if(result1.equals("3")){
				str = "mainpage.jsp";
				
				}else {
					str="loginAction.jsp";
				}
				
				
				break;	
				
				
			case "/enrollBasket.do":
				//장바구니에 넣기
				h7=new BasketEnroll();
			try {
				h7.basket(request, response);
			} catch (Exception e11) {
				e11.printStackTrace();
			}
				System.out.println("장바구니 넣기 서버 도착");
								str="basketList.do";
				
				break;
				
				
				
			case "/deleteBasket.do":
				//장바구니 목록 삭제
				h7=new BasketDelete();
			try {
				h7.basket(request, response);
			} catch (Exception e10) {
				e10.printStackTrace();
			}
				str="basketList.do";
				
				break;
				
				
			case "/basketList.do":
				//장바구니 목록보기
				h7=new BasketPrint();
			try {
				h7.basket(request, response);
			} catch (Exception e9) {
				e9.printStackTrace();
			}
				
			if(session.getAttribute("id")==null) {
				str="login.jsp";
			}else{
				str="c0006.jsp";
			}	
				break;
				
			case "/merchandise.do":
				//상품목록 출력
				System.out.println("상품관리 서버 도착");
				h1=new ProductPrint();
			try {
				h1.product(request, response);
			} catch (Exception e8) {
				e8.printStackTrace();
			}
				str="c0110.jsp";
				break;
			
		     
		case "/delete.do":
			System.out.println("상품 삭제 서버 도착");
			//상품목록에서 삭제
			h1=new ProductDelete();
			try {
				h1.product(request, response);
			} catch (Exception e7) {
				e7.printStackTrace();
			}
			
			str="merchandise.do";
			
			break;
			
		case "/productEnroll.do":
			//상품등록
			h1=new ProductEnroll();
			try {
				h1.product(request, response);
			} catch (Exception e5) {
				e5.printStackTrace();
			}
			
				str="merchandise.do";
				break;
			
		case "/member.do":
			//멤버리스트 출력
			System.out.println("멤버리스트 출력 서버 도착");
			h3=new MemberPrint();
			try {
				h3.member(request, response);
			} catch (Exception e6) {
				e6.printStackTrace();
			}
		
				str="c0210.jsp";
				break;
				
				
		case "/memberDelete.do":
			//아이디 삭제
			System.out.println("아이디 삭제 서버 도착");
			h3=new MemberDelete();
			try {
				h3.member(request, response);
			} catch (Exception e4) {
				e4.printStackTrace();
			}
			
				str="member.do";
				break;
			
		case "/money.do":
			//매출관리
			
			System.out.println("매출관리 서버 도착");
			h6=new OrderlistPrint();
			try {
				h6.Orderlist(request, response);
			} catch (Exception e3) {
				e3.printStackTrace();
			}
				
				str="c0410.jsp";
				break;
		
		case "/enrollNotice.do":
			//공지사항 등록
			h5=new NoticeEnroll();
			
			System.out.println("공지사항 등록 서버도착");
				
			try {
				h5.notice(request, response);
			} catch (Exception e2) {
				e2.printStackTrace();
			}
			str="notice.do";
				break;
				
		case "/choice.do":
			//option을 받아서 구매하기랑 장바구니, 삭제하기중 어디로 갈지
			String option12=request.getParameter("option12");
			System.out.println("들어온 option :"+option12);
			String key12=request.getParameter("key");
			System.out.println("들어온 key :"+key12);
			if(option12.equals("구매하기")){
				str="getPoint.do";
				if(key12.equals("1")) {
					str="paypayList.do";
				}
			}else if(option12.equals("삭제하기")) {
				str="deleteBasket.do";
				
			}else {
				str="enrollBasket.do";
			}
			break;
		
		case "/paypay.do":
			//상세페이지에서 결제
			System.out.println("상세페이지 결제 서버도착");	 
			h4=new PayAtDetail();
			
			try {
				h4.pay(request, response);
			} catch (Exception e2) {
				e2.printStackTrace();
			}
			
			str="PaySuccess.jsp";
			break;	
			
		case "/getPoint.do":
			//포인트를 가져온 다음 결제전에 포인트 사용할지 여부 페이지로 이동
			h3=new MemberPoint();
			try {
				h3.member(request, response);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			if(session.getAttribute("id")==null) {
				str="login.jsp";
			}else {
			str="pointUse.jsp";
			}
			break;
			
		case "/paypayList.do":
			//주문전 주문정보 출력
			System.out.println("장바구니에서 결제상세 서버 도착");
			
			try {
			h4=new PayAtBasket();
			h4.pay(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
				System.out.println("결제창으로 넘어감");
			
			str="pointUse2.jsp";
			break;	
		
		case "/paypayList2.do":
			//장바구니에서 구매하기
			System.out.println("장바구니에서 결제하기 서버 도착");
			
			try {
			h4=new PayAtBasket2();
			h4.pay(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
				System.out.println("결제창으로 넘어감");
			
			str="PaySuccess.jsp";
			break;		
			
			
		case "/productUpdate.do":
			//상품목록에서 수정할 상품 정보 옮기기
			System.out.println("수정할 상품 정보 옮기기 서버 도착");
			
			
			h1=new ProductUpdate();
			try {
				h1.product(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			str="productUpdate.jsp";
			break;			
			
			
		
		case "/thisUpdate.do":
			//상품목록에서 상품 수정
			System.out.println("상품수정 서버 도착");
			h1=new ProductUpdate2();
			
			
			try {
				h1.product(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			str="merchandise.do";
			break;	
			
			
			
		case "/wishList.do":
			//위시리스트 출력
			System.out.println("위시리스트 서버 도착");
			h11=new WishlistPrint();
			try {
				h11.Wishlist(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			str="wishlist.jsp";
			break;
			
		case "/insertWish.do" :
			//위시리스트에 넣기
			System.out.println("위시리스트 넣기 서버도착");
			h11=new WishInsert();
			try {
				h11.Wishlist(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			str="wishList.do";
			break;
			
		case "/deleteWish.do" :
			//위시리스트에서 삭제
			System.out.println("위시리스트 삭제 서버도착");
			h11=new wishDelete();
			try {
				h11.Wishlist(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			str="wishList.do";
			break;
			
			
		case "/wishtobasket.do" :
			//위시리스트에서 장바구니로 이동
			System.out.println("위시리스트에서 장바구니 서버 도착");
			h11=new WishToBasket();
			try {
				h11.Wishlist(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			str="basketList.do";
			break;
			
		}	
	
				
		
		RequestDispatcher rd1 = request.getRequestDispatcher(str);
		rd1.forward(request, response);
	}


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
