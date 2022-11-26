import React, { useEffect, useState } from "react";
import CreatureCard from "../components/CreatureCard";
import { NavLink, useParams, useNavigate } from "react-router-dom";
import "../components/styles/CreatureDetail.css";
import SpinStretch from "react-cssfx-loading/lib/SpinStretch";
import axios from "axios";
import Textarea from 'react-expanding-textarea';
import "../components/styles/OwnedCreatureDetail.css"
import Button from 'react-bootstrap/Button'
import { useModals } from '@mantine/modals';
import { showNotification } from "@mantine/notifications";

const CreatureDetail = (props) => {

    const { ownedCreatureID } = useParams();
    const [ownedCreature, setOwnedCreature] = useState(null);
    const [ownedCreatureState, setOwnedCreatureState] = useState("loading");

    useEffect(() => {
        props.checkTokenExpiration();
    });

    useEffect(() => {
        setOwnedCreatureState("loading");
        if (props.loggedUser !== null) {

            const token = localStorage.getItem("accessToken");

            var config = {
                method: 'get',
                url: `http://localhost:8080/ownedCreature/${ownedCreatureID}`,
                headers: {
                    'Authorization': 'Bearer ' + token
                }
            };

            axios(config)
                .then(function (response) {
                    setOwnedCreature(response.data);
                    setOwnedCreatureState("loaded");
                })
                .catch(function (error) {
                    setOwnedCreatureState("error");
                    console.log(error);
                });
        }
        else {
            setOwnedCreatureState("error");
        }
    }, [props.loggedUser, ownedCreatureID]);

    let navigate = useNavigate();

    const deleteOwnedCreature = () => {
        const token = localStorage.getItem("accessToken");

        var config = {
            method: 'delete',
            url: `http://localhost:8080/ownedCreature/${ownedCreatureID}`,
            headers: {
                'Authorization': 'Bearer ' + token
            }
        };

        axios(config)
            .then(function () {
                showNotification({
                    title: `You deleted ${ownedCreature.creature.name}!`
                  })
                navigate("/Inventory");
            })
            .catch(function (error) {
                console.log(error);
            });
    }

    const modals = useModals();

    const openConfirmModal = () => modals.openConfirmModal({
        title: 'Delete your creature?',
        centered: true,
        children: (
            <>
                Are you sure you want to delete your creature?
            </>
        ),
        labels: { confirm: 'Confirm', cancel: 'Cancel' },
        onConfirm: () => deleteOwnedCreature(),
    });


    const displayOwnedCreature = () => {
        switch (ownedCreatureState) {
            case "loading":
                return (
                    <div className="owned-creature-detail-container">
                        <div className="owned-creature-detail-loading">
                            <SpinStretch width="100px" height="100px" />
                        </div>
                    </div>
                )
            case "error":
                return (
                    <div className="owned-creature-detail-container">
                        <div className="owned-creature-detail-error">
                            <h1>Error</h1>
                            <p>Something went wrong while loading the creature.</p>
                        </div>
                    </div>
                )
            case "loaded":
                return (
                    <div className="owned-creature-detail-container">
                        <div className="owned-creature-detail-card">
                            <CreatureCard creature={ownedCreature.creature} />
                        </div>
                        <div className="owned-creature-detail-information">
                            {ownedCreature.creature && (
                                <>
                                    <div className="owned-creature-detail-name">
                                        Name:
                                    </div>
                                    <div className="owned-creature-detail-name-input">
                                        {ownedCreature.creature.name}
                                    </div>

                                    <div className="owned-creature-detail-rarity">
                                        Rarity:
                                    </div>
                                    <div className="owned-creature-detail-rarity-input">
                                        <span className={ownedCreature.creature.rarity.rarityType}>{ownedCreature.creature.rarity.rarityType}</span>
                                    </div>
                                </>
                            )}
                        </div>
                        <div className="owned-creature-detail-description">
                            Description:
                        </div>
                        <Textarea
                            className="owned-creature-detail-description-input"
                            value={ownedCreature.creature.description}
                            disabled={true}
                        />
                        <div className="owned-creature-detail-back-button">
                            <NavLink data-cy='owned-creature-detail-back' to={`/Inventory`}>
                                Back
                            </NavLink>
                        </div>
                        <div className="owned-creature-detail-delete-button">
                            <Button data-cy='owned-creature-detail-delete' variant="danger" onClick={openConfirmModal}>
                                Delete
                            </Button>
                        </div>
                    </div>
                )
            default:
                break;
        }
    }

    return (
        <div className="owned-creature-detail">
            {displayOwnedCreature()}
        </div>
    )
}

export default CreatureDetail;