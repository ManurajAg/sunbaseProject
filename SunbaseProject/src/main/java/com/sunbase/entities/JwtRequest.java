package com.sunbase.entities;


public class JwtRequest {

	private String email;
	private String password;
	
	
	@Override
	public String toString() {
		return "JwtRequest [email=" + email + ", password=" + password + "]";
	}

	public JwtRequest() {
		super();
		// TODO Auto-generated constructor stub
	}

	// Private constructor to enforce the use of the builder
    private JwtRequest(String email, String password) {
        this.email = email;
        this.password = password;
    }

    // Public static method to create an instance of the builder
    public static JwtRequestBuilder builder() {
        return new JwtRequestBuilder();
    }

    // Public static inner builder class
    public static class JwtRequestBuilder {
        private String email;
        private String password;

        // Setter methods for each field
        public JwtRequestBuilder email(String email) {
            this.email = email;
            return this;
        }

        public JwtRequestBuilder password(String password) {
            this.password = password;
            return this;
        }

        // Build method to create an instance of JwtRequest
        public JwtRequest build() {
            return new JwtRequest(email, password);
        }
    }

    // Getters for the fields
    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
