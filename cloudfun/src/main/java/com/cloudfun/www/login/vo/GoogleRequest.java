package com.cloudfun.www.login.vo;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GoogleRequest {
    private String clientId;    // ���ø����̼��� Ŭ���̾�Ʈ ID
    private String redirectUri; // Google �α��� �� redirect ��ġ
    private String clientSecret;    // Ŭ���̾�Ʈ ���� ���
    private String responseType;    // Google OAuth 2.0 ��������Ʈ�� ���� �ڵ带 ��ȯ�ϴ��� ����
    private String scope;   // OAuth ���ǹ���
    private String code;
    private String accessType;  // ����ڰ� �������� ���� �� ���ø����̼��� �׼��� ��ū�� ���� ��ĥ �� �ִ��� ����
    private String grantType;
    private String state;
    private String includeGrantedScopes;    // ���ø����̼��� ���ؽ�Ʈ���� �߰� ������ ���� �׼����� ��û�ϱ� ���� �߰� ���� �ο��� ���
    private String loginHint;   // ���ø����̼��� �����Ϸ��� ����ڸ� �˰� �ִ� ��� �� �Ű������� ����Ͽ� Google ���� ������ ��Ʈ�� ����
    private String prompt;  // default: ó������ �׼����� ��û�� ���� ����ڿ��� �޽����� ǥ��
}
