# SpringBoot & React

###댓글 기능 구현

댓글 리스트를 가져오는 시점에서 한가지 문제점을 발견
**Entity**관계에서 Comment클래스에 User,Board를 그대로 가져오는것에서 발견됌.
Response값을 보면 Entity가 가진 값을 그대로 가져오기때문.
따라서 댓글 혹은 게시글을 가져오는데 불필요한 유저정보를 갖고오는것을 방지하기위해
userId, boardId만 설정하기로 하였다.
