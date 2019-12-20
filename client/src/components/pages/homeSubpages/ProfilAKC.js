import React from "react";
import { makeStyles } from "@material-ui/core/styles";
import Tabela from "../../TabelaAKC/Tabela";
import DodajKliniku from "../../Tabs/DodajKlinikuTab";
import DodajAK from "../../Tabs/DodajAKTab";
import DodajAKC from "../../Tabs/DodajAKCTab";
import SifranikTab from "../../Tabs/SifranikTab";
import Divider from "@material-ui/core/Divider";
import Tabs from "@material-ui/core/Tabs";
import Tab from "@material-ui/core/Tab";
import Drawer from "@material-ui/core/Drawer";
import TabPanel from "../../Tabs/TabPanel";
import AppBar from "../../layout/AppBarLogedIn";

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

export default function ProfilAKC() {
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
        <Tab label="Zahtevi" {...a11yProps(0)} />
        <Tab label="Dodaj kliniku" {...a11yProps(1)} />
        <Tab label="Dodaj admina klinike" {...a11yProps(2)} />
        <Tab label="Dodaj admina KC" {...a11yProps(3)} />
        <Tab label="Sifranik" {...a11yProps(4)} />
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
            <Tabela />
          </TabPanel>
          <TabPanel value={value} index={1}>
            <DodajKliniku />
          </TabPanel>
          <TabPanel value={value} index={2}>
            <DodajAK />
          </TabPanel>
          <TabPanel value={value} index={3}>
            <DodajAKC />
          </TabPanel>
          <TabPanel value={value} index={4}>
            <SifranikTab />
          </TabPanel>
        </main>
      </div>
    </>
  );
}
