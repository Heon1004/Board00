# SpringBoot & React Board Project

글쓴이는 2021/07 처음 리액트와 스프링부트를 접하였기에<br>
이 프로젝트에서는 JPA보다 Restful API를 만들어 React와 동작시키는것에 관점을 두고있습니다.<br>
기본적인 자바,HTML,CSS,SQL,PHP,JS공부를 한 상태입니다.<br>
틀린것이 있다면 지적해주시면 감사합니다.<br>

### Version
- Spring Boot 2.5.4
- node.js 16.4.1
- Graddle 7.?
---
### Dependency
- Security, JPA, Lombok
---
## Srping Boot

  Error : JPA Repository에서 직접적으로 쿼리를 짜서 사용하려할때 오류가 발생<br>
  Fix : @Query(value="SELECT u FROM User AS u WHERE u.user_id = :user_id", **nativeQuery = true**) nativeQuert를 추가함으로서 해결<br>

  Error : : 쿼리에 값을 지정해주지 않아 파라미터를 찾지못하여 오류가 발생<br>
  Fix : void findById(**@Param("user_id")**userId) @Param을 설정해줌으로서 해결<br>
  
#### Response의 불필요한 정보
  게시글 리스트 혹은 댓글 리스트를 가져올때 관계매핑이 되어있는 userId를 저장하기위한 user객체에<br>
  비밀번호 이외의 불필요한 개인정보들이 포함되어 Response되는게 확인되었다.<br>
  로직에서 userId만 등록해도 다른 정보들이 Entity에 포함되어 정보가 누출된다.<br>
  이러한 문제를 해결하기 위해<br>
  Entity연관관계를 포기하고 userId만 등록하기로 했다.<br>
  **(추가)** FK문제로 인하여 JPA로 연관관계를 매핑하는것을 포기하고<br>
  데이터베이스에서 외래키를 추가하는것으로 우선 해결.
  
## React
기본적인 Axios의 GET,POST 사용법
  #### Axios.GET
  axios.get(url,params) (참고로 get메소드는 body가 없다)<br>
  __axios.get(url,{ headers,params })__<br>
  
  예제
  await axios.get('/board/list',{<br>
  　　　　　　　headers:{<br>
 　　　　　　　　　'Content-Type': 'application/json',<br>
　　　　　　　　},<br>
　　　　　　　　params:{<br>
　　　　　　　　　params:params <br>
　　　　　　　　}<br>
                
 
  
  #### Axios.POST
  __axios.post(url,{headers},{params})__<br>
  
  예제
  axios.post('/user/login',{<br>
　　　　　　　　headers: {<br>
　　　　　　　　　　'Content-Type': 'application/json',<br>
　　　　　　　　　　'token' : cookie.get('token')<br>
　　　　　　　　} <br>
　　　　　　　},{ <br>
　　　　　　　　params:{<br>
　　　　　　　　　　userEmail: email,<br>
　　　　　　　　　　userPw: pw<br>
　　　　　　　　}<br>
  
  **index.js**에 추가한다.
  //다른 페이지에서도 쿠키를 사용하기 위함<br>
  axios.defaults.withCredentials = true;<br>
  
  const cookies = new Cookies();<br>
  axios.interceptors.request.use(<br>
　　config => {<br>
　　　　config.headers.token = cookies.get('token');<br>
　　　　return config;<br>
　　},<br>
　　 error => {<br>
　　　　return Promise.reject(error);<br>
　　}<br>
　)<br>
  이렇게하여 리퀘스트를 요청할때마다 토큰을 넣어서 보낼필요가 없다<br>

