import React, { useEffect, useState } from "react";
import * as FaIcons from "react-icons/fa";
import * as AiIcons from "react-icons/ai";
import { NavLink, Link, useNavigate } from "react-router-dom";
import { SidebarData } from "./datas/SidebarData";
import './styles/Navbar.css';
import { IconContext } from "react-icons";
import ProfileMenu from "./ProfileMenu";
import ProfileMenuItems from "./ProfileMenuItems";
import { useClickOutside } from '@mantine/hooks';

const Navbar = (loggedUser) => {
    const [sidebar, setSidebar] = useState(false);
    const [profileMenuState, setProfileMenuState] = useState(false);
    const [coin, setCoin] = useState(0);
    const clickOutsideSidebarRef = useClickOutside(() => setSidebar(false));
    const clickOutsideProfileMenuRef = useClickOutside(() => setProfileMenuState(false));

    const showSidebar = () => {
        setSidebar(!sidebar);
        if (profileMenuState) {
            setProfileMenuState(!profileMenuState);
        }
    };

    const showProfileMenu = () => {
        setProfileMenuState(!profileMenuState);
        if (sidebar) {
            setSidebar(!sidebar);
        }
    };

    useEffect(() => {
        if (loggedUser.loggedUser !== null) {
            setCoin(loggedUser.loggedUser.coin);
        }
    }, [loggedUser.loggedUser])

    const navigate = useNavigate();

    return (
        <>
            <IconContext.Provider value={{ color: '#fff' }}>
                <div className="navbar">
                    <div className="navbar-menu">
                        <Link to="#" className="menu-bars">
                            <FaIcons.FaBars data-cy="navbar-menu" onClick={showSidebar} />
                        </Link>
                    </div>
                    <div className="navbar-title">
                        <h1>KASINTU</h1>
                    </div>
                    <div className="navbar-profile" ref={clickOutsideProfileMenuRef}>
                        {
                            loggedUser.loggedUser != null ?
                                <>
                                    <div className="coin">
                                        <div className="coin-info">{coin} Coins</div>
                                        <div data-cy="navbar-coin-shop" className="coin-addmore" onClick={() => navigate("/CoinShop")}>+</div>
                                    </div>
                                    <ProfileMenu icon={<FaIcons.FaUser />} profileMenuState={profileMenuState} showProfileMenu={showProfileMenu}>
                                        <ProfileMenuItems showProfileMenu={showProfileMenu} />
                                    </ProfileMenu>
                                </>
                                :
                                <>
                                    <NavLink data-cy="navbar-login" to="/UserAccess" className="navbar-login">
                                        Login
                                    </NavLink>
                                </>
                        }
                    </div>
                </div>
                <nav className={sidebar ? 'nav-menu active' : 'nav-menu'} ref={clickOutsideSidebarRef}>
                    <ul className='nav-menu-items' style={{ paddingLeft: 0 }}>
                        <li className="navbar-toggle" onClick={showSidebar}>
                            <Link to="#" className="menu-bars">
                                <AiIcons.AiOutlineClose />
                            </Link>
                        </li>
                        {SidebarData.map((item, index) => {
                            return (
                                <li key={index} className={item.className}>
                                    <NavLink data-cy={item.datacy} to={item.path} onClick={showSidebar}>
                                        {item.icon}
                                        <span>{item.title}</span>
                                    </NavLink>
                                </li>
                            )
                        })}
                    </ul>
                </nav>
            </IconContext.Provider>
        </>
    )
}

export default Navbar;