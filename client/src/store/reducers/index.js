import { combineReducers } from "redux";
import currentUser from "./currentUser";
import zahtev from "./zahtev";
import pacijent from "./pacijent";
import lekar from "./lekar";
import medsestra from "./medSestra";

const rootReducer = combineReducers({
  currentUser,
  zahtev,
  pacijent,
  lekar,
  medsestra
});

export default rootReducer;
