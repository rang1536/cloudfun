package com.cloudfun.www.post.vo;
import java.sql.Date;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Post{
	
	private String postId       ;   // �Խù��ڵ�   
	private String title        ;   // ����   
	private String contents      ;   // ����   
	private String tags          ;   // �±�   
	private String aiYn         ;   // AI ���ۿ���   
	private String funStartDt    ;   // �Ŀ�������s   
	private String funEndDt    ;   // �Ŀ�������   
	private String tgtAmt       ;   // ��ǥ�ݾ�   
	private String openAmt      ;   // �����������Ŀ��ݾ�   
	private String anoFunYn    ;   // �͸��Ŀ����ſ���   
	private String adultYn      ;   // ���ι�����   
	private String domainType   ;   // ����   
	private String fileGroupId ;   // ���ϱ׷�ID   
	
	private String useYn        ;   // ��뿩��   
	private Date registDt       ; // ����Ͻ� 
	private String registId    ;   // �����   
	private String registIp     ;   // ���IP   
	private Date updateDt       ; // �����Ͻ� 
	private String updateId     ;   // ������   
	private String updateIp     ;   // ����IP   
}
