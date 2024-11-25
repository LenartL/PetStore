package hu.lenartl.petstore.security.filter;

import hu.lenartl.petstore.security.authentication.ApiKeyAuthentication;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.lang.NonNull;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class ApiKeyFilter extends OncePerRequestFilter {
    @Value("${api.key:default-api-key}")
    private String apiKey;

    private static final String HEADER = "x-api-key";

    @Override
    protected void doFilterInternal(HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain filterChain) throws ServletException, IOException {
        String apiKeyHeader = request.getHeader(HEADER);
        if (apiKey.equals(apiKeyHeader)) {
            SecurityContextHolder.getContext()
                    .setAuthentication(new ApiKeyAuthentication(apiKey, AuthorityUtils.NO_AUTHORITIES));
            filterChain.doFilter(request, response);
        } else {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
        }
    }
}
