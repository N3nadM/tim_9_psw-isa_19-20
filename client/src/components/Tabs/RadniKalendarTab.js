import React, { useEffect } from "react";
import { Calendar, momentLocalizer } from "react-big-calendar";
import moment from "moment";
import "react-big-calendar/lib/css/react-big-calendar.css";
import { connect } from "react-redux";
import { withRouter } from "react-router-dom";
import { getAllOsobljePregledi } from "../../store/actions/pregled";
import { getAllOsobljeOperacije } from "../../store/actions/operacija";

const localizer = momentLocalizer(moment);

const RadniKalendarTab = ({
  korisnikId,
  pregledi: { pregledi },
  operacije: { operacije },
  getAllOsobljePregledi,
  getAllOsobljeOperacije,
  history,
  objekat
}) => {
  useEffect(() => {
    getAllOsobljePregledi(korisnikId);
    getAllOsobljeOperacije(korisnikId);
    //eslint-disable-next-line
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

  let preg_oper = pregledi.concat(operacije);

  return (
    <div style={{ height: 700 }}>
      {preg_oper && (
        <Calendar
          style={{ maxHeight: "100%" }}
          events={preg_oper}
          showMultiDayTimes={true}
          startAccessor="datumPocetka"
          endAccessor="datumZavrsetka"
          titleAccessor="tip"
          localizer={localizer}
          onSelectEvent={obj => {
            objekat = obj;
            console.log(objekat);
            history.push({
              pathname: `/pregled_operacija/${objekat.id}`,
              objekat
            });
          }}
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

export default withRouter(
  connect(mapStateToProps, { getAllOsobljePregledi, getAllOsobljeOperacije })(
    RadniKalendarTab
  )
);
