package com.sunbase.securityJWT;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter{

	private Logger logger = LoggerFactory.getLogger(OncePerRequestFilter.class);
    @Autowired
    private JwtHelper jwtHelper;


    @Autowired
    private UserDetailsService userDetailsService;
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		// Authorization

		String requestHeader = request.getHeader("Authorization");		//here we are trying to fetch token value from user
		// Bearer 2352345235sdfrsfgsdfsdf
		logger.info(" Header :  {}", requestHeader);	//basically shows header on log
		String username = null;
		String token = null;
		
		if (requestHeader != null && requestHeader.startsWith("Bearer")) {		//checks header is not empty and token is sent and if it starts with bearer then it is good
			// looking good
			token = requestHeader.substring(7);			//Since token will be like :- Authorization = Bearer asdfadfdf , so token value starts from 'a' which is 7th index thus we take substring(7)
			try {

				username = this.jwtHelper.getUsernameFromToken(token);		//JwtHelper class that we have created, helps to fetch username from token

			} catch (IllegalArgumentException e) {							//If something went wrong then exception is thrown	
				logger.info("Illegal Argument while fetching the username !!");
				e.printStackTrace();
			} catch (ExpiredJwtException e) {
				logger.info("Given jwt token is expired !!");
				e.printStackTrace();
			} catch (MalformedJwtException e) {
				logger.info("Some changed has done in token !! Invalid Token");
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();

			}

		} else {											//If header is empty or doesn't contains token then run this
			logger.info("Invalid Header Value !! ");
		}

		//Since we now have username thus we can verify that we have such user or not
		if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {			//checks username is not null and it is not authenticated yet then authenticate it, if fails then give error

			// fetch user detail from username
			UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);	//here stored user is loaded so that it can be checked with token user
			Boolean validateToken = this.jwtHelper.validateToken(token, userDetails);
			if (validateToken) {															//if validated then set authentication

				// set the authentication
				UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(		//this will tell whether our user is authenticated or not
						userDetails, null, userDetails.getAuthorities());
				authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(authentication);			

			} else {
				logger.info("Validation fails !!");
			}

		}

		filterChain.doFilter(request, response);	//forwards request

		
	}

}
