import React, { useEffect, useState } from "react";
import axios from "axios";
import Form from 'react-bootstrap/Form'
import Button from 'react-bootstrap/Button'
import { NavLink, useParams, useNavigate } from "react-router-dom";
import FloatingLabel from 'react-bootstrap/FloatingLabel';
import "../components/styles/EditCreature.css";
import SpinStretch from "react-cssfx-loading/lib/SpinStretch";
import { useModals } from '@mantine/modals';

const EditCreature = (props) => {

    const { creatureID } = useParams();

    const [creature, setCreature] = useState([]);

    const [rarity, setRarity] = useState([]);

    const [formValue, setFormValue] = useState({
        creatureID: "",
        name: "",
        rarityID: "",
        description: ""
    });

    const [editState, setEditState] = useState(null);

    let navigate = useNavigate();

    useEffect(() => {
        props.checkTokenExpiration();
    }, [props]);

    useEffect(() => {
        setEditState("loading");
        var config = {
            method: 'get',
            url: `http://localhost:8080/creatures/${creatureID}`,
            headers: { "Access-Control-Allow-Origin": "*" }
        };

        axios(config)
            .then(function (response) {
                setCreature(response.data);
                setFormValue({
                    creatureID: response.data.creatureID,
                    name: response.data.name,
                    rarityID: response.data.rarity.rarityID,
                    description: response.data.description
                })
            })
            .catch(function (error) {
                setEditState("error");
                console.log(error);
            });

        config = {
            method: 'get',
            url: 'http://localhost:8080/rarities',
            headers: {}
        };

        axios(config)
            .then(function (response) {
                const rarities = [].concat(response.data.rarities)
                    .sort((a, b) => a.rarityLevel > b.rarityLevel ? 1 : -1);
                setRarity(rarities);
                setEditState("loaded");
            })
            .catch(function (error) {
                setEditState("error");
                console.log(error);
            });
    }, [creatureID])

    const onChange = event => {
        setFormValue({
            ...formValue,
            [event.target.name]: event.target.value
        })
    }

    const handleSubmit = (e) => {

        e.preventDefault();
        setEditState("loading");
        const token = localStorage.getItem("accessToken");

        var data = JSON.stringify({
            "creatureID": formValue.creatureID,
            "name": formValue.name,
            "rarityID": formValue.rarityID,
            "description": formValue.description
        });

        var config = {
            method: 'put',
            url: `http://localhost:8080/creatures/${creatureID}`,
            headers: {
                'Content-Type': 'application/json',
                'Authorization': 'Bearer ' + token
            },
            data: data
        };

        axios(config)
            .then(function (response) {
                props.sendUpdateCreatureNotification();
                navigate("/Creatures");
            })
            .catch(function (error) {
                setEditState("error");
                console.log(error);
            });
    }

    const deleteCreature = () => {
        setEditState("loading");
        const token = localStorage.getItem("accessToken");

        var axios = require('axios');

        var config = {
            method: 'delete',
            url: `http://localhost:8080/creatures/${creatureID}`,
            headers: {
                'Authorization': 'Bearer ' + token
            }
        };

        axios(config)
            .then(function (response) {
                console.log(JSON.stringify(response.data));
                props.sendDeleteCreatureNotification();
                navigate("/Creatures");
            })
            .catch(function (error) {
                setEditState("error");
                console.log(error);
            });
    }

    const displayEditState = () => {
        switch (editState) {
            case "loading":
                return (
                    <div className="editcreature-form-loading">
                        <SpinStretch />
                    </div>
                )
            case "loaded":
                return (
                    <></>
                )
            case "error":
                return (
                    <div className="editcreature-form-error">
                        <span data-cy="creature-edit-error">Error encountered, please try again.</span>
                    </div>
                )
            default:
                break;
        }
    }

    const modals = useModals();

    const confirmDelete = () => modals.openConfirmModal({
        title: 'Delete Creature',
        centered: true,
        children: (
            <>
                Are you sure you wish to delete this creature?
            </>
        ),
        labels: { confirm: 'Delete', cancel: 'Cancel' },
        onConfirm: () => deleteCreature(),
    });

    return (
        <div className="editcreature-form">
            <div className="editcreature-form-container">
                <div className="editcreature-form-title">
                    Edit Creature
                </div>
                <Form onSubmit={handleSubmit}>
                    <div className="editcreature-form-content">
                        {
                            creature.name ?
                                <>
                                    <div className="editcreature-form-content-input">
                                        <FloatingLabel label="Name">
                                            <Form.Control data-cy="creature-edit-name" type="text" placeholder="Name" name="name" onChange={onChange} value={formValue.name} required />
                                        </FloatingLabel>
                                    </div>

                                    <div className="editcreature-form-content-input">
                                        <FloatingLabel label="Rarity">
                                            <Form.Control data-cy="creature-edit-rarity" as="select" name="rarityID" value={formValue.rarityID} onChange={onChange}>
                                                {rarity.map(rarity => (
                                                    <option key={rarity.rarityID} value={rarity.rarityID}>{rarity.rarityType}</option>
                                                ))}
                                            </Form.Control>
                                        </FloatingLabel>
                                    </div>

                                    <div className="editcreature-form-content-input">
                                        <FloatingLabel label="Descriptions">
                                            <Form.Control
                                                data-cy="creature-edit-description"
                                                as="textarea"
                                                name="description"
                                                onChange={onChange}
                                                value={formValue.description}
                                                style={{ height: '100px' }}
                                            />
                                        </FloatingLabel>
                                    </div>
                                </>
                                :
                                <></>
                        }
                    </div>
                    {displayEditState()}
                    <div className="editcreature-form-footer">
                        <div className="editcreature-form-submit">
                            <Button data-cy="creature-edit-save" variant="primary" type="submit">
                                Save
                            </Button>
                        </div>
                        <div className="editcreature-form-delete">
                            <Button data-cy="creature-edit-delete" variant="danger" onClick={confirmDelete} >
                                Delete
                            </Button>
                        </div>
                        <div className="addcreature-form-cancel">
                            <NavLink to={`/Creature/${creature.creatureID}`}>
                                <Button data-cy="creature-edit-cancel" variant="secondary">
                                    Cancel
                                </Button>
                            </NavLink>
                        </div>
                    </div>
                </Form>
            </div>
        </div>
    )
}



export default EditCreature;