import React from "react";
import Button from "@material-ui/core/Button";
import Dialog from "@material-ui/core/Dialog";
import DialogActions from "@material-ui/core/DialogActions";
import DialogContent from "@material-ui/core/DialogContent";
import DialogContentText from "@material-ui/core/DialogContentText";
import DialogTitle from "@material-ui/core/DialogTitle";
import useMediaQuery from "@material-ui/core/useMediaQuery";
import { useTheme } from "@material-ui/core/styles";

import { connect } from "react-redux";
import { withRouter } from "react-router-dom";
import { obrisiLekara } from "../../../store/actions/lekar";

const ResponsiveDialog = ({ id, ime, prezime, obrisiLekara }) => {
  const [open, setOpen] = React.useState(false);
  const theme = useTheme();
  const fullScreen = useMediaQuery(theme.breakpoints.down("sm"));

  const handleClickOpen = () => {
    setOpen(true);
  };

  const handleClose = () => {
    setOpen(false);
  };

  const handleDA = () => {
    obrisiLekara(id);
    setOpen(false);
  };

  return (
    <div>
      <Button variant="outlined" color="primary" onClick={handleClickOpen}>
        Brisanje
      </Button>
      <Dialog
        fullScreen={fullScreen}
        open={open}
        onClose={handleClose}
        aria-labelledby="responsive-dialog-title"
      >
        <DialogTitle id="responsive-dialog-title">
          {"Da li ste sigurni da uklonite lekara " + ime + " " + prezime + "?"}
        </DialogTitle>
        <DialogContent>
          <DialogContentText>
            Uklanjanjem lekara ćete obristi podatke o pregledima koje je on
            održao.
          </DialogContentText>
        </DialogContent>
        <DialogActions>
          <Button autoFocus onClick={handleClose} color="primary">
            NE
          </Button>
          <Button onClick={handleDA} color="primary" autoFocus>
            DA
          </Button>
        </DialogActions>
      </Dialog>
    </div>
  );
};
const mapStateToProps = state => ({});

export default withRouter(
  connect(mapStateToProps, { obrisiLekara })(ResponsiveDialog)
);
