# 나만의 블로그 백엔드 서버

### Use Case
![use_case](https://github.com/mabyoungg/sparta-blog/assets/131260371/e44d942b-82e7-44cc-8b80-7f6dfac18c4c)

-------------------------------------------------
### 요구 사항
#### 입문
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
#### 숙련
예정
#### 심화
예정

-------------------------------------------------
### API 명세서
입문

| 기능      | Method      | URL                     | request                                                                               | response                                                                                                                         |
| --------- | ----------- | ---------------------- |---------------------------------------------------------------------------------------|----------------------------------------------------------------------------------------------------------------------------------|
| 전체 게시글 조회 | GET           |	/api/blogs |                                                                                       | "id" : "id", </br> "title" : "title" </br> "username" : "username", </br> "contents" : "contents", </br> "createdAt" : "createdAt"|
| 선택 게시글 조회 | GET          |/api/blogs/{id} |                                                                                       | "id" : "id", </br> "title" : "title" </br> "username" : "username", </br> "contents" : "contents", </br> "createdAt" : "createdAt"                                                                                                                          |
| 게시글 저장 | POST          |/api/blogs      | "title" : "title" </br> "username" : "username", </br> "contents" : "contents", </br> "password" : "password"| "id" : "id", </br> "title" : "title" </br> "username" : "username", </br> "contents" : "contents", </br> "createdAt" : "createdAt"                                                                                                                         |
| 게시글 수정 | PUT          |/api/blogs/{id}       | "title" : "title" </br> "username" : "username", </br> "contents" : "contents", </br> "password" : "password"                                                                                |"id" : "id", </br> "title" : "title" </br> "username" : "username", </br> "contents" : "contents", </br> "createdAt" : "createdAt"                                                                                                                     |
| 게시글 저장 | DELETE       |/api/blogs/{id}      | 	"password" : "password"                                                                                | "id" : "id"                                                                                                                      |