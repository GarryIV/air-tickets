package com.garryiv.air_tickets.ui.auth;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
public class MeController {
    @RequestMapping({ "/user", "/me" })
    public Map<String, Object> user(Principal principal) {
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("name", principal.getName());
        map.put("authorities", getAuthorities(principal));
        return map;
    }

    private Collection<String> getAuthorities(Principal principal) {
        return ((Authentication)principal).getAuthorities().stream()
                .map(a -> a.getAuthority())
                .collect(Collectors.toList());
    }
}
