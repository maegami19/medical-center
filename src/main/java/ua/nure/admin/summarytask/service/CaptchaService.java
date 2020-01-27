package ua.nure.admin.summarytask.service;

public interface CaptchaService {

    /**
     * This method allows you to verify the correctness of the entered google recaptcha.
     *
     * @param gRecaptchaResponse - answer from captcha.
     * @return result of checking.
     */
    boolean verify(String gRecaptchaResponse);
}
