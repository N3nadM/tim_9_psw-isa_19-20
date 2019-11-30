import { combineReducers } from "redux";
import currentUser from "./currentUser";
import zahtev from "./zahtev";
import pacijent from "./pacijent";
import lekar from "./lekar";
import medsestra from "./medSestra";
import klinika from "./klinika";
import zdrKarton from "./zdrKarton";
import adminKlinike from "./adminKlinike";
import pregled from "./pregled";
import operacija from "./operacija";

const appReducer = combineReducers({
  currentUser,
  zahtev,
  pacijent,
  lekar,
  medsestra,
  klinika,
  zdrKarton,
  adminKlinike,
  pregled,
  operacija
});

const rootReducer = (state, action) => {
  if (action.type === "USER_LOGOUT") {
    state = undefined;
  }

  return appReducer(state, action);
};

export default rootReducer;
