import { combineReducers } from "redux";
import currentUser from "./currentUser";
import zahtev from "./zahtev";
import pacijent from "./pacijent";
import lekar from "./lekar";
import medsestra from "./medSestra";
import klinika from "./klinika";
import zdrKarton from "./zdrKarton";
import adminKlinike from "./adminKlinike";
import tipoviPregleda from "./tipoviPregleda";
import sala from "./sala";
import pregled from "./pregled";
import operacija from "./operacija";
import lek from "./lek";
import dijagnoza from "./dijagnoza";
import odmor from "./odmor";
import odsustvo from "./odsustvo";
import zahtevOdsustvo from "./zahtevOdsustvo";
import zahtevOdmor from "./zahtevOdmor";

const appReducer = combineReducers({
  currentUser,
  zahtev,
  pacijent,
  lekar,
  medsestra,
  klinika,
  zdrKarton,
  adminKlinike,
  tipoviPregleda,
  sala,
  pregled,
  operacija,
  lek,
  dijagnoza,
  odmor,
  odsustvo,
  zahtevOdsustvo,
  zahtevOdmor
});

const rootReducer = (state, action) => {
  if (action.type === "USER_LOGOUT") {
    state = undefined;
  }

  return appReducer(state, action);
};

export default rootReducer;
