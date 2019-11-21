import { combineReducers } from "redux";
import currentUser from "./currentUser";
import zahtev from "./zahtev";
import pacijent from "./pacijent";
import lekar from "./lekar";
import medsestra from "./medSestra";
import klinika from "./klinika";
import zdrKarton from "./zdrKarton";


const rootReducer = combineReducers({
  currentUser,
  zahtev,
  pacijent,
  lekar,
  medsestra,
  klinika,
  zdrKarton

});

export default rootReducer;
