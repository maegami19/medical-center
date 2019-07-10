$(document).ready(function(){
    var currentInputValue;
    $('#user-role').change(function(ev){
        currentInputValue = $(this).val();
        if (currentInputValue === 'doctor') {
            $('.doctor-inputs').show();
            $('.patient-inputs').hide();
        }else if (currentInputValue === 'patient') {
            $('.patient-inputs').show();
            $('.doctor-inputs').hide();
        } else {
            $('.patient-inputs').hide();
            $('.doctor-inputs').hide();
        }
    });

    $('#submit-registration').click(function(){
        $.ajax({
            type: 'post',
            url : '/register',
            data: {
                'username' : $('#login').val(),
                'firstname' : $('#firstname').val(),
                'lastname' : $('#lastname').val(),
                'role' : $('#user-role').val(),
                'email' : $('#email').val(),
                'birthday_patient' : $('#birthday').val(),
                'doctor_id' : $('#patient-doctor').val(),
                'category' : $('#doctor-category').val()
            }
        });
    });
});