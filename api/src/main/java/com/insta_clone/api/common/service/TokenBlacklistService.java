package com.insta_clone.api.common.service;

import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author rua
 */

@Service
public class TokenBlacklistService {
    private final Set<String> blacklist = ConcurrentHashMap.newKeySet();

    // 로그아웃 등으로 블랙리스트 등록
    public void add(String token) {
        blacklist.add(token);
    }
    // 인증 필터에서 사용. 블랙리스트 포함 여부
    public boolean contains(String token) {
        return blacklist.contains(token);
    }
}
