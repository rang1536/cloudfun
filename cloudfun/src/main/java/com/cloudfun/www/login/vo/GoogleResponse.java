package com.cloudfun.www.login.vo;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class GoogleResponse {
    private String access_token; // ���ø����̼��� Google API ��û�� �����ϱ� ���� ������ ��ū
    private String expires_in;   // Access Token�� ���� ����
    private String refresh_token;    // �� �׼��� ��ū�� ��� �� ����� �� �ִ� ��ū
    private String scope;
    private String token_type;   // ��ȯ�� ��ū ����(Bearer ����)
    private String id_token;
}