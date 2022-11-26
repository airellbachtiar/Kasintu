import React, { useEffect, useState } from "react";
import { NavLink, useNavigate } from "react-router-dom";
import "./styles/BannerCard.css";
import ReactTooltip from "react-tooltip";
import axios from "axios";
import * as IoIcons from "react-icons/io5";
import SpinStretch from "react-cssfx-loading/lib/SpinStretch";
import { showNotification } from "@mantine/notifications";

const BannerCard = (props) => {

    const [banner, setBanner] = useState([]);
    const [pullRateInfo, setPullRateInfo] = useState([]);
    const [bannerState, setBannerState] = useState("loading");

    useEffect(() => {
        var config = {
            method: 'get',
            url: 'http://localhost:8080/banner',
            headers: { "Access-Control-Allow-Origin": "*" }
        };

        axios(config)
            .then(function (response) {
                setBanner(response.data.banners);

                var config = {
                    method: 'get',
                    url: `http://localhost:8080/banner/pullRateInfo/${response.data.banners[0].bannerID}`,
                    headers: { "Access-Control-Allow-Origin": "*" }
                };

                axios(config)
                    .then(function (response) {
                        const pullRateInfoData = [].concat(response.data.ratesInformation)
                            .sort((a, b) => a.rarity.rarityLevel > b.rarity.rarityLevel ? 1 : -1);
                        setPullRateInfo(pullRateInfoData);
                        setBannerState("loaded");
                    })
                    .catch(function (error) {
                        setBannerState("error");
                        console.log(error);
                    });
            })
            .catch(function (error) {
                setBannerState("error");
                console.log(error);
            });
    }, [])

    let navigate = useNavigate();

    const clickBanner = (bannerID, times, e) => {
        e.preventDefault();
        if (props.loggedUser) {
            if (props.loggedUser.coin >= times * 100) {
                navigate(`/SummonBanner/${bannerID}/${times}`);
            }
            else {
                showNotification({
                    title: "You don't have enough coins!"
                })
            }
        }
    }

    const displayBanner = () => {
        switch (bannerState) {
            case "loading":
                return (
                    <div className="banner-card-container">
                        <div className="banner-card-loading">
                            <SpinStretch width="100px" height="100px" />
                        </div>
                    </div>
                )
            case "error":
                return (
                    <div className="banner-card-container">
                        <div className="banner-card-error">
                            <h1>Error encountered, please reload!</h1>
                        </div>
                    </div>
                )
            case "loaded":
                return (
                    <>
                        {banner.length <= 0 ? null :
                            <div className="bannercard-item-container">
                                <div className="bannercard-item-moreinfo">
                                    <span data-tip data-for='banner-info'>
                                        < IoIcons.IoInformationCircleSharp />
                                    </span>
                                    <ReactTooltip className="banner-tooltip-on-hover" id='banner-info' place="right" type="info" delayHide={50} effect="solid">
                                        <div className="banner-info-tooltip">
                                            20% chance to obtain any creature
                                            <table className="banner-info-table">
                                                <thead>
                                                    <tr>
                                                        <th>Rarity</th>
                                                        <th>Rate</th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                    {pullRateInfo.map(pullRateInfo => {
                                                        return (
                                                            <tr key={pullRateInfo.rarity.rarityLevel}>
                                                                <td className={pullRateInfo.rarity.rarityType}>{pullRateInfo.rarity.rarityType}</td>
                                                                <td>{pullRateInfo.percentage}</td>
                                                            </tr>
                                                        )
                                                    })}
                                                </tbody>
                                            </table>
                                        </div>
                                    </ReactTooltip>
                                </div>
                                <div className="bannercard-item-title">
                                    SUMMON
                                </div>
                                <div className="bannercard-item-one">
                                    <span className="bannercard-item-one-info">
                                        One Summon
                                    </span>
                                    <NavLink data-cy="bannercard-one-summon" to="#" onClick={(e) => clickBanner(banner[0].bannerID, 1, e)}>
                                        <span className="bannercard-item-one-coin">
                                            100 Coins
                                        </span>
                                    </NavLink>
                                </div>
                                <div className="bannercard-item-ten">
                                    <span className="bannercard-item-ten-info">
                                        Multiple Summon
                                    </span>
                                    <NavLink data-cy="bannercard-multiple-summon" to="#" onClick={(e) => clickBanner(banner[0].bannerID, 10, e)}>
                                        <span className="bannercard-item-ten-coin">
                                            1000 Coins
                                        </span>
                                    </NavLink>
                                </div>
                            </div>
                        }
                    </>
                )
            default:
                break;
        }
    }

    return (
        <>
            {displayBanner()}
        </>
    )
}

export default BannerCard;