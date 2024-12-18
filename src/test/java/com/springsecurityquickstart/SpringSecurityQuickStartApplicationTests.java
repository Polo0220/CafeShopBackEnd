package com.springsecurityquickstart;

import com.springsecurityquickstart.domain.User;
import com.springsecurityquickstart.mapper.PermMapper;
import com.springsecurityquickstart.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;

@SpringBootTest
//@ImportAutoConfiguration(exclude = {WebMvcAutoConfiguration.class, SpringDocConfiguration.class})
class SpringSecurityQuickStartApplicationTests {

	@Autowired
	private UserMapper userMapper;

	@Test
	public void TestBCryptPasswordEncoder(){
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//		String encode = passwordEncoder.encode("1234");
//		String encode2 = passwordEncoder.encode("1234");
//		System.out.println(encode);
//		System.out.println(encode2);

		boolean matches = passwordEncoder.matches("1234", "$2a$10$L9M3Gq8pOaCM6qszzOH6ee6vVRxta8FB.RoGIL5YBuPF79pkzbTX2");
		System.out.println(matches);
	}

	@Autowired
	private PermMapper menuMapper;

	@Test
	public void testSelectPermsByUserId(){
		List<String> list = menuMapper.selectPermsByUserId(1L);
		System.out.println(list);
	}

	@Test
	public void testUserMapper(){
		List<User> users = userMapper.selectList(null);
		System.out.println(users);
	}


}
