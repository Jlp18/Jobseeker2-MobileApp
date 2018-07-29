package cn.tentact.nebula.test;

import java.util.Collections;
import java.util.Map;
import org.eclipse.xtext.xbase.lib.CollectionLiterals;
import org.eclipse.xtext.xbase.lib.Pair;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
@SuppressWarnings("all")
public class TestController {
  @RequestMapping("/hello")
  public Map<String, String> sayHello() {
    Pair<String, String> _mappedTo = Pair.<String, String>of("result", "你好世界");
    return Collections.<String, String>unmodifiableMap(CollectionLiterals.<String, String>newHashMap(_mappedTo));
  }
  
  @RequestMapping("/login")
  public Map<String, String> login(final String username, final String password) {
    Pair<String, String> _mappedTo = Pair.<String, String>of("username", username);
    Pair<String, String> _mappedTo_1 = Pair.<String, String>of("password", password);
    return Collections.<String, String>unmodifiableMap(CollectionLiterals.<String, String>newHashMap(_mappedTo, _mappedTo_1));
  }
}
