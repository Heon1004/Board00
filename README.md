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
  
  **index.js**
  //Transferring cookies between different domains.<br>
  axios.defaults.withCredentials = true;
  const cookies = new Cookies();
  axios.interceptors.request.use(
    config => {
        config.headers.token = cookies.get('token');
        return config;
    },
    error => {
        return Promise.reject(error);
    }
  )
  Now You don't have to keep sending tokens.<br>
  
  
