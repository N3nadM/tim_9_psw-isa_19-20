import React, { useEffect } from "react";
import { makeStyles } from "@material-ui/core/styles";

import Divider from "@material-ui/core/Divider";
import Tabs from "@material-ui/core/Tabs";
import Tab from "@material-ui/core/Tab";
import Drawer from "@material-ui/core/Drawer";
import TabPanel from "../Tabs/TabPanel";
import AppBar from "../layout/AppBarLogedIn";
import PacijentZdrKarton from "../Tabs/Pacijent/PacijentZdrKarton";
import ProfilPacijentaTab from "../Tabs/Pacijent/ProfilPacijentaTab";

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

export default function Pacijent({ match }) {
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
        <Tab label="Profil pacijenta" {...a11yProps(0)} />
        <Tab label="Zdravstveni karton" {...a11yProps(1)} />
      </Tabs>
    </div>
  );

  return (
    <>
      <AppBar pacijentRoute setTab={setValue} handleChange={handleChange} />
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
            {value === 0 && <ProfilPacijentaTab id={match.params.id} />}
          </TabPanel>
          <TabPanel value={value} index={1}>
            {value === 1 && <PacijentZdrKarton id={match.params.id} />}
          </TabPanel>
        </main>
      </div>
    </>
  );
}
