CREATE TABLE board(
	num NUMBER PRIMARY KEY,
	writer VARCHAR2(12) NOT NULL,
	email VARCHAR2(50),
	subject VARCHAR2(50) NOT NULL,
	passwd VARCHAR2(12) NOT NULL,
	reg_date TIMESTAMP(6) NOT NULL,
	readcount NUMBER DEFAULT 0,
	ref NUMBER NOT NULL, -- ���ñ� ��ȣ
	re_step NUMBER NOT NULL,--���� ���ñۿ� ���� ���� ��¼���
	re_level NUMBER NOT NULL,--��۷���, �鿩���� ����
	content VARCHAR2(4000) NOT NULL --�� ����
)

CREATE SEQUENCE board_seq;

SELECT * FROM board;









