package main.java.com.check.rest.controller;

import com.check.model.CheckApiHeaders;
import com.check.model.ClientTypes;
import main.java.com.check.rest.error.ErrorCodes;
import main.java.com.check.rest.error.ValidationException;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Eugene on 13.02.14.
 */
@Component
public class CheckInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        String uuid = httpServletRequest.getHeader(CheckApiHeaders.UUID);
        if (StringUtils.isEmpty(uuid)) {
            throw new ValidationException(ErrorCodes.UUID_IS_EMPTY);
        }
        String clientType = httpServletRequest.getHeader(CheckApiHeaders.CLIENT_TYPE);
        if (clientType == null || (!clientType.equals(ClientTypes.ANDROID) && !clientType.equals(ClientTypes.IOS))) {
            throw new ValidationException(ErrorCodes.INVALID_CLIENT_TYPE);
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
    }
}
