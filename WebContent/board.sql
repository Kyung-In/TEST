CREATE TABLE board(
	num NUMBER PRIMARY KEY,
	writer VARCHAR2(12) NOT NULL,
	email VARCHAR2(50),
	subject VARCHAR2(50) NOT NULL,
	passwd VARCHAR2(12) NOT NULL,
	reg_date TIMESTAMP(6) NOT NULL,
	readcount NUMBER DEFAULT 0,
	ref NUMBER NOT NULL, -- 관련글 번호
	re_step NUMBER NOT NULL,--동일 관련글에 속한 글의 출력순서
	re_level NUMBER NOT NULL,--답글레벨, 들여쓰기 정도
	content VARCHAR2(4000) NOT NULL --글 내용
)

CREATE SEQUENCE board_seq;

SELECT * FROM board;









