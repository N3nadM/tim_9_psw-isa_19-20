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

const appReducer = combineReducers({
  currentUser,
  zahtev,
  pacijent,
  lekar,
  medsestra,
  klinika,
  zdrKarton,
  adminKlinike,
  tipoviPregleda
});

const rootReducer = (state, action) => {
  if (action.type === "USER_LOGOUT") {
    state = undefined;
  }

  return appReducer(state, action);
};

export default rootReducer;
