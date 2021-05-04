TestCase 관련 요약
===============
* `org.junit.jupiter.api.Test` 패키지를 일반적으로 이용한다.
* TestCase는 서로 의존관계여선 안된다. 실행 순서 때문에 오류가 날 경우  `@AfterEach` 를 이용해 콜백 메소드를 만들어 활용한다.
	``` java
	@AfterEach
	public void afterEach() {
		// 이전 TestCase 때문에 발생한 문제를 이곳에서 처리.
		// 각 TestCase가 실행된 후 이 메소드가 실행된다.
	}
	```

