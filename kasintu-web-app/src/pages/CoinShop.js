import React from "react";
import CoinCard from "../components/CoinCard";
import "../components/styles/CoinShop.css";
import { useModals } from '@mantine/modals';
import { showNotification } from "@mantine/notifications";
import { useNavigate } from "react-router-dom";
import { CoinShopData } from "../components/datas/CoinShopData";
import axios from "axios";

const CoinShop = (props) => {

    let navigate = useNavigate();

    const modals = useModals();

    const openConfirmModal = (pack) => modals.openConfirmModal({
        title: 'Purchase Coins',
        centered: true,
        children: (
            <>
                Pay ${pack.cost}?
            </>
        ),
        labels: { confirm: 'Confirm', cancel: 'Cancel' },
        onConfirm: () => {
            props.checkTokenExpiration();
            const token = localStorage.getItem("accessToken");
            var data = JSON.stringify({
                "playerID": props.playerID,
                "coin": pack.amount + props.loggedUser.coin
            });
            var config = {
                method: 'put',
                url: `http://localhost:8080/players/${props.loggedUser.playerID}`,
                headers: {
                    'Content-Type': 'application/json',
                    'Authorization': 'Bearer ' + token
                },
                data: data
            };

            axios(config)
                .then(function () {
                    showNotification({
                        title: "Purchased " + pack.amount + " coins for $" + pack.cost + "!"
                    });
                    props.updateUser();
                    navigate("/");
                })
                .catch(function (error) {
                    console.log(error);
                });
        }
    });

    return (
        <div className="coinshop">
            {
                CoinShopData.map((pack, index) => {
                    return (
                        <CoinCard
                            key={index}
                            title={pack.title}
                            amount={pack.amount}
                            cost={pack.cost}
                            openConfirmModal={() => openConfirmModal(pack)}
                        />
                    )
                })
            }
        </div>
    );
}

export default CoinShop;