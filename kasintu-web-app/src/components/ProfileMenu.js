import React from "react";
import "./styles/ProfileMenu.css"

const ProfileMenu = (props) => {

    return(
        <div className="profile-menu-container">
            <div data-cy="profile-menu" className="profile-button" onClick={props.showProfileMenu}>
                {props.icon}
            </div>
            {props.profileMenuState && props.children}
        </div>
    );
}

export default ProfileMenu;