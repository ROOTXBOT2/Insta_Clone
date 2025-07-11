//package com.insta_clone.api.auth.controller;
//
//import com.insta_clone.api.auth.dto.LoginRequestDto;
//import com.insta_clone.api.auth.dto.LoginResponseDto;
//import com.insta_clone.api.common.dto.ApiResponse;
//import com.insta_clone.api.common.service.JwtTokenProvider;
//import com.insta_clone.api.common.service.TokenBlacklistService;
//import io.swagger.v3.oas.annotations.parameters.RequestBody;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RestController;
//
///**
// * @author rua
// */
//@RequiredArgsConstructor
//@RestController("/auth")
//public class AuthController {
//    private final JwtTokenProvider jwtTokenProvider;
//    private final TokenBlacklistService blacklistService;
//
//    @PostMapping("/login")
//    public ResponseEntity<ApiResponse<LoginResponseDto>> login(@RequestBody LoginRequestDto request) {
//
//        LoginResponseDto data = ...;
//        return ResponseEntity.ok(ApiResponse.success("로그인 성공", data));
//
//    }
//
//    @PostMapping("/idVerification")
//    public
//
//    @PostMapping("/join")
//    public
//
//    @PostMapping("/logout")
//    public ResponseEntity<ApiResponse<LoginResponseDto>>
//
//}
