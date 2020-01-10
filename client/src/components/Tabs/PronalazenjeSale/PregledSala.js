import React, { useEffect } from "react";
import { connect } from "react-redux";
import { getPregledById } from "../../../store/actions/pregled";

import List from "@material-ui/core/List";
import ListItem from "@material-ui/core/ListItem";
import ListItemText from "@material-ui/core/ListItemText";
import Typography from "@material-ui/core/Typography";
import { Paper } from "@material-ui/core";
import Skeleton from "@material-ui/lab/Skeleton";

const PregledSala = ({ id, pregled, getPregledById }) => {
  useEffect(() => {
    getPregledById(id);
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
      {!pregled && <Skeleton height={350} />}
      {pregled && (
        <>
          <List disablePadding>
            <ListItem>
              <ListItemText primary="Lekar" />
              <Typography variant="subtitle1">
                {pregled.lekar.korisnik.ime}
              </Typography>
            </ListItem>
            <ListItem>
              <ListItemText primary="Pacijent" />
              <Typography variant="subtitle1">
                {pregled.pacijent.korisnik.ime}
              </Typography>
            </ListItem>
            <ListItem>
              <ListItemText primary="Datum pocetka" />
              <Typography variant="subtitle1">
                {pregled.datumPocetka}
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
    pregled: state.pregled.pregled
  };
}

export default connect(mapStateToProps, { getPregledById })(PregledSala);
