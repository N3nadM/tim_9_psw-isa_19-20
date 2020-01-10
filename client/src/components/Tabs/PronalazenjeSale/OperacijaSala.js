import React, { useEffect } from "react";
import { connect } from "react-redux";
import { getOperacijaById } from "../../../store/actions/operacija";

import List from "@material-ui/core/List";
import ListItem from "@material-ui/core/ListItem";
import ListItemText from "@material-ui/core/ListItemText";
import Typography from "@material-ui/core/Typography";
import { Paper } from "@material-ui/core";
import Skeleton from "@material-ui/lab/Skeleton";

const OperacijaSala = ({ id, operacija, getOperacijaById }) => {
  useEffect(() => {
    getOperacijaById(id);
    //eslint-disable-next-line
  }, []);

  const [isEdit, setIsEdit] = React.useState(false);

  return (
    <Paper style={{ padding: 50, paddingBottom: 75 }}>
      <Typography
        variant="h4"
        component="h2"
        style={{ marginBottom: 20, marginLeft: 13 }}
      >
        Pregled
      </Typography>
      {!operacija && <Skeleton height={350} />}
      {operacija && (
        <>
          <List disablePadding>
            <ListItem>
              <ListItemText primary="Pacijent" />
              <Typography variant="subtitle1">
                {operacija.pacijent.korisnik.ime}
              </Typography>
            </ListItem>
            <ListItem>
              <ListItemText primary="Datum pocetka" />
              <Typography variant="subtitle1">
                {operacija.datumPocetka}
              </Typography>
            </ListItem>
          </List>
        </>
      )}
    </Paper>
  );
};

function mapStateToProps(state) {
  return {
    operacija: state.operacija.operacija
  };
}

export default connect(mapStateToProps, { getOperacijaById })(OperacijaSala);
