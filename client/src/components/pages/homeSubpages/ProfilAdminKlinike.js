import React from "react";
import PropTypes from "prop-types";
import AppBar from "@material-ui/core/AppBar";
import CssBaseline from "@material-ui/core/CssBaseline";
import Divider from "@material-ui/core/Divider";
import Drawer from "@material-ui/core/Drawer";
import Hidden from "@material-ui/core/Hidden";
import IconButton from "@material-ui/core/IconButton";
import MenuIcon from "@material-ui/icons/Menu";
import Toolbar from "@material-ui/core/Toolbar";
import Typography from "@material-ui/core/Typography";
import { makeStyles, useTheme } from "@material-ui/core/styles";
import PasswordChange from "../../PasswordChange/PasswordChange";
import LekariTabs from "../../Tabs/LekariTabs";
import SaleTabs from "../../Tabs/SaleTabs";
import TipoviPregledaTabs from "../../Tabs/TipoviPregledaTabs";
import SlobodniTerminiTabs from "../../Tabs/SlobodniTerminTabs";
import LicniPodaciTabs from "../../Tabs/LicniPodaciTabs";
import PodaciKlinikaTabs from "../../Tabs/PodaciKlinikaTabs";

import Paper from "@material-ui/core/Paper";
import Grid from "@material-ui/core/Grid";

import Box from "@material-ui/core/Box";

import Tabs from "@material-ui/core/Tabs";
import Tab from "@material-ui/core/Tab";

const drawerWidth = 240;

function TabPanel(props) {
  const { children, value, index, ...other } = props;

  return (
    <Typography
      component="div"
      role="tabpanel"
      hidden={value !== index}
      id={`simple-tabpanel-${index}`}
      aria-labelledby={`simple-tab-${index}`}
      {...other}
    >
      <Box p={3}>{children}</Box>
    </Typography>
  );
}

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
  appBar: {
    [theme.breakpoints.up("sm")]: {
      width: `calc(100% - ${drawerWidth}px)`,
      marginLeft: drawerWidth
    }
  },
  paper: {
    padding: theme.spacing(30),
    textAlign: "center",
    color: theme.palette.text.primary
  },
  content: {
    width: `1000 px`
  },
  menuButton: {
    marginRight: theme.spacing(2),
    [theme.breakpoints.up("sm")]: {
      display: "none"
    }
  },
  drawerPaper: {
    width: drawerWidth
  },
  tabs: {
    borderRight: `1px solid ${theme.palette.divider}`
  }
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
  const { container } = props;
  const classes = useStyles();
  const theme = useTheme();
  const [mobileOpen, setMobileOpen] = React.useState(false);

  const handleDrawerToggle = () => {
    setMobileOpen(!mobileOpen);
  };

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
    <div className={classes.root}>
      <CssBaseline />
      <AppBar position="fixed" className={classes.appBar}>
        <Toolbar>
          <IconButton
            color="inherit"
            aria-label="open drawer"
            edge="start"
            onClick={handleDrawerToggle}
            className={classes.menuButton}
          >
            <MenuIcon />
          </IconButton>
          <Typography variant="h6" noWrap>
            Profil administratora klinike
          </Typography>
        </Toolbar>
      </AppBar>
      <nav className={classes.drawer} aria-label="mailbox folders">
        {/* The implementation can be swapped with js to avoid SEO duplication of links. */}
        <Hidden smUp implementation="css">
          <Drawer
            container={container}
            variant="temporary"
            anchor={theme.direction === "rtl" ? "right" : "left"}
            open={mobileOpen}
            onClose={handleDrawerToggle}
            classes={{
              paper: classes.drawerPaper
            }}
            ModalProps={{
              keepMounted: true // Better open performance on mobile.
            }}
          >
            {drawer}
          </Drawer>
        </Hidden>
        <Hidden xsDown implementation="css">
          <Drawer
            classes={{
              paper: classes.drawerPaper
            }}
            variant="permanent"
            open
          >
            {drawer}
          </Drawer>
        </Hidden>
      </nav>
      <main className={classes.content}>
        <div className={classes.toolbar} />
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
  );
}

ResponsiveDrawer.propTypes = {
  /**
   * Injected by the documentation to work in an iframe.
   * You won't need it on your project.
   */
  container: PropTypes.instanceOf(
    typeof Element === "undefined" ? Object : Element
  )
};

export default ResponsiveDrawer;
