import React from "react";
import ReactDOM from "react-dom";
import { BrowserRouter as Router } from "react-router-dom";
import App from "./App";
import { NotificationsProvider } from '@mantine/notifications';
import { MantineProvider } from '@mantine/core';
import { ModalsProvider } from '@mantine/modals';

ReactDOM.render(
    <React.StrictMode>
        <Router>
            <MantineProvider>
                <ModalsProvider>
                    <NotificationsProvider>
                        <App />
                    </NotificationsProvider>
                </ModalsProvider>
            </MantineProvider>
        </Router>
    </React.StrictMode>
    , document.getElementById("root"));