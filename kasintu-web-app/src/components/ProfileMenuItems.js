import React from "react";
import { NavLink } from "react-router-dom";
import {ProfileMenuItemDataAfterLogin} from "./datas/ProfileMenuItemDataAfterLogin";

const ProfileMenuItems = (toggle) => {

    const ItemsSelection = (props) => {
        return(
            <div className="menu-items">
                {props.data.map((item, index) => {
                    return(
                        <NavLink data-cy={item.datacy} key={index} to={item.path} className={item.className} onClick={toggle.showProfileMenu}>
                            <span>{item.title}</span>
                        </NavLink>
                    )
                })}
            </div>
        )
    }

    return(
        <>
            <ItemsSelection data={ProfileMenuItemDataAfterLogin} />
        </>
    )
}

export default ProfileMenuItems;