import React, { useEffect } from "react";
import { NavLink } from "react-router-dom";
import BannerCard from "../components/BannerCard";
import "../components/styles/Summon.css";

const Summon = (props) => {

    useEffect(() => {
        props.checkTokenExpiration();
    }, [props]);

    if (props.loggedUser !== null) {
        return (
            <div className="summon">
                <div className="banner-container">
                    <BannerCard loggedUser={props.loggedUser}/>
                </div>
            </div>
        )
    }
    else {
        return (
            <div className="summon">
                <div className="summon-container">
                    <div className="summon-login-error">
                        <NavLink to={"/UserAccess"}>
                            Login to summon creatures.
                        </NavLink>
                    </div>
                </div>
            </div>
        )
    }
}

export default Summon;