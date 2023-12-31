# 나만의 블로그 백엔드 서버 Lv.3
### 
> /src/main/resources/application.properties 에서 Mysql url, username, password 수정 필요

### Use Case
#### 입문 Lv.1

<details>
<summary><b>자세히</b></summary>
<div markdown="1">

![use_case](https://github.com/mabyoungg/sparta-blog/assets/131260371/e44d942b-82e7-44cc-8b80-7f6dfac18c4c)

</div>
</details>

-------------------------------------------------
### 요구 사항
#### 입문 Lv.1

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
</br>

#### 숙련 Lv.2

<details>
<summary><b>자세히</b></summary>
<div markdown="1">

#### 새로운 요구 사항

회원 가입 API
- username, password를 Client에서 전달받기
- username은  `최소 4자 이상, 10자 이하이며 알파벳 소문자(a-z), 숫자(0-9)`로 구성되어야 한다.
- password는  `최소 8자 이상, 15자 이하이며 알파벳 대소문자(a-z, A-Z), 숫자(0-9)`로 구성되어야 한다.
- DB에 중복된 username이 없다면 회원을 저장하고 Client 로 성공했다는 메시지, 상태코드 반환하기

로그인 API
- username, password를 Client에서 전달받기
- DB에서 username을 사용하여 저장된 회원의 유무를 확인하고 있다면 password 비교하기
- 로그인 성공 시, 로그인에 성공한 유저의 정보와 JWT를 활용하여 토큰을 발급하고,
- 발급한 토큰을 Header에 추가하고 성공했다는 메시지, 상태코드 와 함께 Client에 반환하기

#### Lv1 수정사항

전체 게시글 목록 조회 API
- 제목, 작성자명`(username)`, 작성 내용, 작성 날짜를 조회하기
- 작성 날짜 기준 내림차순으로 정렬하기

게시글 작성 API
- `토큰을 검사하여, 유효한 토큰일 경우에만 게시글 작성 가능`
- `제목, 작성 내용을 저장하고`
- 저장된 게시글을 Client 로 반환하기(username은 로그인 된 사용자)

선택한 게시글 조회 API
- 선택한 게시글의 제목, 작성자명`(username)`, 작성 날짜, 작성 내용을 조회하기

선택한 게시글 수정 API
- ~~수정을 요청할 때 수정할 데이터와 비밀번호를 같이 보내서 서버에서 비밀번호 일치 여부를 확인 한 후~~
- `토큰을 검사한 후, 유효한 토큰이면서 해당 사용자가 작성한 게시글만 수정 가능`
- `제목, 작성 내용을 수정하고` 수정된 게시글을 Client 로 반환하기

선택한 게시글 삭제 API
- ~~삭제를 요청할 때 비밀번호를 같이 보내서 서버에서 비밀번호 일치 여부를 확인 한 후~~
- `토큰을 검사한 후, 유효한 토큰이면서 해당 사용자가 작성한 게시글만 삭제 가능`
- 선택한 게시글을 삭제하고 Client 로 성공했다는 메시지, 상태코드 반환하기

</div>
</details>
</br>

#### 숙련 Lv.3

<details>
<summary><b>자세히</b></summary>
<div markdown="1">

#### 새로운 요구 사항
회원 가입 API
- username, password를 Client에서 전달받기
- username은  `최소 4자 이상, 10자 이하이며 알파벳 소문자(a-z), 숫자(0-9)`로 구성되어야 한다.
- password는  `최소 8자 이상, 15자 이하이며 알파벳 대소문자(a-z, A-Z), 숫자(0-9), 특수문자`로 구성되어야 한다.
- DB에 중복된 username이 없다면 회원을 저장하고 Client 로 성공했다는 메시지, 상태코드 반환하기
- `회원 권한 부여하기 (ADMIN, USER) - ADMIN 회원은 모든 게시글, 댓글 수정 / 삭제 가능`

로그인 API
- username, password를 Client에서 전달받기
- DB에서 username을 사용하여 저장된 회원의 유무를 확인하고 있다면 password 비교하기
- 로그인 성공 시, 로그인에 성공한 유저의 정보와 JWT를 활용하여 토큰을 발급하고,
- 발급한 토큰을 Header에 추가하고 성공했다는 메시지, 상태코드 와 함께 Client에 반환하기

`댓글 작성 API`
- 토큰을 검사하여, 유효한 토큰일 경우에만 댓글 작성 가능
- 선택한 게시글의 DB 저장 유무를 확인하기
- 선택한 게시글이 있다면 댓글을 등록하고 등록된 댓글 반환하기

`댓글 수정 API`
- 토큰을 검사한 후, 유효한 토큰이면서 해당 사용자가 작성한 댓글만 수정 가능
- 선택한 댓글의 DB 저장 유무를 확인하기
- 선택한 댓글이 있다면 댓글 수정하고 수정된 댓글 반환하기

`댓글 삭제 API`
- 토큰을 검사한 후, 유효한 토큰이면서 해당 사용자가 작성한 댓글만 삭제 가능
- 선택한 댓글의 DB 저장 유무를 확인하기
- 선택한 댓글이 있다면 댓글 삭제하고 Client 로 성공했다는 메시지, 상태코드 반환하기

`예외 처리`
- 토큰이 필요한 API 요청에서 토큰을 전달하지 않았거나 정상 토큰이 아닐 때는 "토큰이 유효하지 않습니다." 라는 에러메시지와 statusCode: 400을 Client에 반환하기
- 토큰이 있고, 유효한 토큰이지만 해당 사용자가 작성한 게시글/댓글이 아닌 경우에는 “작성자만 삭제/수정할 수 있습니다.”라는 에러메시지와 statusCode: 400을 Client에 반환하기
- DB에 이미 존재하는 username으로 회원가입을 요청한 경우 "중복된 username 입니다." 라는 에러메시지와 statusCode: 400을 Client에 반환하기
- 로그인 시, 전달된 username과 password 중 맞지 않는 정보가 있다면 "회원을 찾을 수 없습니다."라는 에러메시지와 statusCode: 400을 Client에 반환하기

#### Lv2 수정사항
전체 게시글 목록 조회 API
- 제목, 작성자명(username), 작성 내용, 작성 날짜를 조회하기
- 작성 날짜 기준 내림차순으로 정렬하기
- `각각의 게시글에 등록된 모든 댓글을 게시글과 같이 Client에 반환하기`
- `댓글은 작성 날짜 기준 내림차순으로 정렬하기`

게시글 작성 API
- 토큰을 검사하여, 유효한 토큰일 경우에만 게시글 작성 가능
- 제목, 작성자명(username), 작성 내용을 저장하고
- 저장된 게시글을 Client 로 반환하기

선택한 게시글 조회 API
- 선택한 게시글의 제목, 작성자명(username), 작성 날짜, 작성 내용을 조회하기
  (검색 기능이 아닙니다. 간단한 게시글 조회만 구현해주세요.)
- `선택한 게시글에 등록된 모든 댓글을 선택한 게시글과 같이 Client에 반환하기`
- `댓글은 작성 날짜 기준 내림차순으로 정렬하기`

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
</br>

#### 심화 Lv.4

<details>
<summary><b>자세히</b></summary>
<div markdown="1">
예정
</div>
</details>
</br>

#### 심화 Lv.5

<details>
<summary><b>자세히</b></summary>
<div markdown="1">
예정
</div>
</details>

-------------------------------------------------
### ERD
#### 숙련 Lv.2

<details>
<summary><b>자세히</b></summary>
<div markdown="1">

![erd](https://github.com/mabyoungg/sparta-blog/assets/131260371/cff827ea-b27b-4dce-8dd0-f63194661f68)

</div>
</details>

#### 숙련 Lv.3

<details>
<summary><b>자세히</b></summary>
<div markdown="1">

![erd2](https://github.com/mabyoungg/sparta-blog/assets/131260371/ba4e79c2-ffce-44b6-aaca-402d34406b13)

</div>
</details>




-------------------------------------------------
### API 명세서
#### 입문 Lv.1

<details>
<summary><b>자세히</b></summary>
<div markdown="1">

| 기능        | Method      | URL             | request                                                                                                               | response                                                                                                                                                                                                                                                                                                                              |
|-----------| ----------- |-----------------|-----------------------------------------------------------------------------------------------------------------------|---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| 전체 게시글 조회 | GET           | 	/api/posts     |                                                                                                                       | { </br>&nbsp;&nbsp;{ </br>&nbsp;&nbsp; "id" : "id", </br>&nbsp;&nbsp; "title" : "title" </br>&nbsp;&nbsp; "username" : "username", </br>&nbsp;&nbsp; "contents" : "contents", </br>&nbsp;&nbsp; "createdAt" : "createdAt" </br>&nbsp;&nbsp; }, </br>&nbsp;&nbsp; { </br>&nbsp;&nbsp; "id" : "id", </br>&nbsp;&nbsp; "title" : "title" </br>&nbsp;&nbsp; "username" : "username", </br>&nbsp;&nbsp; "contents" : "contents", </br>&nbsp;&nbsp; "createdAt" : "createdAt" </br>&nbsp;&nbsp; },</br>&nbsp;&nbsp; ... </br> } |
| 선택 게시글 조회 | GET          | /api/posts/{id} |                                                                                                                       | { </br> "id" : "id", </br> "title" : "title" </br> "username" : "username", </br> "contents" : "contents", </br> "createdAt" : "createdAt" </br> }                                                                                                                                                                                    |
| 게시글 작성    | POST          | /api/posts      | { </br> "title" : "title" </br> "username" : "username", </br> "contents" : "contents", </br> "password" : "password" </br> } | { </br> "id" : "id", </br> "title" : "title" </br> "username" : "username", </br> "contents" : "contents", </br> "createdAt" : "createdAt"</br> }                                                                                                                                                                                     |
| 게시글 수정    | PUT          | /api/posts/{id} | { </br> "title" : "title" </br> "username" : "username", </br> "contents" : "contents", </br> "password" : "password" </br> } | { </br>"id" : "id", </br> "title" : "title" </br> "username" : "username", </br> "contents" : "contents", </br> "createdAt" : "createdAt" </br> }                                                                                                                                                                                     |
| 게시글 삭제    | DELETE       | /api/posts/{id} | { </br>	"password" : "password" </br> }                                                                               | { </br> "success" : true </br> }                                                                                                                                                                                                                                                                                                      |

</div>
</details>
</br>

#### 숙련 Lv.2

<details>
<summary><b>자세히</b></summary>
<div markdown="1">

| 기능        | Method | URL            | Request Header | Request                                                              | Response                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                  | Response Header                                  |
|-----------|--------|----------------|----------------|----------------------------------------------------------------------|---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|--------------------------------------------------|
| 회원가입      | POST   | /api/signup    |                | { </br>"username" : "username" </br>	"password" : "password" </br> } | { </br> "statuscode" : 200 </br> "msg" : "로그인 성공" }                                                                                                                                                                                                                                                                                                                                                                                                                                                                       |                                                  |
| 로그인       | POST   | /api/login     |                | { </br>"username" : "username" </br>	"password" : "password" </br> } | { </br> "statuscode" : 200 </br> "msg" : "회원가입 성공" }                                                                                                                                                                                                                                                                                                                                                                                                                                                                      | Authorization: Bearer abcdefghijklmnopqrstuvwxyz |
| 전체 게시글 조회 | GET    | /api/posts     |                |                                                                      | { </br>&nbsp;&nbsp;{ </br>&nbsp;&nbsp; "id" : "id", </br>&nbsp;&nbsp; "title" : "title" </br>&nbsp;&nbsp; "username" : "username", </br>&nbsp;&nbsp; "contents" : "contents", </br>&nbsp;&nbsp; "createdAt" : "createdAt" </br>&nbsp;&nbsp; }, </br>&nbsp;&nbsp; { </br>&nbsp;&nbsp; "id" : "id", </br>&nbsp;&nbsp; "title" : "title" </br>&nbsp;&nbsp; "username" : "username", </br>&nbsp;&nbsp; "contents" : "contents", </br>&nbsp;&nbsp; "createdAt" : "createdAt" </br>&nbsp;&nbsp; },</br>&nbsp;&nbsp; ... </br> } |                                                  |
| 선택 게시글 조회 | GET    | /api/posts/{id} |                |                                                                      | { </br> "id" : "id", </br> "title" : "title" </br> "username" : "username", </br> "contents" : "contents", </br> "createdAt" : "createdAt" </br> }                                                                                                                                                                                                                                                                                                                                                                        |                                                  |
| 게시글 작성    | POST   | /api/posts     | Authorization: Bearer abcdefghijklmnopqrstuvwxyz | { </br> "title" : "title" </br> "contents" : "contents" </br> }      | { </br> "id" : "id", </br> "title" : "title" </br> "username" : "username", </br> "contents" : "contents", </br> "createdAt" : "createdAt"</br> }                                                                                                                                                                                                                                                                                                                                                                         |                                                  |
| 게시글 수정    | PUT    | /api/posts/{id} | Authorization: Bearer abcdefghijklmnopqrstuvwxyz | { </br> "title" : "title"  </br> "contents" : "contents" </br> }     | { </br>"id" : "id", </br> "title" : "title" </br> "username" : "username", </br> "contents" : "contents", </br> "createdAt" : "createdAt" </br> }                                                                                                                                                                                                                                                                                                                                                                         |                                                  |
| 게시글 삭제    | DELETE | /api/posts/{id} | Authorization: Bearer abcdefghijklmnopqrstuvwxyz |                                                                      | { </br> "statuscode" : 200 </br> "msg" : "게시글 삭제 성공" </br> }                                                                                                                                                                                                                                                                                                                                                                                                                                                              |                                                  |


</div>
</details>
</br>

#### 숙련 Lv.3

<details>
<summary><b>자세히</b></summary>
<div markdown="1">


| 기능        | Method | URL                         | Request Header | Request                                                              | Response                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                  | Response Header                                  |
|:----------|:-------|:----------------------------|:---------------|:---------------------------------------------------------------------|:------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|:-------------------------------------------------|
| 회원가입      | POST   | /api/signup                 |                | { </br>"username" : "username" </br>	"password" : "password" </br> } | { </br> "statuscode" : 200 </br> "msg" : "로그인 성공" }                                                                                                                                                                                                                                                                                                                                                                                                                                                                                       |                                                  |
| 로그인       | POST   | /api/login                  |                | { </br>"username" : "username" </br>	"password" : "password" </br> } | { </br> "statuscode" : 200 </br> "msg" : "회원가입 성공" }                                                                                                                                                                                                                                                                                                                                                                                                                                                                                      | Authorization: Bearer abcdefghijklmnopqrstuvwxyz |
| 전체 게시글 조회 | GET    | /api/posts                  |                |                                                                      | { </br>&nbsp;&nbsp;{ </br>&nbsp;&nbsp; "postId" : "postId", </br>&nbsp;&nbsp; "title" : "title" </br>&nbsp;&nbsp; "username" : "username", </br>&nbsp;&nbsp; "contents" : "contents", </br>&nbsp;&nbsp; "createdAt" : "createdAt", </br>&nbsp;&nbsp;  "commentList" : [] </br>&nbsp;&nbsp; }, </br>&nbsp;&nbsp; { </br>&nbsp;&nbsp; "postId" : "postId", </br>&nbsp;&nbsp; "title" : "title" </br>&nbsp;&nbsp; "username" : "username", </br>&nbsp;&nbsp; "contents" : "contents", </br>&nbsp;&nbsp; "createdAt" : "createdAt", </br>&nbsp;&nbsp;  "commentList" : [] </br>&nbsp;&nbsp; },</br>&nbsp;&nbsp; ... </br> } |                                                  |
| 선택 게시글 조회 | GET    | /api/posts/{id}             |                |                                                                      | { </br> "postId" : "postId", </br> "title" : "title" </br> "username" : "username", </br> "contents" : "contents", </br> "createdAt" : "createdAt", </br>  "commentList" : [] </br> }                                                                                                                                                                                                                                                                                                                                                                                |                                                  |
| 게시글 작성    | POST   | /api/posts                  | Authorization: Bearer abcdefghijklmnopqrstuvwxyz | { </br> "title" : "title" </br> "contents" : "contents" </br> }      | { </br> "postId" : "postId", </br> "title" : "title" </br> "username" : "username", </br> "contents" : "contents", </br> "createdAt" : "createdAt", </br> "commentList" : [] </br> }                                                                                                                                                                                                                                                                                                                                                      |                                                  |
| 게시글 수정    | PUT    | /api/posts/{id}             | Authorization: Bearer abcdefghijklmnopqrstuvwxyz | { </br> "title" : "title"  </br> "contents" : "contents" </br> }     | { </br>"postId" : "postId", </br> "title" : "title" </br> "username" : "username", </br> "contents" : "contents", </br> "createdAt" : "createdAt", </br>  "commentList" : [] </br> }                                                                                                                                                                                                                                                                                                                                                      |                                                  |
| 게시글 삭제    | DELETE | /api/posts/{id}             | Authorization: Bearer abcdefghijklmnopqrstuvwxyz |                                                                      | { </br> "statuscode" : 200 </br> "msg" : "게시글 삭제 성공" </br> }                                                                                                                                                                                                                                                                                                                                                                                                                                                                              |                                                  |
| 댓글 작성     | POST   | /api/posts/{postId}/comments      | Authorization: Bearer abcdefghijklmnopqrstuvwxyz  | {  </br> "contents" : "contents" </br> }                             | { </br>"commentId" : "commentId", </br> "username" : "username", </br> "contents" : "contents", </br> "createdAt" : "createdAt" </br> }                                                                                                                                                                                                                                                                                                                                                                                                   |                                                  |
| 댓글 수정     | PUT    | /api/posts/{postId}/comments/{id} | Authorization: Bearer abcdefghijklmnopqrstuvwxyz   | { </br> "contents" : "contents" </br>}                               | { </br>"commentId" : "commentId", </br> "username" : "username", </br> "contents" : "contents", </br> "createdAt" : "createdAt" </br> }                                                                                                                                                                                                                                                                                                                                                                                                   |                                                  |
| 댓글 삭제     | DELETE | /api/posts/{postId}/comments/{id} | Authorization: Bearer abcdefghijklmnopqrstuvwxyz  |                                                                      | { </br> "statuscode" : 200 </br> "msg" : "댓글 삭제 성공" }                                                                                                                                                                                                                                                                                                                                                                                                                                                                                     |                                                  |



</div>
</details>
</br>

#### 심화 Lv.4

<details>
<summary><b>자세히</b></summary>
<div markdown="1">

예정

</div>
</details>
</br>

#### 심화 Lv.5

<details>
<summary><b>자세히</b></summary>
<div markdown="1">

예정

</div>
</details>