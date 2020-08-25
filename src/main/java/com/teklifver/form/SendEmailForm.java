package com.teklifver.form;

import org.hibernate.validator.constraints.NotEmpty;

public class SendEmailForm implements Form {

    @NotEmpty(message = "Email boş olmamalıdır.")
    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
