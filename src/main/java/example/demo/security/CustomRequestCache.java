package example.demo.security;

import org.springframework.security.web.savedrequest.HttpSessionRequestCache;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// keep track of the URL people are trying to access, if they are not authenticated , if you tried to access the list URL while you are unAuthenticated you'd get send over to a login screen and then as you login you'd get back to where you were trying to go
public class CustomRequestCache extends HttpSessionRequestCache {
    @Override
    public void saveRequest(HttpServletRequest request, HttpServletResponse response) {
        if (!SecurityUtils.isFrameworkInternalRequest(request)) {
            super.saveRequest(request,response);
        }
    }
}
