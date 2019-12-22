import React, { useEffect } from "react";
import { connect } from "react-redux";
import { getPacijent } from "../../store/actions/pacijent";
import Button from "@material-ui/core/Button";
import { editPacijent } from "../../store/actions/pacijent";

import List from "@material-ui/core/List";
import ListItem from "@material-ui/core/ListItem";
import ListItemText from "@material-ui/core/ListItemText";
import Divider from "@material-ui/core/Divider";
import Typography from "@material-ui/core/Typography";
import EditTab from "./EditTab";
import { Paper } from "@material-ui/core";
import Skeleton from "@material-ui/lab/Skeleton";

const PacijentProfilTab = ({
  pacijent: { pacijent },
  korisnikId,
  korisnik,
  getPacijent,
  editPacijent
}) => {
  useEffect(() => {
    getPacijent(korisnikId);
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
        Korisnički profil
      </Typography>
      {!pacijent && <Skeleton height={350} />}
      {isEdit && (
        <EditTab
          korisnik={korisnik}
          editKorisnik={editPacijent}
          setIsEdit={setIsEdit}
        />
      )}
      {!isEdit && pacijent && (
        <>
          <List disablePadding>
            <ListItem>
              <ListItemText primary="Ime" />
              <Typography variant="subtitle1">{korisnik.ime}</Typography>
            </ListItem>
            <ListItem>
              <ListItemText primary="Prezime" />
              <Typography variant="subtitle1">{korisnik.prezime}</Typography>
            </ListItem>
            <ListItem>
              <ListItemText primary="Telefon" />
              <Typography variant="subtitle1">{korisnik.telefon}</Typography>
            </ListItem>
            <Divider />
            <ListItem>
              <ListItemText primary="Adresa" />
              <Typography variant="subtitle1">{korisnik.adresa}</Typography>
            </ListItem>
            <ListItem>
              <ListItemText primary="Grad" />
              <Typography variant="subtitle1">{korisnik.grad}</Typography>
            </ListItem>
            <ListItem>
              <ListItemText primary="Drzava" />
              <Typography variant="subtitle1">{korisnik.drzava}</Typography>
            </ListItem>
            <Divider />
            <ListItem>
              <ListItemText primary="Broj osiguranika" />
              <Typography variant="subtitle1">{pacijent.jbzo}</Typography>
            </ListItem>
            <ListItem>
              <ListItemText primary="Email" />
              <Typography variant="subtitle1">{korisnik.email}</Typography>
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
    pacijent: state.pacijent,
    korisnik: state.currentUser.korisnik,
    korisnikId: state.currentUser.user.id
  };
}

export default connect(mapStateToProps, { getPacijent, editPacijent })(
  PacijentProfilTab
);
