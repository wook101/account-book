## 페이히어 과제


#### 사용기술 및 개발환경
* java
* spring 4.3.5
* maven 3.5
* jdk 1.8
* tomcat 8.5
* intellij

#### 라이브러리
* spring-context
* spring-webmvc
* spring-jdbc
* commons-dbcp2
* mysql-connector-java
* javax.servlet-api
* jackson-databind
* lombok
* jjwt
* slf4j-api

#### 프로젝트 구조

<img width="257" alt="프로젝트 구조" src="https://user-images.githubusercontent.com/45925158/144759635-3b6de512-fcef-4268-9fef-184163cf2a05.PNG">

인텔리제이에서 Spring STS구조로 세팅했습니다.


#### 데이터베이스 ERD

<img width="400" alt="ERD사진" src="https://user-images.githubusercontent.com/45925158/144864162-2d34bdbe-da53-4923-ac07-35bf335d4581.PNG">


#### REST API

### 1. 회원가입 Post localhost:8080/join

* 요청 Body raw
``` 
{ "email":"ehddnr1021@naver.com", "password:"1234"}
```
* 응답 Body

회원가입 성공 (상태코드 | 200 | HttpStatus.OK)
```
{"message":"회원가입 성공"}   
```

이메일 중복 (상태코드 | 409 | HttpStatus.CONFLICT)
```
{"message":"이메일 중복"}
```
설명
- 클라이언트에서 이메일과 패스워드를 body에 담아 서버에게 요청합니다.
- 이메일로 USER 테이블을 조회했을 때 이메일이 중복인 경우 responseEntity의 body값으로 실패 메시지, 상태코드값으로 409를 담아서 응답했습니다.
- 이메일이 존재하지 않는 경우 이메일과 패스워드를 USER테이블에 저장하고, responseEntity의 body값으로 성공 메시지, 상태코드값으로 200를 담아서 응답했습니다




### 2. 로그인 Post localhost:8080/login
* 요청 Body raw
``` 
{"email":"ehddnr1021@naver.com", "password:"1234"}
```

* 응답 Body

로그인 성공 (상태코드 | 200 | HttpStatus.OK)
``` 
{"accessToken":"eyJyZWdEYXRlIjoxNjM4Nzg0Mzk1MDE3LCJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJleHAiOjE2Mzg3ODQ0NTUsImVtYWlsIjoiYWJjMUBnb29nbGUuY29tIn0.0BzaLMEU4d_SOlCAnPwfj8dKWKVyyaCTVVxblmwX_zU"}
```
로그인 실패 (상태코드 | 401 | HttpStatus.UNAUTHORIZED)
```
{"message": "이메일 또는 비밀번호가 일치하지 않습니다."}
```
설명
- 이메일과 패스워드가 USER테이블에 존재한다면 JWT토큰을 생성해서 응답했습니다.
- 이메일과 패스워드가 USER테이블에 존재하지 않으면 실패 메시지를 응답했습니다.
- 클라이언트는 토큰을 저장해두고 인증이 필요한 api에 요청할때 토큰과 함께 요청을 하도록 생각했습니다.



### 3. 가계부 등록 Post localhost:8080/auth/account-book
* 요청
  Header
```
{"Authentication":"eyJyZWdEYXRlIjoxNjM4Nzg0Mzk1MDE3LCJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJleHAiOjE2Mzg3ODQ0NTUsImVtYWlsIjoiYWJjMUBnb29nbGUuY29tIn0.0BzaLMEU4d_SOlCAnPwfj8dKWKVyyaCTVVxblmwX_zU"}
```
Body raw
``` 
{"price":20000, "memo":"치킨 먹은날", "category":"음식"}
```
* 응답 Body
  등록 성공 (상태코드 | 201 | HttpStatus.CREATED)
```
{"message":"등록 성공"}
```



### 4. 가계부 수정 Put localhost:8080/auth/account-book/{id}
* 요청
  Header
```
{"Authentication":"eyJyZWdEYXRlIjoxNjM4Nzg0Mzk1MDE3LCJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJleHAiOjE2Mzg3ODQ0NTUsImVtYWlsIjoiYWJjMUBnb29nbGUuY29tIn0.0BzaLMEU4d_SOlCAnPwfj8dKWKVyyaCTVVxblmwX_zU"}
```
Body raw
``` 
{"price":30000, "memo":"피자 먹은날", "category":"음식"}
```
* 응답 Body
  수정 성공 (상태코드 | 200 | HttpStatus.OK)
```
{"message":"수정 성공"}
```



### 5. 가계부 삭제 Delete localhost:8080/auth/account-book/{id}
* 요청
  Header
```
{"Authentication":"eyJyZWdEYXRlIjoxNjM4Nzg0Mzk1MDE3LCJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJleHAiOjE2Mzg3ODQ0NTUsImVtYWlsIjoiYWJjMUBnb29nbGUuY29tIn0.0BzaLMEU4d_SOlCAnPwfj8dKWKVyyaCTVVxblmwX_zU"}
```

* 응답 Body
  삭제 성공 (상태코드 | 200 | HttpStatus.OK)
```
{"message":"삭제 성공"}
```


### 6. 가계부 복구 Post localhost:8080/auth/account-book/{id}
* 요청
  Header
```
{"Authentication":"eyJyZWdEYXRlIjoxNjM4Nzg0Mzk1MDE3LCJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJleHAiOjE2Mzg3ODQ0NTUsImVtYWlsIjoiYWJjMUBnb29nbGUuY29tIn0.0BzaLMEU4d_SOlCAnPwfj8dKWKVyyaCTVVxblmwX_zU"}
```
Body raw
``` 
{"price":20000, "memo":"치킨 먹은날", "category":"음식"}
```
* 응답 Body
  복구 성공 (상태코드 | 200 | HttpStatus.OK)
```
{"message":"복구 성공"}
```


### 7. 가계부 리스트 Get localhost:8080/auth/account-books
* 요청
  Header
```
{"Authentication":"eyJyZWdEYXRlIjoxNjM4Nzg0Mzk1MDE3LCJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJleHAiOjE2Mzg3ODQ0NTUsImVtYWlsIjoiYWJjMUBnb29nbGUuY29tIn0.0BzaLMEU4d_SOlCAnPwfj8dKWKVyyaCTVVxblmwX_zU"}
```
* 응답 Body raw
  성공 (상태코드 | 200 | HttpStatus.OK)
``` 
[{"price":20000, "memo":"치킨시켜 먹음", "category":"음식","create_at":"2021-12-01 10:10:15"},
{"price":15000, "memo":"치즈피자 먹음", "category":"음식","create_at":"2021-12-01 12:10:13"},
{"price":30000, "memo":"염색한 날", "category":"미용","create_at":"2021-12-01 15:10:12"},
{"price":45000, "memo":"펌한 날", "category":"미용","create_at":"2021-12-01 16:10:11"},
{"price":70000, "memo":"바지와 티셔츠 구매", "category":"의류","create_at":"2021-12-01 20:10:19"}]
```


### 8. 가계부 상세정보 Get localhost:8080/auth/account-book/{id}
* 요청
  Header
```
{"Authentication":"eyJyZWdEYXRlIjoxNjM4Nzg0Mzk1MDE3LCJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJleHAiOjE2Mzg3ODQ0NTUsImVtYWlsIjoiYWJjMUBnb29nbGUuY29tIn0.0BzaLMEU4d_SOlCAnPwfj8dKWKVyyaCTVVxblmwX_zU"}
```
* 응답 Body raw
  성공 (상태코드 | 200 | HttpStatus.OK)
``` 
{"price":20000, "memo":"치킨시켜 먹음", "category":"음식","create_at":"2021-12-01 10:10:15"},

```




#### 구현 못한 부분
발급받은 jwt토큰의 유효성을 검사하는 부분에서 서명인식예외(io.jsonwebtoken.SignatureException)가 발생해서 토큰을 가지고 있는 사용자의 인증처리 로직을 구현하지 못했습니다.
가계부 복구, 가계부 리스트, 가계부 상세정보, docker에 배포하는 부분






#### 배포
docker 이미지 - mysql, tomcat   
.war파일 tomcat에 업로드  