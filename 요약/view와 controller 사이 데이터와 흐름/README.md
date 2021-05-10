View와 Controller 사이 데이터와 흐름
================================

### Home.html
```html
<!-- home 화면에서 회원가입을 하면 /members/new 경로를-->
<!-- Mapping 하는 controller 를 찾음 --> 
<a href="/members/new">회원 가입</a><br/>
```

### MemberController
```java
// /members/new 를 Get방식으로 받는다.
// 이 후 resources/templates 폴더에서 /members/createMemberForm.html을 내준다.
@Controller
public class MemberController {
    
    // ...
    
    @GetMapping("/members/new")
    public String createForm() {
        return "/members/createMemberForm";
    }
    
    // ...
}
```

### createMemberForm.html
```html
<!-- form 태그 안의 input 태그의 'name 속성의 값'을-->
<!-- /members/new 경로를 post 방식으로 받는 컨트롤러를 찾는다.-->
<!-- 이 때 name 속성 값은 받은 객체의 필드변수 명과 같아야한다. -->
<body>
  <div class="container">
    <form action="/members/new" method="post">
      <div class="form-group">
        <label for="name">이름</label>
        <input type="text" id="name" name="name" placeholder="이름을 입력하세요.">
      </div>
      <button type="submit">등록</button>
    </form>
  </div>
</body>
```

### MemberController
``` java
@Controller
public class MemberController {

    // ...
    
    @PostMapping("/members/new")
    // 넘어온 "name"을 memberForm으로 받았다.
    // memberForm은 String name을 가지는 빈이다.
    public String create(MemberForm memberForm) {
        
        Member member = new Member();
        member.setName(memberForm.getName());

        System.out.println("name = " + member.getName());
        memberService.join(member);

        // /members/createdMember.html을 호출한다.
        return "/members/createdMember";
    }
    
    // ...
}

// 빈 형식(setter, getter가 필수)으로 클래스를 정의한다.
public class MemberForm {
    private String name;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}
```

### createdMember.html
```html
<body>
<!-- 회원가입을 잘 하고 확인페이지를 넘겨준다. -->
   맴버 등록됨
</body>
```

-------------------


