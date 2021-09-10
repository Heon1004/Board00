# SpringBoot & React Board Project
### Version
- Spring Boot 2.5.4
- node.js 16.4.1
- Graddle 7.?

### Dependency
- Security, JPA, Lombok

## Srping Boot Error Memo

Error : When i Create to Custom to JpaRepository Method. this Method is Static so can't change.
Fix : @Query(value="SELECT u FROM User AS u WHERE u.user_id = :user_id", <string>nativeQuery = true<string>) and Have to set @Param
