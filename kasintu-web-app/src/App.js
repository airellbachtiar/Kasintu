import React, { useEffect, useState } from "react";
import { Route, Routes, useNavigate } from "react-router-dom";
import Navbar from "./components/Navbar";
import "./App.css";
import 'bootstrap/dist/css/bootstrap.min.css';
import Home from "./pages/Home";
import Summon from "./pages/Summon";
import About from "./pages/About";
import CreaturesList from "./pages/CreaturesList";
import AddCreature from "./pages/AddCreature";
import EditCreature from "./pages/EditCreature";
import UserAccess from "./pages/UserAccess";
import Logout from "./components/Logout";
import SummonBanner from "./pages/SummonBanner";
import axios from "axios";
import jwtDecode from "jwt-decode";
import Inventory from "./pages/Inventory";
import CreatureDetail from "./pages/CreatureDetail";
import OwnedCreatureDetail from "./pages/OwnedCreatureDetail";
import { showNotification } from "@mantine/notifications";
import SockJS from 'sockjs-client';
import Stomp from 'stompjs';
import MyProfile from "./pages/MyProfile";
import CoinShop from "./pages/CoinShop";
const ENDPOINT = "http://localhost:8080/ws";

const App = () => {

    const [loggedUser, setLoggedUser] = useState(() => {
        const saved = localStorage.getItem("loggedUser");
        const initialValue = saved ? JSON.parse(saved) : null;
        return initialValue;
    }
    );

    const [expiration, setExpiration] = useState(() => {
        const token = localStorage.getItem("accessToken");
        const initialValue = token ? JSON.parse(jwtDecode(token).exp) : 0;
        return initialValue;
    }
    );

    const [stompClient, setStompClient] = useState(null);

    const updateUser = () => {
        const token = localStorage.getItem("accessToken");
        var decode = jwtDecode(token);
        const userID = decode.userID;
        setExpiration(decode.exp);

        var config = {
            method: 'get',
            url: `http://localhost:8080/players/${userID}`,
            headers: {}
        };

        axios(config)
            .then(function (response) {
                setLoggedUser(response.data);
                localStorage.setItem("loggedUser", JSON.stringify(response.data));
            })
            .catch(function (error) {
                console.log(error);
            });
    }

    const removeUser = () => {
        setLoggedUser(null);
        setExpiration(0);
        localStorage.removeItem("loggedUser");
        localStorage.removeItem("accessToken");
    }

    let navigate = useNavigate();

    const checkTokenExpiration = () => {
        const currentTime = new Date().getTime() / 1000;
        if (currentTime > expiration) {
            removeUser();
            if (expiration === 0) {
                showNotification({
                    title: 'You need to login!'
                })
            }
            else {
                showNotification({
                    title: 'Your token is expired, you are logged out!'
                })
            }
            navigate("/UserAccess");
        }
    }

    useEffect(() => {
        const socket = SockJS(ENDPOINT);
        const stompClient = Stomp.over(socket);
        stompClient.connect({}, () => {
            stompClient.subscribe('/creature/notification', (data) => {
                const result = JSON.parse(data.body);
                showNotification({
                    title: result.content
                })
            });
        });
        // maintain the client for sending and receiving
        setStompClient(stompClient);
    }, []);

    const sendAddCreatureNotification = () => {
        stompClient.send("/app/create-creature", {});
    };

    const sendUpdateCreatureNotification = () => {
        stompClient.send("/app/update-creature", {});
    };

    const sendDeleteCreatureNotification = () => {
        stompClient.send("/app/delete-creature", {});
    };

    return (
        <>
            <Navbar loggedUser={loggedUser} />
            <Routes>
                <Route path="/" element={<Home loggedUser={loggedUser} />} />
                <Route path="/Summon" element={<Summon loggedUser={loggedUser} checkTokenExpiration={checkTokenExpiration} />} />
                <Route path="/Creatures" element={<CreaturesList />} />
                <Route path="/About" element={<About />} />
                <Route path="/SummonBanner/:bannerID/:amount" element={<SummonBanner loggedUser={loggedUser} updateUser={updateUser} checkTokenExpiration={checkTokenExpiration} />} />
                <Route path="/AddCreature" element={<AddCreature sendAddCreatureNotification={sendAddCreatureNotification} />} />
                <Route path="/EditCreature/:creatureID" element={<EditCreature sendUpdateCreatureNotification={sendUpdateCreatureNotification} sendDeleteCreatureNotification={sendDeleteCreatureNotification} checkTokenExpiration={checkTokenExpiration}/>} />
                <Route path="/UserAccess" element={<UserAccess updateUser={updateUser} />} />
                <Route path="/Logout" element={<Logout removeUser={removeUser} />} />
                <Route path="/Inventory" element={<Inventory loggedUser={loggedUser} checkTokenExpiration={checkTokenExpiration} />} />
                <Route path="/Creature/:creatureID" element={<CreatureDetail />} />
                <Route path="/OwnedCreature/:ownedCreatureID" element={<OwnedCreatureDetail checkTokenExpiration={checkTokenExpiration}/>} />
                <Route path="/MyProfile" element={<MyProfile loggedUser={loggedUser} checkTokenExpiration={checkTokenExpiration} updateUser={updateUser}/>} />
                <Route path="/CoinShop" element={<CoinShop loggedUser={loggedUser} checkTokenExpiration={checkTokenExpiration} updateUser={updateUser}/>} />
            </Routes>
        </>
    )
}

export default App;
