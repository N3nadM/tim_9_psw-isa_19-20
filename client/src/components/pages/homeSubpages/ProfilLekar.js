import React from "react";
import Divider from "@material-ui/core/Divider";
import Drawer from "@material-ui/core/Drawer";
import { makeStyles } from "@material-ui/core/styles";
import PasswordChange from "../../PasswordChange/PasswordChange";
import ZakazivanjePregledaTabs from "../../Tabs/ZakazivanjePregledaTabs";
import AppBar from "../../layout/AppBarLogedIn";
import GodisnjiOdmorTab from "../../Tabs/GodisnjiOdmorTab";
import IzvestajPregledTab from "../../Tabs/IzvestajPregledTab";
import TabPanel from "../../Tabs/TabPanel";
import RadniKalendarTab from "../../Tabs/RadniKalendarTab";
import Tabs from "@material-ui/core/Tabs";
import Tab from "@material-ui/core/Tab";
import LekarProfilTab from "../../Tabs/LekarProfilTab";
import TabelaPacijenataKlinike from "../../Tabele/TabelaPacijenataKlinike";

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
        <Tab label="Zakazivanje pregleda" {...a11yProps(5)} />
        <Tab label="Izveštaj o pregledu" {...a11yProps(6)} />
        <Tab label="Izmena lozinke" {...a11yProps(7)} />
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
        <main className={classes.content}>
          <TabPanel value={value} index={0}>
            Home
          </TabPanel>
          <TabPanel value={value} index={1}>
            <TabelaPacijenataKlinike />
          </TabPanel>
          <TabPanel value={value} index={2}>
            <RadniKalendarTab />
          </TabPanel>
          <TabPanel value={value} index={3}>
            <GodisnjiOdmorTab />
          </TabPanel>
          <TabPanel value={value} index={4}>
            <LekarProfilTab />
          </TabPanel>
          <TabPanel value={value} index={5}>
            <ZakazivanjePregledaTabs />
          </TabPanel>
          <TabPanel value={value} index={6}>
            <IzvestajPregledTab />
          </TabPanel>
          <TabPanel value={value} index={7}>
            <PasswordChange />
          </TabPanel>
        </main>
      </div>
    </>
  );
}

export default ResponsiveDrawer;
