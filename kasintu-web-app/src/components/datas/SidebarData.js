import React from "react";
import * as AiIcons from "react-icons//ai";
//import * as IoIcons from "react-icons/io";
import * as GiIcons from "react-icons/gi";

export const SidebarData = [
    {
        title: "Home",
        path: "/",
        icon: <AiIcons.AiFillHome />,
        className: "nav-text",
        datacy: "navbar-home"
    },
    {
        title: "Creatures",
        path: "/Creatures",
        icon: <GiIcons.GiDove />,
        className: "nav-text",
        datacy: "navbar-creatures"
    },
    {
        title: "Inventory",
        path: "/Inventory",
        icon: <GiIcons.GiBackpack />,
        className: "nav-text",
        datacy: "navbar-inventory"
    },
    {
        title: "Summon",
        path: "/Summon",
        icon: <GiIcons.GiEagleHead />,
        className: "nav-text",
        datacy: "navbar-summon"
    }
]