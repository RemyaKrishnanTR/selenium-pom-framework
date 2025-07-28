package com.automation.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

public class RegexPatternTest {

    @Test(enabled = false)
    public void validateEmailPattern()
    {
        String email="test123@gmail.com";
        boolean isValid=email.matches("^[\\w.-]+@[\\w.-]+\\.\\w{1,3}");
        Assert.assertTrue(isValid,"invalid email");
    }

    @Test(enabled = false)
    public void validatePhoneNumberPattern()
    {
        String phoneNumber="9876353265";
        boolean isValid=phoneNumber.matches("^\\d{10}$");
        Assert.assertTrue(isValid,"invalid phone number");
    }

    @Test(enabled = false)
    public void validateZipCodePattern()
    {
        String zipCode="679503";
        boolean isValid=zipCode.matches("^\\d{4,6}$");
        Assert.assertTrue(isValid,"invalid zip code");
    }

    @Test(enabled = false)
    public void validateUserNamePattern()
    {
        String userName="abc567";
        boolean isValid=userName.matches("^[a-zA-z0-9]{3,10}$");
        Assert.assertTrue(isValid,"Username must be 3-10 alphanumeric characters");
    }


    @Test(enabled = false)
    public void validatePasswordPattern()
    {
        String password="abc$$567";
        boolean isValid=password.matches("^[a-zA-Z](?=.*[0-9])(?=.*[@^%$#]).{5,}$");
        Assert.assertTrue(isValid,"password doesn't meet requirements");
    }

    /*✅ Password Rules You Mentioned:

Should start with a letter (a-z or A-Z)
Must contain at least one special character from %, #, $, or @
Must contain at least one digit (0-9)
(Optionally) You may also enforce a minimum/maximum length

✅ Regex Pattern (with explanation):
^[a-zA-Z](?=.*[0-9])(?=.*[%#$@]).{5,}$
✅ Breakdown:
| Part           | Meaning                                                            |
| -------------- | ------------------------------------------------------------------ |
| `^`            | Start of the string                                                |
| `[a-zA-Z]`     | First character must be a letter                                   |
| `(?=.*[0-9])`  | Lookahead: at least one digit somewhere in the string              |
| `(?=.*[%#$@])` | Lookahead: at least one special character from `%#$@`              |
| `.{5,}`        | At least 5 characters total (you can change this to `{8,16}` etc.) |
| `$`            | End of string                                                      |
*/






}
