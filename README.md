# TestSpringSecurity
spring - SpringSecurity 基本範例

```sql
#Spring Security預設會讀取這兩張table
create table users (   
  username varchar(50) not null primary key,
  password varchar(255) not null,
  enabled boolean not null) ;

create table authorities (
  username varchar(50) not null,
  authority varchar(50) not null,
  foreign key (username) references users (username),
  unique index authorities_idx_1 (username, authority));

#新增帳號權限資料
insert into users(username,password,enabled)
values('user', '$2a$10$JvqOtJaDys0yoXPX9w47YOqu9wZr/PkN1dJqjG9HHAzMyu9EV1R4m', '1');

insert into authorities(username,authority) 
values ('user', 'ROLE_ADMIN');
```
