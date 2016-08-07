package com.garryiv.air_tickets.ui.auth;

import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@RestController
public class StaffLoginSuccessController {

    private RequestCache requestCache = new HttpSessionRequestCache();

    @RequestMapping("/login/staff/success")
    public Map<String, String> redirectInfo(HttpServletRequest request, HttpServletResponse response) {
        Map<String, String> res = new HashMap<>();
        SavedRequest savedRequest = requestCache.getRequest(request, response);
        requestCache.removeRequest(request, response);

        if (savedRequest == null) {
            return res;
        }

        String[] redirectUrls = savedRequest.getParameterValues("redirect_uri");

        if (redirectUrls == null || redirectUrls.length != 1) {
            return res;
        }

        res.put("redirect_uri", redirectUrls[0]);

        return res;
    }
}
