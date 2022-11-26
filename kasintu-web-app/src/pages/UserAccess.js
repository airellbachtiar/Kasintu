import React, { useState } from "react";
import "../components/styles/UserAccess.css";
import Form from 'react-bootstrap/Form';
import FloatingLabel from 'react-bootstrap/FloatingLabel';
import axios from "axios";
import { useNavigate } from "react-router-dom";
import SpinStretch from "react-cssfx-loading/lib/SpinStretch";
import { showNotification } from '@mantine/notifications';

const UserAccess = (updateUser) => {

    const [loginState, setLoginState] = useState(true);
    const [loginForm, setLoginForm] = useState({
        username: "",
        password: ""
    });
    const [loginCheck, setLoginCheck] = useState(null);

    const [registerForm, setRegisterForm] = useState({
        username: "",
        password: "",
        confirmPassword: "",
        email: ""
    });
    const [registerCheck, setRegisterCheck] = useState(null);

    let navigate = useNavigate();

    const onChangeLoginForm = (e) => {
        setLoginForm({
            ...loginForm,
            [e.target.name]: e.target.value
        })
    }

    const onChangeRegisterForm = (e) => {
        setRegisterForm({
            ...registerForm,
            [e.target.name]: e.target.value
        })
    }

    const chooseLogin = () => {
        setLoginState(true);
    }

    const chooseRegister = () => {
        setLoginState(false);
    }

    const onSubmitLoginForm = (e) => {
        e.preventDefault();
        setLoginCheck("loading");
        var data = JSON.stringify({
            "username": loginForm.username,
            "password": loginForm.password
        });

        var config = {
            method: 'post',
            url: 'http://localhost:8080/login',
            headers: {
                'Content-Type': 'application/json',
                "Access-Control-Allow-Origin": "*"
            },
            data: data
        };

        axios(config)
            .then(function (response) {
                localStorage.setItem("accessToken", response.data.accessToken);
                updateUser.updateUser();
                showNotification({
                    title: 'You are logged in!'
                })
                navigate("/");
            })
            .catch(function (error) {
                setLoginCheck("error");
                console.log(error);
            });
    }

    const onSubmitRegisterForm = (e) => {
        e.preventDefault();
        setRegisterCheck("loading");
        if (registerForm.password === registerForm.confirmPassword) {
            var data = JSON.stringify({
                "username": registerForm.username,
                "email": registerForm.email,
                "password": registerForm.password
            });

            var config = {
                method: 'post',
                url: 'http://localhost:8080/players',
                headers: {
                    'Content-Type': 'application/json',
                    "Access-Control-Allow-Origin": "*"
                },
                data: data
            };

            axios(config)
                .then(function () {
                    setRegisterForm(
                        {
                            username: "",
                            password: "",
                            confirmPassword: "",
                            email: ""
                        }
                    );
                    setRegisterCheck("success");
                    chooseLogin();
                })
                .catch(function (error) {
                    setRegisterCheck("error");
                    console.log(error);
                });
        }
        else {
            setRegisterForm({
                ...registerForm,
                password: "",
                confirmPassword: ""
            });
            setRegisterCheck("passwordError");
        }
    }

    const loginNotification = () => {
        switch (loginCheck) {
            case "loading":
                return (
                    <div className="user-access-form-loading">
                        <SpinStretch />
                    </div>
                )
            case "error":
                return (
                    <div data-cy="user-access-login-error" className="user-access-form-error">
                        <p>Login failed! Please try again!</p>
                    </div>
                )
            default:
                break;
        }
    }

    const registerNotification = () => {
        switch (registerCheck) {
            case "loading":
                return (
                    <div className="user-access-form-loading">
                        <SpinStretch />
                    </div>
                )
            case "error":
                return (
                    <div data-cy="user-access-register-error" className="user-access-form-error">
                        <p>Registration failed! Please try again!</p>
                    </div>
                )
            case "passwordError":
                return (
                    <div data-cy="user-access-register-password-error" className="user-access-form-error">
                        <p>Password does not match! Please try again!</p>
                    </div>
                )
            default:
                break;
        }
    }

    return (
        <div className="user-access-form">
            <div className="user-access-container">
                <div className="button-container">
                    <span data-cy="user-access-login-button" className={loginState ? "button-login-active" : "button-login"} onClick={chooseLogin}>Login</span>
                    <span data-cy="user-access-register-button" className={!loginState ? "button-register-active" : "button-register"} onClick={chooseRegister}>Register</span>
                </div>
                {loginState ? (
                    <form className="login-form" onSubmit={onSubmitLoginForm}>
                        <div className="login-form-content">
                            <div className="login-form-content-input">
                                <FloatingLabel label="Username">
                                    <Form.Control data-cy="login-input-username" type="text" placeholder="Username" name="username" onChange={onChangeLoginForm} value={loginForm.username} required />
                                </FloatingLabel>
                            </div>
                            <div className="login-form-content-input">
                                <FloatingLabel label="Password">
                                    <Form.Control data-cy="login-input-password" type="password" placeholder="Password" name="password" onChange={onChangeLoginForm} value={loginForm.password} required />
                                </FloatingLabel>
                            </div>
                        </div>
                        {loginNotification()}
                        <div className="login-form-content-button">
                            <button data-cy="user-access-login" className="button-login">Login</button>
                        </div>
                    </form>
                ) : (
                    <form className="register-form" onSubmit={onSubmitRegisterForm}>
                        <div className="register-form-content">
                            <div className="register-form-content-input">
                                <FloatingLabel label="Email">
                                    <Form.Control data-cy="register-input-email" type="text" placeholder="Email" name="email" onChange={onChangeRegisterForm} value={registerForm.email} required />
                                </FloatingLabel>
                            </div>
                            <div className="register-form-content-input">
                                <FloatingLabel label="Username">
                                    <Form.Control data-cy="register-input-username" type="text" placeholder="Username" name="username" onChange={onChangeRegisterForm} value={registerForm.username} required />
                                </FloatingLabel>
                            </div>
                            <div className="register-form-content-input">
                                <FloatingLabel label="Password">
                                    <Form.Control data-cy="register-input-password" type="password" placeholder="Password" name="password" onChange={onChangeRegisterForm} value={registerForm.password} required />
                                </FloatingLabel>
                            </div>
                            <div className="register-form-content-input">
                                <FloatingLabel label="Confirm Password">
                                    <Form.Control data-cy="register-input-confirm-password" type="password" placeholder="Confirm Password" name="confirmPassword" onChange={onChangeRegisterForm} value={registerForm.confirmPassword} required />
                                </FloatingLabel>
                            </div>
                        </div>
                        {registerNotification()}
                        <div className="register-form-content-button">
                            <button data-cy="user-access-register" className="button-register">Register</button>
                        </div>
                    </form>
                )}
            </div>
        </div>
    )
}

export default UserAccess;