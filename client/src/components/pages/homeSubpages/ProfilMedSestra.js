import React from "react";
import clsx from "@material-ui/core";
import CssBaseline from "@material-ui/core/CssBaseline";
import Divider from "@material-ui/core/Divider";
import Drawer from "@material-ui/core/Drawer";
import Typography from "@material-ui/core/Typography";
import { makeStyles, useTheme } from "@material-ui/core/styles";
import PasswordChange from "../../PasswordChange/PasswordChange";
import LekariTabs from "../../Tabs/LekariTabs";
import SaleTabs from "../../Tabs/SaleTabs";
import TipoviPregledaTabs from "../../Tabs/TipoviPregledaTabs";
import SlobodniTerminiTabs from "../../Tabs/SlobodniTerminTabs";
import LicniPodaciTabs from "../../Tabs/LicniPodaciTabs";
import PodaciKlinikaTabs from "../../Tabs/PodaciKlinikaTabs";
import AppBar from "../../layout/AppBarLogedIn";
import GodisnjiOdmorTab from "../../Tabs/GodisnjiOdmorTab";

import Paper from "@material-ui/core/Paper";
import Grid from "@material-ui/core/Grid";
import TabPanel from "../../Tabs/TabPanel";

import Tabs from "@material-ui/core/Tabs";
import Tab from "@material-ui/core/Tab";

const drawerWidth = 240;

const useStyles = makeStyles(theme => ({
  root: {
    display: "flex"
  },
  drawer: {
    [theme.breakpoints.up("sm")]: {
      width: drawerWidth,
      flexShrink: 0
    }
  },
  paper: {
    padding: theme.spacing(30),
    textAlign: "center",
    color: theme.palette.text.primary
  },
  drawerPaper: {
    width: drawerWidth
  },
  tabs: {
    borderRight: `1px solid ${theme.palette.divider}`
  },
  toolbar: theme.mixins.toolbar
}));
function a11yProps(index) {
  return {
    id: `simple-tab-${index}`,
    "aria-controls": `simple-tabpanel-${index}`
  };
}

function ResponsiveDrawer(props) {
  const [value, setValue] = React.useState(0);
  const handleChange = (event, newValue) => {
    setValue(newValue);
  };
  const classes = useStyles();

  const drawer = (
    <div>
      <div className={classes.toolbar} />
      <Divider />
      <Tabs
        orientation="vertical"
        value={value}
        onChange={handleChange}
        variant="scrollable"
        aria-label="simple tabs example"
      >
        <Tab label="Home" {...a11yProps(0)} />
        <Tab label="Lista pacijenata" {...a11yProps(1)} />
        <Tab label="Radni kalendar" {...a11yProps(2)} />
        <Tab label="Godišnji odmor ili odsustvo" {...a11yProps(3)} />
        <Tab label="Lični podaci" {...a11yProps(4)} />
        <Tab label="Recepti za overu" {...a11yProps(5)} />
      </Tabs>
    </div>
  );

  return (
    <>
      <AppBar setTab={setValue} handleChange={handleChange} />
      <div className={classes.root}>
        <nav className={classes.drawer} aria-label="mailbox folders">
          {/* The implementation can be swapped with js to avoid SEO duplication of links. */}

          <Drawer
            classes={{
              paper: classes.drawerPaper
            }}
            variant="permanent"
            open
          >
            {drawer}
          </Drawer>
        </nav>
        <main>
          <TabPanel value={value} index={0}>
            Home
          </TabPanel>
          <TabPanel value={value} index={1}>
            Lista pacijenata
          </TabPanel>
          <TabPanel value={value} index={2}>
            Radni kalendar
          </TabPanel>
          <TabPanel value={value} index={3}>
            <GodisnjiOdmorTab />
          </TabPanel>
          <TabPanel value={value} index={4}>
            <LicniPodaciTabs />
          </TabPanel>
          <TabPanel value={value} index={5}>
            Recepti za overu
          </TabPanel>
        </main>
      </div>
    </>
  );
}

export default ResponsiveDrawer;
