import React, { useEffect } from "react";
import { Paper, DialogContent } from "@material-ui/core";
import { Calendar, momentLocalizer } from "react-big-calendar";
import moment from "moment";
import "react-big-calendar/lib/css/react-big-calendar.css";
import { connect } from "react-redux";
import { getAllSalaOperacije } from "../../../store/actions/operacija";
import { getAllSalaPregledi } from "../../../store/actions/pregled";
import Button from "@material-ui/core/Button";
import Dialog from "@material-ui/core/Dialog";
import DialogTitle from "@material-ui/core/DialogTitle";
import DialogActions from "@material-ui/core/DialogActions";

const localizer = momentLocalizer(moment);

const RadniKalendarTab = ({
  salaId,
  pregledi,
  operacije,
  getAllSalaPregledi,
  getAllSalaOperacije
}) => {
  {
    pregledi &&
      (pregledi = pregledi.map((pregled, i) => ({
        ...pregled,
        tip: "Pregled: " + pregled.tipPregleda.naziv,
        datumZavrsetka: new Date(pregled.datumZavrsetka),
        datumPocetka: new Date(pregled.datumPocetka)
      })));
  }

  {
    operacije &&
      (operacije = operacije.map((operacija, i) => ({
        ...operacija,
        tip: "Operacija: " + operacija.tipPregleda.naziv,
        datumZavrsetka: new Date(operacija.datumZavrsetka),
        datumPocetka: new Date(operacija.datumPocetka)
      })));
  }

  const [open, setOpen] = React.useState(false);

  const handleClickOpen = () => {
    getAllSalaOperacije(salaId);
    getAllSalaPregledi(salaId);
    setOpen(true);
  };

  const handleClose = () => {
    setOpen(false);
  };

  const handleEvent = obj => {
    console.log(obj);
  };

  let preg_oper = pregledi.concat(operacije);

  return (
    <div>
      <Button variant="outlined" color="primary" onClick={handleClickOpen}>
        Kalendar zauzeća
      </Button>
      <Dialog
        maxWidth="xl"
        open={open}
        onClose={handleClose}
        aria-labelledby="alert-dialog-title"
        aria-describedby="alert-dialog-description"
      >
        <DialogTitle id="alert-dialog-title">
          {"Kalendar zauzeca sale"}
        </DialogTitle>
        <DialogContent style={{ height: 700, width: 1200 }}>
          {preg_oper && (
            <Calendar
              style={{ maxHeight: "100%" }}
              events={preg_oper}
              showMultiDayTimes={true}
              startAccessor="datumPocetka"
              endAccessor="datumZavrsetka"
              titleAccessor="tip"
              localizer={localizer}
              onSelectEvent={obj => handleEvent(obj)}
            />
          )}
        </DialogContent>
        <DialogActions>
          <Button onClick={handleClose} color="primary" autoFocus>
            Zatvori
          </Button>
        </DialogActions>
      </Dialog>
    </div>
  );
};

const mapStateToProps = state => ({
  pregledi: state.pregled.preglediZaSalu,
  operacije: state.operacija.operacijeZaSalu
});

export default connect(mapStateToProps, {
  getAllSalaOperacije,
  getAllSalaPregledi
})(RadniKalendarTab);
