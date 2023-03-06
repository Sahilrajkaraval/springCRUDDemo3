package com.nilesh.springCRUD.security;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PassGen {
	public static void main(String[] args) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		
		System.out.println(encoder.encode("nilesh"));
		System.out.println(encoder.encode("admin"));
	}
}

//$2a$10$DL6iUG1syFvCk5pKqGz2ve3K.aww7VSTVE2xlaEYZMrFI2y3Fauk2
//$2a$10$1P.Z5of42j/ylTKhCCna7OCkRsmNObBguqz84dw04RyQp.aFscvJS
