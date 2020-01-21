import React, { useEffect } from "react";
import { connect } from "react-redux";
import { getAdminKC } from "../../store/actions/adminKC";
import Button from "@material-ui/core/Button";
import { editAdminKC } from "../../store/actions/adminKC";

import List from "@material-ui/core/List";
import ListItem from "@material-ui/core/ListItem";
import ListItemText from "@material-ui/core/ListItemText";
import Divider from "@material-ui/core/Divider";
import Typography from "@material-ui/core/Typography";
import EditTab from "./EditTab";

const AdminProfilTab = ({
  adminKC: { adminKC },
  korisnikId,
  korisnik,
  getAdminKC,
  editAdminKC
}) => {
  useEffect(() => {
    getAdminKC(korisnikId);
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
          editKorisnik={editAdminKC}
          setIsEdit={setIsEdit}
        />
      )}
      {!isEdit && adminKC && (
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
    adminKC: state.adminKC,
    korisnik: state.currentUser.korisnik,
    korisnikId: state.currentUser.user.id
  };
}

export default connect(mapStateToProps, { getAdminKC, editAdminKC })(
  AdminProfilTab
);
