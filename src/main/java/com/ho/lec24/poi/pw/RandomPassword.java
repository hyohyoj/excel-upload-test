package com.ho.lec24.poi.pw;

import java.security.SecureRandom;
import java.util.Date;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RandomPassword {

	@Bean
	public String getRandomPw(int size) {
		char[] charSet = new char[] { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F',
				'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a',
				'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v',
				'w', 'x', 'y', 'z', '!', '@', '#', '$', '%', '^', '&' };

		StringBuffer sb = new StringBuffer();
		SecureRandom sr = new SecureRandom();
		sr.setSeed(new Date().getTime()); // 동일한 seed값이 생기지 않도록 함

		int index = 0;
		int length = charSet.length;

		for (int i = 0; i < size; i++) {
			index = sr.nextInt(length);
			sb.append(charSet[index]);
		}

		return sb.toString();
	}

	@Bean
	public int getPwSize() {
		return (int) Math.floor((Math.random() * 6) + 10); // 10-15 사이 랜덤
	}

}
