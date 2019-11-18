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
        <Tab label="Podaci o klinici" {...a11yProps(0)} />
        <Tab label="Inbox" {...a11yProps(1)} />
        <Tab label="Lični podaci" {...a11yProps(2)} />
        <Tab label="Promena lozinke" {...a11yProps(3)} />
        <Tab label="Slobodni termini pregleda" {...a11yProps(4)} />
        <Tab label="Lekari" {...a11yProps(5)} />
        <Tab label="Sale" {...a11yProps(6)} />
        <Tab label="Tipovi pregleda" {...a11yProps(7)} />
        <Tab label="Izveštaj o poslovanju klinike" {...a11yProps(8)} />
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
            <PodaciKlinikaTabs />
          </TabPanel>
          <TabPanel value={value} index={1}>
            Inbox
          </TabPanel>
          <TabPanel value={value} index={2}>
            <LicniPodaciTabs />
          </TabPanel>
          <TabPanel value={value} index={3}>
            <PasswordChange />
          </TabPanel>
          <TabPanel value={value} index={4}>
            <SlobodniTerminiTabs />
          </TabPanel>
          <TabPanel value={value} index={5}>
            <LekariTabs />
          </TabPanel>
          <TabPanel value={value} index={6}>
            <SaleTabs />
          </TabPanel>
          <TabPanel value={value} index={7}>
            <TipoviPregledaTabs />
          </TabPanel>
          <TabPanel value={value} index={8}>
            <Grid container spacing={3}>
              <Grid item xs>
                <Paper className={classes.paper}>
                  Prosecna ocena klinike i ocene lekara
                </Paper>
              </Grid>
              <Grid item xs>
                <Paper className={classes.paper}>Grafik prihoda klinike</Paper>
              </Grid>
              <Grid item xs>
                <Paper className={classes.paper}>
                  Grafici odrzanih pregleda na nedeljnom, mesecnom i godisnjem
                  nivou
                </Paper>
              </Grid>
            </Grid>
          </TabPanel>
        </main>
      </div>
    </>
  );
}

export default ResponsiveDrawer;
