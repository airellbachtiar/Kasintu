import React, { useEffect, useState } from "react";
import SpinStretch from "react-cssfx-loading/lib/SpinStretch";
import Form from 'react-bootstrap/Form'
import FloatingLabel from 'react-bootstrap/FloatingLabel';
import "../components/styles/MyProfile.css";
import axios from "axios";
import { showNotification } from "@mantine/notifications";
import { useNavigate } from "react-router-dom";

const MyProfile = (props) => {

    const [myProfileState, setMyProfileState] = useState("loading");
    const [formValue, setFormValue] = useState({
        userID: "",
        username: "",
        email: "",
        newPassword: "",
        confirmPassword: ""
    });
    const [includePassword, setIncludePassword] = useState(false);

    useEffect(() => {
        props.checkTokenExpiration();
    }, [props]);

    useEffect(() => {
        setMyProfileState("loading");
        if (props.loggedUser !== null) {
            setMyProfileState("loaded");
            setFormValue({
                userID: props.loggedUser.userID,
                username: props.loggedUser.username,
                email: props.loggedUser.email,
                newPassword: "",
                confirmPassword: ""
            });
        }
        else {
            setMyProfileState("error");
        }
    }, [props.loggedUser]);

    const displayMyProfile = () => {
        switch (myProfileState) {
            case "loading":
                return (
                    <div className="myProfile-state">
                        <SpinStretch width="100px" height="100px" />
                    </div>
                )
            case "error":
                return (
                    <span data-cy="myprofile-error" className="myprofile-state">Error encountered, please try again!</span>
                )
            case "password-mismatch":
                return (
                    <span data-cy="myprofile-password-error" className="myprofile-state">Passwords do not match!</span>
                )
            case "loaded":
                return <></>
            default:
                break;
        }
    }

    const onChange = (event) => {
        setFormValue({
            ...formValue,
            [event.target.name]: event.target.value
        });
    }

    const navigate = useNavigate();

    const saveProfile = () => {
        setMyProfileState("loading");
        const token = localStorage.getItem("accessToken");

        if (includePassword && formValue.newPassword !== formValue.confirmPassword) {
            setMyProfileState("password-mismatch");
            return;
        }
        const password = includePassword ? formValue.newPassword : null;

        var data = JSON.stringify({
            "userID": formValue.userID,
            "username": formValue.username,
            "email": formValue.email,
            "password": password
        });

        var config = {
            method: 'put',
            url: `http://localhost:8080/users/${formValue.userID}`,
            headers: {
                'Content-Type': 'application/json',
                'Authorization': 'Bearer ' + token
            },
            data: data
        };

        axios(config)
            .then(function () {
                showNotification({
                    title: "Profile has been updated!"
                })
                props.updateUser();
                navigate("/");
            })
            .catch(function (error) {
                setMyProfileState("error");
                console.log(error);
            });
    }

    return (
        <>
            <div className="myprofile">
                <div className="myprofile-container">
                    <div className="myprofile-header">
                        <h1>My Profile</h1>
                    </div>
                    <div className="myprofile-content">
                        <div className="myprofile-content-input">
                            <FloatingLabel label="Email">
                                <Form.Control data-cy="myprofile-input-email" type="text" placeholder="Email" name="email" onChange={onChange} value={formValue.email} required />
                            </FloatingLabel>
                        </div>
                        <div className="myprofile-content-input">
                            <FloatingLabel label="Username">
                                <Form.Control data-cy="myprofile-input-username" type="text" placeholder="Username" name="username" onChange={onChange} value={formValue.username} required />
                            </FloatingLabel>
                        </div>
                        <div className="myprofile-content-input">
                            <FloatingLabel label="New Password">
                                <Form.Control data-cy="myprofile-input-password" type="password" placeholder="New Password" name="newPassword" onChange={onChange} value={formValue.newPassword} disabled={!includePassword} />
                            </FloatingLabel>
                        </div>
                        <div className="myprofile-content-input">
                            <FloatingLabel label="Confirm Password">
                                <Form.Control data-cy="myprofile-input-confirm-password" type="password" placeholder="Confirm Password" name="confirmPassword" onChange={onChange} value={formValue.confirmPassword} disabled={!includePassword} />
                            </FloatingLabel>
                        </div>
                        <div className="myprofile-content-input">
                            <Form.Check
                                data-cy="myprofile-input-checkbox"
                                type="switch"
                                label="Change Password"
                                className="myprofile-content-switch"
                                value={includePassword}
                                onChange={() => setIncludePassword(!includePassword)}
                            />
                        </div>
                    </div>
                    <div className="myprofile-footer">
                        <button data-cy="myprofile-save" onClick={saveProfile}>Save</button>
                    </div>
                </div>
            </div>
            {displayMyProfile()}
        </>
    );
}

export default MyProfile;