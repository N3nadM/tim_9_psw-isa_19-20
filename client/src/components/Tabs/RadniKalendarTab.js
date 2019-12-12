import React, { useEffect } from "react";
import { Paper } from "@material-ui/core";
import { Calendar, momentLocalizer } from "react-big-calendar";
import moment from "moment";
import "react-big-calendar/lib/css/react-big-calendar.css";
import { connect } from "react-redux";
import { getAllLekarPregledi } from "../../store/actions/pregled";
import { getAllOsobljeOperacije } from "../../store/actions/operacija";
import LicniPodaciTabs from "../Tabs/LicniPodaciTabs";

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

  pregledi = pregledi.map((pregled, i) => ({
    ...pregled,
    tip: "Pregled: " + pregled.tipPregleda.naziv,
    datumZavrsetka: new Date(pregled.datumZavrsetka),
    datumPocetka: new Date(pregled.datumPocetka)
  }));

  operacije = operacije.map((operacija, i) => ({
    ...operacija,
    tip: "Operacija: " + operacija.tipPregleda.naziv,
    datumZavrsetka: new Date(operacija.datumZavrsetka),
    datumPocetka: new Date(operacija.datumPocetka)
  }));

  console.log(pregledi);
  console.log(operacije);

  const handleEvent = obj => {
    console.log(obj);
  };

  let preg_oper = pregledi.concat(operacije);

  return (
    <div style={{ height: 700 }}>
      {pregledi && (
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
    </div>
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
