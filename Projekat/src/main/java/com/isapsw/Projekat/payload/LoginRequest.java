package com.isapsw.Projekat.payload;

import javax.validation.constraints.NotBlank;

public class LoginRequest {
        @NotBlank(message = "Username ne sme ostati prazan")
        private String username;
        @NotBlank(message = "Password ne sme ostati prazan")
        private String password;

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
}
