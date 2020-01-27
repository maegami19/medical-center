$(document).ready(function () {

    var validLoginRegExp = /\S{3,18}/;
    var validNameRegExp = /\S{3,18}/;
    var validEmailRegExp = /\S+@\S+\.\S+/;
    var currentInputValue;
    $('#user-role').change(function (ev) {
        currentInputValue = $(this).val();
        if (currentInputValue === 'doctor') {
            $('.doctor-inputs').show();
            $('.patient-inputs').hide();
        } else if (currentInputValue === 'patient') {
            $('.patient-inputs').show();
            $('.doctor-inputs').hide();
        } else {
            $('.patient-inputs').hide();
            $('.doctor-inputs').hide();
        }
    });

    $('#submit-registration').click(function () {
        $('.validation-error').remove();
        var results = [];

        results.push(validateLogin());
        results.push(validateFirstName());
        results.push(validateLastName());
        results.push(validateEmail());
        var isAllValuesValid = true;

        for (var res in results) {
            if (results[res] === false) {
                isAllValuesValid = false;
                return;
            }
        }

        if (isAllValuesValid) {
            sendSignInRequest();
        }

    });

    function validateLogin() {
        return checkIsValidValue({
            'regex': validLoginRegExp,
            'val': $('#login').val(),
            'selector': '#login',
            'message': 'Incorrect login, must contain at least 3 and be shorter than 16 symbols'
        });
    }

    function validateFirstName() {
        return checkIsValidValue({
            'regex': validNameRegExp,
            'val': $('#firstname').val(),
            'selector': '#firstname',
            'message': 'Incorrect first name'
        });
    }

    function validateLastName() {
        return checkIsValidValue({
            'regex': validNameRegExp,
            'val': $('#lastname').val(),
            'selector': '#lastname',
            'message': 'Incorrect last name'
        });
    }

    function validateEmail() {
        return checkIsValidValue({
            'regex': validEmailRegExp,
            'val': $('#email').val(),
            'selector': '#email',
            'message': 'Incorrect email'
        });
    }

    function checkIsValidValue(cfg) {
        var res = cfg.regex.test(cfg.val);
        if (!res) {
            appendErrorMessage(cfg.selector, cfg.message);
        }
        return res;
    }

    function appendErrorMessage(elemSelector, message) {
        $(elemSelector).after(`<div class="validation-error" style="color:red">${message}</div>`);
    }

    function sendSignInRequest() {
        console.log('sent');
        $.ajax({
            type: 'post',
            url: '/register',
            data: {
                'username': $('#login').val(),
                'firstname': $('#firstname').val(),
                'lastname': $('#lastname').val(),
                'role': $('#user-role').val(),
                'email': $('#email').val(),
                'birthday_patient': $('#birthday').val(),
                'doctor_id': $('#patient-doctor').val(),
                'category': $('#doctor-category').val()
            }
        }).done(function (data) {
            $('#submit-registration').before(`<div>${data}</div>`);
            setTimeout(function () {
                window.location.reload(1);
            }, 3000);
        }).fail(function (data) {

        });
    }
});