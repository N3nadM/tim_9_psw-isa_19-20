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
  getPreglediKodLekaraOdmor,
  getPreglediKodSestreOdmor
} from "../../../store/actions/pregled";
import {
  getOperacijeZaLekaraOdmor,
  getOperacijeZaSestruOdmor
} from "../../../store/actions/operacija";

import {
  denyZahtevOdmor,
  acceptZahtevOdmor
} from "../../../store/actions/zahtevOdmor";
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
  datum1,
  datum2,
  tekst,
  uloga,
  zahtevId,
  preglediKodLekaraOdmor,
  preglediKodSestreOdmor,
  operacijeKodLekaraOdmor,
  operacijeKodSestreOdmor,
  getOperacijeZaLekaraOdmor,
  getOperacijeZaSestruOdmor,
  getPreglediKodLekaraOdmor,
  getPreglediKodSestreOdmor,
  denyZahtevOdmor,
  acceptZahtevOdmor
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
    acceptZahtevOdmor(zahtevId);
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
      getPreglediKodLekaraOdmor(id, datum1, datum2);
      getOperacijeZaLekaraOdmor(id, datum1, datum2);
    } else {
      getPreglediKodSestreOdmor(id, datum1, datum2);
      getOperacijeZaSestruOdmor(id, datum1, datum2);
    }
    setState({
      poruka: "",
      id: zahtevId
    });
    setOpen(true);
  };

  const handleDeny = () => {
    console.log(state);
    denyZahtevOdmor(state);
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
                Lista pregleda i operacija koje lekar ima zakazane tog dana:
              </Typography>
              <List>
                {preglediKodLekaraOdmor &&
                  preglediKodLekaraOdmor.map(option => (
                    <ListItem key={option.id}>
                      <ListItemText
                        primary={"Pregled " + option.datumPocetka}
                      />
                    </ListItem>
                  ))}
              </List>
              <List>
                {operacijeKodLekaraOdmor &&
                  operacijeKodLekaraOdmor.map(option => (
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
                Lista pregleda i operacija koje medicinska sestra ima zakazane
                tog dana:
              </Typography>
              <List>
                {preglediKodSestreOdmor &&
                  preglediKodSestreOdmor.map(option => (
                    <ListItem key={option.id}>
                      <ListItemText
                        primary={"Pregled " + option.datumPocetka}
                      />
                    </ListItem>
                  ))}
              </List>
              <List>
                {operacijeKodSestreOdmor &&
                  operacijeKodSestreOdmor.map(option => (
                    <ListItem key={option.id}>
                      <ListItemText
                        primary={"Operacija " + option.datumPocetka}
                      />
                    </ListItem>
                  ))}
              </List>
              {uloga === 0 &&
                preglediKodLekaraOdmor.length === 0 &&
                operacijeKodLekaraOdmor.length === 0 && (
                  <Typography id="tableTitle">
                    Nema pregleda i operacija u tom danu.
                  </Typography>
                )}
              {uloga === 1 &&
                preglediKodSestreOdmor.length === 0 &&
                operacijeKodSestreOdmor.length === 0 && (
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
            preglediKodLekaraOdmor.length === 0 &&
            operacijeKodLekaraOdmor.length === 0 && (
              <Button onClick={handleOk} color="primary">
                Prihvati
              </Button>
            )}
          {uloga === 1 &&
            preglediKodSestreOdmor.length === 0 &&
            operacijeKodSestreOdmor.length === 0 && (
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
  preglediKodLekaraOdmor: state.pregled.preglediKodLekaraOdmor,
  preglediKodSestreOdmor: state.pregled.preglediKodSestreOdmor,
  operacijeKodLekaraOdmor: state.operacija.operacijeKodLekaraOdmor,
  operacijeKodSestreOdmor: state.operacija.operacijeKodSestreOdmor
});

export default withRouter(
  connect(mapStateToProps, {
    getPreglediKodLekaraOdmor,
    getPreglediKodSestreOdmor,
    getOperacijeZaLekaraOdmor,
    getOperacijeZaSestruOdmor,
    denyZahtevOdmor,
    acceptZahtevOdmor
  })(Dijalog)
);
