import React, { useEffect } from "react";
import PropTypes from "prop-types";
import { makeStyles, useTheme } from "@material-ui/core/styles";
import Tabs from "@material-ui/core/Tabs";
import Tab from "@material-ui/core/Tab";
import Typography from "@material-ui/core/Typography";
import Box from "@material-ui/core/Box";
import PrikazZahtevaPregledi from "../PronalazenjeSale/PrikazZahtevaPregledi";
import PrikazZahtevaOperacije from "../PronalazenjeSale/PrikazZahtevaOperacije";
import { promeniPregled } from "../../../store/actions/pregled";
import { connect } from "react-redux";
import { withRouter } from "react-router-dom";
function TabPanel(props) {
  const { children, value, index, ...other } = props;

  return (
    <Typography
      component="div"
      role="tabpanel"
      hidden={value !== index}
      id={`full-width-tabpanel-${index}`}
      aria-labelledby={`full-width-tab-${index}`}
      {...other}
    >
      <Box p={3}>{children}</Box>
    </Typography>
  );
}

TabPanel.propTypes = {
  children: PropTypes.node,
  index: PropTypes.any.isRequired,
  value: PropTypes.any.isRequired
};

function a11yProps(index) {
  return {
    id: `full-width-tab-${index}`,
    "aria-controls": `full-width-tabpanel-${index}`
  };
}

const useStyles = makeStyles(theme => ({
  root: {
    backgroundColor: theme.palette.background.paper,
    flexGrow: 1
  },
  form: {
    width: "100%", // Fix IE 11 issue.
    marginTop: theme.spacing(1)
  }
}));

const FullWidthTabs = ({ promeniPregled }) => {
  useEffect(() => {
    console.log("uslo");
    promeniPregled();
  });

  const classes = useStyles();
  const theme = useTheme();
  const [value, setValue] = React.useState(0);

  const handleChange = (event, newValue) => {
    setValue(newValue);
  };

  return (
    <div className={classes.root}>
      <Tabs
        value={value}
        onChange={handleChange}
        indicatorColor="primary"
        textColor="primary"
      >
        <Tab label="Pregledi" {...a11yProps(0)} />
        <Tab label="Operacije" {...a11yProps(1)} />
      </Tabs>
      <TabPanel value={value} index={0} dir={theme.direction}>
        {value === 0 && <PrikazZahtevaPregledi />}
      </TabPanel>
      <TabPanel value={value} index={1} dir={theme.direction}>
        {value === 1 && <PrikazZahtevaOperacije />}
      </TabPanel>
    </div>
  );
};
const mapStateToProps = state => ({});

export default withRouter(
  connect(mapStateToProps, {
    promeniPregled
  })(FullWidthTabs)
);
