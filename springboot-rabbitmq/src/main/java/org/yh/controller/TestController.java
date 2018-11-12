package org.yh.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

	@RequestMapping("test/{i}")
	public String getUser(@PathVariable int i) {
		int j = 1 / i;
		return "result:" + j;
	}

	@RequestMapping("testage")
	public String test2(@RequestParam(value = "age") int age) {
		return "年龄为:" + age;
	}
}
