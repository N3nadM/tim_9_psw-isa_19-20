import React from "react";
import { makeStyles } from "@material-ui/core/styles";
import Button from "@material-ui/core/Button";
import DialogTitle from "@material-ui/core/DialogTitle";
import DialogContent from "@material-ui/core/DialogContent";
import DialogActions from "@material-ui/core/DialogActions";
import Dialog from "@material-ui/core/Dialog";
import RadioGroup from "@material-ui/core/RadioGroup";
import Radio from "@material-ui/core/Radio";
import FormControlLabel from "@material-ui/core/FormControlLabel";
import Axios from "axios";
import { connect } from "react-redux";
import { withRouter } from "react-router-dom";
import { setLekarZakazivanje } from "../../../store/actions/lekar";

function ConfirmationDialogRaw(props) {
  const {
    onClose,
    open,
    options,
    lekar,
    setLekarZakazivanje,
    ...other
  } = props;
  const [value, setValue] = React.useState("");
  const radioGroupRef = React.useRef(null);

  React.useEffect(() => {}, [open]);

  const handleEntering = () => {
    if (radioGroupRef.current != null) {
      radioGroupRef.current.focus();
    }
  };

  const handleCancel = () => {
    onClose();
  };

  const handleOk = () => {
    setLekarZakazivanje(lekar.id, value);
    onClose();
  };

  const handleChange = event => {
    setValue(event.target.value);
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
        Slobodni termini lekara
      </DialogTitle>
      <DialogContent dividers>
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
      </DialogContent>
      <DialogActions>
        <Button autoFocus onClick={handleCancel} color="primary">
          Nazad
        </Button>
        <Button onClick={handleOk} color="primary">
          Zakaži Pregled
        </Button>
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
    maxHeight: 435
  }
}));

const Dijalog = ({ id, datum, setLekarZakazivanje, lekar }) => {
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
        open={open}
        options={options}
        onClose={handleClose}
        lekar={lekar}
        setLekarZakazivanje={setLekarZakazivanje}
      />
    </>
  );
};
const mapStateToProps = state => ({});

export default withRouter(
  connect(mapStateToProps, { setLekarZakazivanje })(Dijalog)
);
