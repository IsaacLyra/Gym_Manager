package br.com.firstclass.spring_boot_essentials.config;

import jakarta.annotation.Nullable;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final TokenProvider tokenProvider;
    private final UserDetailsService userDetailsService; // interface que extrai username


    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException{
        return request.getServletPath().startsWith("/v1/auth/");
    }

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,
                                    @NonNull    HttpServletResponse response,
                                    @NonNull    FilterChain filterChain) throws ServletException, IOException {

        String autozirationHeader = request.getHeader("Authorization");

    if(autozirationHeader == null || !autozirationHeader.startsWith("Bearer ")){
        filterChain.doFilter(request, response);
        return;
        }

        String token = autozirationHeader.substring(7);

        if(tokenProvider.isTokenValid(token)){
            String username = tokenProvider.getUsername(token);
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);

            UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        }


         filterChain.doFilter(request, response);

    }
}
