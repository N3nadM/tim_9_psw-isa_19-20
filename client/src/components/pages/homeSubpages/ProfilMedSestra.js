import React from "react";
import clsx from "@material-ui/core";
import CssBaseline from "@material-ui/core/CssBaseline";
import Divider from "@material-ui/core/Divider";
import Drawer from "@material-ui/core/Drawer";
import Typography from "@material-ui/core/Typography";
import { makeStyles, useTheme } from "@material-ui/core/styles";
import PasswordChange from "../../PasswordChange/PasswordChange";
import TabelaPacijenataKlinike from "../../Tabele/TabelaPacijenataKlinike";
import SaleTabs from "../../Tabs/SaleTabs";
import TipoviPregledaTabs from "../../Tabs/TipoviPregledaTabs";
import SlobodniTerminiTabs from "../../Tabs/SlobodniTerminTabs";
import LicniPodaciTabs from "../../Tabs/LicniPodaciTabs";
import PodaciKlinikaTabs from "../../Tabs/PodaciKlinikaTabs";
import AppBar from "../../layout/AppBarLogedIn";
import GodisnjiOdmorTab from "../../Tabs/GodisnjiOdmorTab";
import MedSestraProfilTab from "../../Tabs/MedSestraProfilTab";
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
    width: drawerWidth,
    flexShrink: 0
  },
  drawerPaper: {
    width: drawerWidth
  },
  content: {
    flexGrow: 1,
    padding: theme.spacing(3)
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
        <Tab label="Izmena lozinke" {...a11yProps(6)} />
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
            <TabelaPacijenataKlinike />
          </TabPanel>
          <TabPanel value={value} index={2}>
            Radni kalendar
          </TabPanel>
          <TabPanel value={value} index={3}>
            <GodisnjiOdmorTab />
          </TabPanel>
          <TabPanel value={value} index={4}>
            <MedSestraProfilTab />
          </TabPanel>
          <TabPanel value={value} index={5}>
            Recepti za overu
          </TabPanel>
          <TabPanel value={value} index={6}>
            <PasswordChange />
          </TabPanel>
        </main>
      </div>
    </>
  );
}

export default ResponsiveDrawer;
