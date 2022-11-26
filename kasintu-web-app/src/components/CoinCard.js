import React from "react";
import "../components/styles/CoinCard.css";


const CoinCard = (props) => {

    return (
        <div className="coincard">
            <div className="coincard-title">{props.title}</div>

            <div className="coincard-amount">+{props.amount} Coins</div>

            <div className="coincard-button">
                <div className="coincard-purchase">Purchase</div>
                <div data-cy={`coincard-${props.amount}`} className="coincard-cost" onClick={props.openConfirmModal}>${props.cost}</div>
            </div>
        </div>
    );
}

export default CoinCard;