package com.cloudfun.www.post.vo;
import java.sql.Date;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Post{
	
	private String postId       ;   // 게시물코드   
	private String title        ;   // 제목   
	private String contents      ;   // 내용   
	private String tags          ;   // 태그   
	private String aiYn         ;   // AI 제작여부   
	private String funStartDt    ;   // 후원시작일s   
	private String funEndDt    ;   // 후원종료일   
	private String tgtAmt       ;   // 목표금액   
	private String openAmt      ;   // 열람공제공후원금액   
	private String anoFunYn    ;   // 익명후원수신여부   
	private String adultYn      ;   // 성인물지정   
	private String domainType   ;   // 유형   
	private String fileGroupId ;   // 파일그룹ID   
	
	private String useYn        ;   // 사용여부   
	private Date registDt       ; // 등록일시 
	private String registId    ;   // 등록자   
	private String registIp     ;   // 등록IP   
	private Date updateDt       ; // 수정일시 
	private String updateId     ;   // 수정자   
	private String updateIp     ;   // 수정IP   
}
