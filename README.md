# SpringBoot & React Board Project
### Version
- Spring Boot 2.5.4
- node.js 16.4.1
- Graddle 7.?
---
### Dependency
- Security, JPA, Lombok
---
## Srping Boot

  Error : When i Create to Custom to JpaRepository Method. this Method is Static so can't change.<br>
  Fix : @Query(value="SELECT u FROM User AS u WHERE u.user_id = :user_id", **nativeQuery = true**)<br>

  Error : :user_id can't find param
  Fix : void findById(**@Param("user_id")**userId)
  
  
## React Error

  When i want to get to Rest API.<br>
  axios.get(url,params) <br>
  i want to know how to send JWT Token in Request<br>
  Http -> Security -> Jwt Filter -> response <br>
  
  ##### axios.get : How to send it to GET.
  
  await axios.get('/board/list',{<br>
                headers:{<br>
                    'Content-Type': 'application/json',<br>
                },<br>
                params:{<br>
                    page:pageNow.current<br>
                }<br>
  
  
  ##### axios.post : How to send it to POST.
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
  
  **index.js**
  //Transferring cookies between different domains.<br>
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
  Now You don't have to keep sending tokens.<br>
  
  
