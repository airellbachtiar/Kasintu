import { showNotification } from "@mantine/notifications";
import React, { useEffect } from "react";
import { useNavigate } from "react-router-dom";

const Logout = (removeUser) => {

    let navigate = useNavigate();

    useEffect(() => {
        removeUser.removeUser();
        showNotification({
            title: 'You are logged out!'
          })
        navigate("/");
    }, [navigate, removeUser]);

    return (
        <>
        </>
    )
}

export default Logout;