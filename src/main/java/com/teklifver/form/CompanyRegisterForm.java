package com.teklifver.form;

import com.teklifver.resetPassword.FieldMatch;
import org.hibernate.validator.constraints.NotEmpty;
import javax.validation.constraints.Size;


@FieldMatch(first = "password", second = "repeatPassword", message = "Şifreler birbiri ile eşleşmiyor.")
public class CompanyRegisterForm {

    @NotEmpty(message = "Şehir boş olmamalıdır.")
    String city;
    @NotEmpty(message = "İlçe boş olmamalıdır.")
    String town;
    @NotEmpty(message = "Mahalle boş olmamalıdır.")
    String line;
    @Size(min=3, max=30,message = "Telefon numarası 3-30 aralığında olmalıdır.")
    String phoneNumber;
    @NotEmpty(message = "Şirket adı boş olmamalıdır.")
    String companyName;
    @NotEmpty(message = "Şifre boş olmamalıdır.")
    String password;
    String categoryId;
    String subCategoryId;
    @NotEmpty(message = "Şifre tekrar boş olmamalıdır.")
    String repeatPassword;
    @NotEmpty(message = "Email boş olmamalıdır.")
    String mail;

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
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

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getSubCategoryId() {
        return subCategoryId;
    }

    public void setSubCategoryId(String subCategoryId) {
        this.subCategoryId = subCategoryId;
    }

    public String getRepeatPassword() {
        return repeatPassword;
    }

    public void setRepeatPassword(String repeatPassword) {
        this.repeatPassword = repeatPassword;
    }
}
