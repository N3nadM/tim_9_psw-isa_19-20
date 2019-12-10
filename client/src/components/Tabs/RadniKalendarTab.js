import React, { useEffect } from "react";
import { Paper } from "@material-ui/core";
import { Calendar, momentLocalizer } from "react-big-calendar";
import moment from "moment";
import "react-big-calendar/lib/css/react-big-calendar.css";
import { connect } from "react-redux";
import { getAllLekarPregledi } from "../../store/actions/pregled";
import { getAllOsobljeOperacije } from "../../store/actions/operacija";

const localizer = momentLocalizer(moment);

const RadniKalendarTab = ({
  korisnikId,
  pregledi: { pregledi },
  operacije: { operacije },
  getAllLekarPregledi,
  getAllOsobljeOperacije
}) => {
  useEffect(() => {
    getAllLekarPregledi(korisnikId);
    getAllOsobljeOperacije(korisnikId);
  }, []);

  {
    pregledi.map(function(pregled, i) {
      pregled.datumZavrsetka = new Date(pregled.datumZavrsetka);
      pregled.datumPocetka = new Date(pregled.datumPocetka);
    });

    operacije.map(function(operacija, i) {
      operacija.datumZavrsetka = new Date(operacija.datumZavrsetka);
      operacija.datumPocetka = new Date(operacija.datumPocetka);
    });

    console.log(pregledi);
    console.log(operacije);
  }

  let preg_oper = pregledi.concat(operacije);

  return (
    <Paper>
      {pregledi && (
        <div>
          <Calendar
            style={{ height: "500pt" }}
            events={preg_oper}
            showMultiDayTimes={true}
            startAccessor="datumPocetka"
            endAccessor="datumZavrsetka"
            titleAccessor="izvestaj"
            localizer={localizer}
          />
        </div>
      )}
    </Paper>
  );
};

const mapStateToProps = state => ({
  korisnikId: state.currentUser.user.id,
  pregledi: state.pregled,
  operacije: state.operacija
});

export default connect(mapStateToProps, {
  getAllLekarPregledi,
  getAllOsobljeOperacije
})(RadniKalendarTab);
