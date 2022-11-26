import React, { useEffect, useState } from "react";
import Form from 'react-bootstrap/Form'
import Button from 'react-bootstrap/Button'
import axios from "axios";
import { NavLink, useNavigate } from "react-router-dom";
import FloatingLabel from 'react-bootstrap/FloatingLabel';
import "../components/styles/AddCreature.css";
import SpinStretch from "react-cssfx-loading/lib/SpinStretch";

const AddCreature = (props) => {

    const [formValue, setFormValue] = useState({
        name: "",
        rarityID: "",
        description: ""
    });

    const [addCreatureState, setAddCreatureState] = useState(null);

    const [rarity, setRarity] = useState([]);

    useEffect(() => {
        var config = {
            method: 'get',
            url: 'http://localhost:8080/rarities',
            headers: {}
        };

        axios(config)
            .then(function (response) {
                const rarities = [].concat(response.data.rarities)
                    .sort((a, b) => a.rarityLevel > b.rarityLevel ? 1 : -1);
                setRarity(rarities);
                setFormValue({
                    name: "",
                    rarityID: rarities[0].rarityID
                })
            })
            .catch(function (error) {
                console.log(error);
            });

    }, []);

    let navigate = useNavigate();

    const handleSubmit = (e) => {

        e.preventDefault();
        setAddCreatureState("loading");
        var data = JSON.stringify({
            "name": formValue.name,
            "rarityID": formValue.rarityID,
            "description": formValue.description
        });

        const token = localStorage.getItem("accessToken");

        const config = {
            method: 'post',
            url: 'http://localhost:8080/creatures',
            headers: {
                'Content-Type': 'application/json',
                "Access-Control-Allow-Origin": "*",
                'Authorization': 'Bearer ' + token
            },
            data: data
        };

        axios(config)
            .then(function () {
                props.sendAddCreatureNotification();
                navigate("/Creatures");
            })
            .catch(function (error) {
                setAddCreatureState("error");
                console.log(error);
            });
    }

    const onChange = event => {
        setFormValue({
            ...formValue,
            [event.target.name]: event.target.value
        })
    }

    const addCreatureNotification = () => {
        switch (addCreatureState) {
            case "loading":
                return (
                    <div className="addcreature-form-loading">
                        <SpinStretch />
                    </div>
                );
            case "error":
                return (
                    <div data-cy="creature-add-error" className="addcreature-form-error">
                        <span>Saving failed! Please try again</span>
                    </div>
                );
            default:
                break;
        }
    }

    return (
        <div className="addcreature-form">
            <div className="addcreature-form-container">
                <div className="addcreature-form-title">
                    Add Creature
                </div>
                <Form onSubmit={handleSubmit}>
                    <div className="addcreature-form-content">
                        <div className="addcreature-form-content-input">
                            <FloatingLabel label="Name">
                                <Form.Control data-cy="creature-add-input-name" type="text" placeholder="Name" name="name" onChange={onChange} value={formValue.name} required />
                            </FloatingLabel>
                        </div>
                        <div className="addcreature-form-content-input">
                            <FloatingLabel label="Rarity">
                                <Form.Control data-cy="creature-add-input-rarity" as="select" name="rarityID" onChange={onChange}>
                                    {rarity.map(rarity => (
                                        <option key={rarity.rarityID} value={rarity.rarityID}>{rarity.rarityType}</option>
                                    ))}
                                </Form.Control>
                            </FloatingLabel>
                        </div>
                        <div className="addcreature-form-content-input">
                            <FloatingLabel label="Description">
                                <Form.Control
                                    data-cy="creature-add-input-description"
                                    as="textarea"
                                    name="description"
                                    placeholder="Description"
                                    onChange={onChange}
                                    value={formValue.description}
                                    style={{ height: '100px' }}
                                />
                            </FloatingLabel>
                        </div>
                    </div>
                    {addCreatureNotification()}
                    <div className="addcreature-form-footer">
                        <div className="addcreature-form-submit">
                            <Button data-cy="creature-add-save" variant="primary" type="submit">
                                Save
                            </Button>
                        </div>
                        <div className="addcreature-form-cancel">
                            <NavLink to="/Creatures">
                                <Button data-cy="creature-add-back" variant="secondary">
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



export default AddCreature;