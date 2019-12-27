import React from "react";
import Button from "@material-ui/core/Button";
import Dialog from "@material-ui/core/Dialog";
import DialogActions from "@material-ui/core/DialogActions";
import DialogTitle from "@material-ui/core/DialogTitle";
import useMediaQuery from "@material-ui/core/useMediaQuery";
import { useTheme } from "@material-ui/core/styles";

import { connect } from "react-redux";
import { withRouter } from "react-router-dom";
import { deleteTip } from "../../../store/actions/tipoviPregleda";

const ResponsiveDialog = ({ id, naziv, deleteTip }) => {
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
    deleteTip(id);
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
          {"Da li ste sigurni da obrisete tip sa nazivom " + naziv + "?"}
        </DialogTitle>
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
  connect(mapStateToProps, { deleteTip })(ResponsiveDialog)
);
