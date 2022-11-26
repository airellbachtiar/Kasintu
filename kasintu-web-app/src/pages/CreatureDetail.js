import React, { useEffect, useState } from "react";
import CreatureCard from "../components/CreatureCard";
import { NavLink, useParams } from "react-router-dom";
import "../components/styles/CreatureDetail.css";
import SpinStretch from "react-cssfx-loading/lib/SpinStretch";
import axios from "axios";
import jwtDecode from "jwt-decode";
import Textarea from 'react-expanding-textarea';

const CreatureDetail = () => {

    const { creatureID } = useParams();
    const [creature, setCreature] = useState(null);
    const [creatureState, setCreatureState] = useState("loading");

    useEffect(() => {
        setCreatureState("loading");
        var config = {
            method: 'get',
            url: `http://localhost:8080/creatures/${creatureID}`,
            headers: { "Access-Control-Allow-Origin": "*" }
        };

        axios(config)
            .then(function (response) {
                setCreature(response.data);
                setCreatureState("loaded");
            })
            .catch(function (error) {
                setCreatureState("error");
                console.log(error);
            });
    }, [creatureID]);

    const displayCreature = () => {
        switch (creatureState) {
            case "loading":
                return (
                    <div className="creature-detail-loading">
                        <SpinStretch width="100px" height="100px" />
                    </div>
                );
            case "loaded":
                return (
                    <>
                        <div className="creature-detail-container">
                            <div className="creature-detail-card">
                                <CreatureCard creature={creature} />
                            </div>
                            <div className="creature-detail-information">
                                {creature && (
                                    <>
                                        <div className="creature-detail-name">
                                            Name:
                                        </div>
                                        <div className="creature-detail-name-input">
                                            {creature.name}
                                        </div>

                                        <div className="creature-detail-rarity">
                                            Rarity:
                                        </div>
                                        <div className="creature-detail-rarity-input">
                                            <span className={creature.rarity.rarityType}>{creature.rarity.rarityType}</span>
                                        </div>
                                    </>
                                )}
                            </div>
                            <div className="creature-detail-description">
                                Description:
                            </div>
                            <Textarea
                            className="creature-detail-description-input"
                            value={creature.description}
                            disabled={true}
                            />
                            <div className="creature-detail-back-button">
                                <NavLink data-cy="creature-detail-back" to={`/Creatures`}>
                                    Back
                                </NavLink>
                            </div>
                            {
                                localStorage.getItem("accessToken") ?
                                    <>
                                        {
                                            jwtDecode(localStorage.getItem("accessToken")).roles.some(role => "ADMIN" === role) ?
                                                <div className="creature-detail-edit-button">
                                                    <NavLink data-cy="creature-detail-edit" to={`/EditCreature/${creature.creatureID}`}>
                                                        Edit
                                                    </NavLink>
                                                </div> : <></>
                                        }
                                    </>
                                    : <></>
                            }
                        </div>

                    </>
                );
            case "error":
                return (
                    <div className="creature-detail-error">
                        <h1>Error encountered, please reload!</h1>
                    </div>
                );
            default:
                break;
        }
    }

    return (
        <div className="creature-detail">
            {displayCreature()}
        </div>
    );
}

export default CreatureDetail;