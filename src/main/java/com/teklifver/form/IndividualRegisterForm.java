package com.teklifver.form;

import com.teklifver.resetPassword.FieldMatch;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;

@FieldMatch(first = "password", second = "repeatPassword", message = "Şifreler birbiri ile eşleşmiyor.")
public class IndividualRegisterForm {

    @NotEmpty(message = "Şifre boş olmamalıdır.")
    @NotNull
    String password;
    @NotEmpty(message = "Email boş olmamalıdır.")
    String mail;
    @NotEmpty(message = "İsim  boş olmamalıdır.")
    String name;
    @NotEmpty(message = "Soyisim boş olmamalıdır.")
    String lastName;
    @NotEmpty(message = "Şehir boş olmamalıdır.")
    String city;
    @NotEmpty(message = "İlçe boş olmamalıdır.")
    String town;
    @NotEmpty(message = "Açıklama boş olmamalıdır.")
    String line;
    @NotEmpty(message = "Telefon alanı boş olmamalıdır.")
    String phoneNumber;
    @NotEmpty(message = "Şifre tekrar boş olmamalıdır.")
    String repeatPassword;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public String getLine() {
        return line;
    }

    public void setLine(String line) {
        this.line = line;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getRepeatPassword() {
        return repeatPassword;
    }

    public void setRepeatPassword(String repeatPassword) {
        this.repeatPassword = repeatPassword;
    }
}
