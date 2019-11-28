import React, { useEffect } from "react";
import { connect } from "react-redux";
import { getKlinikaAdmin } from "../../store/actions/adminKlinike";
import Button from "@material-ui/core/Button";

import List from "@material-ui/core/List";
import ListItem from "@material-ui/core/ListItem";
import ListItemText from "@material-ui/core/ListItemText";
import Divider from "@material-ui/core/Divider";
import Typography from "@material-ui/core/Typography";
import { Paper } from "@material-ui/core";
import Skeleton from "@material-ui/lab/Skeleton";
import { stat } from "fs";

const PodaciKlinikaTabs = ({
  klinika: { klinika },
  korisnikId,
  getKlinikaAdmin
}) => {
  useEffect(() => {
    getKlinikaAdmin(korisnikId);
  }, []);

  const [isEdit, setIsEdit] = React.useState(false);

  return (
    <Paper style={{ padding: 50, paddingBottom: 75 }}>
      <Typography
        variant="h4"
        component="h2"
        style={{ marginBottom: 20, marginLeft: 13 }}
      >
        Profil klinike
      </Typography>
      {!klinika && <Skeleton height={350} />}
      {!isEdit && klinika && (
        <>
          <List disablePadding>
            <ListItem>
              <ListItemText primary="Naziv" />
              <Typography variant="subtitle1">{klinika.naziv}</Typography>
            </ListItem>
            <ListItem>
              <ListItemText primary="Opis" />
              <Typography variant="subtitle1">{klinika.opis}</Typography>
            </ListItem>
            <Divider />
            <ListItem>
              <ListItemText primary="Adresa" />
              <Typography variant="subtitle1">{klinika.adresa}</Typography>
            </ListItem>

            <Divider />
          </List>
          <Button
            variant="contained"
            color="primary"
            style={{ float: "right", marginTop: 19 }}
            onClick={() => setIsEdit(true)}
          >
            Izmeni
          </Button>
        </>
      )}
    </Paper>
  );
};

function mapStateToProps(state) {
  return {
    klinika: state.adminovaKlinika,
    korisnikId: state.currentUser.user.id
  };
}

export default connect(mapStateToProps, { getKlinikaAdmin })(PodaciKlinikaTabs);
