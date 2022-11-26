import axios from "axios";
import React, { useEffect, useState } from "react";
import { NavLink, useNavigate, useParams } from "react-router-dom";
import CreatureCard from "../components/CreatureCard";
import "../components/styles/SummonBanner.css";
import SpinStretch from "react-cssfx-loading/lib/SpinStretch";
import { showNotification } from "@mantine/notifications";

const SummonBanner = (props) => {

    const { amount, bannerID } = useParams();
    const [obtainedCreatures, setObtainedCreatures] = useState([]);
    const [displayCreature, setDisplayCreature] = useState([]);
    const [summonAgain, setSummonAgain] = useState(true);
    const [summonState, setSummonState] = useState("loading");
    const [buttonState, setButtonState] = useState("display");

    useEffect(() => {
        props.checkTokenExpiration();
        if (summonAgain && props.loggedUser !== null) {
            setButtonState("disabled");
            setSummonState("loading");
            var data = JSON.stringify({
                "bannerID": bannerID,
                "summonTimes": amount
            });
            const token = localStorage.getItem("accessToken");

            setSummonAgain(false);

            var config = {
                method: 'post',
                url: `http://localhost:8080/game/${props.loggedUser.userID}`,
                headers: {
                    'Authorization': `Bearer ` + token,
                    'Content-Type': 'application/json'
                },
                data: data
            };

            axios(config)
                .then(function (response) {
                    setObtainedCreatures(response.data);
                    props.updateUser();
                    setSummonState("loaded");
                })
                .catch(function (error) {
                    setSummonState("error");
                    console.log(error);
                });
        }

    }, [summonAgain, amount, bannerID, props]);

    let navigate = useNavigate();

    const onClickSummonAgain = (times, e) => {
        e.preventDefault();
        if (props.loggedUser) {
            if (props.loggedUser.coin >= times * 100) {
                setObtainedCreatures([]);
                setDisplayCreature([]);
                setSummonAgain(true);
                navigate(`/SummonBanner/${bannerID}/${times}`);
            }
            else {
                showNotification({
                    title: "You don't have enough coins to summon again."
                });
            }
        }
    }

    useEffect(() => {
        for (let i = 0; i < obtainedCreatures.length; i++) {
            setTimeout(() => {
                setDisplayCreature(displayCreature => [...displayCreature, obtainedCreatures[i]]);
            }, i * 500);
        }
        if (obtainedCreatures.length > 0) {
            setTimeout(() => { setButtonState("display") }, (obtainedCreatures.length * 500));
        }
    }, [obtainedCreatures]);

    const displayObtainedCreatures = () => {
        switch (summonState) {
            case "loading":
                return (
                    <div className="summon-banner-loading">
                        <SpinStretch width="100px" height="100px" />
                    </div>
                )
            case "error":
                return (
                    <div className="summon-banner-error">
                        <h1>Error encountered, please reload!</h1>
                    </div>
                )
            case "loaded":
                return (
                    <>
                        <div className="creaturecard-container">
                            <div className="creaturecard-layout">
                                {
                                    obtainedCreatures.length !== 1 ? (
                                        displayCreature.map((creature, index) => {
                                            return (
                                                <>
                                                    <div data-cy={creature ? `summon-creature-${creature.creatureID}` : "summon-creature-null"} key={index} className="creature-card-on-summon">
                                                        <CreatureCard creature={creature} />
                                                    </div>
                                                </>
                                            )
                                        }
                                        )
                                    ) : (
                                        <>
                                            <div></div>
                                            <div></div>
                                            <div data-cy={displayCreature[0] ? `summon-creature-${displayCreature[0].creatureID}` : "summon-creature-null"} className="creature-card-on-summon">
                                                <CreatureCard creature={displayCreature[0]} />
                                            </div>
                                            <div></div>
                                            <div></div>
                                        </>
                                    )
                                }
                            </div>
                        </div>
                    </>
                )
            default:
                break;
        }
    }

    const displayButton = () => {
        switch (buttonState) {
            case "display":
                return (
                    <>
                        <NavLink data-cy="summon-once-again" to="#" onClick={(e) => onClickSummonAgain(1, e)}>
                            Summon 1 Time
                        </NavLink>
                        <NavLink data-cy="summon-multiple-again" to="#" onClick={(e) => onClickSummonAgain(10, e)}>
                            Summon 10 Time
                        </NavLink>
                    </>
                )
            default:
                break;
        }
    }

    return (
        <div className="summon-banner">
            <div className="creaturecard-container">
                {displayObtainedCreatures()}
            </div>
            <div className="summon-banner-buttons">
                {displayButton()}
            </div>

        </div>
    )
}

export default SummonBanner;