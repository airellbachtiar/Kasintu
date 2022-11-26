import React, { useEffect, useState } from "react";
import { NavLink } from "react-router-dom";

import "./styles/CreatureRarityColor.css";
import "./styles/AllCreaturesListTable.css";

const AllCreaturesListTable = (props) => {
    const [creatures, setCreatures] = useState([]);

    useEffect(() => {
        setCreatures(props.creatures);
    }, [props.creatures])

    return (
        <div className="creature-list-table-container">
            <table className="creature-list-table">
                <thead>
                    <tr>
                        <th>Name</th>
                        <th>Rarity</th>
                    </tr>
                </thead>
                <tbody>
                    {creatures.map(creature => {
                        return (
                            <tr key={creature.creatureID}>
                                <td>
                                    <NavLink data-cy={`creature-${creature.creatureID}`} to={`/Creature/${creature.creatureID}`}>
                                        {creature.name}
                                    </NavLink>
                                </td>
                                <td className={creature.rarity.rarityType}>{creature.rarity.rarityType}</td>
                            </tr>
                        )
                    })}
                </tbody>
            </table>
        </div>
    )
}

export default AllCreaturesListTable;
