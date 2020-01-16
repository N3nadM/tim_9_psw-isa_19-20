import React from "react";
import { makeStyles } from "@material-ui/core/styles";
import Typography from "@material-ui/core/Typography";
import { connect } from "react-redux";
import { Grid } from "@material-ui/core";

const ProsecnaOcenaKlinike = ({ klinika }) => {
  return (
    <React.Fragment>
      <Grid container spacing={3}>
        <Grid item md={6}>
          <Typography component="h2" variant="h6" color="primary" gutterBottom>
            Prosečna ocena klinike
          </Typography>
        </Grid>
        <Grid item md={6}>
          <Typography component="p" variant="h4">
            {klinika.ocena}
          </Typography>
        </Grid>
      </Grid>
    </React.Fragment>
  );
};

function mapStateToProps(state) {
  return {
    klinika: state.adminKlinike.klinika
  };
}

export default connect(mapStateToProps, {})(ProsecnaOcenaKlinike);
