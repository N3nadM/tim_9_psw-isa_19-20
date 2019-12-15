import React from "react";
import { connect } from "react-redux";

import List from "@material-ui/core/List";
import ListItem from "@material-ui/core/ListItem";
import ListItemText from "@material-ui/core/ListItemText";
import Divider from "@material-ui/core/Divider";
import Typography from "@material-ui/core/Typography";
import { Paper } from "@material-ui/core";

const Informacije = ({ obj }) => {
  let pacijent = obj.pacijent;
  let tip = obj.tip;

  let dZ = obj.datumZavrsetka;
  let dP = obj.datumPocetka;

  let dZTime = dZ.getHours() * 60 + dZ.getMinutes();
  let dPTime = dP.getHours() * 60 + dP.getMinutes();

  let time = dZTime - dPTime;

  let hours = 0;
  let mins = 0;

  var i;
  for (i = 0; i <= 24; i++) {
    if (time > 59) {
      time -= 60;
      hours += 1;
    } else {
      mins = time;
      break;
    }
  }

  return (
    <Paper style={{ padding: 50, paddingBottom: 75 }}>
      <Typography
        variant="h4"
        component="h2"
        style={{ marginBottom: 20, marginLeft: 13 }}
      >
        Osnovne informacije
      </Typography>
      <List disablePadding>
        <ListItem>
          <ListItemText primary="Datum i vreme pocetka" />
          <Typography variant="subtitle1">
            {obj.datumPocetka.toLocaleString()}
          </Typography>
        </ListItem>
        <ListItem>
          <ListItemText primary="Trajanje" />
          <Typography variant="subtitle1">
            {hours + "h " + mins + "min"}
          </Typography>
        </ListItem>
        <ListItem>
          <ListItemText primary="Tip" />
          <Typography variant="subtitle1">{tip}</Typography>
        </ListItem>
        <Divider />
        <ListItem>
          <ListItemText primary="Ime pacijenta" />
          <Typography variant="subtitle1">
            {pacijent ? pacijent.korisnik.ime : "Nema leba"}
          </Typography>
        </ListItem>
        <ListItem>
          <ListItemText primary="Prezime pacijenta" />
          <Typography variant="subtitle1">
            {pacijent ? pacijent.korisnik.prezime : "Nema lebaca"}
          </Typography>
        </ListItem>
        <ListItem>
          <ListItemText primary="Broj osiguranika" />
          <Typography variant="subtitle1">
            {pacijent ? pacijent.jbzo : "Lebac neces jesti"}
          </Typography>
        </ListItem>
      </List>
    </Paper>
  );
};

const mapStateToProps = state => ({});

export default connect(mapStateToProps, {})(Informacije);
