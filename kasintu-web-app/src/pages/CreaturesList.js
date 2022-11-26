import React, { useEffect, useState } from "react";
import { NavLink } from "react-router-dom";
import axios from "axios";
import AllCreaturesListTable from "../components/AllCreaturesListTable";
import "../components/styles/CreaturesList.css";
import jwtDecode from "jwt-decode";
import SpinStretch from "react-cssfx-loading/lib/SpinStretch";

const AllCreaturesList = () => {

    const [creatures, setCreatures] = useState([]);
    const [state, setState] = useState("loading");

    useEffect(() => {
        var config = {
            method: 'get',
            url: 'http://localhost:8080/creatures',
            headers: { "Access-Control-Allow-Origin": "*" }
        };

        axios(config)
            .then(function (response) {
                setState("loaded");
                const creaturesData = [].concat(response.data.creatures)
                    .sort((a, b) => a.name > b.name ? -1 : 1)
                    .sort((a, b) => a.rarity.rarityLevel > b.rarity.rarityLevel ? 1 : -1);
                setCreatures(creaturesData);
            })
            .catch(function (error) {
                console.log(error);
                setState("error");
            });
    }, [])

    const displayCreatures = () => {
        switch (state) {
            case "loading":
                return (
                    <div className="creature-list-container">
                        <div className="creature-list-loading">
                            <SpinStretch width="100px" height="100px" />
                        </div>
                    </div>
                )
            case "error":
                return (
                    <div className="creature-list-container">
                        <div className="creature-list-error">
                            <h1>Error encountered, please reload!</h1>
                        </div>
                    </div>
                )
            case "loaded":
                return (
                    <div className="creature-list-container">
                        <div className="creature-list-loaded">
                            <AllCreaturesListTable creatures={creatures} />
                        </div>
                    </div>
                )
            default:
                break;
        }
    }

    return (
        <div className="creature-list">
            {
                localStorage.getItem("accessToken") ?
                    <>
                        {
                            jwtDecode(localStorage.getItem("accessToken")).roles.some(role => "ADMIN" === role) ?
                                <div className="add-creature-button">
                                    <NavLink data-cy="creature-add-button" to="/AddCreature">
                                        Add Creature
                                    </NavLink>
                                </div> : <></>
                        }
                    </>
                    : <></>
            }

            {displayCreatures()}

        </div>
    )
}

export default AllCreaturesList;