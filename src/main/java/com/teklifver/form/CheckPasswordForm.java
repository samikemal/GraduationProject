package com.teklifver.form;

import com.teklifver.resetPassword.FieldMatch;
import org.hibernate.validator.constraints.NotEmpty;

@FieldMatch(first = "password", second = "repeatPassword", message = "Şifreler birbiri ile eşleşmiyor.")
public class CheckPasswordForm {

    @NotEmpty(message = "Şifre boş olmamalıdır.")
    private String password;

    @NotEmpty(message = "Şifre tekrar boş olmamalıdır.")
    private String repeatPassword;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRepeatPassword() {
        return repeatPassword;
    }

    public void setRepeatPassword(String repeatPassword) {
        this.repeatPassword = repeatPassword;
    }
}
