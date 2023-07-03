# 나만의 블로그 백엔드 서버
### 
> /src/main/resources/application.properties 에서 Mysql url, username, password 수정 필요

### Use Case
![use_case](https://github.com/mabyoungg/sparta-blog/assets/131260371/e44d942b-82e7-44cc-8b80-7f6dfac18c4c)

-------------------------------------------------
### 요구 사항
#### 입문

<details>
<summary><b>자세히</b></summary>
<div markdown="1">

요구사항을 기반으로 Use Case 그리기

전체 게시글 목록 조회 API
- 제목, 작성자명, 작성 내용, 작성 날짜를 조회하기
- 작성 날짜 기준 내림차순으로 정렬하기

게시글 작성 API
- 제목, 작성자명, 비밀번호, 작성 내용을 저장하고
- 저장된 게시글을 Client 로 반환하기

선택한 게시글 조회 API
- 선택한 게시글의 제목, 작성자명, 작성 날짜, 작성 내용을 조회하기

선택한 게시글 수정 API
- 수정을 요청할 때 수정할 데이터와 비밀번호를 같이 보내서 서버에서 비밀번호 일치 여부를 확인 한 후
- 제목, 작성자명, 작성 내용을 수정하고 수정된 게시글을 Client 로 반환하기

선택한 게시글 삭제 API
- 삭제를 요청할 때 비밀번호를 같이 보내서 서버에서 비밀번호 일치 여부를 확인 한 후
- 선택한 게시글을 삭제하고 Client 로 성공했다는 표시 반환하기

</div>
</details>

#### 숙련

<details>
<summary><b>자세히</b></summary>
<div markdown="1">

회원 가입 API
- username, password를 Client에서 전달받기
- username은  최소 4자 이상, 10자 이하이며 알파벳 소문자(a~z), 숫자(0~9)로 구성되어야 한다.
- password는  최소 8자 이상, 15자 이하이며 알파벳 대소문자(a~z, A~Z), 숫자(0~9)로 구성되어야 한다.
- DB에 중복된 username이 없다면 회원을 저장하고 Client 로 성공했다는 메시지, 상태코드 반환하기

로그인 API
- username, password를 Client에서 전달받기
- DB에서 username을 사용하여 저장된 회원의 유무를 확인하고 있다면 password 비교하기
- 로그인 성공 시, 로그인에 성공한 유저의 정보와 JWT를 활용하여 토큰을 발급하고,
- 발급한 토큰을 Header에 추가하고 성공했다는 메시지, 상태코드 와 함께 Client에 반환하기

전체 게시글 목록 조회 API
- 제목, 작성자명(username), 작성 내용, 작성 날짜를 조회하기
- 작성 날짜 기준 내림차순으로 정렬하기

게시글 작성 API
- 토큰을 검사하여, 유효한 토큰일 경우에만 게시글 작성 가능
- 제목, 작성 내용을 저장하고
- 저장된 게시글을 Client 로 반환하기(username은 로그인 된 사용자)

선택한 게시글 조회 API
- 선택한 게시글의 제목, 작성자명(username), 작성 날짜, 작성 내용을 조회하기

선택한 게시글 수정 API
- ~~수정을 요청할 때 수정할 데이터와 비밀번호를 같이 보내서 서버에서 비밀번호 일치 여부를 확인 한 후~~
- 토큰을 검사한 후, 유효한 토큰이면서 해당 사용자가 작성한 게시글만 수정 가능
- 제목, 작성 내용을 수정하고 수정된 게시글을 Client 로 반환하기

선택한 게시글 삭제 API
- ~~삭제를 요청할 때 비밀번호를 같이 보내서 서버에서 비밀번호 일치 여부를 확인 한 후~~
- 토큰을 검사한 후, 유효한 토큰이면서 해당 사용자가 작성한 게시글만 삭제 가능
- 선택한 게시글을 삭제하고 Client 로 성공했다는 메시지, 상태코드 반환하기

</div>
</details>

#### 심화

<details>
<summary><b>자세히</b></summary>
<div markdown="1">
예정
</div>
</details>

-------------------------------------------------
### ERD
![erd](https://github.com/mabyoungg/sparta-blog/assets/131260371/87ba964f-9b87-42e0-b3a6-522bde940e63)

-------------------------------------------------
### API 명세서
#### 입문

<details>
<summary><b>자세히</b></summary>
<div markdown="1">

| 기능        | Method      | URL                     | request                                                                                                               | response                                                                                                                                                                                                                                                                                                                              |
|-----------| ----------- | ---------------------- |-----------------------------------------------------------------------------------------------------------------------|---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| 전체 게시글 조회 | GET           |	/api/blogs |                                                                                                                       | { </br>&nbsp;&nbsp;{ </br>&nbsp;&nbsp; "id" : "id", </br>&nbsp;&nbsp; "title" : "title" </br>&nbsp;&nbsp; "username" : "username", </br>&nbsp;&nbsp; "contents" : "contents", </br>&nbsp;&nbsp; "createdAt" : "createdAt" </br>&nbsp;&nbsp; }, </br>&nbsp;&nbsp; { </br>&nbsp;&nbsp; "id" : "id", </br>&nbsp;&nbsp; "title" : "title" </br>&nbsp;&nbsp; "username" : "username", </br>&nbsp;&nbsp; "contents" : "contents", </br>&nbsp;&nbsp; "createdAt" : "createdAt" </br>&nbsp;&nbsp; },</br>&nbsp;&nbsp; ... </br> } |
| 선택 게시글 조회 | GET          |/api/blogs/{id} |                                                                                                                       | { </br> "id" : "id", </br> "title" : "title" </br> "username" : "username", </br> "contents" : "contents", </br> "createdAt" : "createdAt" </br> }                                                                                                                                                                                    |
| 게시글 저장    | POST          |/api/blogs      | { </br> "title" : "title" </br> "username" : "username", </br> "contents" : "contents", </br> "password" : "password" </br> } | { </br> "id" : "id", </br> "title" : "title" </br> "username" : "username", </br> "contents" : "contents", </br> "createdAt" : "createdAt"</br> }                                                                                                                                                                                     |
| 게시글 수정    | PUT          |/api/blogs/{id}       | { </br> "title" : "title" </br> "username" : "username", </br> "contents" : "contents", </br> "password" : "password" </br> } | { </br>"id" : "id", </br> "title" : "title" </br> "username" : "username", </br> "contents" : "contents", </br> "createdAt" : "createdAt" </br> }                                                                                                                                                                                     |
| 게시글 삭제    | DELETE       |/api/blogs/{id}      | { </br>	"password" : "password" </br> }                                                                               | { </br> "success" : true </br> }                                                                                                                                                                                                                                                                                                      |

</div>
</details>

#### 숙련

<details>
<summary><b>자세히</b></summary>
<div markdown="1">

| 기능        | Method | URL             | request                                                                                        | response                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                  |
|-----------|--------|-----------------|------------------------------------------------------------------------------------------------|---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| 회원가입      | POST   | /api/signup     | { </br>"username" : "username" </br>	"password" : "password" </br> }                           | { </br> "statuscode" : 200 </br> "msg" : "로그인 성공" }                                                                                                                                                                                                                                                                                                                                                                                                                                                                       |
| 로그인       | POST   | /api/login      | { </br>"username" : "username" </br>	"password" : "password" </br> }                           | { </br> "statuscode" : 200 </br> "msg" : "회원가입 성공" }                                                                                                                                                                                                                                                                                                                                                                                                                                                                      |
| 전체 게시글 조회 | GET    | 	/api/blogs     |                                                                                                | { </br>&nbsp;&nbsp;{ </br>&nbsp;&nbsp; "id" : "id", </br>&nbsp;&nbsp; "title" : "title" </br>&nbsp;&nbsp; "username" : "username", </br>&nbsp;&nbsp; "contents" : "contents", </br>&nbsp;&nbsp; "createdAt" : "createdAt" </br>&nbsp;&nbsp; }, </br>&nbsp;&nbsp; { </br>&nbsp;&nbsp; "id" : "id", </br>&nbsp;&nbsp; "title" : "title" </br>&nbsp;&nbsp; "username" : "username", </br>&nbsp;&nbsp; "contents" : "contents", </br>&nbsp;&nbsp; "createdAt" : "createdAt" </br>&nbsp;&nbsp; },</br>&nbsp;&nbsp; ... </br> } |
| 선택 게시글 조회 | GET    | /api/blogs/{id} |                                                                                                | { </br> "id" : "id", </br> "title" : "title" </br> "username" : "username", </br> "contents" : "contents", </br> "createdAt" : "createdAt" </br> }                                                                                                                                                                                                                                                                                                                                                                        |
| 게시글 저장    | POST   | /api/blogs      | { </br> "title" : "title" </br> "contents" : "contents" </br> }                                | { </br> "id" : "id", </br> "title" : "title" </br> "username" : "username", </br> "contents" : "contents", </br> "createdAt" : "createdAt"</br> }                                                                                                                                                                                                                                                                                                                                                                         |
| 게시글 수정    | PUT    | /api/blogs/{id} | { </br> "title" : "title"  </br> "contents" : "contents" </br> } | { </br>"id" : "id", </br> "title" : "title" </br> "username" : "username", </br> "contents" : "contents", </br> "createdAt" : "createdAt" </br> }                                                                                                                                                                                                                                                                                                                                                                         |
| 게시글 삭제    | DELETE | /api/blogs/{id} |                                                         | { </br> "statuscode" : 200 </br> "msg" : "게시글 삭제 성공" </br> }                                                                                                                                                                                                                                                                                                                                                                                                                                                              |


</div>
</details>

#### 심화

<details>
<summary><b>자세히</b></summary>
<div markdown="1">

예정

</div>
</details>