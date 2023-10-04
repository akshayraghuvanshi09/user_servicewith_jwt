package com.ecommerce.user.controller;

import com.ecommerce.user.dto.AuthenticationRequest;
import com.ecommerce.user.dto.AuthenticationResponse;
import com.ecommerce.user.dto.LogoutRequest;
import com.ecommerce.user.dto.RefreshTokenRequest;
import com.ecommerce.user.service.impl.AuthenticationService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/api/v1")
@RestController
public class AuthController {

	@Autowired
	private final AuthenticationService authenticationService;
	
	@PostMapping("/login")
	public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request){
		AuthenticationResponse authenticationResponse = authenticationService.authenticate(request);
		return ResponseEntity.ok(authenticationResponse);
	}
	
	@PostMapping("/refreshToken")
	public ResponseEntity<AuthenticationResponse> refreshToken(@RequestBody RefreshTokenRequest request) {
		AuthenticationResponse authenticationResponse = authenticationService.generateRefreshToken(request);
		return ResponseEntity.ok(authenticationResponse);
		
	}
	
	@PostMapping("/logout")
	public ResponseEntity<?> logOut(@RequestBody LogoutRequest logoutRequest ,HttpServletRequest request, HttpServletResponse response) {
		 return authenticationService.logout(logoutRequest,request, response);
}

}