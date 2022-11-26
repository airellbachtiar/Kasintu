import axios from "axios";
import React, { useEffect, useState } from "react";
import { NavLink, useNavigate } from "react-router-dom";
import CreatureCard from "../components/CreatureCard";
import SpinStretch from "react-cssfx-loading/lib/SpinStretch";
import "../components/styles/Inventory.css";

const Inventory = (props) => {

    const [ownedCreatures, setOwnedCreatures] = useState([]);
    const [inventoryState, setInventoryState] = useState("loading");

    useEffect(() => {
        setInventoryState("loading");
        if (props.loggedUser !== null) {

            const token = localStorage.getItem("accessToken");

            var config = {
                method: 'get',
                url: `http://localhost:8080/inventory/player/${props.loggedUser.userID}`,
                headers: {
                    'Authorization': 'Bearer ' + token
                }
            };

            axios(config)
                .then(function (response) {
                    const ownedCreaturesData = [].concat(response.data.ownedCreatures)
                        .sort((a, b) => a.creature.name > b.creature.name ? -1 : 1)
                        .sort((a, b) => a.creature.rarity.rarityLevel > b.creature.rarity.rarityLevel ? 1 : -1);
                    setOwnedCreatures(ownedCreaturesData);
                    setInventoryState("loaded");
                })
                .catch(function (error) {
                    setInventoryState("error");
                    console.log(error);
                });
        }
        else {
            setInventoryState("error");
        }
    }, [props.loggedUser]);

    useEffect(() => {
        props.checkTokenExpiration();
    }, [props]);

    let navigate = useNavigate();

    const displayOwnedCreatures = () => {
        switch (inventoryState) {
            case "loading":
                return (
                    <div className="inventory-container">
                        <div className="inventory-loading">
                            <SpinStretch width="100px" height="100px" />
                        </div>
                    </div>
                )
            case "error":
                return (
                    <div className="inventory-container">
                        <div className="inventory-error">
                            <h1>Error encountered, please reload!</h1>
                        </div>
                    </div>
                )
            case "loaded":
                return (
                    <>
                        {
                            ownedCreatures.length > 0 ? <div className="creaturecard-container">
                                <div className="creaturecard-layout">
                                    {
                                        ownedCreatures.map(ownedCreature => {
                                            return (
                                                <CreatureCard datacy={`owned-creature-${ownedCreature.ownedCreatureID}`} key={ownedCreature.ownedCreatureID} creature={ownedCreature.creature} hover={true} onClick={() => navigate(`/OwnedCreature/${ownedCreature.ownedCreatureID}`)} />
                                            )
                                        })}
                                </div>
                            </div> :
                                <h1 data-cy="inventory-empty" className="inventory-no-creature">
                                    You don't have any creatures in your inventory.
                                </h1>
                        }
                    </>
                )
            default:
                break;
        }
    }

    if (props.loggedUser !== null) {

        return (
            <div className="inventory">
                {displayOwnedCreatures()}
            </div>
        )
    }
    else {
        return (
            <div className="inventory">
                <div className="inventory-container">
                    <div className="inventory-login-error">
                        <NavLink to={"/UserAccess"}>
                            Login to view your inventory.
                        </NavLink>
                    </div>
                </div>
            </div>
        )
    }
}

export default Inventory;