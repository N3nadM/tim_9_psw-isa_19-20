import React from "react";
import PropTypes from "prop-types";
import { makeStyles } from "@material-ui/core/styles";
import Button from "@material-ui/core/Button";
import DialogTitle from "@material-ui/core/DialogTitle";
import DialogContent from "@material-ui/core/DialogContent";
import DialogActions from "@material-ui/core/DialogActions";
import Dialog from "@material-ui/core/Dialog";
import RadioGroup from "@material-ui/core/RadioGroup";
import Radio from "@material-ui/core/Radio";
import FormControlLabel from "@material-ui/core/FormControlLabel";
import Typography from "@material-ui/core/Typography";
import List from "@material-ui/core/List";
import ListItem from "@material-ui/core/ListItem";
import ListItemText from "@material-ui/core/ListItemText";
import Divider from "@material-ui/core/Divider";
import Axios from "axios";

function ConfirmationDialogRaw(props) {
  const {
    onClose,
    open,
    options,
    adresa,
    naziv,
    lekar,
    id,
    tipPregleda,
    ...other
  } = props;
  const [value, setValue] = React.useState("");

  const [ok, setOk] = React.useState(false);
  const radioGroupRef = React.useRef(null);
  const [success, setSuccess] = React.useState(0);

  React.useEffect(() => {}, [open]);

  const handleEntering = () => {
    if (radioGroupRef.current != null) {
      radioGroupRef.current.focus();
    }
  };

  const handleCancel = () => {
    onClose();
    setSuccess(0);
    setOk(false);
  };

  const handleOk = () => {
    setOk(true);
  };

  const handleChange = event => {
    setValue(event.target.value);
  };

  const handleReservation = async e => {
    const resp = await Axios.post("/api/pregled/zakaziPregled", {
      lekarId: id,
      datum: value
    });
    setSuccess(resp.data ? 1 : 2);
  };

  return (
    <Dialog
      disableBackdropClick
      disableEscapeKeyDown
      maxWidth="xs"
      onEntering={handleEntering}
      aria-labelledby="confirmation-dialog-title"
      open={open}
      {...other}
    >
      <DialogTitle id="confirmation-dialog-title">
        {!ok ? "Slobodni termini" : "Informacije o pregledu"}
      </DialogTitle>
      <DialogContent dividers>
        {!ok ? (
          <RadioGroup
            ref={radioGroupRef}
            aria-label="ringtone"
            name="ringtone"
            onChange={handleChange}
          >
            {options.map(option => (
              <FormControlLabel
                value={option}
                key={option}
                control={<Radio />}
                label={option}
              />
            ))}
          </RadioGroup>
        ) : success === 0 ? (
          <List disablePadding>
            <ListItem>
              <ListItemText>Klinika: </ListItemText>
              <Typography variant="subtitle1">{naziv}</Typography>
            </ListItem>
            <ListItem>
              <ListItemText>Lekar:</ListItemText>
              <Typography variant="subtitle1">{lekar}</Typography>
            </ListItem>
            <Divider />
            <ListItem>
              <ListItemText>Adresa:</ListItemText>
              <Typography variant="subtitle1">{adresa}</Typography>
            </ListItem>
            <Divider />
            <ListItem>
              <ListItemText>Pregled: </ListItemText>
              <Typography variant="subtitle1">{tipPregleda.naziv}</Typography>
            </ListItem>
            <ListItem>
              <ListItemText>Cena: </ListItemText>
              <Typography variant="subtitle1">
                {tipPregleda.cenaPregleda} RSD
              </Typography>
            </ListItem>
            <ListItem>
              <ListItemText>Trajanje pregleda: </ListItemText>
              <Typography variant="subtitle1">
                {tipPregleda.minimalnoTrajanjeMin} minuta
              </Typography>
            </ListItem>
            <ListItem>
              <ListItemText>Termin</ListItemText>
              <Typography variant="subtitle1">{value}</Typography>
            </ListItem>
          </List>
        ) : (
          <Typography>
            {success === 1
              ? "Upit za pregledom uspesno poslat"
              : "Upit se ne moze kreirati jer vec imate zakazan pregled koji se poklapa sa odabranim terminom ili je neko u medjuvremenu zakazao pregled za taj termin kod izabranog lekara. Ponovo otvorite dijalog za prikaz slobodnih termina, ako se termin izlistava znaci da imate zakazan pregled koji se poklapa sa izabranim terminom."}
          </Typography>
        )}
      </DialogContent>
      <DialogActions>
        {success === 0 && (
          <Button autoFocus onClick={handleCancel} color="secondary">
            Nazad
          </Button>
        )}
        {!ok ? (
          <Button onClick={handleOk} color="primary">
            Zakaži Pregled
          </Button>
        ) : success === 0 ? (
          <Button onClick={handleReservation} color="primary">
            Potvrdi
          </Button>
        ) : (
          <Button onClick={handleCancel} color="secondary">
            Zatvori
          </Button>
        )}
      </DialogActions>
    </Dialog>
  );
}

const useStyles = makeStyles(theme => ({
  root: {
    width: "100%",
    maxWidth: 360,
    backgroundColor: theme.palette.background.paper
  },
  paper: {
    width: "80%",
    maxHeight: 460
  }
}));

export default function ConfirmationDialog({
  id,
  datum,
  adresa,
  klinika,
  lekar,
  tipPregleda
}) {
  const classes = useStyles();
  const [open, setOpen] = React.useState(false);
  const [options, setOptions] = React.useState([]);

  const handleClickListItem = async () => {
    setOpen(true);
    const termini = await Axios.post(`/api/lekar/getTermini`, {
      id,
      datum: !!datum ? datum : new Date()
    });
    setOptions(termini.data.sort((a, b) => new Date(a) - new Date(b)));
  };

  const handleClose = () => {
    setOpen(false);
  };

  return (
    <>
      <Button variant="outlined" color="primary" onClick={handleClickListItem}>
        Pogledaj
      </Button>
      <ConfirmationDialogRaw
        classes={{
          paper: classes.paper
        }}
        id="ringtone-menu"
        keepMounted
        adresa={adresa}
        naziv={klinika}
        lekar={lekar}
        open={open}
        options={options}
        onClose={handleClose}
        id={id}
        tipPregleda={tipPregleda}
      />
    </>
  );
}
