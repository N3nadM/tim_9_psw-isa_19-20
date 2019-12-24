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
import FormControlLabel from "@material-ui/core/FormControlLabel";
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
  preglediKodLekara,
  preglediKodSestre,
  operacijeKodLekara,
  operacijeKodSestre,
  getOperacijeZaLekara,
  getOperacijeZaSestru,
  getPreglediKodLekara,
  getPreglediKodSestre
}) => {
  const [value, setValue] = React.useState("");
  const radioGroupRef = React.useRef(null);

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
    setOpen(false);
  };

  const handleChange = event => {
    setValue(event.target.value);
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
    setOpen(true);
  };

  const handleClose = () => {
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
      >
        <DialogTitle id="confirmation-dialog-title">Provera</DialogTitle>
        <DialogContent dividers>
          <Typography variant="h6" id="tableTitle">
            Opis: {tekst}
          </Typography>
          {uloga === 0 && (
            <div>
              <Typography variant="h6" id="tableTitle">
                Lista pregleda i operacije koje lekar ima zakazane tog dana:
              </Typography>
              <List>
                {preglediKodLekara.map(option => (
                  <ListItem>
                    <ListItemText primary={"Pregled " + option.datumPocetka} />
                  </ListItem>
                ))}
              </List>
              <List>
                {operacijeKodLekara.map(option => (
                  <ListItem>
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
                {preglediKodSestre.map(option => (
                  <ListItem>
                    <ListItemText primary={"Pregled " + option.datumPocetka} />
                  </ListItem>
                ))}
              </List>
              <List>
                {operacijeKodSestre.map(option => (
                  <ListItem>
                    <ListItemText
                      primary={"Operacija " + option.datumPocetka}
                    />
                  </ListItem>
                ))}
              </List>
            </div>
          )}
        </DialogContent>
        <DialogActions>
          <Button autoFocus onClick={handleCancel} color="primary">
            Nazad
          </Button>
          <Button onClick={handleOk} color="primary">
            Prihvati
          </Button>
          <Button onClick={handleCancel} color="primary">
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
    getOperacijeZaSestru
  })(Dijalog)
);
