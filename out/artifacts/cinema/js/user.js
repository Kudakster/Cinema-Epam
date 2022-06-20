const MIN_LOGIN_LENGTH = 4;
const MAX_LOGIN_LENGTH = 30;

const MIN_NAME_LENGTH = 2;
const MAX_NAME_LENGTH = 30;

const LOGIN = "Login";
const FIRSTNAME = "Firstname";
const SURNAME = "Surname";

$(document).ready(function () {
    //Login
    requiredLogin("login", LOGIN, MIN_LOGIN_LENGTH, MAX_LOGIN_LENGTH, undefined, undefined, restrictNonEnglishAndNumbers); //Must be first, import!!!

    //Firstname
    requiredFirstname("firstname", FIRSTNAME, MIN_NAME_LENGTH, MAX_NAME_LENGTH, undefined, undefined, restrictNonEnglish);

    //Surname
    requiredSurname("surname", SURNAME, MIN_NAME_LENGTH, MAX_NAME_LENGTH, undefined, undefined, restrictNonEnglish);

    requiredAllFormValid();
});

function required(idElement, formName, minLength, maxLength, regex, regexMessage, function_parameter) {
    $("#" + idElement).on('input', function () {
        let value = document.getElementById(idElement).value
        let value_default = document.getElementById(idElement).defaultValue;

        function_parameter(idElement);
        createElement(idElement); //Create and check if exist (not good)
        checkInput(value, formName, idElement, minLength, maxLength, regex, regexMessage);

        if (value_default === document.getElementById(idElement).value) {
            setDefault(idElement)
        }
    })
}

function requiredLogin(idElement, formName, minLength, maxLength, regex, regexMessage, function_parameter) {
    $("#" + idElement).on('input', function () {
        let value = document.getElementById(idElement).value
        let LOGIN_OLD = document.getElementById(idElement).defaultValue;

        function_parameter(idElement);
        createElement(idElement); //Create and check if exist (not good)
        checkInput(value, formName, idElement, minLength, maxLength, regex, regexMessage);

        if (LOGIN_OLD === document.getElementById(idElement).value) {
            setDefault(idElement)
        } else {
            validateLogin();
        }
    })
}

function requiredFirstname(idElement, formName, minLength, maxLength, regex, regexMessage, function_parameter) {
    required(idElement, formName, minLength, maxLength, regex, regexMessage, function_parameter);
}

function requiredSurname(idElement, formName, minLength, maxLength, regex, regexMessage, function_parameter) {
    required(idElement, formName, minLength, maxLength, regex, regexMessage, function_parameter);
}

function requiredAllFormValid() {
    $("#basic-form").on('change', function () {
        let formArray = [{id: "login"}, {id: "firstname"}, {id: "surname"}];
        let n = 0;

        for (let obj of formArray) {
            let classAttr = document.getElementById(obj.id).getAttribute("class");
            if (classAttr === "form-control invalid") {
                document.getElementById("submit-button").disabled = true;
                return;
            } else if (classAttr === "form-control valid") {
                document.getElementById("submit-button").disabled = false;
            } else {
                n++;
            }
        }

        if (n === 3) {
            document.getElementById("submit-button").disabled = true;
        }
    })
}

function validateLogin() {
        let login = document.getElementById("login").value

        if (login.length >= MIN_LOGIN_LENGTH) {
            $.ajax({
                    url: "main",
                    type: "get",
                    async: false,
                    data: {"command": "validateLogin", "login": login},
                    dataType: "html",
                    success: function (data) {
                        if (data !== "") {
                            setInvalid(data, "login");
                        } else {
                            setValid("login");
                        }
                    },
                }
            )
        }
}

function restrictNonEnglishAndNumbers(id) {
    let element = document.getElementById(id)
    element.value = element.value.replace(/[^A-Za-z0-9]/g, '');
}

function restrictNonEnglish(id) {
    let element = document.getElementById(id)
    element.value = element.value.replace(/[^A-Za-z]/g, '');
}

function setInvalid(data, idInput) {
    let idLabel = idInput + "-valid";
    $("#" + idInput).attr("class", "form-control invalid");
    $("#" + idLabel).attr("style", "display: inline-block;");
    document.getElementById(idLabel).innerText = data;
}

function setValid(idInput) {
    let idLabel = idInput + "-valid";
    $("#" + idInput).attr("class", "form-control valid");
    $("#" + idLabel).attr("style", "display: none;");
}

function setDefault(idInput) {
    let idLabel = idInput + "-valid";
    $("#" + idInput).attr("class", "form-control");
    $("#" + idLabel).attr("style", "display: none;");
}

function createElement(forElement) {
    let idLabel = forElement + "-valid";
    let idForm = forElement + "-form";

    if (document.getElementById(idLabel) === null) {
        let label = document.createElement("label");
        label.setAttribute("for", forElement);
        label.setAttribute("id", idLabel);
        label.setAttribute("class", "invalid")
        let element = document.getElementById(idForm);
        element.appendChild(label);
    }
}

function checkInput(value, formName, idForm, minLength, maxLength, regex, regexMessage) {
    const MUST_BE_INPUT = formName + " must be input!";
    let MUST_BE_SHORTER = formName + " must be shorter that " + maxLength + " symbols";
    let MUST_BE_LONGER = formName + " must be longer that " + minLength + " symbols";
    const MUST_CONTAINS = formName + " must contains " + minLength + " symbols";

    if (minLength === maxLength) {
        MUST_BE_SHORTER = MUST_CONTAINS;
        MUST_BE_LONGER = MUST_CONTAINS;
    }

    if (value === "") {
        setInvalid(MUST_BE_INPUT, idForm);
    } else if (value.length < minLength) {
        setInvalid(MUST_BE_LONGER, idForm);
    } else if (value.length > maxLength) {
        setInvalid(MUST_BE_SHORTER, idForm);
    } else if (regex !== undefined && !regex.test(value)) {
        setInvalid(regexMessage, idForm);
    } else {
        setValid(idForm);
    }
}