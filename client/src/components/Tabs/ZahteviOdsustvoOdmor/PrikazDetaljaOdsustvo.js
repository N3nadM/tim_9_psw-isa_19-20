import React from "react";
import { makeStyles } from "@material-ui/core/styles";
import Button from "@material-ui/core/Button";
import DialogTitle from "@material-ui/core/DialogTitle";
import DialogContent from "@material-ui/core/DialogContent";
import DialogActions from "@material-ui/core/DialogActions";
import Typography from "@material-ui/core/Typography";
import Dialog from "@material-ui/core/Dialog";
import List from "@material-ui/core/List";
import ListItemText from "@material-ui/core/ListItemText";
import TextField from "@material-ui/core/TextField";
import Axios from "axios";
import { connect } from "react-redux";
import { withRouter } from "react-router-dom";
import {
  getPreglediKodLekara,
  getPreglediKodSestre
} from "../../../store/actions/pregled";
import {
  getOperacijeZaLekara,
  getOperacijeZaSestru
} from "../../../store/actions/operacija";

import {
  denyZahtev,
  acceptZahtev
} from "../../../store/actions/zahtevOdsustvo";
import { ListItem } from "@material-ui/core";

const useStyles = makeStyles(theme => ({
  root: {
    width: "100%",
    maxWidth: 360,
    backgroundColor: theme.palette.background.paper
  },
  paper: {
    width: "80%",
    maxHeight: 435
  }
}));

const Dijalog = ({
  id,
  datum,
  tekst,
  uloga,
  zahtevId,
  preglediKodLekara,
  preglediKodSestre,
  operacijeKodLekara,
  operacijeKodSestre,
  getOperacijeZaLekara,
  getOperacijeZaSestru,
  getPreglediKodLekara,
  getPreglediKodSestre,
  denyZahtev,
  acceptZahtev
}) => {
  const [value, setValue] = React.useState("");
  const radioGroupRef = React.useRef(null);
  const [state, setState] = React.useState({
    id: "",
    poruka: ""
  });

  React.useEffect(() => {}, []);

  const handleEntering = () => {
    if (radioGroupRef.current != null) {
      radioGroupRef.current.focus();
    }
  };

  const handleCancel = () => {
    setOpen(false);
  };

  const handleOk = () => {
    acceptZahtev(zahtevId);
    setOpen(false);
  };

  const handleChange = e => {
    const { name, value } = e.target;
    setState(prevState => ({
      ...prevState,
      [name]: value
    }));
  };
  const classes = useStyles();
  const [open, setOpen] = React.useState(false);
  const [options, setOptions] = React.useState([]);

  const handleClickListItem = async () => {
    if (uloga === 0) {
      getPreglediKodLekara(id, datum);
      getOperacijeZaLekara(id, datum);
    } else {
      getPreglediKodSestre(id, datum);
      getOperacijeZaSestru(id, datum);
    }
    setState({
      poruka: "",
      id: zahtevId
    });
    setOpen(true);
  };

  const handleDeny = () => {
    console.log(state);
    denyZahtev(state);
    setOpen(false);
  };

  return (
    <>
      <Button variant="outlined" color="primary" onClick={handleClickListItem}>
        Pogledaj
      </Button>
      <Dialog
        disableBackdropClick
        disableEscapeKeyDown
        maxWidth="xs"
        onEntering={handleEntering}
        aria-labelledby="confirmation-dialog-title"
        open={open}
        maxWidth="xl"
      >
        <DialogTitle id="confirmation-dialog-title">Provera</DialogTitle>
        <DialogContent dividers style={{ width: 600 }}>
          <Typography variant="h6" id="tableTitle">
            Opis: {tekst}
          </Typography>
          {uloga === 0 && (
            <div>
              <Typography variant="h6" id="tableTitle">
                Lista pregleda i operacije koje lekar ima zakazane tog dana:
              </Typography>
              <List>
                {preglediKodLekara &&
                  preglediKodLekara.map(option => (
                    <ListItem key={option.id}>
                      <ListItemText
                        primary={"Pregled " + option.datumPocetka}
                      />
                    </ListItem>
                  ))}
              </List>
              <List>
                {operacijeKodLekara &&
                  operacijeKodLekara.map(option => (
                    <ListItem key={option.id}>
                      <ListItemText
                        primary={"Operacija " + option.datumPocetka}
                      />
                    </ListItem>
                  ))}
              </List>
            </div>
          )}
          {uloga === 1 && (
            <div>
              <Typography variant="h6" id="tableTitle">
                Lista pregleda i operacije koje medicinska sestra ima zakazane
                tog dana:
              </Typography>
              <List>
                {preglediKodSestre &&
                  preglediKodSestre.map(option => (
                    <ListItem key={option.id}>
                      <ListItemText
                        primary={"Pregled " + option.datumPocetka}
                      />
                    </ListItem>
                  ))}
              </List>
              <List>
                {operacijeKodSestre &&
                  operacijeKodSestre.map(option => (
                    <ListItem key={option.id}>
                      <ListItemText
                        primary={"Operacija " + option.datumPocetka}
                      />
                    </ListItem>
                  ))}
              </List>
              {uloga === 0 &&
                preglediKodLekara.length === 0 &&
                operacijeKodLekara.length === 0 && (
                  <Typography id="tableTitle">
                    Nema pregleda i operacija u tom danu.
                  </Typography>
                )}
              {uloga === 1 &&
                preglediKodSestre.length === 0 &&
                operacijeKodSestre.length === 0 && (
                  <Typography id="tableTitle">
                    Nema pregleda i operacija u tom danu.
                  </Typography>
                )}
            </div>
          )}
          <TextField
            id="standard-multiline-static"
            label="Obrazloženje odbijanja zahteva"
            multiline
            name="poruka"
            value={state.poruka}
            onChange={handleChange}
            rows="4"
            variant="outlined"
            style={{ width: 500 }}
          />
        </DialogContent>
        <DialogActions>
          <Button autoFocus onClick={handleCancel} color="primary">
            Nazad
          </Button>
          {uloga === 0 &&
            preglediKodLekara.length === 0 &&
            operacijeKodLekara.length === 0 && (
              <Button onClick={handleOk} color="primary">
                Prihvati
              </Button>
            )}
          {uloga === 1 &&
            preglediKodSestre.length === 0 &&
            operacijeKodSestre.length === 0 && (
              <Button onClick={handleOk} color="primary">
                Prihvati
              </Button>
            )}
          <Button onClick={handleDeny} color="primary">
            Odbij
          </Button>
        </DialogActions>
      </Dialog>
    </>
  );
};
const mapStateToProps = state => ({
  preglediKodLekara: state.pregled.preglediKodLekara,
  preglediKodSestre: state.pregled.preglediKodSestre,
  operacijeKodLekara: state.operacija.operacijeKodLekara,
  operacijeKodSestre: state.operacija.operacijeKodSestre
});

export default withRouter(
  connect(mapStateToProps, {
    getPreglediKodLekara,
    getPreglediKodSestre,
    getOperacijeZaLekara,
    getOperacijeZaSestru,
    denyZahtev,
    acceptZahtev
  })(Dijalog)
);
