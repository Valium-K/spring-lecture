데이터 전송 방식
=============
### 정적 콘텐츠 방식
* resources/static 폴더에 정적 콘텐츠를 내려준다.

### MVC 방식 및 API 방식
```java
@Controller
public class HelloController {

    @GetMapping("hello")
    public String hello(Model model) {
        model.addAttribute("data", "hello");
        return "hello";
    }

    // mvc방식 - template 엔진 이용
    @GetMapping("hello-mvc")
    public String helloMve(@RequestParam(value = "name", required = false) String name, Model model) {
        model.addAttribute("name", name);
        return "hello-template";    // controller 가 viewResolver 에 return 한다.
    }

    // API 방식1 - data를 그대로 내려줌 즉 해당 uri로 접근하면 html 태그없이 hello null 만 나온다.
    @GetMapping("hello-string")
    @ResponseBody   // http의 body부에 해당 return을 직접 넣어준다. spring에서는 기본적으로 json 형태로 반환한다.
    public String helloString(@RequestParam(value = "name", required = false) String name) {
        return "hello " + name; // @ResponseBody 때문에 viewResolver가 아닌 http 바디부에 그대로 넣어 return한다.
    }

    // API 방식2 - 이번엔 Hello 객체를 넘겨줘본다. 접근시 json 파일을 받을 수 있다.
    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name) {
        Hello hello = new Hello();
        hello.setName(name);

        // @ResponseBody 때문에 viewResolver가 아닌 http 바디부에 그대로 넣어 return한다.
        // 이 때 String이 아닌 객체이기에 HttpMessageConverter가 json으로 변환 후 return한다.
        return hello;
    }

    public static class Hello {
        private String name;

        public String getName() { return name; }
        public void setName(String name) { this.name = name; }
    }
}
```