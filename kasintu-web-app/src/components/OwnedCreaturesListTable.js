import React from "react";

const OwnedCreaturesListTable = (props) => {

    return (
        <div className="owned-creatures-list-table-container">
            <table className="owned-creatures-list-table">
                <thead>
                    <tr>
                        <th>Name</th>
                        <th>Rarity</th>
                    </tr>
                </thead>
                <tbody>
                    {props.inventory.ownedCreatures != null ? props.inventory.ownedCreatures.map(ownedCreature => {
                        return (
                            <tr key={ownedCreature.ownedCreatureID}>
                                <td>
                                    {ownedCreature.creature.name}
                                </td>
                                <td className={ownedCreature.creature.rarity.rarityType}>{ownedCreature.creature.rarity.rarityType}</td>
                            </tr>
                        )
                    }) : <></>}
                </tbody>
            </table>
        </div>
    )
}

export default OwnedCreaturesListTable;