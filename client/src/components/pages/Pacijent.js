import React, { useEffect } from "react";
import { makeStyles } from "@material-ui/core/styles";
import { connect } from "react-redux";
import Divider from "@material-ui/core/Divider";
import Tabs from "@material-ui/core/Tabs";
import Tab from "@material-ui/core/Tab";
import Drawer from "@material-ui/core/Drawer";
import TabPanel from "../Tabs/TabPanel";
import AppBar from "../layout/AppBarLogedIn";
import PacijentZdr from "../Tabs/Pacijent/PacijentZdr";
import ProfilPacijentaTab from "../Tabs/Pacijent/ProfilPacijentaTab";
import { proveraPregledanPacijent } from "../../store/actions/pacijent";

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

const Pacijent = ({
  match,
  korisnikId,
  pregledanPacijent,
  proveraPregledanPacijent
}) => {
  useEffect(() => {
    console.log("ima effect");
    proveraPregledanPacijent(korisnikId, match.params.id);
  }, [korisnikId, match.params.id, proveraPregledanPacijent]);
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
            {value === 1 && pregledanPacijent && (
              <PacijentZdr id={match.params.objekat} />
            )}
            {value === 1 && !pregledanPacijent && (
              <div>
                Nemate pravo pristupa zdravstvenom kartonu ovog pacijenta.
              </div>
            )}
          </TabPanel>
        </main>
      </div>
    </>
  );
};
const mapStateToProps = state => ({
  korisnikId: state.currentUser.user.id,
  pregledanPacijent: state.pacijent.pregledanPacijent
});

export default connect(mapStateToProps, {
  proveraPregledanPacijent
})(Pacijent);
