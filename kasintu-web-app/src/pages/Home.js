import React from "react";
import { NavLink } from "react-router-dom";
import "../components/styles/Home.css";

const Home = (loggedUser) => {

    const displayHome = () => {
        if (loggedUser.loggedUser === null) {
            return (
                <div className="home">
                    <div className="home-before-login-container">
                        <div className="home-before-login-text">
                            Login or Register to play!
                        </div>
                        <NavLink className="home-before-login-button" to={"/UserAccess"}>
                            Click here!
                        </NavLink>
                    </div>
                </div>
            )
        }
        else {
            return (
                <div className="home">
                    <div className="home-after-login-container">
                        <div className="home-after-login-text">
                            Welcome {loggedUser.loggedUser.username} to Kasintu!
                        </div>
                        <div className="home-after-login-text">
                            You have {loggedUser.loggedUser.coin} coins!
                        </div>
                        <NavLink className="home-after-login-button" to={"/Summon"}>
                            Click here to Summon!
                        </NavLink>
                    </div>
                </div>
            )
        }
    }

    return (
        <div className="home">
            {displayHome()}
        </div>
    )


}

export default Home;