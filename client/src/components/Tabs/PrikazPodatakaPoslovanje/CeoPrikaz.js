import React from "react";
import { connect } from "react-redux";
import { Paper, Grid } from "@material-ui/core";
import { makeStyles } from "@material-ui/core/styles";
import ProsecnaOcenaKlinike from "../PrikazPodatakaPoslovanje/ProsecnaOcenaKlinike";
import ProsecneOceneLekara from "../PrikazPodatakaPoslovanje/ProsecneOceneLekara";
import RacunanjePrihoda from "../PrikazPodatakaPoslovanje/RacunanjePrihoda";
import GrafikPregleda from "../PrikazPodatakaPoslovanje/GrafikPregleda";

const useStyles = makeStyles(theme => ({
  paper: {
    padding: theme.spacing(5),

    color: theme.palette.text.primary
  }
}));

const CeoPrikaz = ({ adminKlinike: { klinika } }) => {
  const classes = useStyles();
  return (
    <Grid container spacing={3}>
      <Grid item xs={12}>
        <Paper className={classes.paper}>
          <ProsecnaOcenaKlinike />
          <ProsecneOceneLekara klinika={klinika} />
        </Paper>
      </Grid>
      <Grid item xs={12}>
        <Paper className={classes.paper}>
          <RacunanjePrihoda klinika={klinika} />
        </Paper>
      </Grid>
      <Grid item xs={12}>
        <Paper className={classes.paper}>
          <GrafikPregleda klinika={klinika} />
        </Paper>
      </Grid>
    </Grid>
  );
};

function mapStateToProps(state) {
  return {
    adminKlinike: state.adminKlinike
  };
}

export default connect(mapStateToProps, {})(CeoPrikaz);
