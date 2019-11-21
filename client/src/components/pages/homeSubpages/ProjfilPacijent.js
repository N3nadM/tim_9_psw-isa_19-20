import React, { useEffect } from "react";
import { makeStyles } from "@material-ui/core/styles";

import Divider from "@material-ui/core/Divider";
import Tabs from "@material-ui/core/Tabs";
import Tab from "@material-ui/core/Tab";
import Drawer from "@material-ui/core/Drawer";
import TabPanel from "../../Tabs/TabPanel";
import AppBar from "../../layout/AppBarLogedIn";
import PacijentProfilTab from "../../Tabs/PacijentProfilTab";
import ZakaziPregled from "../../Tabs/ZakaziPregled";
import ZdrKarton from "../../Tabs/ZdrKarton";

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

export default function ProfilPacijent() {
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
        <Tab label="Profil" {...a11yProps(0)} />
        <Tab label="Moji pregledi" {...a11yProps(1)} />
        <Tab label="Moje operacije" {...a11yProps(2)} />
        <Tab label="Zdravstveni karton" {...a11yProps(3)} />
        <Tab label="Zakazi pregled" {...a11yProps(4)} />
      </Tabs>
    </div>
  );

  return (
    <>
      <AppBar setTab={setValue} handleChange={handleChange} />
      <div className={classes.root}>
        <nav className={classes.drawer} aria-label="mailbox folders">
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
            <PacijentProfilTab />
          </TabPanel>
          <TabPanel value={value} index={1}>
            Pregledi
          </TabPanel>
          <TabPanel value={value} index={2}>
            operacije
          </TabPanel>
          <TabPanel value={value} index={3}>
            {value === 3 && <ZdrKarton />}
          </TabPanel>
          <TabPanel value={value} index={4}>
            <ZakaziPregled />
          </TabPanel>
        </main>
      </div>
    </>
  );
}
