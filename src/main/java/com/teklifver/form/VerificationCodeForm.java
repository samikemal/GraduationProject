package com.teklifver.form;

import org.hibernate.validator.constraints.NotEmpty;

public class VerificationCodeForm implements Form {

    @NotEmpty(message = "Code alanı boş olmamalıdır.")
    private String verificationCode;

    public String getVerificationCode() {
        return verificationCode;
    }

    public void setVerificationCode(String verificationCode) {
        this.verificationCode = verificationCode;
    }
}
