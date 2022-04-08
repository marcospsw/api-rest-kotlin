package com.donus.restapiforum.modules.common.config

import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.filter.OncePerRequestFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class JWTAuthenticationFilter(private val jwtUtils: JWTUtils) : OncePerRequestFilter() {
    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val token = request.getHeader("Authorization")
        val jwt = getTokenDetail(token)
        if (jwtUtils.isValid(jwt)) {
            val authentication = jwtUtils.getAuthenticated(jwt)
            SecurityContextHolder.getContext().authentication = authentication
        }
        filterChain.doFilter(request, response)
    }

    private fun getTokenDetail(token: String?): String? {
        return token?.let { jwt ->
            jwt.startsWith("Bearer ")
            jwt.substring(7, token.length)
        }
    }
}
