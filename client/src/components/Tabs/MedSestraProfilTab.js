import React, { useEffect } from "react";
import { connect } from "react-redux";
import { getMedSestra } from "../../store/actions/medSestra";
import Button from "@material-ui/core/Button";
import { editMedSestra } from "../../store/actions/medSestra";

import List from "@material-ui/core/List";
import ListItem from "@material-ui/core/ListItem";
import ListItemText from "@material-ui/core/ListItemText";
import Divider from "@material-ui/core/Divider";
import Typography from "@material-ui/core/Typography";
import EditTab from "./EditTab";

const MedSestraProfilTab = ({
  medsestra: { medsestra },
  korisnikId,
  korisnik,
  getMedSestra,
  editMedSestra
}) => {
  useEffect(() => {
    getMedSestra(korisnikId);
    //eslint-disable-next-line
  }, []);

  const [isEdit, setIsEdit] = React.useState(false);

  return (
    <div>
      <Typography
        variant="h4"
        component="h2"
        style={{ marginBottom: 20, marginLeft: 13 }}
      >
        Korisnički profil
      </Typography>

      {isEdit && (
        <EditTab
          korisnik={korisnik}
          editKorisnik={editMedSestra}
          setIsEdit={setIsEdit}
        />
      )}
      {!isEdit && medsestra && (
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
            <ListItem>
              <ListItemText primary="Email" />
              <Typography variant="subtitle1">{korisnik.email}</Typography>
            </ListItem>
            <Divider />

            <Divider />
          </List>
          <Button
            variant="contained"
            color="primary"
            style={{ float: "right", marginTop: 20 }}
            onClick={() => setIsEdit(true)}
          >
            Izmeni
          </Button>
        </>
      )}
    </div>
  );
};

function mapStateToProps(state) {
  return {
    medsestra: state.medsestra,
    korisnik: state.currentUser.korisnik,
    korisnikId: state.currentUser.user.id
  };
}

export default connect(mapStateToProps, { getMedSestra, editMedSestra })(
  MedSestraProfilTab
);
