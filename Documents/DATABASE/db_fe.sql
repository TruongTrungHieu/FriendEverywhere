CREATE DATABASE db_fe

USE db_fe

CREATE TABLE tbl_user
(
	user_id varchar(20) NOT NULL PRIMARY KEY,
	fullname nvarchar(50),
	birthday date,
	email varchar(20),
	phone varchar(20),
	latitude float,
	longtitude float,
	pass varchar(20),
	online_status int,
	photo text,
	about_me text,
	gcm_id text
)

CREATE TABLE tbl_friend
(
	friend_pk varchar(20) NOT NULL PRIMARY KEY,
	friend_status int 
)

ALTER TABLE tbl_friend
	ADD me_id varchar(20) 
		CONSTRAINT tblfriend_fk_me FOREIGN KEY
			REFERENCES tbl_user(user_id) NOT NULL

ALTER TABLE tbl_friend
	ADD friend_id varchar(20) 
		CONSTRAINT tblfriend_fk_friend FOREIGN KEY
			REFERENCES tbl_user(user_id) NOT NULL

CREATE TABLE tbl_group
(
	group_id varchar(20) NOT NULL PRIMARY KEY,
	display_name nvarchar(50)
)

ALTER TABLE tbl_group
	ADD user_id varchar(20)
		CONSTRAINT fk_group_boss FOREIGN KEY
			REFERENCES tbl_user(user_id) NOT NULL

CREATE TABLE tbl_group_user
(
	group_user_id varchar(20) NOT NULL PRIMARY KEY	
)

ALTER TABLE tbl_group_user
	ADD group_id varchar(20)
		CONSTRAINT fk_group_groupid FOREIGN KEY
			REFERENCES tbl_group(group_id) NOT NULL

ALTER TABLE tbl_group_user
	ADD user_id varchar(20)
		CONSTRAINT fk_group_userid FOREIGN KEY
			REFERENCES tbl_user(user_id) NOT NULL

CREATE TABLE tbl_message
(
	message_id varchar(20) NOT NULL PRIMARY KEY,
	content text,
	created_date datetime,
	error int,
	attachment int,
	attachment_url text,
	attachment_type int
)

ALTER TABLE tbl_message
	ADD user_id varchar(20)
		CONSTRAINT fk_message_userid FOREIGN KEY
			REFERENCES tbl_user(user_id) NOT NULL

ALTER TABLE tbl_message
	ADD group_id varchar(20)
		CONSTRAINT fk_message_groupid FOREIGN KEY
			REFERENCES tbl_group(group_id) NOT NULL

CREATE TABLE tbl_feedback
(
	feedback_id varchar(20) NOT NULL PRIMARY KEY,
	content text,
	subject text,
	created_date datetime
)

ALTER TABLE tbl_feedback
	ADD user_id varchar(20)
		CONSTRAINT fk_feedback_userid FOREIGN KEY
			REFERENCES tbl_user(user_id) NOT NULL
				