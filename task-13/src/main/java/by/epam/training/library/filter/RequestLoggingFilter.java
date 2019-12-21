package by.epam.training.library.filter;

import org.springframework.web.filter.CommonsRequestLoggingFilter;

import javax.servlet.http.HttpServletRequest;

public class RequestLoggingFilter extends CommonsRequestLoggingFilter {

    public RequestLoggingFilter() {
        super();
        this.setIncludeQueryString(true);
        this.setIncludePayload(true);
        this.setMaxPayloadLength(10000);
        this.setIncludeHeaders(true);
        this.setIncludeClientInfo(true);
        this.setBeforeMessagePrefix("Request: [");
    }

    @Override
    protected void afterRequest(HttpServletRequest request, String message) {
    }
}
