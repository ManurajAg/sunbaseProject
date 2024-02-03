package com.sunbase.entities;

//Model for response to be sent

public class JwtResponse {

    private String jwtToken;
    private String username;

    
    
    @Override
	public String toString() {
		return "JwtResponse [jwtToken=" + jwtToken + ", username=" + username + "]";
	}

	public JwtResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	// Private constructor to enforce the use of the builder
    private JwtResponse(String jwtToken, String username) {
        this.jwtToken = jwtToken;
        this.username = username;
    }

    // Public static method to create an instance of the builder
    public static JwtResponseBuilder builder() {
        return new JwtResponseBuilder();
    }

    // Public static inner builder class
    public static class JwtResponseBuilder {
        private String jwtToken;
        private String username;

        // Setter methods for each field
        public JwtResponseBuilder jwtToken(String jwtToken) {
            this.jwtToken = jwtToken;
            return this;
        }

        public JwtResponseBuilder username(String username) {
            this.username = username;
            return this;
        }

        // Build method to create an instance of JwtResponse
        public JwtResponse build() {
            return new JwtResponse(jwtToken, username);
        }
    }

    // Getters for the fields
    public String getJwtToken() {
        return jwtToken;
    }

    public String getUsername() {
        return username;
    }
}
