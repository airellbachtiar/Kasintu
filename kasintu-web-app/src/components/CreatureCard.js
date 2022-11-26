import React from "react";
import "./styles/CreatureRarityColor.css";
import "./styles/CreatureCard.css";

const CreatureCard = (props) => {
    return (
        <>
            <div className={ props.hover ? "creaturecard-hover" : "creaturecard"} onClick={props.onClick}>
                <div className="creaturecard-content" data-cy={props.datacy}>
                    {
                        props.creature ? (
                            <>
                                <div className="creaturecard-title">
                                    <span>{props.creature.name}</span>
                                </div>
                                <div className="creaturecard-rarity">
                                    <span className={props.creature.rarity.rarityType}>{props.creature.rarity.rarityType}</span>
                                </div>
                            </>
                        ) : (
                            <>
                                <div className="creaturecard-title">
                                    None
                                </div>
                            </>)
                    }
                </div>
            </div>
        </>

    )
}

export default CreatureCard;